package com.xyz.mydiary.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryDTO {
	 
	private Long id;
	
	 
	
	@NotBlank(message = "Subject should be valid")
 	private String subject;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 	private LocalDate diaryDate;
	
	@NotBlank(message = "Subject should be valid")
 	private String content;
}
