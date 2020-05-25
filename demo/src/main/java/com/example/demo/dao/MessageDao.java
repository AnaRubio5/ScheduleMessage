package com.example.demo.dao;
import com.example.demo.model.Message;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

//Interface for Message functionality
public interface MessageDao {
    //Insert a message using a text and a date
    int insertMessage(Message message, String date);
    //Initializing the DateTimeFormatter with an specific format
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    //Insert a message receiving only a text
    default int insertMessage (Message message){
        //Format actual date and time
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        return insertMessage(message, date);
    }

    //Select All Messages method
    List<Message> selectAllMessages();

    //Recover Messages method
    default void recoverMessages(){
    }

    //Scheduled Messages method
    default void scheduleMessage(){
    }

}
