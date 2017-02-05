package com.nowgroup.scsee.springBoot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nowgroup.scsee.cat.company.CompanyRestController;
import com.nowgroup.scsee.controller.rest.cat.StorageRestController;

@Configuration
@ComponentScan(basePackageClasses = { CompanyRestController.class, StorageRestController.class })
public class RestConfig {
}
