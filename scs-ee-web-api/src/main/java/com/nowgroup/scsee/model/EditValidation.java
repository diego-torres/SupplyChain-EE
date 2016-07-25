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

import java.io.Serializable;

/**
 * @author https://github.com/diego-torres
 * 		
 */
public class EditValidation implements Serializable {
	private static final long		serialVersionUID	= 1L;
	private String					fieldName;
	private String					validationMessage;
	private EditValidationSeverity	severity			= EditValidationSeverity.LOW;
	
	/**
	 * instance defaulted severity with a description,
	 * 
	 * @param validationMessage
	 */
	public EditValidation(String validationMessage) {
		this.validationMessage = validationMessage;
	}
	
	/**
	 * @param validationMessage
	 * @param severity
	 */
	public EditValidation(String validationMessage, EditValidationSeverity severity) {
		this.validationMessage = validationMessage;
		this.severity = severity;
	}
	
	/**
	 * @param fieldName
	 * @param validationMessage
	 * @param severity
	 */
	public EditValidation(String fieldName, String validationMessage, EditValidationSeverity severity) {
		this.fieldName = fieldName;
		this.validationMessage = validationMessage;
		this.severity = severity;
	}
	
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	
	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	/**
	 * @return the validationMessage
	 */
	public String getValidationMessage() {
		return validationMessage;
	}
	
	/**
	 * @param validationMessage
	 *            the validationMessage to set
	 */
	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}
	
	/**
	 * @return the severity
	 */
	public EditValidationSeverity getSeverity() {
		return severity;
	}
	
	/**
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(EditValidationSeverity severity) {
		this.severity = severity;
	}
	
	public static enum EditValidationSeverity {
		LOW, MEDIUM, CRITICAL
	}
}
