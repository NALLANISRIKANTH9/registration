package com.nallani.login.registration.util;

public interface RegExConstants {
    int USER_NAME_MIN_LENGTH = 2;
    int USER_NAME_MAX_LENGTH = 32;
    String USER_NAME = "^[^'\";<>^`%&{},=\\s]*$";

    int PWD_MIN_LENGTH = 6;
    int PWD_MAX_LENGTH = 32;
    String PASS_WD_FORMAT = "^[^'\";^`%&{}=,+]*$";

    int ZIP_CODE_MIN_LENGTH = 5;
    int ZIP_CODE_MAX_LENGTH = 10;
    String ZIP_CODE = "^\\d{5}(?:[-\\s]\\d{4})?$";

    int NEW_PWD_MIN_LENGTH = 8;
    int NEW_PWD_MAX_LENGTH = 32;
    String PASS_WD_FORMAT_OR_BLANK = "^(([ ]*)|(^[^'\";^`%&{}=,+]*))$";

    int OTP_MIN_LENGTH = 1;
    int OTP_MAX_LENGTH = 100;
    String OTP_CODE = "^[a-zA-Z0-9]*$";

    String DATE_OF_BIRTH = "^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$";

    int SSN_MIN_LENGTH = 9;
    int SSN_MAX_LENGTH = 9;
    String SSN = "^[0-9]+$";

    int UUID_MAX_LENGTH = 64;
    String UUID = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}";

    int MFAID_MAX_LENGTH = 250;
    String MFAID_FORMAT = "^[a-zA-Z0-9?/()'\\s]*$|^[A-Za-z0-9._%'\\*\\+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z0-9-]{2,}$|^[0-9\\s\\(\\)\\*-]+$";

    String USER_NAME_THREE = "^[a-zA-Z]{3}\\d{3}$";

    String EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    String PHONE = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
}
