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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "cat_company")
public class Company extends BaseNamableModel {
	private static final long serialVersionUID = 1L;
	private String roles;
	private String taxId;
	private String email;
	private List<CompanyAddress> addresses = new ArrayList<>();

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
	 * @return the addresses
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "company", orphanRemoval = true, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REMOVE })
	public List<CompanyAddress> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(List<CompanyAddress> addresses) {
		this.addresses = addresses;
	}

	/**
	 * @return the taxId
	 */
	@Column(length = 30)
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
	@Column(length = 150)
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

	/**
	 * @return the roles
	 */
	@Column(length = 80)
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [roles=" + roles + ", taxId=" + taxId + ", email=" + email + ", addresses=" + addresses + "]";
	}

	@Entity
	@Table(name = "cat_company_address")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	public static class CompanyAddress extends Address {
		private static final long serialVersionUID = 1L;

		private Company company;

		@ManyToOne
		@JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "FK_COMPANY_ADDRESS"))
		@JsonIgnore
		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}
	}
}
