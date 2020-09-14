package com.xyz.mydiary.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.xyz.mydiary.model.UserDAO;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appUrl;
    private Locale locale;
    private UserDAO user;
 
    public OnRegistrationCompleteEvent(
    		UserDAO user, Locale locale, String appUrl) {
        super(user);
        
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
    
    // standard getters and setters
}