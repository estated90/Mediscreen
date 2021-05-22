package web.mediscreen.personalinfo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Nicolas
 * <p>Exception handler for custom exception</p>
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(PatientExistException.class)
    public ResponseEntity<Object> handleApiException(
	    PatientExistException ex) {
    	final var apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "This patient is already in db");
    	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    } 
    
    @ExceptionHandler(PatientNoExistException.class)
    public ResponseEntity<Object> handleApiException(
	    PatientNoExistException ex) {
    	final var apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "This patient do not exist in db");
    	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    } 
}
