package model;

import java.util.regex.Pattern;

public class Customer {
    protected String firstName;
    protected String lastName;
    protected String email;

    public Customer(String firstName, String lastName, String email) {
        String emailRegex = "^(.+)@(.+)[.](.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid Email");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "First name:" + firstName + " Last name:" + lastName + " Email:" + email;
    }
}
