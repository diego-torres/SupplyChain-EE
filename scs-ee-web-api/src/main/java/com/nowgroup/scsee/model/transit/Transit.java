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
package com.nowgroup.scsee.model.transit;

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
import com.nowgroup.scsee.model.cat.Company;
import com.nowgroup.scsee.model.cat.TransportationMode;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "transits")
public class Transit extends BaseGenericModel {
	private static final long serialVersionUID = 1L;
	
	private TransportationMode	transportationMode;
	private String				driverName;
	private String				vehiclePlates;
	private String				vehicleNumber;
	private String				vehicleDescription;
	private String				sealNumbers;
	private String				trackingNumbers;
	private String				lastGpsLocation;
	private BigDecimal			weightCapacity	= BigDecimal.ZERO;
	private BigDecimal			length			= BigDecimal.ZERO;
	private BigDecimal			height			= BigDecimal.ZERO;
	private BigDecimal			width			= BigDecimal.ZERO;
	private Date				estimatedDeliveryWhen;
	private Date				actualDeliveryWhen;
	
	private Company	freighterCompany;
	private Company	billToCompany;
	private Company	senderCompany;
	private Company	receiverCompany;
	private Company	purchaserCompany;
	private Company	payerCompany;
	
	private String	clientRef;
	private String	transitInstructions;
	private String	observations;
	
	/**
	 * 
	 */
	public Transit() {
	}
	
	/**
	 * @return the transportationMode
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "transportation_mode_id")
	public TransportationMode getTransportationMode() {
		return transportationMode;
	}
	
	/**
	 * @param transportationMode
	 *            the transportationMode to set
	 */
	public void setTransportationMode(TransportationMode transportationMode) {
		this.transportationMode = transportationMode;
	}
	
	/**
	 * @return the driverName
	 */
	@Column(length = 150)
	public String getDriverName() {
		return driverName;
	}
	
	/**
	 * @param driverName
	 *            the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	/**
	 * @return the vehiclePlates
	 */
	@Column(length = 20)
	public String getVehiclePlates() {
		return vehiclePlates;
	}
	
	/**
	 * @param vehiclePlates
	 *            the vehiclePlates to set
	 */
	public void setVehiclePlates(String vehiclePlates) {
		this.vehiclePlates = vehiclePlates;
	}
	
	/**
	 * @return the vehicleNumber
	 */
	@Column(length = 20)
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	
	/**
	 * @param vehicleNumber
	 *            the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	
	/**
	 * @return the vehicleDescription
	 */
	@Column(length = 150)
	public String getVehicleDescription() {
		return vehicleDescription;
	}
	
	/**
	 * @param vehicleDescription
	 *            the vehicleDescription to set
	 */
	public void setVehicleDescription(String vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}
	
	/**
	 * @return the sealNumbers
	 */
	@Column(length = 250)
	public String getSealNumbers() {
		return sealNumbers;
	}
	
	/**
	 * @param sealNumbers
	 *            the sealNumbers to set
	 */
	public void setSealNumbers(String sealNumbers) {
		this.sealNumbers = sealNumbers;
	}
	
	/**
	 * @return the trackingNumbers
	 */
	@Column(length = 250)
	public String getTrackingNumbers() {
		return trackingNumbers;
	}
	
	/**
	 * @param trackingNumbers
	 *            the trackingNumbers to set
	 */
	public void setTrackingNumbers(String trackingNumbers) {
		this.trackingNumbers = trackingNumbers;
	}
	
	/**
	 * @return the lastGpsLocation
	 */
	@Column(length = 150)
	public String getLastGpsLocation() {
		return lastGpsLocation;
	}
	
	/**
	 * @param lastGpsLocation
	 *            the lastGpsLocation to set
	 */
	public void setLastGpsLocation(String lastGpsLocation) {
		this.lastGpsLocation = lastGpsLocation;
	}
	
	/**
	 * @return the weightCapacity
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	@ColumnDefault(value = "0")
	public BigDecimal getWeightCapacity() {
		return weightCapacity;
	}
	
	/**
	 * @param weightCapacity
	 *            the weightCapacity to set
	 */
	public void setWeightCapacity(BigDecimal weightCapacity) {
		this.weightCapacity = weightCapacity;
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
	 * @return the estimatedDeliveryWhen
	 */
	@Column
	@Temporal(TemporalType.DATE)
	public Date getEstimatedDeliveryWhen() {
		return estimatedDeliveryWhen;
	}
	
	/**
	 * @param estimatedDeliveryWhen
	 *            the estimatedDeliveryWhen to set
	 */
	public void setEstimatedDeliveryWhen(Date estimatedDeliveryWhen) {
		this.estimatedDeliveryWhen = estimatedDeliveryWhen;
	}
	
	/**
	 * @return the actualDeliveryWhen
	 */
	@Column
	@Temporal(TemporalType.DATE)
	public Date getActualDeliveryWhen() {
		return actualDeliveryWhen;
	}
	
	/**
	 * @param actualDeliveryWhen
	 *            the actualDeliveryWhen to set
	 */
	public void setActualDeliveryWhen(Date actualDeliveryWhen) {
		this.actualDeliveryWhen = actualDeliveryWhen;
	}
	
	/**
	 * @return the freighterCompany
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "freighter_company_id")
	public Company getFreighterCompany() {
		return freighterCompany;
	}
	
	/**
	 * @param freighterCompany
	 *            the freighterCompany to set
	 */
	public void setFreighterCompany(Company freighterCompany) {
		this.freighterCompany = freighterCompany;
	}
	
	/**
	 * @return the billToCompany
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "billing_company_id")
	public Company getBillToCompany() {
		return billToCompany;
	}
	
	/**
	 * @param billToCompany
	 *            the billToCompany to set
	 */
	public void setBillToCompany(Company billToCompany) {
		this.billToCompany = billToCompany;
	}
	
	/**
	 * @return the senderCompany
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "sender_company_id")
	public Company getSenderCompany() {
		return senderCompany;
	}
	
	/**
	 * @param senderCompany
	 *            the senderCompany to set
	 */
	public void setSenderCompany(Company senderCompany) {
		this.senderCompany = senderCompany;
	}
	
	/**
	 * @return the receiverCompany
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "receiver_company_id")
	public Company getReceiverCompany() {
		return receiverCompany;
	}
	
	/**
	 * @param receiverCompany
	 *            the receiverCompany to set
	 */
	public void setReceiverCompany(Company receiverCompany) {
		this.receiverCompany = receiverCompany;
	}
	
	/**
	 * @return the purchaserCompany
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "purchaser_company_id")
	public Company getPurchaserCompany() {
		return purchaserCompany;
	}
	
	/**
	 * @param purchaserCompany
	 *            the purchaserCompany to set
	 */
	public void setPurchaserCompany(Company purchaserCompany) {
		this.purchaserCompany = purchaserCompany;
	}
	
	/**
	 * @return the payerCompany
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "payer_company_id")
	public Company getPayerCompany() {
		return payerCompany;
	}
	
	/**
	 * @param payerCompany
	 *            the payerCompany to set
	 */
	public void setPayerCompany(Company payerCompany) {
		this.payerCompany = payerCompany;
	}
	
	/**
	 * @return the clientRef
	 */
	@Column(length = 50)
	public String getClientRef() {
		return clientRef;
	}
	
	/**
	 * @param clientRef
	 *            the clientRef to set
	 */
	public void setClientRef(String clientRef) {
		this.clientRef = clientRef;
	}
	
	/**
	 * @return the transitInstructions
	 */
	@Column(length = 250)
	public String getTransitInstructions() {
		return transitInstructions;
	}
	
	/**
	 * @param transitInstructions
	 *            the transitInstructions to set
	 */
	public void setTransitInstructions(String transitInstructions) {
		this.transitInstructions = transitInstructions;
	}
	
	/**
	 * @return the observations
	 */
	@Column(length = 250)
	public String getObservations() {
		return observations;
	}
	
	/**
	 * @param observations
	 *            the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}
}
