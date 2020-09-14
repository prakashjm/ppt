package com.xyz.mydiary.controlleradvice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    // API

    // 400

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific1";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific2";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific3";
        // ex.getCause() instanceof JsonMappingException, JsonParseException // for additional information later on
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    	Map<String, Object> body = new LinkedHashMap<>();
        //final String bodyOfResponse = ex.getBindingResult().getGlobalError().getDefaultMessage();//"This should be application specific";
        String bodyOfResponse = "error";//ex.getBindingResult().getGlobalError().getDefaultMessage();//"This should be application specific";
        ObjectError objectError = ex.getBindingResult().getGlobalError();
        if(null!=objectError) {
        	bodyOfResponse = objectError.getDefaultMessage();
        }
        else {
        	FieldError fieldError = ex.getBindingResult().getFieldError();
        	bodyOfResponse = fieldError.getDefaultMessage();
        }
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        body.put("errors", bodyOfResponse);
        BindingResult result = ex.getBindingResult();
        
        
        
           //System.out.println(ex.getBindingResult().getGlobalError().getDefaultMessage());
           
       // return error;
       // .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
        //return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // 404

    @ExceptionHandler(value = { EntityNotFoundException.class, MyResourceNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific4";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409

    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific5";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 412

    // 500

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    /*500*/public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
      //  final String bodyOfResponse = "This should be application specific6";
      //  return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        String bodyOfResponse = ex.getLocalizedMessage();//"error";//ex.getBindingResult().getGlobalError().getDefaultMessage();//"This should be application specific";
//        ObjectError objectError = ex.getLocalizedMessage()
//        	bodyOfResponse = objectError.getDefaultMessage();
//        }
//        else {
//        	FieldError fieldError = ex.getBindingResult().getFieldError();
//        	bodyOfResponse = fieldError.getDefaultMessage();
//        }
    	Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", new Date());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        body.put("errors", bodyOfResponse);
       // BindingResult result = ex.getBindingResult();
        
        
        
           //System.out.println(ex.getBindingResult().getGlobalError().getDefaultMessage());
           
       // return error;
       // .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
        //return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
    @ExceptionHandler({ ResponseStatusException.class })
    protected ResponseEntity<Object> handleConflict(final ResponseStatusException ex, final WebRequest request) {
    	Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", new Date());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        final String bodyOfResponse = ex.getLocalizedMessage();

        body.put("errors", bodyOfResponse);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        //return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    
    

}