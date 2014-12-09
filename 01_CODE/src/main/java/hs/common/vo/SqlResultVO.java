package hs.common.vo;

import java.util.Date;
import java.util.HashMap;

import hs.common.exception.ApplicationException;
import hs.util.ConvertUtil;

public class SqlResultVO extends HashMap<String, Object> {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -2922327231568486892L;

    public String getString(String column) {

        Object obj = getData(column, Object.class);
        if (obj == null) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return obj.toString();
        }
    }

    public String getString(String column, String dateFormat) {

        Date d = getDate(column);
        if (d == null) {
            return null;
        }

        return ConvertUtil.toString(d, dateFormat);
    }

    public Integer getInteger(String column) {
        Number number = getData(column, Number.class);
        if (number == null) {
            return null;
        }

        return number.intValue();
    }

    public Long getLong(String column) {
        Number number = getData(column, Number.class);
        if (number == null) {
            return null;
        }

        return number.longValue();
    }

    public Double getDouble(String column) {
        Number number = getData(column, Number.class);
        if (number == null) {
            return null;
        }

        return number.doubleValue();
    }

    public Date getDate(String column) {
        return getData(column, Date.class);
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private <P> P getData(String column, Class<P> dataType) {

        if (!super.containsKey(column)) {
            throw new ApplicationException(
                    ApplicationException.DB_COLUMN_NOT_EXIST, column);
        }

        Object obj = super.get(column);
        if (obj == null) {
            return null;
        }

        if (obj.getClass() instanceof Class<?>) {
            return (P) obj;
        } else {
            throw new ApplicationException(
                    ApplicationException.DB_COLUMN_TYPE_INCORRECT, column, obj
                            .getClass().toString(), dataType.toString());
        }
    }
}
