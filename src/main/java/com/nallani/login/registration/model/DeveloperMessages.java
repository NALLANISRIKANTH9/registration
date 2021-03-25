package com.nallani.login.registration.model;

import javax.ws.rs.core.Response;

public enum DeveloperMessages {
    INVALID_KEY("100","", Response.Status.BAD_REQUEST),
    INVALID_HEADERS("","",Response.Status.BAD_REQUEST),
    SYSTEM_ERROR("","",Response.Status.BAD_REQUEST);

    private String messageId;
    private String msgkey;
    private Response.Status httpsStatus;

    DeveloperMessages(String messageId, String msgkey, Response.Status httpsStatus) {
        this.messageId = messageId;
        this.msgkey = msgkey;
        this.httpsStatus = httpsStatus;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMsgkey() {
        return msgkey;
    }

    public Response.Status getHttpsStatus() {
        return httpsStatus;
    }
}
