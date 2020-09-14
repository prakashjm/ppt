package com.xyz.mydiary.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DynamicObject {

	
	private String type;
	private Boolean status;
	private String value;
	private String httpStatus;
	
}
