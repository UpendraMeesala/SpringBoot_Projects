package com.example.microservices;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion retrieveCurrencyConvertedValues(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
		HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		
		String baseUrl = "http://localhost:8000";
		ResponseEntity<CurrencyConversion> responseBody = new RestTemplate()
				.getForEntity(baseUrl + "/currency-exchange/from/{from}/to/{to}"
				, CurrencyConversion.class, uriVariables );
		CurrencyConversion currencyConversion = responseBody.getBody();
		
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity
				 ,currencyConversion.getConversionMultiple()
				 ,quantity.multiply(currencyConversion.getConversionMultiple())
				 ,currencyConversion.getEnvironment() + "" + "by restTemplate");
	}
	
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion retrieveCurrencyConvertedValuesFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
		
		CurrencyConversion currencyConversion  = proxy.retriveExchangeValue(from, to);
		
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity
				 ,currencyConversion.getConversionMultiple()
				 ,quantity.multiply(currencyConversion.getConversionMultiple())
				 ,currencyConversion.getEnvironment() + "" + "by feign");
	}
}
