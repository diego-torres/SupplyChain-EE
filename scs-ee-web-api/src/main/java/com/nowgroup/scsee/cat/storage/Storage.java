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
package com.nowgroup.scsee.cat.storage;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nowgroup.scsee.ParameterMisuseException;
import com.nowgroup.scsee.cat.company.Company;
import com.nowgroup.scsee.model.BaseNamableModel;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "cat_storage")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Storage extends BaseNamableModel {
	private static final long	serialVersionUID	= 1L;
	private Company				company;
	
	/**
	 * 
	 */
	public Storage() {
	}
	
	/**
	 * @return the company
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}
	
	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) throws ParameterMisuseException {
		if (company != null) {
			boolean isReceiver = company.getRoles().contains("receiver");
			if (!isReceiver) throw new ParameterMisuseException("Company must be receiver to assign storages in it.");
		}
		this.company = company;
	}
}
