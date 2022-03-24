package com.springboot.restapi.enums;

/**
 * Created by User on 3/23/2022.
 */
public enum ErrorMessageContainer {

    MESSAGE_TYPE_BAD_REQUEST("Bad Request", "Request is not proper because %s is missing"),
    MESSAGE_TYPE_DUPlICATE_ENTRY("Duplicate Entry", "Already exist %s"),
    MESSAGE_TYPE_NULL_POINTER_EXCEPTION("Internal Server Error", "Server interupted internally because %s is empty"),
    MESSAGE_TYPE_RESOURCE_NOT_FOUND("Resource Not Found", "No Data found with %s"),
    MESSAGE_TYPE_UNAUTHORIZED("Unauthorized", "Unauthorized request with username %s"),
    MESSAGE_TYPE_SOMETHIN_WRONG("Something went wrong", "Something went wrong %s"),
    MESSAGE_TYPE_FOREIGN_KEY_NOT_FOUND("UserId is not Found , Please check your user table id", "UserId is not Found , Please check your user table id"),;

    private final String messageTitle;
    private final String messageDetails;

    private ErrorMessageContainer(String messageTitle, String messageDetails) {
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
