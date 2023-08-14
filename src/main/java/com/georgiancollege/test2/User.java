package com.georgiancollege.test2;

import com.google.gson.annotations.SerializedName;

public class User {
    private int id;
    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("LastName")
    private String lastName;
    private int age;
    private String email;
    @SerializedName("pHoNe")
    private String phone;
    private String birthDate;
    private String university;
    private String image;
    private Address address;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getUniversity() {
        return university;
    }

    public String getImage() {
        return image;
    }

    public Address getAddress() {
        return address;
    }
}
