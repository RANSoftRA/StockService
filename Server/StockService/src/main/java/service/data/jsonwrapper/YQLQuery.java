package service.data.jsonwrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class YQLQuery {

	private YQLResult results;

	public YQLResult getResults() {
		return results;
	}

	public void setResults(YQLResult results) {
		this.results = results;
	}
}
