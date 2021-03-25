package com.nallani.login.registration.service.exception;

import com.nallani.login.registration.model.DeveloperMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component(value = "exceptionalHandler")
public class ExceptionHandler {

    private void businessException(DeveloperMessages developerMessages, String developertText){
    }
}
