package com.nallani.login.registration.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Optional;

import static com.google.common.base.Strings.emptyToNull;

public class SessionToken implements Serializable
{
    private static final long serialVersionUID = -5858114518847424709L;

    private String token;
    private String deviceToken;

    public SessionToken()
    {
        // necessary for Jackson
    }

    public SessionToken(String token, String deviceToken)
    {
        this.token = token;
        this.deviceToken = deviceToken;
    }

    @JsonIgnore
    public Optional<String> getMaybeToken()
    {
        return Optional.ofNullable(emptyToNull(token));
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getDeviceToken()
    {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken)
    {
        this.deviceToken = deviceToken;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        SessionToken token1 = (SessionToken) o;
        return Objects.equal(getToken(), token1.getToken()) &&
                Objects.equal(getDeviceToken(), token1.getDeviceToken());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getToken(), getDeviceToken());
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("token", token)
                .add("deviceToken", deviceToken)
                .toString();
    }
}
