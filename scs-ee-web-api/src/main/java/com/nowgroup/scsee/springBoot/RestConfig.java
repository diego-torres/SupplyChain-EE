package com.nowgroup.scsee.springBoot;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.nowgroup.scsee.authentication.AuthenticationRestController;
import com.nowgroup.scsee.cat.company.CompanyRestController;
import com.nowgroup.scsee.cat.part.PartRestController;
import com.nowgroup.scsee.cat.storage.StorageRestController;
import com.nowgroup.scsee.cat.unit.UnitRestController;
import com.nowgroup.scsee.geo.country.CountryRestController;
import com.nowgroup.scsee.geo.state.GeoStateRestController;
import com.nowgroup.scsee.packingList.PackingListRestController;

@Configuration
@ComponentScan(basePackageClasses = { CompanyRestController.class, StorageRestController.class,
		UnitRestController.class, PackingListRestController.class, AuthenticationRestController.class,
		CountryRestController.class, GeoStateRestController.class, PartRestController.class })
public class RestConfig {
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("HEAD");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
}
