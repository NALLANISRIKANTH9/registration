package com.nallani.login.registration.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Objects;

@JsonDeserialize(builder = ClientRequestInfo.Builder.class)
public class ClientRequestInfo implements Serializable
{

    private static final long serialVersionUID = -5583714200546883410L;

    private final SessionToken       sessionToken; // for pdSessionId and deviceToken
    private final String             loginSessionId;
    private final String             userAgent;
    private final String             ipAddress;   // client's ip address
    private final String             applicationName;
    private final String             applicationView;
    private final String             mobileSdkData;


    private ClientRequestInfo(
            SessionToken sessionToken,
            String loginSessionId,
            String userAgent,
            String ipAddress,
            String applicationName,
            String applicationView,
            String mobileSdkData)
    {
        this.sessionToken = sessionToken;
        this.loginSessionId = loginSessionId;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.applicationName = applicationName;
        this.applicationView = applicationView;
        this.mobileSdkData = mobileSdkData;
    }


    public SessionToken getSessionToken() { return sessionToken; }

    public String getLoginSessionId() { return loginSessionId; }

    public String getUserAgent() { return userAgent; }

    public String getIpAddress() { return ipAddress; }

    public String getApplicationName() { return applicationName; }

    public String getApplicationView() { return applicationView; }

    public String getMobileSdkData() { return mobileSdkData; }


    public static Builder builder() { return new Builder(); }

    public static Builder copy(ClientRequestInfo from)
    {
        return new Builder()
                .withIpAddress(from.getIpAddress())
                .withLoginSessionId(from.getLoginSessionId())
                .withApplicationName(from.getApplicationName())
                .withApplicationView(from.getApplicationView())
                .withToken(from.getSessionToken())
                .withUserAgent(from.getUserAgent());
    }

    public static class Builder
    {
        private SessionToken sessionToken;
        private String loginSessionId;
        private String userAgent;
        private String ipAddress;
        private String applicationName;
        private String applicationView;
        private String mobileSdkData;

        public Builder() {/** necessary to support the out class builder method */}

        public Builder withToken(SessionToken sessionToken)
        {
            this.sessionToken = sessionToken;
            return this;
        }


        public Builder withLoginSessionId(String loginSessionId)
        {
            this.loginSessionId = loginSessionId;
            return this;
        }

        public Builder withUserAgent(String userAgent)
        {
            this.userAgent = userAgent;
            return this;
        }

        public Builder withIpAddress(String ipAddress)
        {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder withApplicationName(String applicationName)
        {
            this.applicationName = applicationName;
            return this;
        }

        public Builder withApplicationView(String applicationView)
        {
            this.applicationView = applicationView;
            return this;
        }

        public Builder withMobileSdkData(String mobileSdkData)
        {
            this.mobileSdkData = mobileSdkData;
            return this;
        }

        public ClientRequestInfo build()
        {
            return new ClientRequestInfo(
                    sessionToken,
                    loginSessionId,
                    userAgent,
                    ipAddress,
                    applicationName,
                    applicationView,
                    mobileSdkData);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof ClientRequestInfo))
        {
            return false;
        }
        ClientRequestInfo that = (ClientRequestInfo) o;
        return Objects.equals(getSessionToken(), that.getSessionToken()) &&
                Objects.equals(getLoginSessionId(), that.getLoginSessionId()) &&
                Objects.equals(getUserAgent(), that.getUserAgent()) &&
                Objects.equals(getIpAddress(), that.getIpAddress()) &&
                Objects.equals(getApplicationName(), that.getApplicationName()) &&
                Objects.equals(getApplicationView(), that.getApplicationView()) &&
                Objects.equals(getMobileSdkData(), that.getMobileSdkData());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getSessionToken(), getLoginSessionId(), getUserAgent(), getIpAddress(),
                getApplicationName(),
                getApplicationView(),
                getMobileSdkData());
    }

    @Override
    public String toString()
    {
        return "ClientRequestInfo{" +
                "sessionToken=" + sessionToken +
                ", loginSessionId='" + loginSessionId + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", applicationView='" + applicationView + '\'' +
                ", mobileSdkData='" + mobileSdkData + '\'' +
                '}';
    }
}
