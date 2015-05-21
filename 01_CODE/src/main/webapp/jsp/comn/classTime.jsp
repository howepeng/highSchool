<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        $('#comn_classTimeManager_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/classTimeAction!datagrid.action',
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
                                sortable : true,
                                checkbox : true
                            }, {
                                field : 'name',
                                title : '课程名称',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                field : 'showStartTime',
                                title : '开始时间',
                                width : 150,
                                sortable : true
                            },{
                                field : 'showEndTime',
                                title : '结束时间',
                                width : 150,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                                text : '增加',
                                iconCls : 'icon-add',
                                handler : function() {
                                    comn_classTimeManager_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_classTimeManager_remove();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_classTimeManager_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    $('#comn_classTimeManager_datagrid').datagrid('unselectAll');
                                    $('#comn_classTimeManager_datagrid').datagrid('uncheckAll');
                                }
                            }, '-'  ]
                        });

    });

    function comn_classTimeManager_searchFun() {
        $('#comn_classTimeManager_datagrid').datagrid('load',
                serializeObject($('#comn_classTimeManager_searchForm')));

    }

    function comn_classTimeManager_cleanFun() {
        $('#comn_classTimeManager_layout input').val('');
        $('#comn_classTimeManager_datagrid').datagrid('load', {});
    }
    function comn_classTimeManager_appendFun() {
         $('<div id="classTimeManagerAdd_Open" style="5px;"/>').dialog({
             href : 'jsp/comn/classTimeManagerAdd.jsp',
             width : 500,
             height :160,
             modal : true,
             title : '添加课程时间',
             onLoad: function() {
                 $('#comn_classTimeManager_addForm').form('clear');
                 } ,
             onClose : function() {
                 $(this).dialog('destroy');
             },
             buttons:[
                      {
                          text : '增加',
                          iconCls : 'icon-add',
                          handler : function() {
                              comn_classTimeManager_add();
                          }
                      }
                  ]
             });
    }
    function comn_classTimeManager_add() {
        $('#comn_classTimeManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/classTimeAction!add.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_classTimeManager_datagrid').datagrid('load');
                    $('#classTimeManagerAdd_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }

    function comn_classTimeManager_remove() {
        var rows = $('#comn_classTimeManager_datagrid').datagrid('getChecked');
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
                                        url : '${pageContext.request.contextPath}/classTimeAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#comn_classTimeManager_datagrid').datagrid('load');
                                                $('#comn_classTimeManager_datagrid').datagrid('unselectAll');
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
    function comn_classTimeManager_editFun() {
        var rows = $('#comn_classTimeManager_datagrid').datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('<div id="classTimeManagerEdit_Open" style="5px;"/>').dialog({
                href : 'jsp/comn/classTimeManagerEdit.jsp',
                width : 500,
                height :160,
                modal : true,
                title : '修改课程时间',
                onLoad: function() {
                    $('#comn_classTimeManager_editForm').form('load', {
                        id : rows[0].id,
                        name : rows[0].name,
                        startTime : rows[0].showStartTime,
                        endTime : rows[0].showEndTime
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
                                 comn_classTimeManager_edit();

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
    function comn_classTimeManager_edit() {
        $('#comn_classTimeManager_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/classTimeAction!edit.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_classTimeManager_datagrid').datagrid('load');
                    $('#classTimeManagerEdit_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="comn_classTimeManager_datagrid"></table>
    </div>

    <div id="comn_classTimeManager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="comn_classTimeManager_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="comn_classTimeManager_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="comn_classTimeManager_edit();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>
