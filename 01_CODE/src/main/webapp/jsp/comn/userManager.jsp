<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        $('#comn_userManager_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/userAction!datagrid.action',
                            fit : true,
                            border : false,
                            pagination : true,
                            idField : 'id',
                            fitColumns : true,
                            pageSize : 5,
                            pageList : [ 5, 10, 20, 30, 40, 50 ],
                            rownumbers : true,
                            sortName : 'username',
                            sortOrder : 'desc',
                            selectOnCheck : false,
                            checkOnSelect : false,
                            frozenColumns : [ [ {
                                field : 'id',
                                title : '编号',
                                width : 150,
                                checkbox : true
                            }, {
                                field : 'username',
                                title : '登录名',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                field : 'name',
                                title : '姓名',
                                width : 150
                            },{
                                field : 'password',
                                title : '密码',
                                width : 150,
                                formatter : function(value, row, index) {
                                    return '******';
                                }
                            //}, {
                            //    field : 'createdatetime',
                            //    title : '创建时间',
                            //    width : 150,
                            //    sortable : true
                            //}, {
                            //    field : 'modifydatetime',
                            //    title : '最后修改时间',
                            //    width : 150,
                            //    sortable : true
                            }, {
                                field : 'roleIds',
                                title : '所属角色Id',
                                width : 150,
                                hidden : true
                            }, {
                                field : 'roleNames',
                                title : '所属角色',
                                width : 150
                            } ] ],
                            toolbar : [ {
                                text : '增加',
                                iconCls : 'icon-add',
                                handler : function() {
                                    comn_userManager_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_userManager_removeUser();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_userManager_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    $('#comn_userManager_datagrid').datagrid('unselectAll');
                                    $('#comn_userManager_datagrid').datagrid('uncheckAll');
                                }
                            }, '-'  ]
                        });

    });

    function comn_userManager_searchFun() {
        $('#comn_userManager_datagrid').datagrid('load',
                serializeObject($('#comn_userManager_searchForm')));

    }

    function comn_userManager_cleanFun() {
        $('#comn_userManager_layout input').val('');
        $('#comn_userManager_datagrid').datagrid('load', {});
    }
    function comn_userManager_appendFun() {
         $('<div id="userManagerAdd_Open" style="5px;"/>').dialog({
             href : 'jsp/comn/userManagerAdd.jsp',
             width : 500,
             height :160,
             modal : true,
             title : '添加用户',
             onLoad: function() {
                 $('#comn_userManager_addForm').form('clear');
                 } ,
             onClose : function() {
                 $(this).dialog('destroy');
             },
             buttons:[
                      {
                          text : '增加',
                          iconCls : 'icon-add',
                          handler : function() {
                              comn_userManager_addUser();
                          }
                      }
                  ]
             });
    }
    function comn_userManager_addUser() {
        $('#comn_userManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/userAction!add.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_userManager_datagrid').datagrid('load');
                    $('#userManagerAdd_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }

    function comn_userManager_removeUser() {
        var rows = $('#comn_userManager_datagrid').datagrid('getChecked');
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
                                    $
                                            .ajax({
                                                url : '${pageContext.request.contextPath}/userAction!remove.action',
                                                data : {
                                                    ids : ids.join(',')
                                                },
                                                dataType : 'json',
                                                success : function(obj) {
                                                    if (obj.success) {
                                                        $(
                                                                '#comn_userManager_datagrid')
                                                                .datagrid(
                                                                        'load');
                                                        $(
                                                                '#comn_userManager_datagrid')
                                                                .datagrid(
                                                                        'unselectAll');
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
    function comn_userManager_editFun() {
        var rows = $('#comn_userManager_datagrid').datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('<div id="userManagerEdit_Open" style="5px;"/>').dialog({
                href : 'jsp/comn/userManagerEdit.jsp',
                width : 500,
                height :160,
                modal : true,
                title : '修改用户',
                onLoad: function() {
                    $('#comn_userManager_editForm').form('load', {
                        id : rows[0].id,
                        username : rows[0].username,
                        roleIds : getList(rows[0].roleIds)
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
                                 comn_userManager_editUser();

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
    function comn_userManager_editUser() {
        $('#comn_userManager_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/userAction!edit.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#comn_userManager_datagrid').datagrid('load');
                    $('#userManagerEdit_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }
</script>
<div id="comn_userManager_layout" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false,title:'过滤条件'" style="height: 85px;overflow: hidden;" align="left">
        <form id="comn_userManager_searchForm">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>用户名</th>
                    <td><input name="username" style="width:317px;" />
                    </td>
                </tr>
                <tr>
                    <th>姓名</th>
                    <td><input name="name" style="width:317px;" /><a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_userManager_searchFun();">过滤</a><a href="javascript:void(0);"
                        class="easyui-linkbutton" onclick="comn_userManager_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false">
        <table id="comn_userManager_datagrid"></table>
    </div>
</div>
