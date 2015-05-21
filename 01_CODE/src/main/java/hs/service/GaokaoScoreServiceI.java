package hs.service;

import hs.common.vo.SqlResultVO;
import hs.pageModel.DataGrid;
import hs.pageModel.GaokaoScore;
import hs.pageModel.GaokaoScoreStudent;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;

import java.util.List;

public interface GaokaoScoreServiceI {

    public DataGrid datagrid(GaokaoScore gaokaoScore, SessionInfo sessionInfo);
    public void remove(String ids);
    public String[] uploadClassScore(GaokaoScore gaokaoScore);
    public SqlResultVO averageClassType(GaokaoScore gaokaoScore);
    public SqlResultVO averageClassName(GaokaoScore gaokaoScore);
    public List<GaokaoScore> getGaokaoScoreList(GaokaoScore gaokaoScore, SessionInfo sessionInfo);
    public List<GaokaoScoreStudent> getStudentInfo(Student student, SessionInfo sessionInfo);

}
