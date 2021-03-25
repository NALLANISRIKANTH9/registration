package com.nallani.login.registration.service.exception;

import com.nallani.login.registration.model.DeveloperMessages;

import java.security.PrivilegedActionException;

public class BusinessException extends Exception {

    private String message;
    private DeveloperMessages developerMessages;

    public BusinessException(){}

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public BusinessException(String message, DeveloperMessages developerMessages) {
        this.message = message;
        this.developerMessages = developerMessages;
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BusinessException(String message, String message1, DeveloperMessages developerMessages) {
        super(message);
        this.message = message1;
        this.developerMessages = developerMessages;
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public BusinessException(String message, Throwable cause, String message1, DeveloperMessages developerMessages) {
        super(message, cause);
        this.message = message1;
        this.developerMessages = developerMessages;
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public BusinessException(Throwable cause, String message, DeveloperMessages developerMessages) {
        super(cause);
        this.message = message;
        this.developerMessages = developerMessages;
    }

    /**
     * Constructs a new exception with the specified detail message,
     * cause, suppression enabled or disabled, and writable stack
     * trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     * @since 1.7
     */
    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, DeveloperMessages developerMessages) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.developerMessages = developerMessages;
    }
}
