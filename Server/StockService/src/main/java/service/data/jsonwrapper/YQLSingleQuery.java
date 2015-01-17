package service.data.jsonwrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class YQLSingleQuery {
	
	private YQLSingleResult results;

	public YQLSingleResult getResults() {
		return results;
	}

	public void setResults(YQLSingleResult results) {
		this.results = results;
	}
}
