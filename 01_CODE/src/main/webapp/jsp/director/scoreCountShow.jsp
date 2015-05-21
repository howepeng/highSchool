<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var director_scoreCount_datagrid;
    $(function() {
        director_scoreCount_datagrid = $('#director_scoreCount_datagrid')
                .datagrid(
                        {
                            url: '${pageContext.request.contextPath}/scoreCountAction!datagrid.action',
                            title : '',
                            iconCls : 'icon-save',
                            pagination : true,
                            pageSize : 10,
                            pageList : [ 10, 20, 30, 40 ],
                            fit : true,
                            fitColumns : true,
                            nowrap : false,
                            border : false,
                            idField : 'id',
                            sortName : 'id',
                            sortOrder : 'desc',
                            checkOnSelect : false,
                            selectOnCheck : true,
                            frozenColumns : [ [ {
                                title : '编号',
                                field : 'id',
                                width : 100,
                                sortable : true,
                                checkbox : true
                            }, {
                                title : '比较月考名称',
                                field : 'monthTime1',
                                width : 80,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                title : '计算月考名称',
                                field : 'monthTime2',
                                width : 150,
                                sortable : true
                            }, {
                                title : '学生姓名',
                                field : 'studentName',
                                width : 150,
                                sortable : true
                            }, {
                                 title : '专业',
                                 field : 'professionalName',
                                 width : 150,
                                 sortable : true
                            }, {
                                title : '班级名称',
                                field : 'className',
                                sortable:true,
                                width : 150
                            } , {
                                title : '学科',
                                field : 'subjectName',
                                width : 80,
                                sortable : true
                            } , {
                                title : '成绩',
                                field : 'fraction',
                                width : 80
                            } , {
                                title : '班级名次',
                                field : 'classRank',
                                width : 150,
                                sortable : true
                            } , {
                                title : '位置变化',
                                field : 'classChange',
                                width : 150,
                                sortable : true
                            } , {
                                title : '年级名次',
                                field : 'gradeRank',
                                width : 150,
                                sortable : true
                            } , {
                                title : '进步/退步名次',
                                field : 'gradeChange',
                                width : 150,
                                sortable : true
                            }] ],
                            toolbar : [ {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    director_scoreCount_datagrid.datagrid('unselectAll');
                                    director_scoreCount_datagrid.datagrid('uncheckAll');
                                }
                            },'-', {
                                text : '导出',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    director_scoreCount_download_All_Info_Fun();
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#stu_stuArrears_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });
    });

    function director_scoreCount_searchFun() {
        director_scoreCount_datagrid.datagrid('load',
                serializeObject($('#director_scoreCount_SearchForm')));
    }

    function director_scoreCount_cleanFun() {
        $('#director_scoreCount_SearchForm input').val('');
        director_scoreCount_datagrid.datagrid('load', {});
    }
    function director_scoreCount_download_All_Info_Fun(){
        var monthTime1 = $('#director_scoreCount_SearchForm').find("input[name^='monthTime1']").val();
        var monthTime2 = $('#director_scoreCount_SearchForm').find("input[name^='monthTime2']").val();
        var professionalId = $('#director_scoreCount_SearchForm').find("input[name^='professionalId']").val();
        var classInfoId = $('#director_scoreCount_SearchForm').find("input[name^='classInfoId']").val();
        var subjectId = $('#director_scoreCount_SearchForm').find("input[name^='subjectId']").val();
        var param = "monthTime1="+monthTime1;
        param = param +"&monthTime2="+ monthTime2;
        param = param +"&professionalId="+ professionalId;
        param = param +"&classInfoId="+ classInfoId;
        param = param +"&subjectId="+ subjectId;
        window.open('${pageContext.request.contextPath}/downloadScoreCountInfoAction.action?'+param);
    }
</script>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 135px;overflow: hidden;" align="left">
        <form id="director_scoreCount_SearchForm" method="post">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>比较月考名称</th>
                    <td>
                        <input id="monthTime1" name="monthTime1" class="easyui-combobox" style="width: 160px;"
                               data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/monthInfoAction!combox.action'"/>
                    </td>
                    <th>计算月考名称</th>
                    <td>
                         <input id="monthTime2" name="monthTime2" class="easyui-combobox" style="width: 160px;"
                               data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/monthInfoAction!combox.action'"/>
                    </td>
                </tr>
                <tr>
                    <th>专业</th>
                     <td>
                          <input id="professionalId" name="professionalId" class="easyui-combobox"
                                 data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=112',
                                 valueField : 'id',
                                 textField : 'name',
                                 multiple : false,
                                 editable : true,
                                 panelHeight : '200',
                                 missingMessage:'请填写专业'" />
                    </td>
                    <th>班级名称</th>
                     <td>
                        <input id="classInfoId" name="classInfoId" class="easyui-combobox" style="width:160px;"
                            data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/classInfoAction!combox.action'" />
                    </td>
                </tr>
                <tr>
                    <th>学科</th>
                     <td>
                        <input id="subjectId" name="subjectId" class="easyui-combobox"
                               data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=106',
                               valueField : 'id',
                               textField : 'name',
                               multiple : false,
                               editable : true,
                               panelHeight : '200'" />
                    </td>
                </tr>

                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="director_scoreCount_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="director_scoreCount_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="director_scoreCount_datagrid"></table>
    </div>
</div>
