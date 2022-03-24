package com.springboot.restapi.enums;

/**
 * Created by User on 3/23/2022.
 */
public enum MessageContainer {


    MESSAGE_SAVE_SUCCESS("Success", "Successfully saved"),
    MESSAGE_UPDATE_SUCCESS("Success", "Successfully updated"),
    MESSAGE_DELETE_SUCCESS("Success", "Successfully deleted"),
    MESSAGE_IS_EXIST("Id", "User Id is not found in user table"),
    MESSAGE_IS_FOUND_NULL("Id", "UserId/ Foreign Key is found null "),
    MESSAGE_DATA_EXIST("Data", "Data is not available"),
    MESSAGE_FOREIGN_KEY_EXIST("Key", "A User can not have multiple user progress / score");

    private final String messageTitle;
    private final String messageDetails;

    private MessageContainer(String messageTitle, String messageDetails) {
        this.messageTitle = messageTitle;
        this.messageDetails = messageDetails;
    }

    public String getMessageDetails() {
        return messageDetails;
    }

    public String getMessageTitle() {
        return messageTitle;
    }


}
