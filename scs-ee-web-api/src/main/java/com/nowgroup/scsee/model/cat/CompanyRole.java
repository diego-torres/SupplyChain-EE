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

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.nowgroup.scsee.model.cat.CompanyRoleKey.CompanyRoleType;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "cross_company_role")
public class CompanyRole implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private CompanyRoleKey		key;
	
	/**
	 * 
	 */
	public CompanyRole() {
	}
	
	/**
	 * 
	 * @param company
	 * @param roleName
	 */
	public CompanyRole(Company company, String roleName) {
		CompanyRoleKey key = new CompanyRoleKey(company, roleName);
		this.key = key;
	}
	
	/**
	 * 
	 * @param company
	 * @param roleType
	 */
	public CompanyRole(Company company, CompanyRoleType roleType) {
		CompanyRoleKey key = new CompanyRoleKey(company, roleType);
		this.key = key;
	}
	
	/**
	 * @param key
	 */
	public CompanyRole(CompanyRoleKey key) {
		this.key = key;
	}
	
	/**
	 * @return the key
	 */
	@EmbeddedId
	public CompanyRoleKey getKey() {
		return key;
	}
	
	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(CompanyRoleKey key) {
		this.key = key;
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
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		if (!(obj instanceof CompanyRole)) return false;
		CompanyRole other = (CompanyRole) obj;
		if (key == null) {
			if (other.key != null) return false;
		} else if (!key.equals(other.key)) return false;
		return true;
	}
}
