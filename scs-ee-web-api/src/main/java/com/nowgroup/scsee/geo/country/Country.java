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
package com.nowgroup.scsee.geo.country;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nowgroup.scsee.geo.state.GeoState;
import com.nowgroup.scsee.model.BaseNamableModel;

/**
 * @author https://github.com/diego-torres
 *
 */
@Entity
@Table(	name = "geo_country",
		uniqueConstraints = { @UniqueConstraint(columnNames = { "country_code" }, name = "ux_country_code") })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Country extends BaseNamableModel {
	private static final long	serialVersionUID	= 1L;
	private String					countryCode;
	private Set<GeoState> 	states = new HashSet<>();

	/**
	 *
	 */
	public Country() {	}

	/**
	 * @param name
	 */
	public Country(String name) {
		super(name);
	}

	/**
	 * @param id
	 * @param name
	 */
	public Country(Integer id, String name) {
		super(id, name);
	}

	/**
	 * @return the countryCode
	 */
	@Column(name = "country_code", length = 3, nullable = false)
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the states
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
	public Set<GeoState> getStates() {
		return this.states;
	}

	/**
	 * @param states
	 *            the states to set
	 */
	public void setStates(Set<GeoState> states) {
		this.states = states;
	}

}
