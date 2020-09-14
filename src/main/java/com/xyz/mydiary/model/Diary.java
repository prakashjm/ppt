package com.xyz.mydiary.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_diary")
@Getter
@Setter
@NoArgsConstructor

public class Diary {
	
	@Id
	@SequenceGenerator(name = "seq_t_diary", sequenceName = "seq_t_diary", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_t_diary")
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private UserDAO user;
	
	@NotBlank(message = "Subject should be valid")
	@Column(name = "subject", unique = true, nullable = false)
	private String subject;
	
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "doc_date", unique = true, nullable = false)
	private LocalDate diaryDate;
	
	@NotBlank(message = "Subject should be valid")
	@Column(name = "content", unique = true, nullable = false)
	private String content;

}
