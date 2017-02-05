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
package com.nowgroup.scsee.model.inventory;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.ValidationException;

import org.hibernate.annotations.ColumnDefault;

import com.nowgroup.scsee.cat.location.Location;
import com.nowgroup.scsee.model.BaseGenericModel;
import com.nowgroup.scsee.model.cat.Part;
import com.nowgroup.scsee.model.cat.Unit;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "inventory")
public class Inventory extends BaseGenericModel {
	private static final long serialVersionUID = 1L;
	
	private Part		part;
	private Unit		unit;
	private Location	location;
	private BigDecimal	quantity	= BigDecimal.ZERO;
	
	/**
	 * 
	 */
	public Inventory() {
	}
	
	/**
	 * @param part
	 * @param unit
	 * @param location
	 * @param quantity
	 */
	public Inventory(Part part, Unit unit, Location location) {
		this.part = part;
		this.unit = unit;
		this.location = location;
	}
	
	/**
	 * @return the part
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "part_id")
	public Part getPart() {
		return part;
	}
	
	/**
	 * @param part
	 *            the part to set
	 */
	public void setPart(Part part) {
		this.part = part;
	}
	
	/**
	 * @return the unit
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "unit_id")
	public Unit getUnit() {
		return unit;
	}
	
	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	/**
	 * @return the location
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "location_id")
	public Location getLocation() {
		return location;
	}
	
	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * @return the quantity
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	@ColumnDefault(value = "0")
	public BigDecimal getQuantity() {
		return quantity;
	}
	
	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(BigDecimal quantity) {
		if (quantity.compareTo(BigDecimal.ZERO) < 0)
			throw new ValidationException("Insuficient inventory for operation [" + quantity.doubleValue() + "]");
		this.quantity = quantity;
	}
}
