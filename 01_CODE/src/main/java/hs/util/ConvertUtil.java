package hs.util;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hs.common.exception.ApplicationException;

public abstract class ConvertUtil {

    public static Date toDate(String dateString, String format) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateString);
        } catch (Exception e) {
            throw new ApplicationException(
                    ApplicationException.CONVERT_STRING_TO_DATE, e, dateString,
                    format);
        }
    }

    public static String toString(Date date, String format) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            throw new ApplicationException(
                    ApplicationException.CONVERT_DATE_TO_STRING, e,
                    date.toString(), format);
        }
    }

    public static Integer toInt(String intString) {

        try {
            return Integer.parseInt(intString);
        } catch (Exception e) {
            throw new ApplicationException(
                    ApplicationException.CONVERT_STRING_TO_NUMBER, e,
                    intString.toString());
        }
    }

    public static Long toLong(String longString) {

        try {
            return Long.parseLong(longString);
        } catch (Exception e) {
            throw new ApplicationException(
                    ApplicationException.CONVERT_STRING_TO_NUMBER, e,
                    longString.toString());
        }
    }

    public static Double toDouble(String doubleString) {

        try {
            return Double.parseDouble(doubleString);
        } catch (Exception e) {
            throw new ApplicationException(
                    ApplicationException.CONVERT_STRING_TO_NUMBER, e,
                    doubleString.toString());
        }
    }

    public static String messageFormat(String messageFormat, String... param) {
        try {
            MessageFormat mf = new MessageFormat(messageFormat);
            return mf.format(param);
        } catch (Exception e) {
            throw new ApplicationException(
                    ApplicationException.CONVERT_MSG_TO_STRING, e,
                    param.toString(), messageFormat);
        }
    }

}
