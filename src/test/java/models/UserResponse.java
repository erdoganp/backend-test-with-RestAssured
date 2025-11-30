package models;

import lombok.Data;

@Data
public class UserResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String gender;
}
