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
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scsee.model.EditValidation;
import com.nowgroup.scsee.model.EditValidation.EditValidationSeverity;
import com.nowgroup.scsee.model.Model;
import com.nowgroup.scsee.repository.Repository;

/**
 * 
 * @author https://github.com/diego-torres
 * 		
 * @param <T>
 *            The Model Type.
 * @param <U>
 *            The Model ID Type.
 */
public abstract class BaseService<T extends Model<U>, U extends Serializable> extends BaseReadOnlyService<T, U>
									implements SupplyChainService<T, U> {
	private Repository<T, U> repository;
	
	/**
	 * 
	 * @param repository
	 */
	public BaseService(Repository<T, U> repository) {
		super(repository);
		this.repository = repository;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nowgroup.scsee.service.SupplyChainService#add(com.nowgroup.scsee.
	 * model.Model)
	 */
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public List<EditValidation> add(T entity) {
		List<EditValidation> validation = validate(entity);
		if (validation == null || validation.isEmpty()) {
			try {
				repository.add(entity);
			} catch (Exception e) {
				validation = new ArrayList<EditValidation>();
				validation.add(new EditValidation(e.getMessage(), EditValidationSeverity.CRITICAL));
			}
		}
		return validation;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nowgroup.scsee.service.SupplyChainService#update(com.nowgroup.scsee.
	 * model.Model)
	 */
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public List<EditValidation> update(T entity) {
		List<EditValidation> validation = validate(entity);
		if (validation == null || validation.isEmpty()) {
			try {
				repository.update(entity);
			} catch (Exception e) {
				validation = new ArrayList<EditValidation>();
				validation.add(new EditValidation(e.getMessage(), EditValidationSeverity.CRITICAL));
			}
		}
		return validation;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nowgroup.scsee.service.SupplyChainService#delete(com.nowgroup.scsee.
	 * model.Model)
	 */
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public List<EditValidation> delete(U id) {
		List<EditValidation> validation = null;
		try {
			T entity = repository.getById(id);
			repository.delete(entity);
		} catch (Exception e) {
			validation = new ArrayList<EditValidation>();
			validation.add(new EditValidation(e.getMessage(), EditValidationSeverity.CRITICAL));
		}
		return validation;
	}
}
