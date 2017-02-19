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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nowgroup.scsee.controller.rest.dto.GlobalRestResponseDto;
import com.nowgroup.scsee.model.Model;
import com.nowgroup.scsee.service.SupplyChainReadOnlyService;

/**
 * 
 * @author https://github.com/diego-torres
 * 
 * @param <T>
 *            The Model Type
 * @param <U>
 *            The Model ID Type
 */
public class ReadOnlyRestController<T extends Model<U>, U extends Serializable> {
	private SupplyChainReadOnlyService<T, U> readOnlyService;

	/**
	 * A ReadOnlyService instance is required for the Rest controller to work.
	 * 
	 * @param readOnlyService
	 */
	public ReadOnlyRestController(SupplyChainReadOnlyService<T, U> readOnlyService) {
		this.readOnlyService = readOnlyService;
	}

	/**
	 * Get a single record by Id.
	 * 
	 * @param id
	 *            The record ID to retrieve
	 * @return A GlobalRestResponseDto containing a List with a single element.
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public GlobalRestResponseDto<T> getById(@PathVariable U id) {
		GlobalRestResponseDto<T> response = new GlobalRestResponseDto<>(
				"Unable to obtain a single record by id: " + id);
		try {
			T entity = readOnlyService.getById(id);
			if (entity == null)
				response = new GlobalRestResponseDto<>("Entity not found with id: " + id);
			else
				response = new GlobalRestResponseDto<>(entity);
		} catch (Exception e) {
			response = new GlobalRestResponseDto<>(e.getMessage());
		}

		return response;
	}

	/**
	 * Get all records from database.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public GlobalRestResponseDto<T> getAll() {
		GlobalRestResponseDto<T> response = new GlobalRestResponseDto<>("Unable to obtain all records from database");
		try {
			List<T> entities = readOnlyService.getAll();
			if (entities == null || entities.isEmpty())
				response = new GlobalRestResponseDto<>("no data found in database.");
			else
				response = new GlobalRestResponseDto<>(entities);
		} catch (Exception e) {
			response = new GlobalRestResponseDto<>(e.getMessage());
		}

		return response;
	}
}
