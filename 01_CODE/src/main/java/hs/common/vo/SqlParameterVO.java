package hs.common.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hs.common.exception.ApplicationException;
import hs.util.ConvertUtil;
import hs.util.SystemSettingUtil;

public class SqlParameterVO extends HashMap<String, Object> {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 4684185739841072639L;

    private static final String PARAMETER_KEY_ROW_LIMIT = "PARAMETER_KEY_ROW_LIMIT";

    private static final String PARAMETER_KEY_ROW_START_INDEX = "PARAMETER_KEY_ROW_START_INDEX";

    private static final String PARAMETER_KEY_ROW_COUNT = "PARAMETER_KEY_ROW_COUNT";

    private boolean isValueList = false;

    private String valueListName = null;

    public SqlParameterVO() {
        super.put(PARAMETER_KEY_ROW_LIMIT, "0");
    }

    public void setRowLimit(int start) {
        super.put(PARAMETER_KEY_ROW_LIMIT, "1");
        super.put(PARAMETER_KEY_ROW_START_INDEX, start);
        super.put(PARAMETER_KEY_ROW_COUNT, SystemSettingUtil.getPageItemCount());
    }

    public void startPutValueList() {
        isValueList = true;
        valueListName = null;
    }

    public void putVarchar(String name, String data) {
        putData(name, data);
    }

    public void putNumber(String name, String data) {
        putNumberData(name, data);
    }

    public void putNumber(String name, long data) {
        putNumberData(name, data);
    }

    public void putNumber(String name, double data) {
        putNumberData(name, data);
    }

    public void putDatetime(String name, String data, String format) {

        Timestamp date = null;
        if (data != null) {
            date = new Timestamp(ConvertUtil.toDate(data, format).getTime());
        }

        putData(name, date);
    }

    public void putDatetime(String name, java.util.Date data) {
        if (data != null) {
            putData(name, new Timestamp(data.getTime()));
        } else {
            putData(name, null);
        }
    }

    public void putTimestamp(String name, String data, String format) {

        Timestamp time = null;
        if (data != null) {
            time = new Timestamp(ConvertUtil.toDate(data, format).getTime());
        }

        putData(name, time);
    }

    public void putTimestamp(String name, java.util.Date data) {
        if (data != null) {
            putData(name, new Timestamp(data.getTime()));
        } else {
            putData(name, null);
        }

    }

    @Override
    public Object put(String name, Object data) {
        return null;
    }

    private void putNumberData(String name, Object data) {

        BigDecimal bd = null;
        if (data != null) {
            try {
                bd = new BigDecimal(data.toString());
            } catch (Exception e) {
                throw new ApplicationException(
                        ApplicationException.CONVERT_STRING_TO_NUMBER, e,
                        data.toString());
            }
        }

        putData(name, bd);
    }

    @SuppressWarnings("unchecked")
    private <P> void putData(String name, P data) {

        if (valueListName == null) {
            if (isValueList) {
                valueListName = name;

                List<P> list = new ArrayList<P>();
                list.add(data);
                super.put(name, list);
            } else {
                super.put(name, data);
            }
        } else {
            if (valueListName.equals(name)) {
                List<P> list = (List<P>) super.get(name);
                list.add(data);
            } else {
                valueListName = null;
                isValueList = false;

                super.put(name, data);
            }
        }
    }
}
