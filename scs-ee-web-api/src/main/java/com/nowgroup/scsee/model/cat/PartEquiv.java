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

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.nowgroup.scsee.cat.part.Part;
import com.nowgroup.scsee.model.Model;
import com.nowgroup.scsee.model.cat.PartEquiv.PartEquivKey;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "equiv_parts")
public class PartEquiv implements Model<PartEquivKey> {
	private static final long	serialVersionUID	= 1L;
	private PartEquivKey		id;
	private BigDecimal			rateAtoB;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nowgroup.scsee.model.Model#getId()
	 */
	@Override
	@EmbeddedId
	public PartEquivKey getId() {
		return id;
	}
	
	/**
	 * @param key
	 *            the key to set
	 */
	public void setId(PartEquivKey key) {
		this.id = key;
	}
	
	/**
	 * @return the rateAtoB
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	@ColumnDefault(value = "1")
	public BigDecimal getRateAtoB() {
		return rateAtoB;
	}
	
	/**
	 * @param rateAtoB
	 *            the rateAtoB to set
	 */
	public void setRateAtoB(BigDecimal rateAtoB) {
		this.rateAtoB = rateAtoB;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof PartEquiv)) return false;
		PartEquiv other = (PartEquiv) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}
	
	/**
	 * 
	 * @author https://github.com/diego-torres
	 * 		
	 */
	@Embeddable
	public static class PartEquivKey implements Serializable {
		private static final long	serialVersionUID	= 1L;
		private Part				partA;
		private Part				partB;
		
		/**
		 * @return the partIdA
		 */
		@ManyToOne(	fetch = FetchType.LAZY, optional = false)
		@JoinColumn(name = "part_id_a")
		public Part getPartA() {
			return partA;
		}
		
		/**
		 * @param partIdA
		 *            the partIdA to set
		 */
		public void setPartA(Part partA) {
			this.partA = partA;
		}
		
		/**
		 * @return the partIdB
		 */
		@ManyToOne(	fetch = FetchType.LAZY, optional = false)
		@JoinColumn(name = "part_id_b")
		public Part getPartB() {
			return partB;
		}
		
		/**
		 * @param partIdB
		 *            the partIdB to set
		 */
		public void setPartB(Part partB) {
			this.partB = partB;
		}
	}
}
