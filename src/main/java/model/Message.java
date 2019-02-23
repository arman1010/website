package model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private int id;
    private User fromId;
    private User toId;
    private String text;
    private Date date;
}
