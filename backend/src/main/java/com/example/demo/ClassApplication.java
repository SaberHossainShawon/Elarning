package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.context.annotation.Bean;

// import com.example.demo.entity.Student;
// import com.example.demo.repository.StudentRepostitory;
// import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassApplication.class, args);

		//ata on hole upe ar ta off kore dite hobe------------------------
		// ConfigurableApplicationContext context = SpringApplication.run(ClassApplication.class, args);
		// StudentRepostitory studentRepostitory = context.getBean(StudentRepostitory.class);
		// for (int i = 0; i < Integer.MAX_VALUE; i++) {
		// 	try {
		// 		Student student = new Student("Shawon"+i, "Dhaka"+i, "23"+i, "01711111111"+1, null,null , null);
		// 		studentRepostitory.save(student);
		// 	} catch (Exception e) {

		// 	}

		// }
	}

	// @Bean
	// public ObjectMapper ObjectMapper() {
	// 	return new ObjectMapper();
	// }

}
