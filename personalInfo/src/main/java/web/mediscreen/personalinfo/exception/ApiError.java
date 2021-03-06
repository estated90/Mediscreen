package web.mediscreen.personalinfo.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * @author Nicolas
 *
 */
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    /**
     * <p>Default constructor</p>
     */
    public ApiError() {
        super();
    }

    
    /**
     * @param status HTTP status
     * @param message Message from error
     * @param errors List of errors
     */
    public ApiError(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    
    /**
     * @param status HTTP status
     * @param message Message from error
     * @param error The error
     */
    public ApiError(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
    
    
}
