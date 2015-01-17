package service.data.jsonwrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class YQLSingleResult {
	
	private YQLQuote quote;

	public YQLQuote getQuote() {
		return quote;
	}

	public void setQuote(YQLQuote quote) {
		this.quote = quote;
	}
	
	
	
}
