package com.ntustars.controller;

/**
 Controller to control all the student related operation.
 @author FENG HAOLIN
 @version 1.0
 @since 2020-11-10
 */

public abstract class SendNotificatoin {
    /**
     * The email sender
     */
    protected static String usernameFrom;
    /**
     * The email receiver
     */
    protected static String usernameTo;

    /**
     * Initialize the email receiver and sender
     * @param usernameFrom email address of the sender
     * @param usernameTo email address of the receiver
     */
    public SendNotificatoin(String usernameFrom, String usernameTo){
        SendNotificatoin.usernameFrom = usernameFrom;
		SendNotificatoin.usernameTo = usernameTo ;
    }

    /**
    send notification to student
     */
    public abstract void sendNotificationsToStudent();
}
