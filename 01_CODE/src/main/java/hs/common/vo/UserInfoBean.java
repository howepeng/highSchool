package hs.common.vo;

import hs.common.vo.EntUserVO;

public class UserInfoBean extends BaseBean {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 5646016450311504678L;

    private EntUserVO baseInfo = null;

    private int regionId = 0;

    private int defaultGrade = 0;

    /**
     * Ĭ��ѧ��ID.
     */
    private int defaultCourse = 0;

    /**
     * ȡ���û�����Ϣ
     *
     * @return �û�����Ϣ
     */
    public EntUserVO getBaseInfo() {
        return baseInfo;
    }

    /**
     * �趨�û�����Ϣ
     *
     * @param baseInfo �û�����Ϣ
     */
    public void setBaseInfo(EntUserVO baseInfo) {
        this.baseInfo = baseInfo;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getDefaultGrade() {
        return defaultGrade;
    }

    public void setDefaultGrade(int defaultGrade) {
        this.defaultGrade = defaultGrade;
    }

    public int getDefaultCourse() {
        return defaultCourse;
    }

    public void setDefaultCourse(int defaultCourse) {
        this.defaultCourse = defaultCourse;
    }
}
