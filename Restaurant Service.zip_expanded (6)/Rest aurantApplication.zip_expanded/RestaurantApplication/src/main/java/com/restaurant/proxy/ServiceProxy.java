package com.restaurant.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name ="restaurant-application")
@RibbonClient(name="restaurant-application")  
public interface ServiceProxy {
	@GetMapping("/greet")
	public String greeting();
}
