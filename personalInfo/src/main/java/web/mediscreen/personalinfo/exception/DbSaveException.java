package web.mediscreen.personalinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DbSaveException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

	private final String message;
	
	/**
	 * @param message to return
	 */
	public DbSaveException(String message) {
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
