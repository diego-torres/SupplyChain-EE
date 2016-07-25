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

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scsee.EntityNotFoundException;
import com.nowgroup.scsee.ParameterMisuseException;
import com.nowgroup.scsee.model.Model;
import com.nowgroup.scsee.repository.ReadOnlyRepository;

/**
 * 
 * @author https://github.com/diego-torres
 * 		
 * @param <T>
 *            The Model Type
 * @param <U>
 *            The Model ID type
 */
public abstract class BaseReadOnlyService<T extends Model<U>, U extends Serializable>
											implements SupplyChainReadOnlyService<T, U> {
											
	private ReadOnlyRepository<T, U> readOnlyRepository;
	
	public BaseReadOnlyService(ReadOnlyRepository<T, U> readOnlyRepository) {
		this.readOnlyRepository = readOnlyRepository;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nowgroup.scsee.service.SupplyChainReadOnlyService#getById(java.io.
	 * Serializable)
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	@Override
	public T getById(U id) throws EntityNotFoundException, ParameterMisuseException {
		if (id == null) return null;
		return readOnlyRepository.getById(id);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nowgroup.scsee.service.SupplyChainReadOnlyService#getAll()
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	@Override
	public List<T> getAll() {
		return readOnlyRepository.getAll();
	}
	
}
