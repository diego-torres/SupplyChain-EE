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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.nowgroup.scsee.model.BaseGenericModel;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "inventory_log")
public class InventoryLog extends BaseGenericModel {
	private static final long	serialVersionUID	= 1L;
	private Inventory			inventory;
	private BigDecimal			quantity;
	private Date				createdWhen			= new Date();
	private String				createdBy;
	
	/**
	 * 
	 */
	public InventoryLog() {
	}
	
	/**
	 * @param inventory
	 * @param quantity
	 * @param createdWhen
	 * @param createdBy
	 */
	public InventoryLog(Inventory inventory, BigDecimal quantity, Date createdWhen, String createdBy) {
		this.inventory = inventory;
		this.quantity = quantity;
		this.createdWhen = createdWhen;
		this.createdBy = createdBy;
	}
	
	/**
	 * @return the inventory
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "inventory_id")
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * @param inventory
	 *            the inventory to set
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
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
		this.quantity = quantity;
	}
	
	/**
	 * @return the createdWhen
	 */
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedWhen() {
		return createdWhen;
	}
	
	/**
	 * @param createdWhen
	 *            the createdWhen to set
	 */
	public void setCreatedWhen(Date createdWhen) {
		this.createdWhen = createdWhen;
	}
	
	/**
	 * @return the createdBy
	 */
	@Column
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
