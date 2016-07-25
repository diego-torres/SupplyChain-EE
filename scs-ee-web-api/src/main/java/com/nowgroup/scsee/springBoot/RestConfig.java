package com.nowgroup.scsee.springBoot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nowgroup.scsee.controller.rest.cat.CompanyRestController;

@Configuration
@ComponentScan(basePackageClasses = { CompanyRestController.class })
public class RestConfig {
}
