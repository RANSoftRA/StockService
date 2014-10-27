package service.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.rest.data.Stock;

@RestController
public class StockController {
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public Stock justReturnSomething() {
		return new Stock("Test Stock was successful!", 100.0);
	}

}
