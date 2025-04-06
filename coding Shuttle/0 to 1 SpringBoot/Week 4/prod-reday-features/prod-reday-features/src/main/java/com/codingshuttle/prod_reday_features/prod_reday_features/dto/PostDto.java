package com.codingshuttle.prod_reday_features.prod_reday_features.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import com.codingshuttle.prod_reday_features.prod_reday_features.entity.AutableBaseClass;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto extends AutableBaseClass {
	
	private Long id;
	
	@NotBlank(message = "Title is Required")
	@NotEmpty(message = "Title cant be empty")
	private String title;
	
	@NotBlank(message = "Description is Required")
	private String description;
	

}
