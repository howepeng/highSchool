package hs.common.exception;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class ApplicationException extends RuntimeException {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 4924880079097368704L;

    public static final int CONVERT_STRING_TO_DATE = 11;

    public static final int CONVERT_STRING_TO_NUMBER = 12;

    public static final int CONVERT_DATE_TO_STRING = 13;

    public static final int CONVERT_MSG_TO_STRING = 14;

    public static final int DB_COLUMN_NOT_EXIST = 21;

    public static final int DB_COLUMN_TYPE_INCORRECT = 22;

    public static final int SQL_INSERT_ID_NOT_EXIST = 31;

    public static final int SQL_RESULT_TYPE_INCORRECT = 32;

    public static final int ACTION_FORWARD_NOT_EXIST = 41;

    public static final int ACTION_JSON_OBJECT_NOT_SETTED = 42;

    public static final int CODE_TYPE_NOT_EXIST = 51;

    public static final int CODE_VALUE_NOT_EXIST = 52;

    public static final int FILE_NOT_EXIST = 61;

    public static final int FILE_READ_ERROR = 62;

    public static final int FILE_WRITE_ERROR = 63;

    public static final int SYSTEM_SETTING_NOT_EXIST = 71;

    private static Map<Integer, String> messageMap = null;

    static {
        messageMap = new HashMap<Integer, String>();
        messageMap.put(CONVERT_STRING_TO_DATE,
                "can not convert String:[{0}] to Date use format:[{1}].");
        messageMap.put(CONVERT_STRING_TO_NUMBER,
                "can not convert String:[{0}] to Number.");
        messageMap.put(CONVERT_DATE_TO_STRING,
                "can not convert Date:[{0}] to String use format:[{1}].");
        messageMap.put(CONVERT_MSG_TO_STRING,
                "can not convert Msg:[{0}] to String use format:[{1}].");
        messageMap.put(DB_COLUMN_NOT_EXIST,
                "column:[{0}] is not exists in sql query result.");
        messageMap.put(DB_COLUMN_TYPE_INCORRECT,
                "can not convert column:[{0}] type from [{1}] to [{2}].");
        messageMap.put(SQL_INSERT_ID_NOT_EXIST,
                "simple insert sql-id for bean:[{0}] is not exists.");
        messageMap.put(SQL_RESULT_TYPE_INCORRECT,
                "can not convert sql:[{0}] result type from [{1}] to [{2}].");
        messageMap.put(ACTION_FORWARD_NOT_EXIST,
                "action forward:[{0}] is not exists in config file.");
        messageMap.put(ACTION_JSON_OBJECT_NOT_SETTED,
                "JSON object is not setted.");
        messageMap.put(CODE_TYPE_NOT_EXIST, "code-type:[{0}] is not exists.");
        messageMap.put(CODE_VALUE_NOT_EXIST,
                "code-value:[{1}] in code-type:[{0}] is not exists.");
        messageMap.put(FILE_NOT_EXIST, "file:[{0}] is not exists.");
        messageMap.put(FILE_READ_ERROR, "can not read file:[{0}].");
        messageMap.put(FILE_WRITE_ERROR, "can not write file:[{0}].");
        messageMap.put(SYSTEM_SETTING_NOT_EXIST,
                "system-setting:[{0}] is not exists.");
    }

    public ApplicationException(int type, String... param) {
        super(getMessage(type, param));
    }

    public ApplicationException(int type, Throwable t, String... param) {
        super(getMessage(type, param), t);
    }

    private static String getMessage(int type, String... param) {

        if (messageMap.containsKey(type)) {
            String message = messageMap.get(type);
            MessageFormat mf = new MessageFormat(message);

            return mf.format(param);
        } else {
            return "unknown accplition exception occourd.";
        }
    }
}
