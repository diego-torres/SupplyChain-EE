package com.nowgroup.scsee.springBoot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nowgroup.scsee.cat.company.SpringCompanyService;
import com.nowgroup.scsee.cat.storage.SpringStorageService;
import com.nowgroup.scsee.cat.unit.SpringUnitService;

@Configuration
@ComponentScan(basePackageClasses = { SpringCompanyService.class, SpringStorageService.class, SpringUnitService.class })
public class ServicesConfig {

}
