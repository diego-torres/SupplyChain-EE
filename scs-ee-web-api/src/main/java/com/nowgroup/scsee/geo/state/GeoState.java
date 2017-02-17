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
package com.nowgroup.scsee.geo.state;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.nowgroup.scsee.geo.country.Country;
import com.nowgroup.scsee.model.BaseNamableModel;

/**
 * @author https://github.com/diego-torres
 *
 */
@Entity
@Table(name = "geo_state", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "country_id", "conventional_abreviation" }, name = "ux_country_state") })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class GeoState extends BaseNamableModel {
	private static final long serialVersionUID = 1L;

	private Country country;
	private String conventionalAbreviation;

	/**
	 *
	 */
	public GeoState() {	}

	/**
	 * @return the country
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "FK_GEO_COUNTRY_GEO_STATE"))
	@JsonIgnore
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the conventionalAbreviation
	 */
	@Column(name = "conventional_abreviation", length = 8)
	public String getConventionalAbreviation() {
		return conventionalAbreviation;
	}

	/**
	 * @param conventionalAbreviation
	 *            the conventionalAbreviation to set
	 */
	public void setConventionalAbreviation(String conventionalAbreviation) {
		this.conventionalAbreviation = conventionalAbreviation;
	}

}
