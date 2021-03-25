package com.nallani.login.registration.model;

import com.nallani.login.registration.util.RegExConstants;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = 7167184975650462412L;

    private String line1;
    private String line2;
    private String city;
    private String state;

    @Pattern(regexp = RegExConstants.ZIP_CODE)
    @Size(min = RegExConstants.ZIP_CODE_MIN_LENGTH, max = RegExConstants.ZIP_CODE_MAX_LENGTH)
    private String postalCode;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
