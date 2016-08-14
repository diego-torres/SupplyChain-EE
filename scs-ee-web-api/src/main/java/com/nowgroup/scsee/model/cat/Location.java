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

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.nowgroup.scsee.model.BaseNamableModel;

/**
 * Inventory locations in the storage.
 * 
 * @author https://github.com/diego-torres
 * 		
 */
public class Location extends BaseNamableModel {
	private static final long	serialVersionUID	= 1L;
	private Storage				storage;
	
	/**
	 * 
	 */
	public Location() {
	}
	
	/**
	 * @param name
	 */
	public Location(String name) {
		super(name);
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public Location(Integer id, String name) {
		super(id, name);
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param storage
	 */
	public Location(Integer id, String name, Storage storage) {
		this(id, name);
		this.storage = storage;
	}
	
	/**
	 * @return the storage
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "storage_id")
	public Storage getStorage() {
		return storage;
	}
	
	/**
	 * @param storage
	 *            the storage to set
	 */
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
}
