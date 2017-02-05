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
package com.nowgroup.scsee.model.packingList;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nowgroup.scsee.cat.assessmentMethod.AssessmentMethod;
import com.nowgroup.scsee.cat.bundleType.BundleType;
import com.nowgroup.scsee.cat.company.Company;
import com.nowgroup.scsee.model.BaseGenericModel;
import com.nowgroup.scsee.model.cat.Currency;
import com.nowgroup.scsee.model.cat.CustomsRegime;
import com.nowgroup.scsee.model.cat.Incoterm;
import com.nowgroup.scsee.model.cat.Part;
import com.nowgroup.scsee.model.cat.Unit;
import com.nowgroup.scsee.model.loc.Country;

/**
 * @author https://github.com/diego-torres
 * 		
 */
@Entity
@Table(name = "packing_list", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "folio", "seller_company_id" }, name = "ux_packing_list_seller_folio") })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PackingList extends BaseGenericModel {
	private static final long serialVersionUID = 1L;
	
	private String	folio;
	private Date	packingListDate;
	private String	purchaseOrderFolio;
	private String	salesOrderFolio;
	private String	incontermLocation;
	private Integer	bundleQuantity;
	private String	bundleComment;
	private String	publicComments;
	private String	internalComments;
	private String	instructions;
	private String	additionalInstructions;
	private Boolean	blocked;
	
	private Currency			currency;
	private Incoterm			inconterm;
	private Country				originCountry;
	private CustomsRegime		customsRegime;
	private AssessmentMethod	assessmentMethod;
	private BundleType			bundleType;
	
	private Company	purchaser;
	private Company	seller;
	private Company	sender;
	private Company	receiver;
	
	private Set<PackingListItem> items = new HashSet<>();
	
	/**
	 * 
	 */
	public PackingList() {
		super();
	}
	
	/**
	 * @param folio
	 * @param packingListDate
	 * @param purchaseOrderFolio
	 * @param salesOrderFolio
	 * @param incontermLocation
	 * @param bundleQuantity
	 * @param bundleComment
	 * @param publicComments
	 * @param internalComments
	 * @param instructions
	 * @param additionalInstructions
	 * @param blocked
	 * @param currency
	 * @param inconterm
	 * @param originCountry
	 * @param customsRegime
	 * @param assessmentMethod
	 * @param bundleType
	 * @param purchaser
	 * @param seller
	 * @param sender
	 * @param receiver
	 * @param items
	 */
	public PackingList(	String folio, Date packingListDate, String purchaseOrderFolio, String salesOrderFolio,
						String incontermLocation, Integer bundleQuantity, String bundleComment, String publicComments,
						String internalComments, String instructions, String additionalInstructions, Boolean blocked,
						Currency currency, Incoterm inconterm, Country originCountry, CustomsRegime customsRegime,
						AssessmentMethod assessmentMethod, BundleType bundleType, Company purchaser, Company seller,
						Company sender, Company receiver, Set<PackingListItem> items) {
		this.folio = folio;
		this.packingListDate = packingListDate;
		this.purchaseOrderFolio = purchaseOrderFolio;
		this.salesOrderFolio = salesOrderFolio;
		this.incontermLocation = incontermLocation;
		this.bundleQuantity = bundleQuantity;
		this.bundleComment = bundleComment;
		this.publicComments = publicComments;
		this.internalComments = internalComments;
		this.instructions = instructions;
		this.additionalInstructions = additionalInstructions;
		this.blocked = blocked;
		this.currency = currency;
		this.inconterm = inconterm;
		this.originCountry = originCountry;
		this.customsRegime = customsRegime;
		this.assessmentMethod = assessmentMethod;
		this.bundleType = bundleType;
		this.purchaser = purchaser;
		this.seller = seller;
		this.sender = sender;
		this.receiver = receiver;
		this.items = items;
	}
	
	/**
	 * @return the folio
	 */
	@Column(length = 50)
	public String getFolio() {
		return folio;
	}
	
	/**
	 * @param folio
	 *            the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	/**
	 * @return the packingListDate
	 */
	@Column
	@Temporal(TemporalType.DATE)
	public Date getPackingListDate() {
		return packingListDate;
	}
	
	/**
	 * @param packingListDate
	 *            the packingListDate to set
	 */
	public void setPackingListDate(Date packingListDate) {
		this.packingListDate = packingListDate;
	}
	
	/**
	 * @return the purchaseOrderFolio
	 */
	@Column(name = "purchase_order_folio", length = 50)
	public String getPurchaseOrderFolio() {
		return purchaseOrderFolio;
	}
	
	/**
	 * @param purchaseOrderFolio
	 *            the purchaseOrderFolio to set
	 */
	public void setPurchaseOrderFolio(String purchaseOrderFolio) {
		this.purchaseOrderFolio = purchaseOrderFolio;
	}
	
	/**
	 * @return the salesOrderFolio
	 */
	@Column(name = "sales_order_folio", length = 50)
	public String getSalesOrderFolio() {
		return salesOrderFolio;
	}
	
	/**
	 * @param salesOrderFolio
	 *            the salesOrderFolio to set
	 */
	public void setSalesOrderFolio(String salesOrderFolio) {
		this.salesOrderFolio = salesOrderFolio;
	}
	
	/**
	 * @return the incontermLocation
	 */
	@Column(name = "incoterm_location", length = 150)
	public String getIncontermLocation() {
		return incontermLocation;
	}
	
	/**
	 * @param incontermLocation
	 *            the incontermLocation to set
	 */
	public void setIncontermLocation(String incontermLocation) {
		this.incontermLocation = incontermLocation;
	}
	
	/**
	 * @return the bundleQuantity
	 */
	@Column(name = "bundle_quantity")
	public Integer getBundleQuantity() {
		return bundleQuantity;
	}
	
	/**
	 * @param bundleQuantity
	 *            the bundleQuantity to set
	 */
	public void setBundleQuantity(Integer bundleQuantity) {
		this.bundleQuantity = bundleQuantity;
	}
	
	/**
	 * @return the bundleComment
	 */
	@Column(name = "bundle_comment", length = 150)
	public String getBundleComment() {
		return bundleComment;
	}
	
	/**
	 * @param bundleComment
	 *            the bundleComment to set
	 */
	public void setBundleComment(String bundleComment) {
		this.bundleComment = bundleComment;
	}
	
	/**
	 * @return the publicComments
	 */
	@Column(name = "public_comments", length = 250)
	public String getPublicComments() {
		return publicComments;
	}
	
	/**
	 * @param publicComments
	 *            the publicComments to set
	 */
	public void setPublicComments(String publicComments) {
		this.publicComments = publicComments;
	}
	
	/**
	 * @return the internalComments
	 */
	@Column(name = "internal_comments", length = 250)
	public String getInternalComments() {
		return internalComments;
	}
	
	/**
	 * @param internalComments
	 *            the internalComments to set
	 */
	public void setInternalComments(String internalComments) {
		this.internalComments = internalComments;
	}
	
	/**
	 * @return the instructions
	 */
	@Column(name = "instructions", length = 250)
	public String getInstructions() {
		return instructions;
	}
	
	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	/**
	 * @return the additionalInstructions
	 */
	@Column(name = "additional_instructions", length = 250)
	public String getAdditionalInstructions() {
		return additionalInstructions;
	}
	
	/**
	 * @param additionalInstructions
	 *            the additionalInstructions to set
	 */
	public void setAdditionalInstructions(String additionalInstructions) {
		this.additionalInstructions = additionalInstructions;
	}
	
	/**
	 * @return the blocked
	 */
	@Column
	public Boolean isBlocked() {
		return blocked;
	}
	
	/**
	 * @param blocked
	 *            the blocked to set
	 */
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
	
	/**
	 * @return the currency
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "currency_id")
	public Currency getCurrency() {
		return currency;
	}
	
	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	/**
	 * @return the inconterm
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "incoterm_id")
	public Incoterm getInconterm() {
		return inconterm;
	}
	
	/**
	 * @param inconterm
	 *            the inconterm to set
	 */
	public void setInconterm(Incoterm inconterm) {
		this.inconterm = inconterm;
	}
	
	/**
	 * @return the originCountry
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "origin_country_id")
	public Country getOriginCountry() {
		return originCountry;
	}
	
	/**
	 * @param originCountry
	 *            the originCountry to set
	 */
	public void setOriginCountry(Country originCountry) {
		this.originCountry = originCountry;
	}
	
	/**
	 * @return the customsRegime
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "customs_regime_id")
	public CustomsRegime getCustomsRegime() {
		return customsRegime;
	}
	
	/**
	 * @param customsRegime
	 *            the customsRegime to set
	 */
	public void setCustomsRegime(CustomsRegime customsRegime) {
		this.customsRegime = customsRegime;
	}
	
	/**
	 * @return the assessmentMethod
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "assessment_method_id")
	public AssessmentMethod getAssessmentMethod() {
		return assessmentMethod;
	}
	
	/**
	 * @param assessmentMethod
	 *            the assessmentMethod to set
	 */
	public void setAssessmentMethod(AssessmentMethod assessmentMethod) {
		this.assessmentMethod = assessmentMethod;
	}
	
	/**
	 * @return the bundleType
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "bundle_type_id")
	public BundleType getBundleType() {
		return bundleType;
	}
	
	/**
	 * @param bundleType
	 *            the bundleType to set
	 */
	public void setBundleType(BundleType bundleType) {
		this.bundleType = bundleType;
	}
	
	/**
	 * @return the purchaser
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "purchaser_company_id")
	public Company getPurchaser() {
		return purchaser;
	}
	
	/**
	 * @param purchaser
	 *            the purchaser to set
	 */
	public void setPurchaser(Company purchaser) {
		this.purchaser = purchaser;
	}
	
	/**
	 * @return the seller
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "seller_company_id")
	public Company getSeller() {
		return seller;
	}
	
	/**
	 * @param seller
	 *            the seller to set
	 */
	public void setSeller(Company seller) {
		this.seller = seller;
	}
	
	/**
	 * @return the sender
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "sender_company_id")
	public Company getSender() {
		return sender;
	}
	
	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(Company sender) {
		this.sender = sender;
	}
	
	/**
	 * @return the receiver
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "receiver_company_id")
	public Company getReceiver() {
		return receiver;
	}
	
	/**
	 * @param receiver
	 *            the receiver to set
	 */
	public void setReceiver(Company receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * @return the items
	 */
	@OneToMany(mappedBy = "packingList", fetch = FetchType.EAGER, orphanRemoval = true)
	public Set<PackingListItem> getItems() {
		return items;
	}
	
	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(Set<PackingListItem> items) {
		this.items = items;
	}
	
	/**
	 * 
	 * @author https://github.com/diego-torres
	 * 		
	 */
	@Entity
	@Table(name = "packing_list_item", uniqueConstraints = {
			@UniqueConstraint(columnNames = { "packing_list_id", "item_seq" }, name = "ux_packing_list_item_seq") })
	public static class PackingListItem extends BaseGenericModel {
		private static final long serialVersionUID = 1L;
		
		private PackingList packingList;
		
		private String		itemSeq;
		private Part		part;
		private BigDecimal	quantity		= new BigDecimal(0.0);
		private BigDecimal	unitPrice		= new BigDecimal(0.0);
		private Boolean		tlc;
		private Boolean		ma;
		private BigDecimal	weightKilos		= new BigDecimal(0.0);
		private String		lot;
		private Integer		bundles;
		private Integer		pieces;
		private BigDecimal	lengthInches	= new BigDecimal(0.0);
		private BigDecimal	widthInches		= new BigDecimal(0.0);
		private BigDecimal	heightInches	= new BigDecimal(0.0);
		private String		marks;
		private String		models;
		private String		series;
		private String		observations;
		private Boolean		blocked;
		
		private Unit				unitOfMeasure;
		private Country				originCountry;
		private Incoterm			inconterm;
		private CustomsRegime		customsRegime;
		private AssessmentMethod	assessmentMethod;
		
		/**
		 * @return the packingList
		 */
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "packing_list_id")
		@JsonIgnore
		public PackingList getPackingList() {
			return packingList;
		}
		
		/**
		 * @param packingList
		 *            the packingList to set
		 */
		public void setPackingList(PackingList packingList) {
			this.packingList = packingList;
		}
		
		/**
		 * @return the itemSeq
		 */
		@Column(name = "item_seq", length = 4)
		public String getItemSeq() {
			return itemSeq;
		}
		
		/**
		 * @param itemSeq
		 *            the itemSeq to set
		 */
		public void setItemSeq(String itemSeq) {
			this.itemSeq = itemSeq;
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
		 * @return the unitPrice
		 */
		@Column(name = "unit_price", nullable = false, precision = 18, scale = 6)
		@ColumnDefault(value = "0")
		public BigDecimal getUnitPrice() {
			return unitPrice;
		}
		
		/**
		 * @param unitPrice
		 *            the unitPrice to set
		 */
		public void setUnitPrice(BigDecimal unitPrice) {
			this.unitPrice = unitPrice;
		}
		
		/**
		 * @return the tlc
		 */
		@Column
		public Boolean getTlc() {
			return tlc;
		}
		
		/**
		 * @param tlc
		 *            the tlc to set
		 */
		public void setTlc(Boolean tlc) {
			this.tlc = tlc;
		}
		
		/**
		 * @return the ma
		 */
		@Column
		public Boolean getMa() {
			return ma;
		}
		
		/**
		 * @param ma
		 *            the ma to set
		 */
		public void setMa(Boolean ma) {
			this.ma = ma;
		}
		
		/**
		 * @return the weightKilos
		 */
		@Column(name="weight_kilos", nullable = false, precision = 18, scale = 6)
		@ColumnDefault(value = "0")
		public BigDecimal getWeightKilos() {
			return weightKilos;
		}
		
		/**
		 * @param weightKilos
		 *            the weightKilos to set
		 */
		public void setWeightKilos(BigDecimal weightKilos) {
			this.weightKilos = weightKilos;
		}
		
		/**
		 * @return the lot
		 */
		@Column
		public String getLot() {
			return lot;
		}
		
		/**
		 * @param lot
		 *            the lot to set
		 */
		public void setLot(String lot) {
			this.lot = lot;
		}
		
		/**
		 * @return the bundles
		 */
		@Column
		public Integer getBundles() {
			return bundles;
		}
		
		/**
		 * @param bundles
		 *            the bundles to set
		 */
		public void setBundles(Integer bundles) {
			this.bundles = bundles;
		}
		
		/**
		 * @return the pieces
		 */
		@Column
		public Integer getPieces() {
			return pieces;
		}
		
		/**
		 * @param pieces
		 *            the pieces to set
		 */
		public void setPieces(Integer pieces) {
			this.pieces = pieces;
		}
		
		/**
		 * @return the lengthInches
		 */
		@Column(name="length_inches", nullable = false, precision = 18, scale = 6)
		@ColumnDefault(value = "0")
		public BigDecimal getLengthInches() {
			return lengthInches;
		}
		
		/**
		 * @param lengthInches
		 *            the lengthInches to set
		 */
		public void setLengthInches(BigDecimal lengthInches) {
			this.lengthInches = lengthInches;
		}
		
		/**
		 * @return the widthInches
		 */
		@Column(name="width_inches", nullable = false, precision = 18, scale = 6)
		@ColumnDefault(value = "0")
		public BigDecimal getWidthInches() {
			return widthInches;
		}
		
		/**
		 * @param widthInches
		 *            the widthInches to set
		 */
		public void setWidthInches(BigDecimal widthInches) {
			this.widthInches = widthInches;
		}
		
		/**
		 * @return the heightInches
		 */
		@Column(name="height_inches", nullable = false, precision = 18, scale = 6)
		@ColumnDefault(value = "0")
		public BigDecimal getHeightInches() {
			return heightInches;
		}
		
		/**
		 * @param heightInches
		 *            the heightInches to set
		 */
		public void setHeightInches(BigDecimal heightInches) {
			this.heightInches = heightInches;
		}
		
		/**
		 * @return the marks
		 */
		@Column(length=1024)
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
		@Column(length=1024)
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
		 * @return the series
		 */
		@Column(length=1024)
		public String getSeries() {
			return series;
		}
		
		/**
		 * @param series
		 *            the series to set
		 */
		public void setSeries(String series) {
			this.series = series;
		}
		
		/**
		 * @return the observations
		 */
		@Column(length=1024)
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
		 * @return the blocked
		 */
		@Column
		public Boolean isBlocked() {
			return blocked;
		}
		
		/**
		 * @param blocked
		 *            the blocked to set
		 */
		public void setBlocked(Boolean blocked) {
			this.blocked = blocked;
		}
		
		/**
		 * @return the unitOfMeasure
		 */
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "unit_of_measure_id")
		public Unit getUnitOfMeasure() {
			return unitOfMeasure;
		}
		
		/**
		 * @param unitOfMeasure
		 *            the unitOfMeasure to set
		 */
		public void setUnitOfMeasure(Unit unitOfMeasure) {
			this.unitOfMeasure = unitOfMeasure;
		}
		
		/**
		 * @return the originCountry
		 */
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "origin_country_id")
		public Country getOriginCountry() {
			return originCountry;
		}
		
		/**
		 * @param originCountry
		 *            the originCountry to set
		 */
		public void setOriginCountry(Country originCountry) {
			this.originCountry = originCountry;
		}
		
		/**
		 * @return the inconterm
		 */
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "incoterm_id")
		public Incoterm getInconterm() {
			return inconterm;
		}
		
		/**
		 * @param inconterm
		 *            the inconterm to set
		 */
		public void setInconterm(Incoterm inconterm) {
			this.inconterm = inconterm;
		}
		
		/**
		 * @return the customsRegime
		 */
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "customs_regime_id")
		public CustomsRegime getCustomsRegime() {
			return customsRegime;
		}
		
		/**
		 * @param customsRegime
		 *            the customsRegime to set
		 */
		public void setCustomsRegime(CustomsRegime customsRegime) {
			this.customsRegime = customsRegime;
		}
		
		/**
		 * @return the assessmentMethod
		 */
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "assessment_method_id")
		public AssessmentMethod getAssessmentMethod() {
			return assessmentMethod;
		}
		
		/**
		 * @param assessmentMethod
		 *            the assessmentMethod to set
		 */
		public void setAssessmentMethod(AssessmentMethod assessmentMethod) {
			this.assessmentMethod = assessmentMethod;
		}
	}
	
}
