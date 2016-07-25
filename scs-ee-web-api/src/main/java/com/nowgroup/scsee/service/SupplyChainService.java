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
package com.nowgroup.scsee.service;

import java.io.Serializable;
import java.util.List;

import com.nowgroup.scsee.model.EditValidation;
import com.nowgroup.scsee.model.Model;

/**
 * 
 * @author https://github.com/diego-torres
 * 		
 * @param <T>
 *            The Model Type
 * @param <U>
 *            The Model ID type.
 */
public interface SupplyChainService<T extends Model<U>, U extends Serializable>
									extends SupplyChainReadOnlyService<T, U> {
	/**
	 * 
	 * @param entity
	 * @return
	 */
	List<EditValidation> add(T entity);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	List<EditValidation> update(T entity);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	List<EditValidation> delete(U id);
	
	/**
	 * Validate an entity before performing any edit operation.
	 * 
	 * @param entity
	 *            The entity to be validated.
	 * @return A list of validation errors. Null return is the default
	 *         implementation.
	 */
	default List<EditValidation> validate(T entity) {
		return null;
	}
}
