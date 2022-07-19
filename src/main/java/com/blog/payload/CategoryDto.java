package com.blog.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min = 3 ,  message = "Min size of category title is 3")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 8, message = "Min size of the category description is 8")
	private String categoryDescription;
}
