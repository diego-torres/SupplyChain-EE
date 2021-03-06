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
package com.nowgroup.scsee.springBoot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import com.nowgroup.scsee.cat.assessmentMethod.AssessmentMethod;
import com.nowgroup.scsee.cat.bundleType.BundleType;
import com.nowgroup.scsee.cat.company.Company;
import com.nowgroup.scsee.cat.company.HibernateCompanyRepository;
import com.nowgroup.scsee.cat.currency.Currency;
import com.nowgroup.scsee.cat.customsRegime.CustomsRegime;
import com.nowgroup.scsee.cat.incoterm.Incoterm;
import com.nowgroup.scsee.cat.location.HibernateLocationRepository;
import com.nowgroup.scsee.cat.location.Location;
import com.nowgroup.scsee.cat.part.Part;
import com.nowgroup.scsee.cat.part.HibernatePartRepository;
import com.nowgroup.scsee.cat.partEquiv.PartEquiv;
import com.nowgroup.scsee.cat.storage.HibernateStorageRepository;
import com.nowgroup.scsee.cat.storage.Storage;
import com.nowgroup.scsee.cat.tariff.Tariff;
import com.nowgroup.scsee.cat.trafficType.TrafficType;
import com.nowgroup.scsee.cat.transportationMode.TransportationMode;
import com.nowgroup.scsee.cat.unLabel.UnLabel;
import com.nowgroup.scsee.cat.unit.HibernateUnitRepository;
import com.nowgroup.scsee.cat.unit.Unit;
import com.nowgroup.scsee.geo.address.Address;
import com.nowgroup.scsee.geo.country.Country;
import com.nowgroup.scsee.geo.country.HibernateCountryRepository;
import com.nowgroup.scsee.geo.state.GeoState;
import com.nowgroup.scsee.geo.state.HibernateStateRepository;
import com.nowgroup.scsee.inventory.Inventory;
import com.nowgroup.scsee.packingList.HibernatePackingListRepository;
import com.nowgroup.scsee.packingList.PackingList;
import com.nowgroup.scsee.transit.Transit;

/**
 * @author https://github.com/diego-torres
 *
 */
@Configuration
@EntityScan(basePackageClasses = { AssessmentMethod.class, BundleType.class, Company.class, Currency.class,
		CustomsRegime.class, Incoterm.class, Location.class, Part.class, PartEquiv.class, Storage.class, Tariff.class,
		TrafficType.class, TransportationMode.class, Unit.class, UnLabel.class, Inventory.class, Address.class,
		Country.class, Transit.class, PackingList.class, GeoState.class })
@ComponentScan(basePackageClasses = { HibernateCompanyRepository.class, HibernateLocationRepository.class,
		HibernateStorageRepository.class, HibernateUnitRepository.class, HibernatePackingListRepository.class,
		HibernateCountryRepository.class, HibernateStateRepository.class, HibernatePartRepository.class })
public class PersistenceConfig {
	@Value("${spring.datasource.driver-class-name}")
	private String dbDriver;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUser;

	@Value("${spring.datasource.password}")
	private String dbPwd;

	/**
	 * Enable Hibernate session factory based on the JPA entity manager.
	 *
	 * @return
	 */
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}

	/**
	 * Enable a manual dataSource bean to be Autowired to the hibernate
	 * sessionFactory
	 *
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPwd);
		return dataSource;
	}
}
