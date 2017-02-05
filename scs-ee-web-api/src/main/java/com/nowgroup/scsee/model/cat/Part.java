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

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;

import com.nowgroup.scsee.cat.company.Company;
import com.nowgroup.scsee.model.BaseGenericModel;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "cat_part", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "part_number", "company_id" }, name = "ux_company_part_number") })
public class Part extends BaseGenericModel {
	private static final long	serialVersionUID	= 1L;
	private String				partNumber;
	private String				description;
	private Company				company;
	private BigDecimal			weight				= BigDecimal.ZERO;
	private BigDecimal			height				= BigDecimal.ZERO;
	private BigDecimal			width				= BigDecimal.ZERO;
	private BigDecimal			length				= BigDecimal.ZERO;
	private Boolean				blocked;
	private String				handlingInstructions;
	private String				otherDimensions;
	
	/**
	 * 
	 */
	public Part() {
	}
	
	/**
	 * @return the partNumber
	 */
	@Column(name = "part_number", length = 20, nullable = false)
	public String getPartNumber() {
		return partNumber;
	}
	
	/**
	 * @param partNumber
	 *            the partNumber to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
	/**
	 * @return the description
	 */
	@Column(length = 512)
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the company
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}
	
	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	/**
	 * @return the weight
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	@ColumnDefault(value = "0")
	public BigDecimal getWeight() {
		return weight;
	}
	
	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	/**
	 * @return the height
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	@ColumnDefault(value = "0")
	public BigDecimal getHeight() {
		return height;
	}
	
	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	
	/**
	 * @return the width
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	@ColumnDefault(value = "0")
	public BigDecimal getWidth() {
		return width;
	}
	
	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(BigDecimal width) {
		this.width = width;
	}
	
	/**
	 * @return the length
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	@ColumnDefault(value = "0")
	public BigDecimal getLength() {
		return length;
	}
	
	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	
	/**
	 * @return the handlingInstructions
	 */
	@Column(length = 250)
	public String getHandlingInstructions() {
		return handlingInstructions;
	}
	
	/**
	 * @param handlingInstructions
	 *            the handlingInstructions to set
	 */
	public void setHandlingInstructions(String handlingInstructions) {
		this.handlingInstructions = handlingInstructions;
	}
	
	/**
	 * @return the otherDimensions
	 */
	@Column(length = 250)
	public String getOtherDimensions() {
		return otherDimensions;
	}
	
	/**
	 * @param otherDimensions
	 *            the otherDimensions to set
	 */
	public void setOtherDimensions(String otherDimensions) {
		this.otherDimensions = otherDimensions;
	}
	
	/**
	 * @return the blocked
	 */
	public Boolean getBlocked() {
		return blocked;
	}
	
	/**
	 * @param blocked
	 *            the blocked to set
	 */
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
}
