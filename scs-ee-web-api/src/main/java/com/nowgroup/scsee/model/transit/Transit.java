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
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;

import com.nowgroup.scsee.cat.company.Company;
import com.nowgroup.scsee.model.BaseGenericModel;
import com.nowgroup.scsee.model.BaseNamableModel;
import com.nowgroup.scsee.model.cat.Packing;
import com.nowgroup.scsee.model.cat.TransportationMode;
import com.nowgroup.scsee.model.cat.UnLabel;

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
	 * Calculate the transit volume
	 * 
	 * @return
	 */
	@Transient
	public BigDecimal getVolume() {
		if (height == null || BigDecimal.ZERO.compareTo(height) == 0 || length == null
				|| BigDecimal.ZERO.compareTo(length) == 0 || width == null || BigDecimal.ZERO.compareTo(width) == 0)
			return BigDecimal.ZERO;
		return height.multiply(length).multiply(width);
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
	
	/**
	 * Transit items.
	 * 
	 * @author https://github.com/diego-torres
	 * 		
	 */
	@Entity
	@Table(name = "transit_items")
	public static class TransitItem extends BaseNamableModel {
		private static final long serialVersionUID = 1L;
		
		private Transit		transit;
		private Integer		quantity	= 0;
		private Packing		packingUnit;
		private BigDecimal	weight		= BigDecimal.ZERO;
		private BigDecimal	length		= BigDecimal.ZERO;
		private BigDecimal	height		= BigDecimal.ZERO;
		private BigDecimal	width		= BigDecimal.ZERO;
		private String		description;
		private UnLabel		label;
		private String		marks;
		private String		models;
		private String		serials;
		
		/**
		 * 
		 */
		public TransitItem() {
			super();
		}
		
		/**
		 * @param id
		 * @param name
		 */
		public TransitItem(Integer id, String name) {
			super(id, name);
		}
		
		/**
		 * @param name
		 */
		public TransitItem(String name) {
			super(name);
		}
		
		/**
		 * @return the transit
		 */
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "transit_id")
		public Transit getTransit() {
			return transit;
		}
		
		/**
		 * @param transit
		 *            the transit to set
		 */
		public void setTransit(Transit transit) {
			this.transit = transit;
		}
		
		/**
		 * @return the quantity
		 */
		@Column
		public Integer getQuantity() {
			return quantity;
		}
		
		/**
		 * @param quantity
		 *            the quantity to set
		 */
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		
		/**
		 * @return the packingUnit
		 */
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "packing_unit_id")
		public Packing getPackingUnit() {
			return packingUnit;
		}
		
		/**
		 * @param packingUnit
		 *            the packingUnit to set
		 */
		public void setPackingUnit(Packing packingUnit) {
			this.packingUnit = packingUnit;
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
		 * Calculate the transit item volume
		 * 
		 * @return
		 */
		@Transient
		public BigDecimal getVolume() {
			if (height == null || BigDecimal.ZERO.compareTo(height) == 0 || length == null
					|| BigDecimal.ZERO.compareTo(length) == 0 || width == null || BigDecimal.ZERO.compareTo(width) == 0)
				return BigDecimal.ZERO;
			return height.multiply(length).multiply(width);
		}
		
		/**
		 * @return the description
		 */
		@Column(length = 250)
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
		 * @return the label
		 */
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "label_id")
		public UnLabel getLabel() {
			return label;
		}
		
		/**
		 * @param label
		 *            the label to set
		 */
		public void setLabel(UnLabel label) {
			this.label = label;
		}
		
		/**
		 * @return the marks
		 */
		@Column(length = 100)
		public String getMarks() {
			return marks;
		}
		
		/**
		 * @param marks
		 *            the marks to set
		 */
		public void setMarks(String marks) {
			this.marks = marks;
		}
		
		/**
		 * @return the models
		 */
		@Column(length = 100)
		public String getModels() {
			return models;
		}
		
		/**
		 * @param models
		 *            the models to set
		 */
		public void setModels(String models) {
			this.models = models;
		}
		
		/**
		 * @return the serials
		 */
		@Column(length = 100)
		public String getSerials() {
			return serials;
		}
		
		/**
		 * @param serials
		 *            the serials to set
		 */
		public void setSerials(String serials) {
			this.serials = serials;
		}
	}
}
