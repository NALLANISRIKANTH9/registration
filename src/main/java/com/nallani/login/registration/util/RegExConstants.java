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

    int KBA_ANS_MIN_LENGTH = 2;
    int KBA_ANS_MAX_LENGTH = 250;
    String KBA_ANSWER = "^[ ]*[A-Za-z0-9\\W\\w\\S\\s]*[ ]*$";

    int OTP_MIN_LENGTH = 1;
    int OTP_MAX_LENGTH = 100;
    String OTP_CODE = "^[a-zA-Z0-9]*$";

    String DATE_OF_BIRTH = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";

    int SSN_MIN_LENGTH = 9;
    int SSN_MAX_LENGTH = 9;
    String SSN = "^[0-9]+$";

    int UUID_MAX_LENGTH = 64;
    String UUID = "^[A-Fa-f0-9-]*$";

    int MFAID_MAX_LENGTH = 250;
    String MFAID_FORMAT = "^[a-zA-Z0-9?/()'\\s]*$|^[A-Za-z0-9._%'\\*\\+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z0-9-]{2,}$|^[0-9\\s\\(\\)\\*-]+$";

    String UBER_ID = "^[a-zA-Z0-9\\-.]*$";
}
