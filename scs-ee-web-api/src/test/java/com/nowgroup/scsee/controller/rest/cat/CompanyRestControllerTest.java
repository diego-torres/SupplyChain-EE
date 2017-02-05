/**
 * Copyright 2016 https://github.com/diego-torres
 * 
 * Licensed under the MIT License (MIT).
 *  
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sub-license, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.nowgroup.scsee.controller.rest.cat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowgroup.scsee.cat.company.Company;
import com.nowgroup.scsee.model.EditValidation;
import com.nowgroup.scsee.springBoot.Application;

/**
 * Test cases for CompanyRestController
 * 
 * @author https://github.com/diego-torres
 * 		
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CompanyRestControllerTest {
	
	@Autowired
	private WebApplicationContext webContext;
	
	private MediaType						contentType	= new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private HttpMessageConverter<Object>	mappingJackson2HttpMessageConverter;
	
	private MockMvc mockMvc;
	
	/**
	 * 
	 * @param converters
	 */
	@SuppressWarnings("unchecked")
	@Autowired
	public void setConverters(HttpMessageConverter<?>[] converters) {
		
		mappingJackson2HttpMessageConverter = (HttpMessageConverter<Object>) Arrays.asList(converters).stream()
				.filter(converter -> {
					return converter instanceof MappingJackson2HttpMessageConverter;
				}).findAny().get();
				
		Assert.assertNotNull("the JSON message converter must not be null", mappingJackson2HttpMessageConverter);
	}
	
	/**
	 * 
	 */
	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCompanyNotFound() throws Exception {
		int nonExistingId = 0;
		String requestPath = "/rest/company/%d";
		requestPath = String.format(requestPath, nonExistingId);
		
		mockMvc.perform(get(requestPath)).andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.result", Matchers.is("FAIL")))
				.andExpect(jsonPath("$.errorDetails[0].validationMessage",
						Matchers.is("Entity not found with id: " + nonExistingId)))
				.andExpect(jsonPath("$.errorDetails[0].fieldName", Matchers.nullValue())).andExpect(jsonPath(
						"$.errorDetails[0].severity", Matchers.is(EditValidation.EditValidationSeverity.LOW.name())));
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCrud() throws Exception {
		String companyName = "NOWGROUP INC.";
		Company testCompany = new Company(companyName);
		String companyJson = json(testCompany);
		MvcResult result = mockMvc.perform(post("/rest/company").contentType(contentType).content(companyJson))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.result", Matchers.is("OK")))
				.andExpect(jsonPath("$.data[0].id", Matchers.not(0)))
				.andExpect(jsonPath("$.data[0].name", Matchers.is(companyName)))
				.andReturn();
				
		// try to add a duplicate
		Company testCompany2 = new Company(companyName);
		String company2Json = json(testCompany2);
		mockMvc.perform(post("/rest/company").contentType(contentType).content(company2Json))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.result", Matchers.is("FAIL")))
				.andExpect(jsonPath("$.errorDetails[0].validationMessage", Matchers.notNullValue()))
				.andExpect(jsonPath("$.errorDetails[0].fieldName", Matchers.nullValue()))
				.andExpect(jsonPath("$.errorDetails[0].severity", Matchers.is(EditValidation.EditValidationSeverity.LOW.name())));
						
		// TODO: Improve polymorphic instance of JSON Company
		String jsonResult = result.getResponse().getContentAsString().substring(41);
		ObjectMapper mapper = new ObjectMapper();
		
		Company response = mapper.reader().forType(Company.class)
				.readValue(jsonResult.substring(0, jsonResult.length() - 2));
		int responseCompanyId = response.getId();
		
		String requestPath = "/rest/company/%d";
		requestPath = String.format(requestPath, responseCompanyId);
		
		mockMvc.perform(get(requestPath)).andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.result", Matchers.is("OK")))
				.andExpect(jsonPath("$.data[0].id", Matchers.is(responseCompanyId)))
				.andExpect(jsonPath("$.data[0].name", Matchers.is(companyName)));
				
		String newCompanyName = "NOWGROUP NEW.";
		testCompany.setId(responseCompanyId);
		testCompany.setName(newCompanyName);
		companyJson = json(testCompany);
		
		mockMvc.perform(put("/rest/company").contentType(contentType).content(companyJson)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.result", Matchers.is("OK")))
				.andExpect(jsonPath("$.data[0].id", Matchers.is(responseCompanyId)))
				.andExpect(jsonPath("$.data[0].name", Matchers.is(newCompanyName)));
				
		// TEST GET ALL, company updated tested
		mockMvc.perform(get("/rest/company"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.result", Matchers.is("OK")))
				.andExpect(jsonPath("$.data[0].id", Matchers.is(responseCompanyId)))
				.andExpect(jsonPath("$.data[0].name", Matchers.is(newCompanyName)));
				
		mockMvc.perform(delete(requestPath)).andExpect(status().isOk())
				.andExpect(jsonPath("$.result", Matchers.is("OK")));
				
		mockMvc.perform(get(requestPath)).andExpect(status().isOk())
				.andExpect(jsonPath("$.result", Matchers.is("FAIL")))
				.andExpect(jsonPath("$.errorDetails[0].validationMessage",
						Matchers.is("Entity not found with id: " + responseCompanyId)))
				.andExpect(jsonPath("$.errorDetails[0].fieldName", Matchers.nullValue()))
				.andExpect(jsonPath("$.errorDetails[0].severity", Matchers.is(EditValidation.EditValidationSeverity.LOW.name())));
						
		// Test delete an non existing company
		mockMvc.perform(delete(requestPath)).andExpect(status().isOk())
				.andExpect(jsonPath("$.result", Matchers.is("FAIL")));
	}

	/**
	 * Helper for JSON conversion.
	 * 
	 * @param o
	 * @return
	 * @throws IOException
	 */
	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
