package com.nallani.login.registration.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Otp extends Login implements Serializable
{
    private static final long serialVersionUID = -3929285373891904885L;

    public static Optional<Otp> from(String code)
    {
        return Optional.ofNullable(Strings.emptyToNull(code)).map(Otp::new);
    }

    public Otp()
    {
        // for jackson
    }

    public Otp(String withCode)
    {
        this.code = withCode;
    }

    private String code;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
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
        Otp otp = (Otp) o;
        return Objects.equals(getCode(), otp.getCode());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getCode());
    }
}
