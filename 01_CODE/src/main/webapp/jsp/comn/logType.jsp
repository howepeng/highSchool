<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        $('#comn_logTypeManager_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/logTypeAction!datagrid.action',
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
                            }, {
                                field : 'name',
                                title : '类型名称',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                field : 'typeName',
                                title : '方式',
                                width : 150
                            },{
                                field : 'modeName',
                                title : '模式',
                                width : 150
                            },{
                                field : 'count',
                                title : '次数',
                                width : 150
                            },{
                                field : 'score',
                                title : '积分',
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
                                    comn_logTypeManager_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_logTypeManager_remove();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_logTypeManager_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    $('#comn_logTypeManager_datagrid').datagrid('unselectAll');
                                    $('#comn_logTypeManager_datagrid').datagrid('uncheckAll');
                                }
                            }, '-'  ]
                        });

    });

    function comn_logTypeManager_searchFun() {
        $('#comn_logTypeManager_datagrid').datagrid('load',
                serializeObject($('#comn_logTypeManager_searchForm')));

    }

    function comn_logTypeManager_cleanFun() {
        $('#comn_logTypeManager_layout input').val('');
        $('#comn_logTypeManager_datagrid').datagrid('load', {});
    }
    function comn_logTypeManager_appendFun() {
         $('<div id="logTypeManagerAdd_Open" style="5px;"/>').dialog({
             href : 'jsp/comn/logTypeManagerAdd.jsp',
             width : 650,
             height :260,
             modal : true,
             title : '添加课程时间',
             onLoad: function() {
                 $('#comn_logTypeManager_addForm').form('clear');
                 } ,
             onClose : function() {
                 $(this).dialog('destroy');
             },
             buttons:[
                      {
                          text : '增加',
                          iconCls : 'icon-add',
                          handler : function() {
                              comn_logTypeManager_add();
                          }
                      }
                  ]
             });
    }
    function comn_logTypeManager_add() {
        $('#comn_logTypeManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/logTypeAction!add.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_logTypeManager_datagrid').datagrid('load');
                    $('#logTypeManagerAdd_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }

    function comn_logTypeManager_remove() {
        var rows = $('#comn_logTypeManager_datagrid').datagrid('getChecked');
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
                                        url : '${pageContext.request.contextPath}/logTypeAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#comn_logTypeManager_datagrid').datagrid('load');
                                                $('#comn_logTypeManager_datagrid').datagrid('unselectAll');
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
    function comn_logTypeManager_editFun() {
        var rows = $('#comn_logTypeManager_datagrid').datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('<div id="logTypeManagerEdit_Open" style="5px;"/>').dialog({
                href : 'jsp/comn/logTypeManagerEdit.jsp',
                width : 650,
                height :260,
                modal : true,
                title : '修改日志类型',
                onLoad: function() {
                    $('#comn_logTypeManager_editForm').form('load', {
                        id : rows[0].id,
                        name : rows[0].name,
                        typeId : rows[0].typeId,
                        modeId : rows[0].modeId,
                        count : rows[0].count,
                        score : rows[0].score,
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
                                 comn_logTypeManager_edit();

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
    function comn_logTypeManager_edit() {
        $('#comn_logTypeManager_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/logTypeAction!edit.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_logTypeManager_datagrid').datagrid('load');
                    $('#logTypeManagerEdit_Open').dialog('destroy');
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
        <table id="comn_logTypeManager_datagrid"></table>
    </div>

    <div id="comn_logTypeManager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="comn_logTypeManager_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="comn_logTypeManager_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="comn_logTypeManager_edit();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>
