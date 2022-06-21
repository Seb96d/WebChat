package com.seb.SebBot;

import com.seb.SebBot.entities.QnA;
import com.seb.SebBot.repos.QRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SebBotApplication {
	@Autowired
	QRepo qRepo;

	public static void main(String[] args) {
		SpringApplication.run(SebBotApplication.class, args);
	}

	@Bean
	public void addData(){
		qRepo.save(new QnA( null, "Campus address" ,
				"Address: Aleje Racławickie 14, 20-037 Lublin, Poland" , "Campus" , "CAMPUS"));
		qRepo.save(new QnA( null, "Number of Departments" , "10" , "Department" , "DEPARTMENTS" ));
		qRepo.save(new QnA( null, "hi" , "Hello" , "hi" ,"HI" ));
		qRepo.save(new QnA( null, "hello" , "Hello" , "hi" ,"HI" ));
		qRepo.save(new QnA( null, "bye" , "Hava good day" , "goodbye" ,"GOODBYE" ));
		qRepo.save(new QnA( null, "food" , "There's a cafeteria on our campus" , "food" ,"FOOD" ));
		qRepo.save(new QnA( null, "office" , "Dean's office is open 8-15 Monday to Friday" , "office" ,"OFFICE" ));
	}

}
