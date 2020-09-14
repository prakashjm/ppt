package com.xyz.mydiary.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.mydiary.config.JwtTokenUtil;
import com.xyz.mydiary.dto.DiaryDTO;
import com.xyz.mydiary.service.DiaryService;
import com.xyz.mydiary.service.JwtUserDetailsService;
import com.xyz.mydiary.service.MapValidationErrorService;

@RestController
@CrossOrigin
@RequestMapping("/api/diary")
public class DiaryController {


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	
	 @Autowired
	    private MapValidationErrorService mapValidationErrorService;
	 
	 @Autowired
	 private DiaryService diaryService;
	 
	 
	 
	 
		@RequestMapping(value = "/savediary", method = RequestMethod.POST)
		public ResponseEntity<?> saveDiary(@Valid @RequestBody DiaryDTO dto, BindingResult result, Principal principal)  {
			
			  ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		        if(errorMap!=null) return errorMap;

		        dto = diaryService.save(dto);
		        return new ResponseEntity<DiaryDTO>(dto, HttpStatus.CREATED);
			
		}
		
		
		   @GetMapping("/diaryall")
		    public Iterable<DiaryDTO> getAllDiaries(){
			   return diaryService.findByUser();}
	}

