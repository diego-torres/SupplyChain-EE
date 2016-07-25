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
package com.nowgroup.scsee.controller.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.nowgroup.scsee.model.EditValidation;
import com.nowgroup.scsee.model.Model;

/**
 * @author https://github.com/diego-torres
 * 		
 */
public class GlobalRestResponseDto<T extends Model<? extends Serializable>> {
	private GlobalResponseStatus	result			= GlobalResponseStatus.OK;
	private List<EditValidation>	errorDetails	= new ArrayList<>();
	private List<T>					data			= new ArrayList<>();
	
	public GlobalRestResponseDto() {
	}
	
	/**
	 * A failure mode constructor.
	 * 
	 * @param errorDetails
	 *            the error details to be reported.
	 */
	public GlobalRestResponseDto(String errorDetails) {
		result = GlobalResponseStatus.FAIL;
		this.errorDetails.add(new EditValidation(errorDetails));
	}
	
	public GlobalRestResponseDto(T entity, List<EditValidation> validationErrors) {
		result = GlobalResponseStatus.FAIL;
		this.errorDetails.addAll(validationErrors);
		data.add(entity);
	}
	
	/**
	 * Single result mode constructor.
	 * 
	 * @param entity
	 *            The single entity to respond.
	 */
	public GlobalRestResponseDto(T entity) {
		data.add(entity);
	}
	
	/**
	 * Multiple result collection mode constructor
	 * 
	 * @param entities
	 *            All the entities to respond.
	 */
	public GlobalRestResponseDto(List<T> entities) {
		data.addAll(entities);
	}
	
	/**
	 * @return the result
	 */
	public GlobalResponseStatus getResult() {
		return result;
	}
	
	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(GlobalResponseStatus result) {
		this.result = result;
	}
	
	/**
	 * @return the errorDetails
	 */
	public List<EditValidation> getErrorDetails() {
		return errorDetails;
	}
	
	/**
	 * @param errorDetails
	 *            the errorDetails to set
	 */
	public void setErrorDetails(List<EditValidation> errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}
	
	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public static enum GlobalResponseStatus {
		OK, FAIL
	}
}
