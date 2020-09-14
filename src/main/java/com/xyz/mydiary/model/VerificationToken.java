package com.xyz.mydiary.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "m_verification_token")

@Getter
@Setter
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;
 
	@Id
	@SequenceGenerator(name = "seq_m_ver_token", sequenceName = "seq_m_ver_token", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_m_ver_token")
	@Column(name = "id")
	private Long id;
    
    
    
    private String token;
  
    @OneToOne(targetEntity = UserDAO.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserDAO user;
    
    @Column(name="expiry_date")
    private LocalDate expiryDate;
   
    public VerificationToken(String token2, UserDAO user2) {
    	this.token = token2;
    	this.user=user2;
}

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
    
    // standard constructors, getters and setters
}
