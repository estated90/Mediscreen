package web.mediscreen.historic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final String message;

    /**
     * @param message to return
     */
    public PatientNotFoundException(String message) {
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
