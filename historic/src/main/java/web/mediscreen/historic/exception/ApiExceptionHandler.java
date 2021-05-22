package web.mediscreen.historic.exception;

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
    
    @ExceptionHandler(HistoryNotFoundException.class)
    public ResponseEntity<Object> handleApiException(
	    HistoryNotFoundException ex) {
    	final var apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "No patient history returned");
    	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    } 
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Object> handleApiException(
	    PatientNotFoundException ex) {
    	final var apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "No patient in DB with that id");
    	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    } 
}
