<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var comn_scoreCount_datagrid;
    $(function() {
        comn_scoreCount_datagrid = $('#comn_scoreCount_datagrid')
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
                            sortName : 'gradeRank',
                            sortOrder : 'asc',
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
                                width : 80,
                                sortable : true
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
                                text : '月考成绩计算',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_scoreCount();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    director_monthScore_remove();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    comn_scoreCount_datagrid.datagrid('unselectAll');
                                    comn_scoreCount_datagrid.datagrid('uncheckAll');
                                }
                            },'-', {
                                text : '导出',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    comn_scoreCount_download_All_Info_Fun();
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

    function comn_scoreCount_searchFun() {
        comn_scoreCount_datagrid.datagrid('load',
                serializeObject($('#comn_scoreCount_SearchForm')));
    }

    function comn_scoreCount_cleanFun() {
        $('#comn_scoreCount_SearchForm input').val('');
        comn_scoreCount_datagrid.datagrid('load', {});
    }

    function comn_scoreCount() {
        $('<div id="comnScoreCount_Open" style="5px;"/>').dialog({
            href : 'jsp/comn/scoreCount.jsp',
            width : 550,
            height :150,
            modal : true,
            title : '月考成绩计算',
            onLoad: function() {
                $('#comn_scoreCountForm').form('clear');
                } ,
            onClose : function() {
                $(this).dialog('destroy');
            },
            buttons:[
                     {
                         text : '计算',
                         iconCls : 'icon-add',
                         handler : function() {
                             comn_scoreCount_calculateFun();
                         }
                     }
                 ]
            });
    }

    function comn_scoreCount_calculateFun() {
        $('#comn_scoreCountForm').form('submit', {
            url : '${pageContext.request.contextPath}/scoreCountAction!calculate.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    comn_scoreCount_datagrid.datagrid('load',
                            serializeObject($('#comn_scoreCount_datagrid')));
                    $('#comnScoreCount_Open').dialog('destroy');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }

    function director_monthScore_remove(){
        var rows = $('#comn_scoreCount_datagrid').datagrid('getChecked');
        var ids = [];
        if (rows.length > 0) {
            $.messager
                    .confirm(
                            '确认',
                            '您是否删除当前选中项目？',
                            function(r) {
                                if (r) {
                                    for ( var i = 0; i < rows.length; i++) {
                                        ids.push(rows[i].id);
                                    }
                                    $.ajax({
                                        url : '${pageContext.request.contextPath}/monthScoreAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#comn_scoreCount_datagrid').datagrid('load');
                                                $('#comn_scoreCount_datagrid').datagrid('unselectAll');
                                            }
                                            $.messager.show({
                                                title : '提示',
                                                msg : obj.msg
                                            });
                                        }
                                    });
                                }
                            });
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要删除的记录'
            });
        }
    }

    function comn_scoreCount_download_All_Info_Fun(){
        var monthTime1 = $('#comn_scoreCount_SearchForm').find("input[name^='monthTime1']").val();
        var monthTime2 = $('#comn_scoreCount_SearchForm').find("input[name^='monthTime2']").val();
        var professionalId = $('#comn_scoreCount_SearchForm').find("input[name^='professionalId']").val();
        var classInfoId = $('#comn_scoreCount_SearchForm').find("input[name^='classInfoId']").val();
        var subjectId = $('#comn_scoreCount_SearchForm').find("input[name^='subjectId']").val();
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
        <form id="comn_scoreCount_SearchForm" method="post">
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
                              panelHeight : '200'" />
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
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_scoreCount_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_scoreCount_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="comn_scoreCount_datagrid"></table>
    </div>
</div>
