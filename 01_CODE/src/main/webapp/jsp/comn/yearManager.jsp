<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        $('#comn_yearManager_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/yearAction!datagrid.action',
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
                                title : '学年名称',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                field : 'showIsDefault',
                                title : '是否当前学年',
                                width : 150,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                                text : '增加',
                                iconCls : 'icon-add',
                                handler : function() {
                                    comn_yearManager_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_yearManager_remove();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_yearManager_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    $('#comn_yearManager_datagrid').datagrid('unselectAll');
                                    $('#comn_yearManager_datagrid').datagrid('uncheckAll');
                                }
                            }, '-'  ]
                        });

    });

    function comn_yearManager_searchFun() {
        $('#comn_yearManager_datagrid').datagrid('load',
                serializeObject($('#comn_yearManager_searchForm')));

    }

    function comn_yearManager_cleanFun() {
        $('#comn_yearManager_layout input').val('');
        $('#comn_yearManager_datagrid').datagrid('load', {});
    }
    function comn_yearManager_appendFun() {
         $('<div id="yearManagerAdd_Open" style="5px;"/>').dialog({
             href : 'jsp/comn/yearManagerAdd.jsp',
             width : 500,
             height :160,
             modal : true,
             title : '添加学年',
             onLoad: function() {
                 $('#comn_yearManager_addForm').form('clear');
                 } ,
             onClose : function() {
                 $(this).dialog('destroy');
             },
             buttons:[
                      {
                          text : '增加',
                          iconCls : 'icon-add',
                          handler : function() {
                              comn_yearManager_add();
                          }
                      }
                  ]
             });
    }
    function comn_yearManager_add() {
        var name = $("#comn_yearManager_addForm").find("input[name^='name']");
        if (name.val().length == 0) {
            alert("请输入学年名称！");
            return;
        }
        var isDefault = $("#comn_yearManager_addForm").find("input[name^='isDefault']");
        if (isDefault.val().length == 0) {
            alert("请输入是否是当前学年！");
            return;
        }
        $('#comn_yearManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/yearAction!add.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_yearManager_datagrid').datagrid('load');
                    $('#yearManagerAdd_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }

    function comn_yearManager_remove() {
        var rows = $('#comn_yearManager_datagrid').datagrid('getChecked');
        var ids = [];
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行删除'
            });
        } else if (rows.length == 1) {
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
                                        url : '${pageContext.request.contextPath}/yearAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#comn_yearManager_datagrid').datagrid('load');
                                                $('#comn_yearManager_datagrid').datagrid('unselectAll');
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
    function comn_yearManager_editFun() {
        var rows = $('#comn_yearManager_datagrid').datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('<div id="yearManagerEdit_Open" style="5px;"/>').dialog({
                href : 'jsp/comn/yearManagerAdd.jsp',
                width : 500,
                height :160,
                modal : true,
                title : '修改学年',
                onLoad: function() {
                    $('#comn_yearManager_addForm').form('load', {
                        id : rows[0].id,
                        name : rows[0].name,
                        isDefault : rows[0].isDefault
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
                                 comn_yearManager_edit();

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
    function comn_yearManager_edit() {
        var name = $("#comn_yearManager_addForm").find("input[name^='name']");
        if (name.val().length == 0) {
            alert("请输入学年名称！");
            return;
        }
        var isDefault = $("#comn_yearManager_addForm").find("input[name^='isDefault']");
        if (isDefault.val().length == 0) {
            alert("请输入是否是当前学年！");
            return;
        }
        $('#comn_yearManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/yearAction!edit.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_yearManager_datagrid').datagrid('load');
                    $('#yearManagerEdit_Open').dialog('destroy');
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
        <table id="comn_yearManager_datagrid"></table>
    </div>

    <div id="comn_yearManager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="comn_yearManager_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="comn_yearManager_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="comn_yearManager_edit();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>
