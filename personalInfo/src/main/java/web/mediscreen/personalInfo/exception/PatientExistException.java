package web.mediscreen.personalInfo.exception;

public class PatientExistException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final String message;

    /**
     * @param message to return
     */
    public PatientExistException(String message) {
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
