package com.xyz.mydiary.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Service
public class MapValidationErrorService {

	public ResponseEntity<?> MapValidationService(BindingResult result) {

		if (result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}

			if (0 == errorMap.size()) {
				String objectErrors = "";
				for (ObjectError error : result.getAllErrors()) {
					objectErrors = objectErrors + error.getDefaultMessage();
				}
				errorMap.put("OBJERROR", objectErrors);
			}

			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}

		return null;

	}
}
