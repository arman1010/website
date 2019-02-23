package model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String picUrl;
}


