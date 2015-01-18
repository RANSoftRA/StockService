package service.misc;

@SuppressWarnings("serial")
public class TransactionError extends Exception {

	public TransactionError(Type type) {
		super(type.getMessage());
	}
	
	
	
	public enum Type {
		NOT_ENOUGH_MONEY("Not enough Money to buy!"), 
		NOT_ENOUGH_STOCKS("Not enough Stocks available!");
		
		private String message;
		
		Type(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return this.message;
		}
		
	}
}
