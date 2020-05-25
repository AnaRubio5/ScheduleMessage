package com.example.demo.service;

import com.example.demo.dao.MessageDao;
import com.example.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    private final MessageDao messageDao;

    @Autowired
    //Define MessageDao class as a postgres repository
    public MessageService(@Qualifier("postgres") MessageDao messageDao) {
        this.messageDao = messageDao;
    }
    //Add message method call
    public int addMessage(Message message) {
        return messageDao.insertMessage(message);
    }

    //Get all messages method call
    public List<Message> getAllMessages() {
        return messageDao.selectAllMessages();
    }

    //Recover messages method call
    public void recoverMessages() {
        messageDao.recoverMessages();
    }

    //Schedule message method call
    public void scheduleMessage() {
        messageDao.scheduleMessage();
    }

}
