package com.ntustars.entity;

/**
 Represents administer of overal system.
 This is admin abstract class
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */


public class Admin extends Person {

    /**
     * Creates a admin with username and password
     * @param username The admin's username.
     * @param password The admin's password.
     */

    public Admin(String username, String password){
        super(username,password);
    }
}