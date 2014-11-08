package hs.pageModel;

import java.io.Serializable;

public class Json implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean success = false;
    private String msg = null;
    private Object returnObject;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

}
