package com.xyz.mydiary.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xyz.mydiary.config.JwtTokenUtil;
import com.xyz.mydiary.dto.DiaryDTO;
import com.xyz.mydiary.dto.UserDTO;
import com.xyz.mydiary.event.OnRegistrationCompleteEvent;
import com.xyz.mydiary.model.DynamicObject;
import com.xyz.mydiary.model.JwtRequest;
import com.xyz.mydiary.model.JwtResponse;
import com.xyz.mydiary.model.UserDAO;
import com.xyz.mydiary.service.DiaryService;
import com.xyz.mydiary.service.JwtUserDetailsService;
import com.xyz.mydiary.service.MapValidationErrorService;



@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class JwtAuthenticationController {

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
	 @Autowired
	 ApplicationEventPublisher eventPublisher;
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		
		
		

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	} 
	 
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO user, BindingResult result,  HttpServletRequest request,Principal principal)  {
		
		  ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
	        if(errorMap!=null) return errorMap;

 	        
	        
	        try {
		        user = userDetailsService.save(user);
	            UserDAO registered= userDetailsService.covertUserDTOToDAO(user);
	            String appUrl = request.getContextPath();
	            //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, 
	              //request.getLocale(), appUrl));
		        return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);

	        
	        } catch (RuntimeException ex) {
 		        return new ResponseEntity<String>("RUN TIME ERROR", HttpStatus.BAD_REQUEST);

	        }
		
		//return ResponseEntity.ok(userDetailsService.save(user),HttpStatus.CREATED);
	}
	
	
	
	@Secured("ROLE_ACCESS")
	@RequestMapping(value = "/checkusername", method = RequestMethod.GET)
	public ResponseEntity<?> checkUserName(@RequestBody String userName)  {
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> map = springParser.parseMap(userName);
		//String value = null;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("userName")) {
			//	 value = entry.getValue().toString();
			}
		}
		DynamicObject obj = new DynamicObject();
	//	obj.setType("B");
	//	obj.setStatus(userDetailsService.findByUserName(value));
		return ResponseEntity.ok(obj);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	@RequestMapping(value = "/savediary", method = RequestMethod.POST)
	public ResponseEntity<?> saveDiary(@Valid @RequestBody DiaryDTO dto, BindingResult result, Principal principal)  {
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		  ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
	        if(errorMap!=null) return errorMap;

	        dto = diaryService.save(dto);
	        return new ResponseEntity<DiaryDTO>(dto, HttpStatus.CREATED);
		
		//return ResponseEntity.ok(userDetailsService.save(user),HttpStatus.CREATED);
	}
	
	
	   @GetMapping("/diaryall")
	    public Iterable<DiaryDTO> getAllDiaries(){
		   return diaryService.findByUser();}
}