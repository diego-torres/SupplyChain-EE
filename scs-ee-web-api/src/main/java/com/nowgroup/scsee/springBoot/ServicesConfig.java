package com.nowgroup.scsee.springBoot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nowgroup.scsee.service.cat.SpringCompanyService;

@Configuration
@ComponentScan(basePackageClasses = { SpringCompanyService.class })
public class ServicesConfig {

}
