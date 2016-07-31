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
package com.nowgroup.scsee.model.loc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.nowgroup.scsee.model.BaseGenericModel;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "loc_address")
public class Address extends BaseGenericModel {
	private static final long	serialVersionUID	= 1L;
	private String				streetAddress;
	private String				additionalAddressInfo;
	private String				city;
	private String				addressState;
	private String				zip;
	private String				landLine;
	private String				contactName;
	private AddressType			addressType;
	
	/**
	 * 
	 */
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the streetAddress
	 */
	@Column(length = 150)
	public String getStreetAddress() {
		return streetAddress;
	}
	
	/**
	 * @param streetAddress
	 *            the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	/**
	 * @return the additionalAddressInfo
	 */
	@Column(length = 150)
	public String getAdditionalAddressInfo() {
		return additionalAddressInfo;
	}
	
	/**
	 * @param additionalAddressInfo
	 *            the additionalAddressInfo to set
	 */
	public void setAdditionalAddressInfo(String additionalAddressInfo) {
		this.additionalAddressInfo = additionalAddressInfo;
	}
	
	/**
	 * @return the city
	 */
	@Column(length = 80)
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @return the addressState
	 */
	@Column(length = 80)
	public String getAddressState() {
		return addressState;
	}
	
	/**
	 * @param addressState
	 *            the addressState to set
	 */
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}
	
	/**
	 * @return the zip
	 */
	@Column(length = 80)
	public String getZip() {
		return zip;
	}
	
	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/**
	 * @return the landLine
	 */
	@Column(length = 20)
	public String getLandLine() {
		return landLine;
	}
	
	/**
	 * @param landLine
	 *            the landLine to set
	 */
	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}
	
	/**
	 * @return the contactName
	 */
	@Column(length = 80)
	public String getContactName() {
		return contactName;
	}
	
	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	/**
	 * @return the addressType
	 */
	@Column(length = 20)
	public String getAddressType() {
		return addressType.name();
	}
	
	/**
	 * @param addressType
	 *            the addressType to set
	 */
	public void setAddressType(String addressType) {
		try {
			this.addressType = AddressType.valueOf(addressType);
		} catch (Exception e) {
			this.addressType = AddressType.UNKNOWN;
		}
	}
	
	public static enum AddressType {
		UNKNOWN, LEGAL, MAILING
	}
	
}
