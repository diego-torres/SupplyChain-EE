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

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Embeddable
public class CompanyRoleKey implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private Company				company;
	private CompanyRoleType		roleType;
	
	/**
	 * 
	 */
	public CompanyRoleKey() {
	}
	
	/**
	 * @param company
	 * @param roleName
	 */
	public CompanyRoleKey(Company company, String roleName) {
		this.company = company;
		this.setRoleName(roleName);
	}
	
	/**
	 * 
	 * @param company
	 * @param crt
	 */
	public CompanyRoleKey(Company company, CompanyRoleType crt) {
		this.company = company;
		this.roleType = crt;
	}
	
	/**
	 * @return the company
	 */
	@ManyToOne(	fetch = FetchType.LAZY, optional = false,
				cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "company_id")
	@JsonIgnore
	public Company getCompany() {
		return company;
	}
	
	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	/**
	 * @return the roleName
	 */
	@Column(name = "role_name", length = 20, nullable = false)
	public String getRoleName() {
		return roleType.name();
	}
	
	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		try {
			this.roleType = CompanyRoleType.valueOf(roleName);
		} catch (Exception e) {
			this.roleType = CompanyRoleType.UNKNOWN;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((roleType == null) ? 0 : roleType.hashCode());
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof CompanyRoleKey)) return false;
		CompanyRoleKey other = (CompanyRoleKey) obj;
		if (company == null) {
			if (other.company != null) return false;
		} else if (!company.equals(other.company)) return false;
		if (roleType == null) {
			if (other.roleType != null) return false;
		} else if (this.roleType != other.roleType) return false;
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompanyRoleKey [" + (company != null ? "company=" + company + ", " : "")
				+ (roleType != null ? "roleName=" + roleType.name() : "") + "]";
	}
	
	public static enum CompanyRoleType {
		UNKNOWN, PURCHASER, SELLER, RECEIVER, SENDER, FREIGHTER, TRADE_AGENCY, BILLABLE
	}
}
