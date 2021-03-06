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
package com.nowgroup.scsee.geo.address;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.nowgroup.scsee.geo.state.GeoState;
import com.nowgroup.scsee.model.BaseGenericModel;

/**
 * @author https://github.com/diego-torres
 * 
 */
@MappedSuperclass
public class Address extends BaseGenericModel {
	private static final long serialVersionUID = 1L;
	private String streetAddress;
	private String additionalAddressInfo;
	private String city;
	private GeoState geoState;
	private String zip;
	private String landLine;
	private String contactName;
	private String addressType;

	/**
	 * 
	 */
	public Address() {
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
	 * @return the geoState
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "geo_state_id", foreignKey = @ForeignKey(name = "FK_GEO_STATE_GEO_ADDRESS"))
	public GeoState getGeoState() {
		return geoState;
	}

	/**
	 * @param geoState
	 *            the geoState to set
	 */
	public void setGeoState(GeoState geoState) {
		this.geoState = geoState;
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
		return addressType;
	}

	/**
	 * @param addressType
	 *            the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [streetAddress=" + streetAddress + ", additionalAddressInfo=" + additionalAddressInfo
				+ ", city=" + city + ", geoState=" + geoState + ", zip=" + zip + ", landLine=" + landLine
				+ ", contactName=" + contactName + ", addressType=" + addressType + "]";
	}

}
