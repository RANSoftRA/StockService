package service.rest.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import service.data.Quote;
import service.misc.YQL;

@RestController
@RequestMapping("/finance")
public class FinanceController {

	@RequestMapping(value = "/stocks", method = RequestMethod.GET)
	public List<Quote> getStocks() {

		RestTemplate template = new RestTemplate();

		List<Quote> quotes = new ArrayList<>();
		
		for(String stock : YQL.stocks) {
			
			
			try {
				Quote quote;
				quote = template.getForObject(URLEncoder.encode(YQL.serviceUrl
						+ "select * from yahoo.finance.quote where symbol in (\""
						+ stock + "\")&format=json", "UTF-8"), Quote.class);
				quotes.add(quote);
			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return quotes;
	}

}
