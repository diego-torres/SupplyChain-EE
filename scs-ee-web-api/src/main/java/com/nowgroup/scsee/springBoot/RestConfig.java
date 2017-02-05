package com.nowgroup.scsee.springBoot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nowgroup.scsee.cat.company.CompanyRestController;
import com.nowgroup.scsee.cat.storage.StorageRestController;
import com.nowgroup.scsee.cat.unit.UnitRestController;

@Configuration
@ComponentScan(basePackageClasses = { CompanyRestController.class, StorageRestController.class,
		UnitRestController.class })
public class RestConfig {
}
