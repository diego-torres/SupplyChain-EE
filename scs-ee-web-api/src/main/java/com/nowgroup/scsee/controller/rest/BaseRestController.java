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
package com.nowgroup.scsee.controller.rest;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nowgroup.scsee.controller.rest.dto.GlobalRestResponseDto;
import com.nowgroup.scsee.model.EditValidation;
import com.nowgroup.scsee.model.Model;
import com.nowgroup.scsee.service.SupplyChainService;

/**
 * 
 * @author https://github.com/diego-torres
 * 
 * @param <T>
 * @param <U>
 */
public abstract class BaseRestController<T extends Model<U>, U extends Serializable>
		extends ReadOnlyRestController<T, U> {
	private SupplyChainService<T, U> service;

	/**
	 * 
	 * @param service
	 */
	public BaseRestController(SupplyChainService<T, U> service) {
		super(service);
		this.service = service;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public GlobalRestResponseDto<T> add(@RequestBody(required = true) T entity) {
		GlobalRestResponseDto<T> response = new GlobalRestResponseDto<>(
				"Unable to persist entity in database: " + entity);
		try {
			List<EditValidation> validationErrors = service.add(entity);
			if (validationErrors == null || validationErrors.isEmpty()) {
				response = new GlobalRestResponseDto<>(entity);
			} else {
				response = new GlobalRestResponseDto<>(entity, validationErrors);
			}
		} catch (Exception e) {
			response = new GlobalRestResponseDto<>(e.getMessage());
		}

		return response;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8")
	public GlobalRestResponseDto<T> update(@RequestBody(required = true) T entity) {
		GlobalRestResponseDto<T> response = new GlobalRestResponseDto<>(
				"Unable to merge entity in database: " + entity);
		try {
			List<EditValidation> validationErrors = service.update(entity);
			if (validationErrors == null || validationErrors.isEmpty()) {
				response = new GlobalRestResponseDto<>(entity);
			} else {
				response = new GlobalRestResponseDto<>(entity, validationErrors);
			}
		} catch (Exception e) {
			response = new GlobalRestResponseDto<>(e.getMessage());
		}

		return response;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
	public GlobalRestResponseDto<T> delete(@PathVariable("id") U id) {
		GlobalRestResponseDto<T> response = new GlobalRestResponseDto<>(
				"Unable to delete entity from database with id: " + id);
		try {
			List<EditValidation> validationErrors = service.delete(id);
			if (validationErrors == null || validationErrors.isEmpty()) {
				response = new GlobalRestResponseDto<>();
			} else {
				response = new GlobalRestResponseDto<>(null, validationErrors);
			}
		} catch (Exception e) {
			response = new GlobalRestResponseDto<>(e.getMessage());
		}

		return response;
	}
}
