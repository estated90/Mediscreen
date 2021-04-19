package web.mediscreen.personalInfo.exception;

public class PatientNoExistException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

	private final String message;
	
	/**
	 * @param message to return
	 */
	public PatientNoExistException(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
