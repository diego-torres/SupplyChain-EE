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
package com.nowgroup.scsee.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Required;

/**
 * Basic catalogs will need only id + name model. They can extend this
 * implementation to accomplish that.
 * 
 * @author https://github.com/diego-torres
 * 		
 */
@MappedSuperclass
public abstract class BaseNamableModel extends BaseGenericModel {
	private static final long	serialVersionUID	= 1L;
	private String				name;
	
	/**
	 * 
	 */
	public BaseNamableModel() {
	}
	
	/**
	 * Create a BaseNamableModel using a name
	 * 
	 * @param name
	 */
	public BaseNamableModel(String name) {
		setName(name);
	}
	
	/**
	 * Option to build a BaseNamable from id and name.
	 * 
	 * @param id
	 * @param name
	 */
	public BaseNamableModel(Integer id, String name) {
		setId(id);
		setName(name);
	}
	
	/**
	 * @return the name
	 */
	@Column(name = "name", unique = true, length = 150, nullable = false)
	@Required
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
