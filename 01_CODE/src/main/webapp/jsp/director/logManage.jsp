<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    var director_logManager_datagrid;
    $(function() {
        init();
    });
    function init() {
        director_logManager_datagrid = $('#director_logManager_datagrid').datagrid(
                {
                    url : '${pageContext.request.contextPath}/logManagerAction!datagrid.action',
                    fit : true,
                    border : false,
                    pagination : true,
                    idField : 'id',
                    fitColumns : true,
                    pageSize : 5,
                    pageList : [ 5, 10, 20, 30, 40, 50 ],
                    rownumbers : true,
                    sortName : 'id',
                    sortOrder : 'desc',
                    selectOnCheck : false,
                    checkOnSelect : false,
                    frozenColumns : [ [ {
                        field : 'id',
                        title : '编号',
                        width : 150,
                        checkbox : true
                    } ] ],
                    columns : [ [ {
                        field : 'typeName',
                        title : '日志类型',
                        width : 150,
                        sortable : true
                    },{
                        field : 'className',
                        title : '班级',
                        width : 150
                    },{
                        field : 'studentName',
                        title : '学生姓名',
                        width : 150
                    },{
                        field : 'classTimeName',
                        title : '课程',
                        width : 150
                    },{
                        field : 'showDate',
                        title : '日期',
                        width : 150
                    },{
                        field : 'showStartTime',
                        title : '开始时间',
                        width : 150
                    },{
                        field : 'showEndTime',
                        title : '结束时间',
                        width : 150
                    },{
                        field : 'resultName',
                        title : '日志结果',
                        width : 150
                    },{
                        field : 'remark',
                        title : '备注',
                        width : 150
                    } ] ],
                    toolbar : [ {
                        text : '增加',
                        iconCls : 'icon-add',
                        handler : function() {
                            director_logManager_appendFun();
                        }
                    }, '-', {
                        text : '删除',
                        iconCls : 'icon-remove',
                        handler : function() {
                            director_logManager_remove();
                        }
                    }, '-', {
                        text : '修改',
                        iconCls : 'icon-edit',
                        handler : function() {
                            director_logManager_editFun();
                        }
                    }, '-', {
                        text : '取消选中',
                        iconCls : 'icon-undo',
                        handler : function() {
                            $('#director_logManager_datagrid').datagrid('unselectAll');
                            $('#director_logManager_datagrid').datagrid('uncheckAll');
                        }
                    }, '-'  ]
                });
    }
    function director_logManager_searchFun() {
        $('#director_logManager_datagrid').datagrid('load',
                serializeObject($('#director_logManager_searchForm')));

    }

    function director_logManager_cleanFun() {
        $('#director_logManager_layout input').val('');
        $('#director_logManager_datagrid').datagrid('load', {});
    }
    function director_logManager_appendFun() {
         $('<div id="logManagerAdd_Open" style="5px;"/>').dialog({
             href : 'jsp/director/logManagerAdd.jsp',
             width : 550,
             height :260,
             modal : true,
             title : '添加日志',
             onLoad: function() {
                 $('#director_logManager_addForm').form('clear');
                 } ,
             onClose : function() {
                 $(this).dialog('destroy');
             },
             buttons:[
                      {
                          text : '增加',
                          iconCls : 'icon-add',
                          handler : function() {
                              director_logManager_add();
                          }
                      }
                  ]
             });
    }
    function director_logManager_add() {
        var typeId = $("#director_logManager_addForm").find("input[name^='typeId']");
        if (typeId.val().length == 0) {
            alert("请输入日志类型！");
            return;
        }
        var classTimeId = $("#director_logManager_addForm").find("input[name^='classTimeId']");
        if (classTimeId.val().length == 0) {
            alert("请输入课程！");
            return;
        }
        var date = $("#director_logManager_addForm").find('#showDate');
        if (date.val().length == 0) {
            alert("请输入发生日期！");
            return;
        }
        var startTime = $("#director_logManager_addForm").find('#showStartTime');
        if (startTime.val().length == 0) {
            alert("请输入开始时间！");
            return;
        }
        var endTime = $("#director_logManager_addForm").find('#showEndTime');
        if (endTime.val().length == 0) {
            alert("请输入结束时间！");
            return;
        }
        var classId = $("#director_logManager_addForm").find("input[name^='classId']");
        if (classId.val().length == 0) {
            alert("请输入班级！");
            return;
        }
        var studentId = $("#director_logManager_addForm").find("input[name^='studentId']");
        if (studentId.val().length == 0) {
            alert("请输入学生！");
            return;
        }
        var resultId = $("#director_logManager_addForm").find("input[name^='resultId']");
        if (resultId.val().length == 0) {
            alert("请输入日志结果！");
            return;
        }
        $("#director_logManager_addForm").find('#date').val($("#director_logManager_addForm").find('#showDate').val());
        $("#director_logManager_addForm").find('#startTime').val($("#director_logManager_addForm").find('#showStartTime').val());
        $("#director_logManager_addForm").find('#endTime').val($("#director_logManager_addForm").find('#showEndTime').val());
        $('#director_logManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/logManagerAction!add.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#director_logManager_datagrid').datagrid('load');
                    $('#logManagerAdd_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }

    function director_logManager_remove() {
        var rows = $('#director_logManager_datagrid').datagrid('getChecked');
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
                                        url : '${pageContext.request.contextPath}/logManagerAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#director_logManager_datagrid').datagrid('load');
                                                $('#director_logManager_datagrid').datagrid('unselectAll');
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
    function director_logManager_editFun() {
        var rows = $('#director_logManager_datagrid').datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('<div id="logManagerEdit_Open" style="5px;"/>').dialog({
                href : 'jsp/director/logManagerAdd.jsp',
                width : 650,
                height :260,
                modal : true,
                title : '修改日志类型',
                onLoad: function() {
                    $('#director_logManager_addForm').form('load', {
                        id : rows[0].id,
                        typeId : rows[0].typeId,
                        classTimeId : rows[0].classTimeId,
                        date : rows[0].date,
                        startTime : rows[0].startTime,
                        endTime : rows[0].endTime,
                        showDate : rows[0].showDate,
                        showStartTime : rows[0].showStartTime,
                        showEndTime : rows[0].showEndTime,
                        classId : rows[0].classId,
                        studentId : rows[0].studentId,
                        resultId : rows[0].resultId,
                        remark : rows[0].remark
                    });
                    } ,
                onClose : function() {
                    $(this).dialog('destroy');
                },
                buttons:[
                         {
                             text : '修改',
                             iconCls : 'icon-add',
                             handler : function() {
                                 director_logManager_edit();

                             }
                         }
                     ]
                });

        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要修改的记录'
            });
        }
    }
    function director_logManager_edit() {
        var typeId = $("#director_logManager_addForm").find("input[name^='typeId']");
        if (typeId.val().length == 0) {
            alert("请输入日志类型！");
            return;
        }
        var classTimeId = $("#director_logManager_addForm").find("input[name^='classTimeId']");
        if (classTimeId.val().length == 0) {
            alert("请输入课程！");
            return;
        }
        var date = $("#director_logManager_addForm").find('#showDate');
        if (date.val().length == 0) {
            alert("请输入发生日期！");
            return;
        }
        var startTime = $("#director_logManager_addForm").find('#showStartTime');
        if (startTime.val().length == 0) {
            alert("请输入开始时间！");
            return;
        }
        var endTime = $("#director_logManager_addForm").find('#showEndTime');
        if (endTime.val().length == 0) {
            alert("请输入结束时间！");
            return;
        }
        var classId = $("#director_logManager_addForm").find("input[name^='classId']");
        if (classId.val().length == 0) {
            alert("请输入班级！");
            return;
        }
        var studentId = $("#director_logManager_addForm").find("input[name^='studentId']");
        if (studentId.val().length == 0) {
            alert("请输入学生！");
            return;
        }
        var resultId = $("#director_logManager_addForm").find("input[name^='resultId']");
        if (resultId.val().length == 0) {
            alert("请输入日志结果！");
            return;
        }
        $("#director_logManager_addForm").find('#date').val($("#director_logManager_addForm").find('#showDate').val());
        $("#director_logManager_addForm").find('#startTime').val($("#director_logManager_addForm").find('#showStartTime').val());
        $("#director_logManager_addForm").find('#endTime').val($("#director_logManager_addForm").find('#showEndTime').val());
        $('#director_logManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/logManagerAction!edit.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#director_logManager_datagrid').datagrid('load');
                    $('#logManagerEdit_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }
    function director_logManager_searchForm() {
        director_logManager_datagrid.datagrid('load',
                serializeObject($('#director_logManager_searchForm')));
    }
    function director_logManager_clean_searchForm() {
        $('#director_logManager_searchForm input').val('');
        director_logManager_datagrid.datagrid('load', {});
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'过滤条件'" style="height: 120px;overflow: hidden;" align="left">
        <form id="director_logManager_searchForm">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>姓名</th>
                    <td>
                        <input id = "studentId" name="studentId" class="easyui-combobox"
                               data-options="url : '${pageContext.request.contextPath}/studentAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : true,
                                    panelHeight : 'auto'" />
                    </td>
                    <th>班级</th>
                    <td>
                        <input id = "classId" name="classId" class="easyui-combobox"
                               data-options="url : '${pageContext.request.contextPath}/logResultAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : false,
                                    panelHeight : 'auto'" />
                    </td>
                    <th>课程</th>
                    <td>
                        <input id = "classTimeId" name="classTimeId" class="easyui-combobox"
                               data-options="url : '${pageContext.request.contextPath}/classTimeAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : false,
                                    panelHeight : 'auto'" />
                    </td>
                </tr>
                <tr>
                    <th>日期</th>
                    <td>
                        <input name="date" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                    </td>
                    <th>时间</th>
                    <td>
                        <input name="startTime" type="text" style="width:75px;" onfocus="WdatePicker({dateFmt:'HH:mm:ss',realDateFmt:'HH:mm:ss'})" readonly="readonly"/>至
                        <input name="endTime" type="text" style="width:75px;" onfocus="WdatePicker({dateFmt:'HH:mm:ss',realDateFmt:'HH:mm:ss'})" readonly="readonly"/>
                    </td>
                    <th>日志结果</th>
                    <td>
                        <input id = "resultId" name="resultId" class="easyui-combobox"
                               data-options="url : '${pageContext.request.contextPath}/logResultAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : false,
                                    panelHeight : 'auto'" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="director_logManager_searchForm();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="director_logManager_clean_searchForm();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="director_logManager_datagrid"></table>
    </div>
    <div id="director_logManager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="director_logManager_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="director_logManager_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="director_logManager_edit();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>