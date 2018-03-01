package com.mytaxi;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class MyTaxiOAuth2ResourceConfig extends ResourceServerConfigurerAdapter
{

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
	        .authorizeRequests()
	        .antMatchers(HttpMethod.POST, "/v1/cars/manufacturer").access("hasIpAddress('127.0.0.1')")
	        .antMatchers(HttpMethod.PUT, "/v1/drivers/{\\d+}/activate").access("hasIpAddress('127.0.0.1')")
	        .antMatchers(HttpMethod.PUT, "/v1/drivers/{\\d+}/deactivate").access("hasIpAddress('127.0.0.1')")
	        .antMatchers(HttpMethod.GET, "/v1/drivers").access("hasIpAddress('127.0.0.1')")
	        .antMatchers(HttpMethod.GET, "/v1/drivers/online").access("hasIpAddress('127.0.0.1')")
	        .antMatchers(HttpMethod.GET, "/v1/drivers/offline").access("hasIpAddress('127.0.0.1')")
	        .antMatchers(HttpMethod.POST, "/v1/drivers").permitAll()
	        .and()
	        .authorizeRequests()
	        .antMatchers("/v1/**").authenticated();
         
    }

}
