package com.currency.conversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exch",url="${CURRENCY-EXCH:localhost:9000}")
public interface ProxyFeign {
	
	@GetMapping("/exchange-rate/{from}/to/{to}")
	public Double getCurrencyExchangeRate(@PathVariable("from") String from, @PathVariable("to") String to);
	
}
