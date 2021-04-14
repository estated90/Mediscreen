package web.mediscreen.personalinfo.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Nico
 *         <p>
 *         Exception handler for global API exception
 *         </p>
 *
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 400

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
	    final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
	logger.info(ex.getClass().getName());
	//
	final List<String> errors = new ArrayList<>();
	for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
	    errors.add(error.getField() + ": " + error.getDefaultMessage());
	}
	for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	    errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	}
	final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    //

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
	    final WebRequest request) {
	logger.info(ex.getClass().getName());
	//
	final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

	final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // 405

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
	    final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
	    final WebRequest request) {
	logger.info(ex.getClass().getName());
	//
	final StringBuilder builder = new StringBuilder();
	builder.append(ex.getMethod());
	builder.append(" method is not supported for this request. Supported methods are ");
	ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

	final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(),
		builder.toString());
	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // 415

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
	    final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
	logger.info(ex.getClass().getName());
	//
	final StringBuilder builder = new StringBuilder();
	builder.append(ex.getContentType());
	builder.append(" media type is not supported. Supported media types are ");
	ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

	final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(),
		builder.substring(0, builder.length() - 2));
	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // 500

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
	logger.info(ex.getClass().getName());
	logger.error("error", ex);
	//
	final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),
		"error occurred");
	return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
