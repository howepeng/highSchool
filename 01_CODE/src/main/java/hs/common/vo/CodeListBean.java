package hs.common.vo;

import hs.common.dao.QueryDAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class CodeListBean extends BaseBean {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1579887772337469733L;

    @Autowired
    private QueryDAO queryDAO = null;

    /**
     * CODE_MAP
     */
    private Map<Integer, List<CodeInfoBean>> codeMap = null;

    /**
     * init
     */
    public void init() {

//        if (codeMap != null) {
//            return;
//        }
//
//        // CODE_MAP
//        codeMap = new HashMap<Integer, List<CodeInfoBean>>();
//
//        SqlParameterVO typeParam = new SqlParameterVO();
//        typeParam.putNumber("type", CodeConst.CODE_TYPE_ALL);
//        List<MstCodeVO> typeResultList = queryDAO.queryForList(
//                "common.getCodeList", typeParam, MstCodeVO.class);
//
//        List<CodeInfoBean> typeList = new ArrayList<CodeInfoBean>();
//        for (MstCodeVO typeResult : typeResultList) {
//            typeList.add(createCodeInfo(typeResult));
//            SqlParameterVO codeParam = new SqlParameterVO();
//            codeParam.putNumber("type", typeResult.getCodeId());
//            List<MstCodeVO> codeResultList = queryDAO.queryForList(
//                    "common.getCodeList", codeParam, MstCodeVO.class);
//            List<CodeInfoBean> codeList = new ArrayList<CodeInfoBean>();
//            for (MstCodeVO codeResult : codeResultList) {
//                codeList.add(createCodeInfo(codeResult));
//            }
//            codeMap.put(typeResult.getCodeId(), codeList);
//        }
//
//        codeMap.put(CodeConst.CODE_TYPE_ALL, typeList);
    }

    public Map<Integer, List<CodeInfoBean>> getCodeMap() {
        return codeMap;
    }
}
