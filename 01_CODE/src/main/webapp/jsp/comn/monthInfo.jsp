<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        $('#comn_monthInfoManager_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/monthInfoAction!datagrid.action',
                            fit : true,
                            border : false,
                            pagination : true,
                            idField : 'id',
                            fitColumns : true,
                            pageSize : 5,
                            pageList : [ 5, 10, 20, 30, 40, 50 ],
                            rownumbers : true,
                            sortName : 'value',
                            sortOrder : 'asc',
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
                                title : '月考名称',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                field : 'value',
                                title : '代表数值',
                                width : 150,
                                sortable : true
                            }] ],
                            toolbar : [ {
                                text : '增加',
                                iconCls : 'icon-add',
                                handler : function() {
                                    comn_monthInfoManager_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_monthInfoManager_remove();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_monthInfoManager_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    $('#comn_monthInfoManager_datagrid').datagrid('unselectAll');
                                    $('#comn_monthInfoManager_datagrid').datagrid('uncheckAll');
                                }
                            }, '-'  ]
                        });

    });

    function comn_monthInfoManager_searchFun() {
        $('#comn_monthInfoManager_datagrid').datagrid('load',
                serializeObject($('#comn_monthInfoManager_searchForm')));

    }

    function comn_monthInfoManager_cleanFun() {
        $('#comn_monthInfoManager_layout input').val('');
        $('#comn_monthInfoManager_datagrid').datagrid('load', {});
    }
    function comn_monthInfoManager_appendFun() {
         $('<div id="monthInfoManagerAdd_Open" style="5px;"/>').dialog({
             href : 'jsp/comn/monthInfoManagerAdd.jsp',
             width : 500,
             height :160,
             modal : true,
             title : '添加月考信息',
             onLoad: function() {
                 $('#comn_monthInfoManager_addForm').form('clear');
                 } ,
             onClose : function() {
                 $(this).dialog('destroy');
             },
             buttons:[
                      {
                          text : '增加',
                          iconCls : 'icon-add',
                          handler : function() {
                              comn_monthInfoManager_add();
                          }
                      }
                  ]
             });
    }
    function comn_monthInfoManager_add() {
        $('#comn_monthInfoManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/monthInfoAction!add.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_monthInfoManager_datagrid').datagrid('load');
                    $('#monthInfoManagerAdd_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }

    function comn_monthInfoManager_remove() {
        var rows = $('#comn_monthInfoManager_datagrid').datagrid('getChecked');
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
                                        url : '${pageContext.request.contextPath}/monthInfoAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#comn_monthInfoManager_datagrid').datagrid('load');
                                                $('#comn_monthInfoManager_datagrid').datagrid('unselectAll');
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
    function comn_monthInfoManager_editFun() {
        var rows = $('#comn_monthInfoManager_datagrid').datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('<div id="monthInfoManagerEdit_Open" style="5px;"/>').dialog({
                href : 'jsp/comn/monthInfoManagerEdit.jsp',
                width : 500,
                height :160,
                modal : true,
                title : '修改课程时间',
                onLoad: function() {
                    $('#comn_monthInfoManager_editForm').form('load', {
                        id : rows[0].id,
                        name : rows[0].name,
                        value : rows[0].value
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
                                 comn_monthInfoManager_edit();

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
    function comn_monthInfoManager_edit() {
        $('#comn_monthInfoManager_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/monthInfoAction!edit.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_monthInfoManager_datagrid').datagrid('load');
                    $('#monthInfoManagerEdit_Open').dialog('destroy');
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
        <table id="comn_monthInfoManager_datagrid"></table>
    </div>

    <div id="comn_monthInfoManager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="comn_monthInfoManager_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="comn_monthInfoManager_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="comn_monthInfoManager_edit();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>
