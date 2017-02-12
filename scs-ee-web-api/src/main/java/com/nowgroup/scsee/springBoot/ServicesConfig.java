package com.nowgroup.scsee.springBoot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nowgroup.scsee.cat.company.SpringCompanyService;
import com.nowgroup.scsee.cat.storage.SpringStorageService;
import com.nowgroup.scsee.cat.unit.SpringUnitService;
import com.nowgroup.scsee.geo.country.SpringCountryService;
import com.nowgroup.scsee.packingList.SpringPackingListService;

@Configuration
@ComponentScan(basePackageClasses = { SpringCompanyService.class, SpringStorageService.class, SpringUnitService.class,
		SpringPackingListService.class, SpringCountryService.class })
public class ServicesConfig {

}
