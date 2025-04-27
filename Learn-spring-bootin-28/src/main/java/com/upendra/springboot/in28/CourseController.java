package com.upendra.springboot.in28;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

	@RequestMapping("/courses")
	public List<course>retriveAllCourses(){
		return Arrays.asList(
				new course(1,"Aws","in nareshIt"),
				new course(2,"Java","Sssit"),
				new course(3,"Devops","in coursera")
				);
	}
	
}
