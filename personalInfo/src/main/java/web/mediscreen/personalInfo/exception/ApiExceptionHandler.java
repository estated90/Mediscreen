package web.mediscreen.personalInfo.exception;

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

    /**
     * @param ex Error raised
     * @return the JSON answer
     */
    @ExceptionHandler(PatientException.class)
    public ResponseEntity<Object> handleApiException(
    		PatientException ex) {
    	final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "Patient not Found");
    	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }  
    
    @ExceptionHandler(DateParsingException.class)
    public ResponseEntity<Object> handleApiException(
    		DateParsingException ex) {
    	final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "Date not parsed correctly");
    	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    } 
}
