package com.xyz.mydiary.controlleradvice;
//package com.afs.dms.controllersdvice;
//
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@ControllerAdvice
//public class RestExceptionHandler extends ResponseEntityExceptionHandler {
//
//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		String error = "Malformed JSON request";
//		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
//	}
//
//	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
//		return new ResponseEntity<>(apiError, apiError.getStatus());
//	}
//
////	@Override
////	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
////			HttpStatus status, WebRequest request) {
////		// log.error(ex.getMessage(), ex);
////		FieldError fieldError = ex.getBindingResult().getFieldError();
////		BindingResult result = ex.getBindingResult();
////        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
////		if(null==fieldError) {
////			
////			return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,ex.toString(), ex));
////
////		}
////
////		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,fieldError.getDefaultMessage(), ex));
////
////
////	}
//
//	@ExceptionHandler(value = DataIntegrityViolationException.class)
//	public ResponseEntity<?> handleException(DataIntegrityViolationException e) {
//		// UserResponse response = null;
//		System.out.println("Dddddddd");
//		Map<String, Object> body = new LinkedHashMap<>();
//		body.put("timestamp", new Date());
//		body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
//
//		DataIntegrityViolationException ex = (DataIntegrityViolationException) e;
//		String a = ex.toString();
//
//		body.put("errors", a);
//
//		return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	
////	@ExceptionHandler(MethodArgumentNotValidException.class)
////	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
////	  HttpServletRequest request, MethodArgumentNotValidException ex
////	) {
////	 final Optional<ObjectError> firstError = ex.getBindingResult().getAllErrors().stream().findFirst();
////	 if (firstError.isPresent()) {
////	  final String firstErrorMessage = firstError.get().getDefaultMessage();
////	  return handleError(request, BAD_REQUEST, new BadRequestException(firstErrorMessage));
////	 }
////	 return handleError(request, BAD_REQUEST, ex);
////	}
//
//}