package com.nallani.login.registration.util;

import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import static java.lang.String.format;

public interface Dates
{
    static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    static final String YYYYMMDD = "yyyy-MM-dd";
    static final String MM_DD_YYYY = "MM/dd/yyyy";
    static final String MMDDYYYY = "MMddyyyy";
    static final int SECONDS_IN_A_YEAR = 31536000;

    static Date nowPlusAYear()
    {
        LocalDate now = LocalDate.now().plusYears(1);
        return Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    static boolean isEqual(Date toCompare, Date with)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYYMMDD);
        simpleDateFormat.setTimeZone(UTC);
        String toCompareStr = simpleDateFormat.format(toCompare);
        String withStr = simpleDateFormat.format(with);
        return toCompareStr.equals(withStr);
    }

    static Optional<Date> toDate(String from, Logger LOGGER)
    {
        SimpleDateFormat[] formats = {
                new SimpleDateFormat(YYYYMMDD),
                new SimpleDateFormat(MM_DD_YYYY),
                new SimpleDateFormat(MMDDYYYY)
        };
        for(SimpleDateFormat format : formats)
        {
            try
            {
                return Optional.of(format.parse(from));
            } catch(ParseException e) {
                LOGGER.trace(format("failed to parse date [%s] using format [%s].", from, format.toPattern()));
            }
        }
        return Optional.empty();
    }

    static String toFormattedString(Date toFormat, String withPattern)
    {
        if(toFormat == null)
        {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(withPattern);
        return format.format(toFormat);
    }

    static String toFormattedString(Date toFormat, String withPattern, TimeZone andTimeZone)
    {
        if(toFormat == null)
        {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(withPattern);
        format.setTimeZone(andTimeZone);
        return format.format(toFormat);
    }

    static boolean before(Date date1, Date date2)
    {
        return date2 == null || date1.before(date2);
    }

    static boolean after(Date date1, Date date2)
    {
        return date2 == null || date1.after(date2);
    }

    static String utcNowInISOFormat(Logger LOGGER)
    {
        String dateStr = null;
        try
        {
            dateStr = OffsetDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
        }
        catch(Exception e)
        {
            LOGGER.info("Exception getting current date in UTC format: " + e);
        }
        LOGGER.info(format("Returning currentDate %s in UTC format ", dateStr));
        return dateStr;
    }
}