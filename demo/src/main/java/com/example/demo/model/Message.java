package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//Message class that will function as a model for the message application
public class Message  {
    //Variables from the message object
    private final String message;
    private final String date;
    private final int id;
    private final int flag;

    //Define the variables as JsonProperty so they can be parsed as Json
    public Message(@JsonProperty("message") String message,
                   @JsonProperty("date") String date,
                   @JsonProperty("id") int id,
                   @JsonProperty("flag") int flag) {
        this.message = message;
        this.date = date;
        this.id = id;
        this.flag = flag;
    }

    //Get Methods for each variable in the message object
    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public int getFlag() {
        return flag;
    }

    public int getId() {
        return id;
    }
}
