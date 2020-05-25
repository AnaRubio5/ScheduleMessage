package com.example.demo.api;
import com.example.demo.model.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//URL for REST web service request
@RequestMapping ("api/v1/message")

//Indicate SpringBoot this is a REST controller class
@RestController
public class MessageController {
    //MessageService class new instance
    private final MessageService messageService;

    //MessageService class constructor
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    //Add new message POST method
    @PostMapping
    public void addMessage(@RequestBody Message message){
        messageService.addMessage(message);
    }

    //Get all messages GET method
    @GetMapping
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

}
