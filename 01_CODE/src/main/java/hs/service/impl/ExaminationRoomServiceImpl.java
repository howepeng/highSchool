package hs.service.impl;

import hs.common.dao.QueryDAO;
import hs.common.vo.ExaminationRoomColumn;
import hs.common.vo.ExaminationRoomItem;
import hs.common.vo.ExaminationRoomRow;
import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;
import hs.dao.DictionaryDaoI;
import hs.dao.ExaminationRoomDaoI;
import hs.dao.MonthInfoDaoI;
import hs.model.TbDictionary;
import hs.model.TbExaminationRoom;
import hs.model.TbMonthInfo;
import hs.model.TbYearInfo;
import hs.pageModel.DataGrid;
import hs.pageModel.ExaminationRoom;
import hs.pageModel.SessionInfo;
import hs.service.ExaminationRoomServiceI;
import hs.service.YearInfoServiceI;
import hs.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("examinationRoomService")
public class ExaminationRoomServiceImpl implements ExaminationRoomServiceI {

    private ExaminationRoomDaoI examinationRoomDao;

    @Autowired
    public void setExaminationRoomDao(ExaminationRoomDaoI examinationRoomDao) {
        this.examinationRoomDao = examinationRoomDao;
    }

    private MonthInfoDaoI monthInfoDao;

    @Autowired
    public void setMonthInfoDao(MonthInfoDaoI monthInfoDao) {
        this.monthInfoDao = monthInfoDao;
    }

    private YearInfoServiceI yearInfoService;

    @Autowired
    public void setYearInfoService(YearInfoServiceI yearInfoService) {
        this.yearInfoService = yearInfoService;
    }

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setDictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Autowired
    protected QueryDAO queryDAO = null;

    @Override
    public DataGrid datagrid(ExaminationRoom examinationRoom, SessionInfo sessionInfo) {
        DataGrid j = new DataGrid();
        List<ExaminationRoom> finList = new ArrayList<ExaminationRoom>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbExaminationRoom t where t.tbYearInfo.isDefault = '105001' ";
        hql += this.addCondition(examinationRoom, params, sessionInfo);
        List<TbExaminationRoom> tbExaminationRoomList = examinationRoomDao.find(hql, params, examinationRoom.getPage(), examinationRoom.getRows());
        if (tbExaminationRoomList != null && tbExaminationRoomList.size() > 0) {
            for (TbExaminationRoom t : tbExaminationRoomList) {
                ExaminationRoom f = new ExaminationRoom();
                BeanUtils.copyProperties(t, f);
                TbMonthInfo tbMonthInfo1 = monthInfoDao.getById(TbMonthInfo.class, f.getMonthTimeId1());
                f.setMonthTimeName1(tbMonthInfo1.getName());
                TbMonthInfo tbMonthInfo2 = monthInfoDao.getById(TbMonthInfo.class, f.getMonthTimeId2());
                f.setMonthTimeName2(tbMonthInfo2.getName());
                TbDictionary dispatchType = dictionaryDao.getById(TbDictionary.class, f.getDispatchType());
                f.setDispatchTypeName(dispatchType.getName());
                TbDictionary professional = dictionaryDao.getById(TbDictionary.class, f.getProfessionalId());
                f.setProfessionalName(professional.getName());
                TbDictionary rangeOrder = dictionaryDao.getById(TbDictionary.class, f.getRangeOrder());
                f.setRangeOrderName(rangeOrder.getName());
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(examinationRoomDao.count("SELECT count(*) " + hql, params));
        return j;
    }

     @Override
     public void remove(String ids) {
         if (ids != null) {
             for (String id : ids.split(",")) {
                 TbExaminationRoom r = examinationRoomDao.getById(TbExaminationRoom.class, id.trim());
                 if (r != null) {
                     examinationRoomDao.delete(r);
                 }
             }
         }
     }

    @Override
    public List<ExaminationRoomItem> dispatch(
            ExaminationRoom examinationRoom, SessionInfo sessionInfo, boolean isUpdate) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("monthTime1", examinationRoom.getMonthTimeId1());
        param.putVarchar("monthTime2", examinationRoom.getMonthTimeId2());
        param.putVarchar("professionalId", examinationRoom.getProfessionalId());
        if ("114002".equals(examinationRoom.getRangeOrder())) {
            param.putVarchar("rangeOrder", "asc");
        } else {
            param.putVarchar("rangeOrder", "desc");
        }
        List<SqlResultVO> student = getStudent(param);
        List<ExaminationRoomItem> ret = null;
        if ("113002".equals(examinationRoom.getDispatchType())) {
            // I型分布
            ret = getI(examinationRoom, student);
        } else if ("113001".equals(examinationRoom.getDispatchType())) {
            ret = getS(examinationRoom, student);
        }
        if (ret != null && ret.size() > 0) {
            if (isUpdate) {
                TbExaminationRoom nItem = examinationRoomDao.getById(TbExaminationRoom.class, examinationRoom.getId());
                nItem.setFileName(examinationRoom.getFileName());
                examinationRoomDao.save(nItem);
            } else {
                TbExaminationRoom nItem = new TbExaminationRoom();
                nItem.setId(UUID.randomUUID().toString());
                nItem.setColumn(examinationRoom.getColumn());
                nItem.setDispatchType(examinationRoom.getDispatchType());
                nItem.setFileName(examinationRoom.getFileName());
                nItem.setMonthTimeId1(examinationRoom.getMonthTimeId1());
                nItem.setMonthTimeId2(examinationRoom.getMonthTimeId2());
                nItem.setName(examinationRoom.getName());
                nItem.setProfessionalId(examinationRoom.getProfessionalId());
                nItem.setRangeOrder(examinationRoom.getRangeOrder());
                nItem.setRow(examinationRoom.getRow());
                TbYearInfo y = yearInfoService.getDefaultYear();
                nItem.setTbYearInfo(y);
                examinationRoomDao.save(nItem);
            }
        }
        return ret;
    }

    private List<ExaminationRoomItem> getS(ExaminationRoom examinationRoom,
            List<SqlResultVO> student) {
        if (student == null || student.size() <= 0) {
            return null;
        }
        int row = Integer.valueOf(examinationRoom.getRow());
        int column = Integer.valueOf(examinationRoom.getColumn());
        if (row*column == 0) {
            return null;
        }
        int count = student.size();
        int sheetCount= count / (row*column);
        if (count%(row*column) > 0) {
            sheetCount = sheetCount +1;
        }
        List<ExaminationRoomColumn> columns = null;
        List<ExaminationRoomRow> rows = new ArrayList<ExaminationRoomRow>();
        Map<Integer, List<ExaminationRoomColumn>> mRows = new HashMap<Integer, List<ExaminationRoomColumn>>();
        List<ExaminationRoomItem> ret = new ArrayList<ExaminationRoomItem>();
        int c = 0;
        for (int i = 0; i < count; i++) {
            int cRow = (i+1)%row;
            if ((i+1)%row == 0) {
                cRow = row;
            }
            if (c%2==1) {
                cRow = row - cRow + 1;
            }
            if ((i+1)%row == 0) {
                c++;
            }
            if (!mRows.containsKey(cRow)) {
                columns = new ArrayList<ExaminationRoomColumn>();
                mRows.put(cRow, columns);
            }
            columns = mRows.get(cRow);

            SqlResultVO tiem = student.get(i);
            ExaminationRoomColumn columnItem = new ExaminationRoomColumn();
            columnItem.setName(tiem.getString("name"));
            columnItem.setNum(tiem.getString("num"));
            columns.add(columnItem);
            if ((columns.size()) == column){
                if (c == column) {
                    if (!mRows.isEmpty()) {
                        for (Entry<Integer, List<ExaminationRoomColumn>> endRow : mRows.entrySet()) {
                            ExaminationRoomRow rowItem = new ExaminationRoomRow();
                            rowItem.setColumns(endRow.getValue());
                            rows.add(rowItem);
                        }
                    }
                    ExaminationRoomItem erItem = new ExaminationRoomItem();
                    erItem.setSheetName(examinationRoom.getName()+(ret.size() + 1));
                    erItem.setRows(rows);
                    ret.add(erItem);
                    rows = new ArrayList<ExaminationRoomRow>();
                    mRows = new HashMap<Integer, List<ExaminationRoomColumn>>();
                    c=0;
                }
            }
            if ((i+1) == count) {
                if (!mRows.isEmpty()) {
                    for (Entry<Integer, List<ExaminationRoomColumn>> endRow : mRows.entrySet()) {
                        ExaminationRoomRow rowItem = new ExaminationRoomRow();
                        rowItem.setColumns(endRow.getValue());
                        rows.add(rowItem);
                    }
                }
                if (rows.size() > 0) {
                    ExaminationRoomItem erItem = new ExaminationRoomItem();
                    erItem.setSheetName(examinationRoom.getName()+(ret.size() + 1));
                    erItem.setRows(rows);
                    ret.add(erItem);
                }
            }
        }
        return ret;
    }

    private List<ExaminationRoomItem> getI(ExaminationRoom examinationRoom,
            List<SqlResultVO> student) {
        if (student == null || student.size() <= 0) {
            return null;
        }
        int row = Integer.valueOf(examinationRoom.getRow());
        int column = Integer.valueOf(examinationRoom.getColumn());
        if (row*column == 0) {
            return null;
        }
        int count = student.size();
        int sheetCount= count / (row*column);
        if (count%(row*column) > 0) {
            sheetCount = sheetCount +1;
        }
        List<ExaminationRoomColumn> columns = null;
        List<ExaminationRoomRow> rows = new ArrayList<ExaminationRoomRow>();
        Map<Integer, List<ExaminationRoomColumn>> mRows = new HashMap<Integer, List<ExaminationRoomColumn>>();
        List<ExaminationRoomItem> ret = new ArrayList<ExaminationRoomItem>();
        for (int i = 0; i < count; i++) {
            int cRow = (i+1)%row;
            if ((i+1)%row == 0) {
                cRow = row;
            }
            if (!mRows.containsKey(cRow)) {
                columns = new ArrayList<ExaminationRoomColumn>();
                mRows.put(cRow, columns);
            }
            columns = mRows.get(cRow);

            SqlResultVO tiem = student.get(i);
            ExaminationRoomColumn columnItem = new ExaminationRoomColumn();
            columnItem.setName(tiem.getString("name"));
            columnItem.setNum(tiem.getString("num"));
            columns.add(columnItem);
            if ((columns.size()) == column){
                ExaminationRoomRow rowItem = new ExaminationRoomRow();
                rowItem.setColumns(columns);
                rows.add(rowItem);
                mRows.remove(cRow);
                if (rows.size() == row) {
                    ExaminationRoomItem erItem = new ExaminationRoomItem();
                    erItem.setSheetName(examinationRoom.getName()+(ret.size() + 1));
                    erItem.setRows(rows);
                    ret.add(erItem);
                    rows = new ArrayList<ExaminationRoomRow>();
                }
            }
            if ((i+1) == count) {
                if (!mRows.isEmpty()) {
                    for (Entry<Integer, List<ExaminationRoomColumn>> endRow : mRows.entrySet()) {
                        ExaminationRoomRow rowItem = new ExaminationRoomRow();
                        rowItem.setColumns(endRow.getValue());
                        rows.add(rowItem);
                    }
                }
                if (rows.size() > 0) {
                    ExaminationRoomItem erItem = new ExaminationRoomItem();
                    erItem.setSheetName(examinationRoom.getName()+(ret.size() + 1));
                    erItem.setRows(rows);
                    ret.add(erItem);
                }
            }
        }
        return ret;
    }

    public List<SqlResultVO> getStudent(SqlParameterVO param) {
        List<SqlResultVO> ret = queryDAO.queryForList("com.getStudentForExaminationRoom", param, SqlResultVO.class);
        return ret;
    }


    /**
     * 生成查询hql语句
     *
     * @param student
     * @param params
     * @return
     */
    private String addCondition(ExaminationRoom examinationRoom, Map<String, Object> params, SessionInfo sessionInfo) {
        String hql = "";

        if(examinationRoom.getMonthTimeId1() != null && !examinationRoom.getMonthTimeId1().trim().equals("")) {
            hql += " AND t.monthTimeId1 LIKE :monthTimeId";
            params.put("monthTimeId1", "%%" + examinationRoom.getMonthTimeId1() + "%%");
        }

        if (examinationRoom.getMonthTimeId2() != null && !examinationRoom.getMonthTimeId2().trim().equals("")) {
            hql += " AND t.monthTimeId2 LIKE :monthTimeId";
            params.put("monthTimeId2", "%%" + examinationRoom.getMonthTimeId2() + "%%");
        }

        if (examinationRoom.getDispatchType() != null && !examinationRoom.getDispatchType().trim().equals("")) {
            hql += " AND t.dispatchType = :dispatchType";
            params.put("dispatchType", examinationRoom.getDispatchType());
        }

        if (examinationRoom.getProfessionalId() != null && !examinationRoom.getProfessionalId().trim().equals("")) {
            hql += " AND t.professionalId = :professionalId";
            params.put("professionalId", examinationRoom.getProfessionalId());
        }

        if (examinationRoom.getName() != null && !examinationRoom.getName().trim().equals("")) {
            hql += " AND t.name = :name";
            params.put("name", examinationRoom.getName());
        }

        if (examinationRoom.getRangeOrder() != null && !examinationRoom.getRangeOrder().trim().equals("")) {
            hql += " AND t.tangeOrder = :tangeOrder";
            params.put("tangeOrder", examinationRoom.getRangeOrder());
        }
        if (StringUtil.isNotEmpty(examinationRoom.getSort())) {

            if ("professionalName".equals(examinationRoom.getSort())) {
                hql += " ORDER BY t.professionalId";
            } else if ("dispatchTypeName".equals(examinationRoom.getSort())) {
                hql += " ORDER BY t.dispatchType";
            } else if ("rangeOrderName".equals(examinationRoom.getSort())) {
                hql += " ORDER BY t.rangeOrder";
            } else if ("monthTimeName1".equals(examinationRoom.getSort())) {
                hql += " ORDER BY t.monthTimeId1";
            } else if ("monthTimeName2".equals(examinationRoom.getSort())) {
                hql += " ORDER BY t.monthTimeId1";
            } else {
                hql += " ORDER BY t." + examinationRoom.getSort();
            }
            hql += " " + examinationRoom.getOrder();
        }
        return hql;
    }
}
