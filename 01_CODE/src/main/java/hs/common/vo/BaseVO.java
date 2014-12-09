package hs.common.vo;

import java.io.Serializable;

public abstract class BaseVO implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -1L;

    private Integer lastInsertIntId = 0;

    private Long lastInsertLongId = 0L;

    public Integer getLastInsertIntId() {
        return lastInsertIntId;
    }

    public void setLastInsertIntId(Integer lastInsertIntId) {
        this.lastInsertIntId = lastInsertIntId;
    }

    public Long getLastInsertLongId() {
        return lastInsertLongId;
    }

    public void setLastInsertLongId(Long lastInsertLongId) {
        this.lastInsertLongId = lastInsertLongId;
    }
}