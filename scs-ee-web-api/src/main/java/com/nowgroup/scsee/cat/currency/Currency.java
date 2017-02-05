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
package com.nowgroup.scsee.cat.currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nowgroup.scsee.model.BaseNamableModel;

/**
 * @author https://github.com/diego-torres
 *		
 */
@Entity
@Table(	name = "cat_currency",
		uniqueConstraints = { @UniqueConstraint(columnNames = { "currency_code" }, name = "ux_currency_code") })
public class Currency extends BaseNamableModel {
	private static final long serialVersionUID = 1L;
	
	private String currencyCode;
	
	/**
	 * 
	 */
	public Currency() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param name
	 */
	public Currency(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public Currency(Integer id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the currencyCode
	 */
	@Column(name = "currency_code", length = 3, nullable = false)
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	/**
	 * @param currencyCode
	 *            the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
