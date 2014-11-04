package service.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class YQLQuery {

	private Results results;
	
	
	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}


	
	
}
