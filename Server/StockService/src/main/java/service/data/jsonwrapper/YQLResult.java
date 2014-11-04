package service.data.jsonwrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class YQLResult {
	
	private List<YQLQuote> quote;
	
	
	public List<YQLQuote> getQuote() {
		return quote;
	}
	
	public void setQuote(List<YQLQuote> quote) {
		this.quote = quote;
	}
	
	
}