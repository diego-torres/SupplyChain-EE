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
package com.nowgroup.scsee.model.cat;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nowgroup.scsee.model.BaseNamableModel;

/**
 * The different companies that participate in the supply chain are modeled by
 * these properties.
 * 
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "cat_companies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company extends BaseNamableModel {
	private static final long	serialVersionUID	= 1L;
	private Set<CompanyRole>	companyRole			= new HashSet<>();
	
	/**
	 * Empty constructor
	 */
	public Company() {
	}
	
	/**
	 * Constructor for company by name
	 * 
	 * @param name
	 *            The company name
	 */
	public Company(String name) {
		super(name);
	}
	
	/**
	 * @return the companyRole
	 */
	@OneToMany(	mappedBy = "key.company",
				fetch = FetchType.EAGER, orphanRemoval = true)
	public Set<CompanyRole> getCompanyRole() {
		return companyRole;
	}
	
	/**
	 * @param companyRole
	 *            the companyRole to set
	 */
	public void setCompanyRole(Set<CompanyRole> companyRole) {
		this.companyRole = companyRole;
	}
}
