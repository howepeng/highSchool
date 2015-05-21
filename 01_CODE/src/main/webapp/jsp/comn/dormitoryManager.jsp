<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        $('#comn_dormitoryManager_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/dormitoryAction!datagrid.action',
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
                                title : '名称',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                field : 'peopleCount',
                                title : '人数',
                                width : 150,
                                sortable : true
                            },{
                                field : 'dormitoryCount',
                                title : '房间个数',
                                width : 150,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                                text : '增加',
                                iconCls : 'icon-add',
                                handler : function() {
                                    comn_dormitoryManager_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_dormitoryManager_remove();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_dormitoryManager_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    $('#comn_dormitoryManager_datagrid').datagrid('unselectAll');
                                    $('#comn_dormitoryManager_datagrid').datagrid('uncheckAll');
                                }
                            }, '-'  ]
                        });

    });

    function comn_dormitoryManager_searchFun() {
        $('#comn_dormitoryManager_datagrid').datagrid('load',
                serializeObject($('#comn_dormitoryManager_searchForm')));

    }

    function comn_dormitoryManager_cleanFun() {
        $('#comn_dormitoryManager_layout input').val('');
        $('#comn_dormitoryManager_datagrid').datagrid('load', {});
    }
    function comn_dormitoryManager_appendFun() {
         $('<div id="dormitoryManagerAdd_Open" style="5px;"/>').dialog({
             href : 'jsp/comn/dormitoryManagerAdd.jsp',
             width : 500,
             height :160,
             modal : true,
             title : '添加宿舍信息',
             onLoad: function() {
                 $('#comn_dormitoryManager_addForm').form('clear');
                 } ,
             onClose : function() {
                 $(this).dialog('destroy');
             },
             buttons:[
                      {
                          text : '增加',
                          iconCls : 'icon-add',
                          handler : function() {
                              comn_dormitoryManager_add();
                          }
                      }
                  ]
             });
    }
    function comn_dormitoryManager_add() {
        var name = $("#comn_dormitoryManager_addForm").find("input[name^='name']");
        if (name.val().length == 0) {
            alert("请输入宿舍名称！");
            return;
        }
        $('#comn_dormitoryManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/dormitoryAction!add.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_dormitoryManager_datagrid').datagrid('load');
                    $('#dormitoryManagerAdd_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }

    function comn_dormitoryManager_remove() {
        var rows = $('#comn_dormitoryManager_datagrid').datagrid('getChecked');
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
                                        url : '${pageContext.request.contextPath}/dormitoryAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#comn_dormitoryManager_datagrid').datagrid('load');
                                                $('#comn_dormitoryManager_datagrid').datagrid('unselectAll');
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
    function comn_dormitoryManager_editFun() {
        var rows = $('#comn_dormitoryManager_datagrid').datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('<div id="dormitoryManagerEdit_Open" style="5px;"/>').dialog({
                href : 'jsp/comn/dormitoryManagerAdd.jsp',
                width : 500,
                height :160,
                modal : true,
                title : '修改宿舍',
                onLoad: function() {
                    $('#comn_dormitoryManager_addForm').form('load', {
                        id : rows[0].id,
                        name : rows[0].name,
                        peopleCount : rows[0].peopleCount,
                        dormitoryCount : rows[0].dormitoryCount,
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
                                 comn_dormitoryManager_edit();

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
    function comn_dormitoryManager_edit() {
        var name = $("#comn_dormitoryManager_addForm").find("input[name^='name']");
        if (name.val().length == 0) {
            alert("请输入宿舍名称！");
            return;
        }
        $('#comn_dormitoryManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/dormitoryAction!edit.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_dormitoryManager_datagrid').datagrid('load');
                    $('#dormitoryManagerEdit_Open').dialog('destroy');
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
        <table id="comn_dormitoryManager_datagrid"></table>
    </div>

    <div id="comn_dormitoryManager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="comn_dormitoryManager_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="comn_dormitoryManager_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="comn_dormitoryManager_edit();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>
