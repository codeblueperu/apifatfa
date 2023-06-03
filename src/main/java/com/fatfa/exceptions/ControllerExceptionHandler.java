package com.fatfa.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ErrorNotFoundException.class)
	public ResponseEntity<ErrorMessage> manejarResourceNotFoundException(Exception e) {
		HttpStatus status = HttpStatus.NOT_FOUND; // 404
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		// String stackTrace = stringWriter.toString();

		return new ResponseEntity<>(new ErrorMessage(status, e.getMessage()), status);
	}

	@ExceptionHandler(ErrorConflictException.class)
	public ResponseEntity<ErrorMessage> manejarConflictException(Exception e) {
		HttpStatus status = HttpStatus.CONFLICT; // 409
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		// String stackTrace = stringWriter.toString();

		return new ResponseEntity<>(new ErrorMessage(status, e.getMessage()), status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception, WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public  ResponseEntity<ErrorDetalles> databaseError(SQLException exception, WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getCause().toString(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String nombreCampo = ((FieldError) error).getField();
			String mensaje = error.getDefaultMessage();

			errores.put(nombreCampo, mensaje);
		});

		return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
	}
	
	 @ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<ErrorMessageStorageFile> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ErrorMessageStorageFile("Fallo al cargar el archivo!"));
	  }

}
