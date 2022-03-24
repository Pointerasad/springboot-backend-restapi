package com.springboot.restapi.exception;

/**
 * Created by User on 3/23/2022.
 */


public class BadRequestExceptionHandler extends RuntimeException{

    public BadRequestExceptionHandler(String message){
        super(message);
    }

}
