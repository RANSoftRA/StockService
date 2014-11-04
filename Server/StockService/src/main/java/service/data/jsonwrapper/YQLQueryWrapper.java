package service.data.jsonwrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class YQLQueryWrapper {
	
	private YQLQuery query;

	public YQLQuery getQuery() {
		return query;
	}

	public void setQuery(YQLQuery query) {
		this.query = query;
	}
}
