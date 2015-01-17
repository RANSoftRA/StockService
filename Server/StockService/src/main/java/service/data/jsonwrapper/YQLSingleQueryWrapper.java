package service.data.jsonwrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class YQLSingleQueryWrapper {
	
	private YQLSingleQuery query;

	public YQLSingleQuery getQuery() {
		return query;
	}

	public void setQuery(YQLSingleQuery query) {
		this.query = query;
	}
}
