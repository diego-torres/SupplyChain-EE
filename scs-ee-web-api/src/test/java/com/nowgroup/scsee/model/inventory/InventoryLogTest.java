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

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;

import com.nowgroup.scsee.inventory.Inventory;
import com.nowgroup.scsee.inventory.InventoryLog;

/**
 * @author https://github.com/diego-torres
 *		
 */
public class InventoryLogTest {
	@Test(expected = ValidationException.class)
	public void testInventoryLogChanges() {
		Inventory inventory = new Inventory();
		InventoryLog inventoryLog = new InventoryLog(inventory, BigDecimal.ZERO, new Date(), "me");
		Assert.assertEquals(inventory.getQuantity(), BigDecimal.ZERO);
		Assert.assertEquals(inventoryLog.getQuantity(), BigDecimal.ZERO);
		Assert.assertEquals(inventory.getQuantity(), inventoryLog.getQuantity());
		BigDecimal addFive = new BigDecimal(5);
		inventoryLog.setQuantity(addFive);
		Assert.assertEquals(inventory.getQuantity(), addFive);
		Assert.assertEquals(inventoryLog.getQuantity(), addFive);
		Assert.assertEquals(inventory.getQuantity(), inventoryLog.getQuantity());
		
		inventoryLog.setQuantity(addFive);
		Assert.assertEquals(inventory.getQuantity(), addFive.add(addFive));
		Assert.assertEquals(inventoryLog.getQuantity(), addFive);
		
		BigDecimal takeAwayFifteen = new BigDecimal(-15);
		inventoryLog.setQuantity(takeAwayFifteen);
		Assert.fail("Should fail on too much takeway");
	}
}
