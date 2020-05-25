package com.example.demo;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Timer;
import java.util.TimerTask;

//Spring boot application main class
@SpringBootApplication
public class ScheduleApplication extends TimerTask {

	//Instances for JdbcTemplate and MessageService class
	public static JdbcTemplate jdbcTemplate;
	public static MessageService messageService;

	//Constructors for JdbcTemplate and MessageService class
	@Autowired
	public ScheduleApplication(JdbcTemplate jdbcTemplate, MessageService messageService) {
		this.jdbcTemplate = jdbcTemplate;
		this.messageService = messageService;
	}

	public ScheduleApplication(){

	}

	public static void main(String[] args)  {
		SpringApplication.run(ScheduleApplication.class, args);
		//Call to the recover messages method once the application is run
		messageService.recoverMessages();
		//Created a timer so we can call the schedule message method every minute
		Timer timer = new Timer();
		timer.schedule(new ScheduleApplication(jdbcTemplate, messageService), 0,60000);
	}

	@Override
	public void run() {
		//Call to the schedule Message method
		messageService.scheduleMessage();
	}

}
