package com.example.demo.dao;
import com.example.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//Indicate postgres is the Repository
@Repository("postgres")
public class MessageDataAccessService implements MessageDao {

    //Jdbc template instance
    private final JdbcTemplate jdbcTemplate;

    //Jdbc template constructor
    @Autowired
    public MessageDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Method to insert Message
    @Override
    public int insertMessage(Message message, String date) {
        //New SimpleDateFormat instance with a specific format
        SimpleDateFormat stf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
        stf.setLenient(false);
        Date validDate;
        String finalDate;
        String add;
        int flag = 0;
        if (message.getDate() == null){
            //SQL Query if no date was specified by user
            add = "INSERT INTO message (message,date,flag) VALUES ('" + message.getMessage() + "','" + date + "',"+flag+")";
            jdbcTemplate.update(add);
        } else {
            //Validate date input by user
            try{
                validDate = stf.parse(message.getDate());
                finalDate = stf.format(validDate);
                //SQL Query after if user entered a valid date
                add = "INSERT INTO message (message,date,flag) VALUES ('" + message.getMessage() + "','" + finalDate + "',"+flag+")";
                jdbcTemplate.update(add);
            } catch (IllegalArgumentException | ParseException exception){
                System.out.println("Date input not valid");
            }
        }
        return 0;
    }
    //Method to display all messages
    @Override
    public List<Message> selectAllMessages() {
        //SQL Query to select all messages
        String sql = "SELECT message, date, id, flag FROM message";
        //List created with all messages retrieved
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message(
                    resultSet.getString("message"),
                    resultSet.getString("date"),
                    resultSet.getInt("id"),
                    resultSet.getInt("flag"));
        });
        return messages;
    }

    //Method to recover previous messages not displayed
    @Override
    public void recoverMessages(){
        //Get actual time in order to retrieve previous messages not displayed
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        //SQL to select previous messages not displayed
        String sql = "SELECT message, date, id, flag FROM message WHERE date<='"+date+"' AND flag=0";
        //List created with all messages retrieved
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message(
                    resultSet.getString("message"),
                    resultSet.getString("date"),
                    resultSet.getInt("id"),
                    resultSet.getInt("flag"));
        });
        if (messages.size()==0){
            //If no messages were from a previous date and not displayed sent a message to console
            System.out.print(date + ": ");
            System.out.println("There are no recovered messages");
        } else {
            //Print every message recovered
            for(int i = 0; i<messages.size(); i++){
                String text = messages.get(i).getMessage();
                System.out.print(messages.get(i).getDate() + ": ");
                System.out.println(text);
            }
            //Update the flag to indicate the messages were now recovered and displayed
            String updatesql= "UPDATE message SET flag=1 WHERE date<='"+date+"' AND flag=0";
            jdbcTemplate.update(updatesql);
        }
    }

    //Method to schedule a message
    @Override
    public void scheduleMessage(){
        //Get actual date and time
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        //SQL query to select message that has actual date
        String sql = "SELECT message, date, id, flag FROM message WHERE date='"+date+"'";
        //Get all messages that correspond to the actual date
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message(
                    resultSet.getString("message"),
                    resultSet.getString("date"),
                    resultSet.getInt("id"),
                    resultSet.getInt("flag"));
        });
        //If no message was found, display a message in console
        if (messages.size()==0){
            System.out.print(date + ": ");
            System.out.println("No message scheduled at this time");
        } else {
            //Print every message that is schedule at the actual time
            for(int i = 0; i<messages.size(); i++) {
                String text = messages.get(i).getMessage();
                int idtoupdate = messages.get(i).getId();
                System.out.print(date + ": ");
                System.out.println(text);
                //Update the flag to avoid this message is retrieved later on
                String updatesql = "UPDATE message SET flag=1 WHERE id=" + idtoupdate;
                jdbcTemplate.update(updatesql);
            }
        }
    }
}
