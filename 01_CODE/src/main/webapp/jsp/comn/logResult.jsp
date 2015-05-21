<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        $('#comn_logResultManager_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/logResultAction!datagrid.action',
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
                            } ] ],
                            columns : [ [ {
                                field : 'name',
                                title : '名称',
                                width : 150,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                            }, '-'  ]
                        });

    });

    function comn_logResultManager_searchFun() {
        $('#comn_logResultManager_datagrid').datagrid('load',
                serializeObject($('#comn_logResultManager_searchForm')));

    }

    function comn_logResultManager_cleanFun() {
        $('#comn_logResultManager_layout input').val('');
        $('#comn_logResultManager_datagrid').datagrid('load', {});
    }
    function comn_logResultManager_appendFun() {
         $('<div id="logResultManagerAdd_Open" style="5px;"/>').dialog({
             href : 'jsp/comn/logResultManagerAdd.jsp',
             width : 650,
             height :260,
             modal : true,
             title : '添加课程时间',
             onLoad: function() {
                 $('#comn_logResultManager_addForm').form('clear');
                 } ,
             onClose : function() {
                 $(this).dialog('destroy');
             },
             buttons:[
                      {
                          text : '增加',
                          iconCls : 'icon-add',
                          handler : function() {
                              comn_logResultManager_add();
                          }
                      }
                  ]
             });
    }
    function comn_logResultManager_add() {
        $('#comn_logResultManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/logResultAction!add.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_logResultManager_datagrid').datagrid('load');
                    $('#logResultManagerAdd_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }

    function comn_logResultManager_remove() {
        var rows = $('#comn_logResultManager_datagrid').datagrid('getChecked');
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
                                        url : '${pageContext.request.contextPath}/logResultAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#comn_logResultManager_datagrid').datagrid('load');
                                                $('#comn_logResultManager_datagrid').datagrid('unselectAll');
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
    function comn_logResultManager_editFun() {
        var rows = $('#comn_logResultManager_datagrid').datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('<div id="logResultManagerEdit_Open" style="5px;"/>').dialog({
                href : 'jsp/comn/logResultManagerEdit.jsp',
                width : 650,
                height :260,
                modal : true,
                title : '修改日志类型',
                onLoad: function() {
                    $('#comn_logResultManager_editForm').form('load', {
                        id : rows[0].id,
                        name : rows[0].name
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
                                 comn_logResultManager_edit();

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
    function comn_logResultManager_edit() {
        $('#comn_logResultManager_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/logResultAction!edit.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_logResultManager_datagrid').datagrid('load');
                    $('#logResultManagerEdit_Open').dialog('destroy');
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
        <table id="comn_logResultManager_datagrid"></table>
    </div>

    <div id="comn_logResultManager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="comn_logResultManager_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="comn_logResultManager_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="comn_logResultManager_edit();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>
