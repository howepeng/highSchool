package hs.common.vo;

public class CodeInfoBean extends BaseBean {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 7775577637473717378L;

    /**
     * ���?
     */
    private String name = null;

    /**
     * ֵ.
     */
    private String value = null;

    /**
     * ȡ�����?
     *
     * @return ���?
     */
    public String getName() {
        return name;
    }

    /**
     * �趨���?
     *
     * @param name ���?
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ȡ��ֵ
     *
     * @return ֵ
     */
    public String getValue() {
        return value;
    }

    /**
     * �趨ֵ
     *
     * @param value ֵ
     */
    public void setValue(String value) {
        this.value = value;
    }

}
