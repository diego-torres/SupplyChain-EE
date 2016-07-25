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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nowgroup.scsee.controller.rest.dto.GlobalRestResponseDto;
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
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
			// private HttpMessageConverter mappingJackson2HttpMessageConverter;
			
	// private Company company;
	// private List<Company> companies;
	private MockMvc mockMvc;
	
	/**
	 * 
	 * @param converters
	 */
	@Autowired
	public void setConverters(HttpMessageConverter<?>[] converters) {
		/*
		 * mappingJackson2HttpMessageConverter =
		 * Arrays.asList(converters).stream().filter(converter -> { return
		 * converter instanceof MappingJackson2HttpMessageConverter;
		 * }).findAny().get();
		 */
		
		// Assert.assertNotNull("the JSON message converter must not be null",
		// mappingJackson2HttpMessageConverter);
	}
	
	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	@Test
	public void testCompanyNotFound() throws Exception {
		int nonExistingId = 0;
		String requestPath = "/rest/company/%d";
		requestPath = String.format(requestPath, nonExistingId);
		
		mockMvc.perform(get(requestPath))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.result", Matchers.is("FAIL")))
				.andExpect(jsonPath("$.errorDetails[0].validationMessage",
						Matchers.is("Entity not found with id: " + nonExistingId)))
				.andExpect(jsonPath("$.errorDetails[0].fieldName", Matchers.nullValue()))
				.andExpect(jsonPath("$.errorDetails[0].severity", 
						Matchers.is(EditValidation.EditValidationSeverity.LOW.name())));
	}
	// TODO: Test adding company
	// TODO; Test retrieve an existing company
	// TODO: Test delete a non existing company
	// TODO: Test delete an existing company
	// TODO: Test retrieve all companies
}
