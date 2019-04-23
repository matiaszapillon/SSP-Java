package util.customizedEceptions;

public class AppDataException {
	
	private Throwable innerException;
	private String message;
		
	// Getters and Setters
	public Throwable getInnerException() {
		return innerException;
	}

	public void setInnerException(Throwable innerException) {
		this.innerException = innerException;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	// Constructor
	public AppDataException(Throwable e, String message) {
		this.setInnerException(e);
		this.setMessage(message);
	}

}
