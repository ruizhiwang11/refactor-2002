package com.ntustars.entity;
/**
 Represents a person in the school, it may be student or admin
 @author MA YIHENG
 @version 1.0
 @since 2020-11-10
 */
public abstract class Person {
    /**
     * The Username of the Person.
     */
    protected String username;
    /**
     * The Password of the Person.
     */
    protected String password;
    /**
     * The default constructor.
     */
    public Person()
    {

    }
    /**
     * Creates a new person with username and password
     * @param username This person's username.
     * @param password This person's password.
     */
    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * gets username of this person.
     * @return this person's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username of this person.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * gets password of this person.
     * @return this person's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * set password of this person.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}