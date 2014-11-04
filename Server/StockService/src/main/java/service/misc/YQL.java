package service.misc;

public class YQL {

	public final String serviceUrlPrefix = "https://query.yahooapis.com/v1/public/yql?q=";
	public final String serviceUrlPostfix = "&diagnostics=true&env=store://datatables.org/alltableswithkeys&callback=";
	public final String[] stocks = new String[] { "YHOO", "AAPL", "GOOG", "MSFT", "AGU",
			"UAN", "SAP", "SOW.DE", "PCMI", "PETM" };
	
	
	public static String getUriSelectAllStocks() {
		
		
		
		return null;
	}
	
	public interface Queries {
		String selectAllStocks = "select * from yahoo.finance.quote where symbol in (%s)";
	}
}
