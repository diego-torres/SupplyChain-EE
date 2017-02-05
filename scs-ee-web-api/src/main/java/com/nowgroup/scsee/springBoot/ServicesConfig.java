package com.nowgroup.scsee.springBoot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nowgroup.scsee.cat.company.SpringCompanyService;
import com.nowgroup.scsee.service.cat.SpringStorageService;

@Configuration
@ComponentScan(basePackageClasses = { SpringCompanyService.class, SpringStorageService.class })
public class ServicesConfig {

}
