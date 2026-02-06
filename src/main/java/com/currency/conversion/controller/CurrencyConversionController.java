package com.currency.conversion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currency.conversion.ProxyFeign;
import com.currency.conversion.dto.CurrencyConversion;
import com.currency.conversion.kafka.MessageProducer;
import com.currency.conversion.model.ConversionModel;

@RestController
@RequestMapping("/currency-conversion/{from}/to/{to}/quantity/{quantity}")
public class CurrencyConversionController {
	
	@Value("${messages:default}")
	private String msg;
	
	@Autowired
	public RestTemplate restTemplate;
	
	@Autowired
	public ProxyFeign proxyFeign;
	
	private Logger myLogger = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	private MessageProducer producer;
	
	private final Integer MIN=1;
	private final Integer MAX=1000;
	
	@GetMapping
	public CurrencyConversion getCurrencyConversion(@PathVariable("from") String from,@PathVariable("to") String to, @PathVariable String quantity) {
		/**
		System.out.print(from+" *******************  "+to);
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from",from);
		uriVariables.put("to", to);
		Double conversionRate = restTemplate.getForObject("http://currency-exchange/exchange-rate/{from}/to/{to}", Double.class,uriVariables);
		*/
		System.out.println("   ------->"+msg+",<-------   ");
		myLogger.info("getCurrencyConversion  is being called  ");
		Double conversionRate = proxyFeign.getCurrencyExchangeRate(from, to);
		Double totalAmount = Integer.parseInt(quantity)*conversionRate;
		/**
		 * Kafka create object and called messageProducer 
		 */
		int randomInt = MIN + (int)(Math.random() * (MAX - MIN + 1));

		String id = from+randomInt+to;
		ConversionModel model = new ConversionModel(id, from, to, Integer.parseInt(quantity), conversionRate, totalAmount);
		producer.createMessage(model);
		
		myLogger.info("Message created in Kafka");
		
		return new CurrencyConversion(from, to, Integer.parseInt(quantity), conversionRate, totalAmount);
	}

}
