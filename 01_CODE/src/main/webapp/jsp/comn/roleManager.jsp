<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var comn_roleManager_datagrid;
    $(function() {

        comn_roleManager_datagrid = $('#comn_roleManager_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/roleAction!datagrid.action',
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
                            sortName : 'name',
                            sortOrder : 'desc',
                            checkOnSelect : false,
                            selectOnCheck : true,
                            frozenColumns : [ [ {
                                title : '编号',
                                field : 'id',
                                width : 150,
                                sortable : true,
                                checkbox : true
                            }, {
                                title : '角色名称',
                                field : 'name',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                title : '备注',
                                field : 'detail',
                                width : 150,
                                sortable : true
                            }, {
                                title : '拥有权限ID',
                                field : 'menuIds',
                                width : 300,
                                hidden : true,
                                sortable : true
                            }, {
                                title : '拥有权限',
                                field : 'menuNames',
                                width : 300,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                                text : '增加',
                                iconCls : 'icon-add',
                                handler : function() {
                                    comn_roleManager_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_roleManager_remove();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_roleManager_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    comn_roleManager_datagrid.datagrid('unselectAll');
                                    comn_roleManager_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#comn_roleManager_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });

    });

    function comn_roleManager_appendFun() {
        $('#comn_roleManager_addDialog').dialog('open');
        $('#comn_roleManager_addForm').form('clear');
    }
    function comn_roleManager_append() {
        $('#comn_roleManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/roleAction!add.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    comn_roleManager_datagrid.datagrid('reload');
                    $('#comn_roleManager_addDialog').dialog('close');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }

    function comn_roleManager_editFun() {
        var rows = comn_roleManager_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            console.info(getList(rows[0].menuIds));
            $('#comn_roleManager_editForm').form('load', {
                id : rows[0].id,
                name : rows[0].name,
                detail : rows[0].detail,
                menuIds : getList(rows[0].menuIds)
            });
            $('#comn_roleManager_editDialog').dialog('open');
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要修改的记录'
            });
        }
    }
    function comn_roleManager_edit() {
        $('#comn_roleManager_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/roleAction!edit.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    comn_roleManager_datagrid.datagrid('reload');
                    $('#comn_roleManager_editDialog').dialog('close');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }

    function comn_roleManager_remove() {
        var rows = comn_roleManager_datagrid.datagrid('getChecked');
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
                                                url : '${pageContext.request.contextPath}/roleAction!remove.action',
                                                data : {
                                                    ids : ids.join(',')
                                                },
                                                dataType : 'json',
                                                success : function(obj) {
                                                    if (obj.success) {
                                                        comn_roleManager_datagrid
                                                                .datagrid('load');
                                                        comn_roleManager_datagrid
                                                                .datagrid('unselectAll');
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
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="comn_roleManager_datagrid"></table>
    </div>

    <div id="comn_roleManager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="comn_roleManager_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="comn_roleManager_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="comn_roleManager_editFun();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>

<div id="comn_roleManager_addDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'增加角色',buttons:[ {
                text : '增加',
                handler : function() {
                    comn_roleManager_append();
                }
            } ]">
    <form id="comn_roleManager_addForm" method="post">
        <table class="tableForm">
            <tr>
                <th style="width: 55px;">角色名称</th>
                <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写菜单名称'" style="width:323px;" /></td>
            </tr>
            <tr>
                <th>拥有权限</th>
                <td><input name="menuIds" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/menuAction!getTreeNode.action',lines : true,checkbox : true,multiple : true"
                    style="width:327px;" /></td>
            </tr>
            <tr>
                <th>备注</th>
                <td><textarea name="detail" style="height: 100px;"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="comn_roleManager_editDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'修改角色',buttons:[ {
                text : '修改',
                handler : function() {
                    comn_roleManager_edit();
                }
            } ]">
    <form id="comn_roleManager_editForm" method="post">
        <input type="hidden" name="id" />
        <table class="tableForm">
            <tr>
                <th style="width: 55px;">角色名称</th>
                <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写菜单名称'" style="width:323px;" /></td>
            </tr>
            <tr>
                <th>拥有权限</th>
                <td><input name="menuIds" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/menuAction!getTreeNode.action',lines : true,checkbox : true,multiple : true"
                    style="width:327px;" /></td>
            </tr>
            <tr>
                <th>备注</th>
                <td><textarea name="detail" style="height: 100px;"></textarea></td>
            </tr>
        </table>
    </form>
</div>
