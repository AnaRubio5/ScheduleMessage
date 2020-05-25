Readme 

Software Prerequisites: 
- Postgresql installed and create database with name demodb
- Postman installed
- Maven installed
- Java installed
- Eclipse or IntelliJ IDE installed (Or any IDE of your preference)

Instructions for running: 
- Create database with name demodb in postgresql
- Use username:postgres and password:password
- Use default port 5432
- Open your IDE and import the project from Git, allow Maven to update all dependencies
- Java 8 was used to build this application, if you have a different version update it accordingly in the file pom.xml
- Run the application
- Open postman, select post method and enter the URL below. 
POST method URL: localhost:8080/api/v1/message
- Send messages using Json format as in the following example: 
{
	"message": "Scheduled another time",
	"date": "2020/05/25 12:14"
}
- Date format used in this application is YYYY/MM/dd HH:mm 
- Go back to your IDE at the date and time you selected to view the scheduled message displayed in the console
- You can see all the messages in the database using Postman and selecting a GET method and using the URL: localhost:8080/api/v1/message
- You can stop the application after sending a few POST requests for new messages, you can run the application after the
scheduled time for the messages sent and they should be recovered and displayed in console. 
- You can send more than one message at the same date and time

