package web.mediscreen.personalinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientRetrevalException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

	private final String message;
	
	/**
	 * @param message to return
	 */
	public PatientRetrevalException(String message) {
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
