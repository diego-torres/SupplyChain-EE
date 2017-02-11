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
package com.nowgroup.scsee.cat.company;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.nowgroup.scsee.geo.address.Address;
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
public class Company extends BaseNamableModel {
	private static final long	serialVersionUID	= 1L;
	private Integer				companyRole			= 0;
	private String				taxId;
	private String				email;
	private Set<Address>		addresses			= new HashSet<>();
	
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
	 * Buyer = 2, Seller = 3, Sender = 5, Receiver = 7, Freighter = 11, Trade
	 * Agency = 13, Billable = 17 UNKNOWN = 0 If a company is buyer and
	 * freighter then 2 * 11 = 22, then the role is 22. When calculating the
	 * role, if [role]%2 = 0 then the company is a buyer.
	 * 
	 * @return the companyRole
	 */
	@Column(name = "company_role")
	public Integer getCompanyRole() {
		return companyRole;
	}
	
	/**
	 * @param companyRole
	 *            the companyRole to set
	 */
	public void setCompanyRole(Integer companyRole) {
		this.companyRole = companyRole;
	}
	
	/**
	 * @return the addresses
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "loc_company_address")
	public Set<Address> getAddresses() {
		return addresses;
	}
	
	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	/**
	 * @return the taxId
	 */
	public String getTaxId() {
		return taxId;
	}
	
	/**
	 * @param taxId
	 *            the taxId to set
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
