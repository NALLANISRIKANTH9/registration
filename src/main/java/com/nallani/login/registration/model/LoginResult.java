package com.nallani.login.registration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResult implements Serializable {

    private static final long serialVersionUID = -4632866657782622981L;

    private String transferId;
    private boolean resultType;

    public String getTransferId() { return transferId; }

    public void setTransferId(String transferId) { this.transferId = transferId; }

    public boolean isResultType() {
        return resultType;
    }

    public void setResultType(boolean resultType) {
        this.resultType = resultType;
    }
}
