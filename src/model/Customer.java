package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    protected final String firstName;
    protected final String lastName;
    protected final String email;

    public Customer(String firstName, String lastName, String email) {
        String emailRegex = "^(.+)@(.+)[.](.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid Email format, please try again");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    // Idea for overriding: https://mkyong.com/java/java-how-to-overrides-equals-and-hashcode/
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email);

    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "First name:" + firstName + " Last name:" + lastName + " Email:" + email;
    }
}
