package com.vmware.bookmanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info= @Info(title= "Book Management API",
		version = "1.0",
		description = "online book store API’s, where user would be able to to perform below\n" +
				"operations.\n" +
				"1:Add the book\n" +
				"2:Delete the book\n" +
				"3:Get book by id\n" +
				"4:Get books by particular category (Technical, Literature, Humour, Poetry, Science\n" +
				"Fiction)\n" +
				"5:Get books by Author name\n" +
				"6:Get all books\n" +
				"7:Search book by name"))
public class BookManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementApplication.class, args);
	}

}
