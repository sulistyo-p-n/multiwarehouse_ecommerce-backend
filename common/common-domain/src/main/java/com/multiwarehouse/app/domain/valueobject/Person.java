package com.multiwarehouse.app.domain.valueobject;

import java.util.Date;
import java.util.Objects;

public class Person {
    private final String firstname;
    private final String lastname;
    private final Date dateOfBirth;

    public Person(String firstname, String lastname, Date dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(firstname, person.firstname) && Objects.equals(lastname, person.lastname) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, dateOfBirth);
    }
}
