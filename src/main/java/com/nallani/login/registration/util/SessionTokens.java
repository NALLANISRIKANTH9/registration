package com.nallani.login.registration.util;

import com.nallani.login.registration.service.ClientRequestInfo;
import com.nallani.login.registration.service.SessionToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nallani.login.registration.util.Dates.SECONDS_IN_A_YEAR;
import static java.lang.String.format;
import static java.util.stream.Collectors.toMap;

public interface SessionTokens {
    static final Logger LOGGER = LoggerFactory.getLogger(SessionTokens.class);

    static final String AUTH_TOKEN = "sri-token";
    static final String TRANSFER_ID_TOKEN = "transferId-token";
    static final String USER_AGENT = "x-useragent";
    static final String IP_ADDR = "x-ipaddress";
    static final String APPLICATION_NAME = "x-application-context";
    static final String APPLICATION_VIEW = "x-application-view";
    static final String MOBILE_SDK_DATA = "x-mobile-sdk-token";
    static final String DEVICE = "x-device-token";
    static final String COOKIE_DOMAIN = "localHost";
    static final String HEADER_SET_COOKIE = "SET-COOKIE";
    static final String HEADER_FORMAT = "^[a-zA-Z0-9()%\\-=\\*\\.\\?;,+\\/:&_ ]*$";
    static final Pattern HEADER_PATTERN = Pattern.compile(HEADER_FORMAT);

    static final String UUID = "UUID";
    static final Pattern UUID_PATTERN = Pattern.compile(RegExConstants.UUID);

    static boolean hasToken(ClientRequestInfo headerInfo) {
        return headerInfo != null
                && headerInfo.getSessionToken() != null
                && headerInfo.getSessionToken().getMaybeToken().isPresent();
    }

    static void setAuthToken(HttpServletResponse servletResponse, SessionToken withToken) {
        SessionToken token = Optional.ofNullable(withToken).orElseGet(SessionToken::new);
        setHttpOnlyCookie(servletResponse, AUTH_TOKEN, token.getToken());
    }

    static void setTransferIdToken(HttpServletResponse servletResponse, String withToken)
    {
        setHttpVisibleCookie(servletResponse, TRANSFER_ID_TOKEN, withToken);
    }

    static void setHttpOnlyCookie(HttpServletResponse servletResponse, String cookieName, String cookieValue) {
        //NewCookie newCookie = new NewCookie(cookieName,cookieValue, "/", null, 1, null, -1, true);
        Cookie newCookie = new Cookie(cookieName,cookieValue);
        servletResponse.addHeader(HttpHeaders.COOKIE, newCookie + "; HttpOnly");
        servletResponse.addHeader(HEADER_SET_COOKIE, newCookie + "; HttpOnly");
    }

    static Map<String, String> getCookieValues(HttpServletRequest servletRequest) {
        Cookie[] cookies = Optional.ofNullable(servletRequest.getCookies())
                .orElseGet(() -> new Cookie[0]);
        return Arrays.stream(cookies)
                .filter(SessionTokens::isRequiredCookie)
                .collect(toMap(Cookie::getName, Cookie::getValue));
    }

    static boolean isRequiredCookie(Cookie toCheck) {
        return AUTH_TOKEN.equals(toCheck.getName()) ||
                TRANSFER_ID_TOKEN.equals(toCheck.getName());
    }

    static Map<String, String> getHeaderValues(HttpServletRequest servletRequest) {
        HashMap<String, String> hm = new HashMap<>();
        Enumeration<String> headerNames = servletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            switch (header) {
                case USER_AGENT:
                    hm.put(USER_AGENT, servletRequest.getHeader(USER_AGENT));
                    break;
                case IP_ADDR:
                    hm.put(IP_ADDR, servletRequest.getHeader(IP_ADDR));
                    break;
                case APPLICATION_NAME:
                    hm.put(APPLICATION_NAME, servletRequest.getHeader(APPLICATION_NAME));
                    break;
                case APPLICATION_VIEW:
                    hm.put(APPLICATION_VIEW, servletRequest.getHeader(APPLICATION_VIEW));
                    break;
                case DEVICE:
                    hm.put(DEVICE, servletRequest.getHeader(DEVICE));
                    break;
                default:
                    break;
            }
        }
        return hm;
    }

    static ClientRequestInfo buildClientRequestInfoRequireToken(HttpServletRequest servletRequest) throws Exception {
        ClientRequestInfo toReturn = buildClientRequestInfo(servletRequest);
        if (!Optional.ofNullable(toReturn.getSessionToken()).isPresent()) {
            LOGGER.info("No Session token found {}", toReturn);
            throw new Exception("No Session token found for request from IP [%s]");
        }
        return toReturn;
    }

    static ClientRequestInfo buildClientRequestInfo(HttpServletRequest servletRequest) throws Exception {
        Map<String, String> headers = getHeaderValues(servletRequest);
        Map<String, String> cookies = getCookieValues(servletRequest);
        SessionToken sessionToken = new SessionToken();
        sessionToken.setToken(getAuthToken(cookies));
        sessionToken.setDeviceToken(applyRegEx(DEVICE, headers.get(DEVICE)));

        return ClientRequestInfo.builder().
                withToken(sessionToken).
                withUserAgent(applyRegEx(USER_AGENT, headers.get(USER_AGENT))).
                withApplicationName(applyRegEx(APPLICATION_NAME, headers.get(APPLICATION_NAME))).
                withApplicationView(headers.get(APPLICATION_VIEW) != null ? applyRegEx(APPLICATION_VIEW, headers.get(APPLICATION_VIEW)) : null).
                withMobileSdkData(headers.get(MOBILE_SDK_DATA) != null ? applyRegEx(MOBILE_SDK_DATA, headers.get(MOBILE_SDK_DATA)) : null).
                withLoginSessionId(servletRequest.getSession().getId()).
                withIpAddress(applyRegEx(IP_ADDR, headers.get(IP_ADDR))).
                build();
    }

    static String getAuthToken(Map<String, String> fromCookieMap) {
        return Optional.ofNullable(fromCookieMap.get(AUTH_TOKEN))
                .orElseGet(() -> fromCookieMap.get(TRANSFER_ID_TOKEN));
    }

    static String applyRegEx(String headerNm, String toTest) throws Exception {
        Matcher m = getHeaderRegexPattern(headerNm).matcher(toTest);
        if (!m.matches()) {
            throw new Exception(format("Invalid header[%s] value of[%s]", headerNm, toTest));
        }
        return toTest;
    }

    static Pattern getHeaderRegexPattern(String headerNm) {
        if (UUID.equals(headerNm)) {
            return UUID_PATTERN;
        }
        return HEADER_PATTERN;
    }

    static void setHttpVisibleCookie(HttpServletResponse servletResponse, String cookieName, String cookieValue) {
        //NewCookie newCookie = new NewCookie(cookieName, cookieValue, "/", COOKIE_DOMAIN, null, SECONDS_IN_A_YEAR, true, false);
        Cookie newCookie = new Cookie(cookieName,cookieValue);
        servletResponse.addHeader(HttpHeaders.COOKIE, newCookie + ";");
        servletResponse.addHeader(HEADER_SET_COOKIE, newCookie + ";");
    }
}
