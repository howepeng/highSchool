<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    var treegrid;
    var iconData;
    $(function() {

        iconData = [ {
            value : '',
            text : '默认'
        }, {
            value : 'icon-add',
            text : 'icon-add'
        }, {
            value : 'icon-edit',
            text : 'icon-edit'
        }, {
            value : 'icon-remove',
            text : 'icon-remove'
        }, {
            value : 'icon-save',
            text : 'icon-save'
        }, {
            value : 'icon-cut',
            text : 'icon-cut'
        }, {
            value : 'icon-ok',
            text : 'icon-ok'
        }, {
            value : 'icon-no',
            text : 'icon-no'
        }, {
            value : 'icon-cancel',
            text : 'icon-cancel'
        }, {
            value : 'icon-reload',
            text : 'icon-reload'
        }, {
            value : 'icon-search',
            text : 'icon-search'
        }, {
            value : 'icon-print',
            text : 'icon-print'
        }, {
            value : 'icon-help',
            text : 'icon-help'
        }, {
            value : 'icon-undo',
            text : 'icon-undo'
        }, {
            value : 'icon-redo',
            text : 'icon-redo'
        }, {
            value : 'icon-back',
            text : 'icon-back'
        }, {
            value : 'icon-sum',
            text : 'icon-sum'
        }, {
            value : 'icon-tip',
            text : 'icon-tip'
        } ];

        treegrid = $('#comn_menuManager_treegrid')
                .treegrid(
                        {
                            url : '${pageContext.request.contextPath}/menuAction!treegrid.action',
                            toolbar : [
                                    {
                                        text : '展开',
                                        iconCls : 'icon-redo',
                                        handler : function() {
                                            var node = treegrid
                                                    .treegrid('getSelected');
                                            if (node) {
                                                treegrid.treegrid('expandAll',
                                                        node.id);
                                            } else {
                                                treegrid.treegrid('expandAll');
                                            }
                                        }
                                    },
                                    '-',
                                    {
                                        text : '折叠',
                                        iconCls : 'icon-undo',
                                        handler : function() {
                                            var node = treegrid
                                                    .treegrid('getSelected');
                                            if (node) {
                                                treegrid.treegrid(
                                                        'collapseAll', node.id);
                                            } else {
                                                treegrid
                                                        .treegrid('collapseAll');
                                            }
                                        }
                                    }, '-', {
                                        text : '刷新',
                                        iconCls : 'icon-reload',
                                        handler : function() {
                                            treegrid.treegrid('reload');
                                        }
                                    }, '-', {
                                        text : '增加',
                                        iconCls : 'icon-add',
                                        handler : function() {
                                            comn_menuManager_appendFun();
                                        }
                                    }, '-', {
                                        text : '删除',
                                        iconCls : 'icon-remove',
                                        handler : function() {
                                            comn_menuManager_remove();
                                        }
                                    }, '-', {
                                        text : '编辑',
                                        iconCls : 'icon-edit',
                                        handler : function() {
                                            comn_menuManager_editFun();
                                        }
                                    }, '-', {
                                        text : '取消选中',
                                        iconCls : 'icon-undo',
                                        handler : function() {
                                            treegrid.treegrid('unselectAll');
                                            treegrid.datagrid('uncheckAll');
                                        }
                                    }, '-' ],
                            title : '',
                            iconCls : 'icon-save',
                            fit : true,
                            fitColumns : true,
                            nowrap : false,
                            animate : false,
                            border : false,
                            idField : 'id',
                            treeField : 'text',
                            frozenColumns : [ [
                                    {
                                        title : 'id',
                                        field : 'id',
                                        width : 150,
                                        hidden : true
                                    },
                                    {
                                        field : 'text',
                                        title : '菜单名称',
                                        width : 180,
                                        formatter : function(value) {
                                            if (value) {
                                                return fs(
                                                        '<span title="{0}">{1}</span>',
                                                        value, value);
                                            }
                                        }
                                    } ] ],
                            columns : [ [
                                    {
                                        field : 'iconCls',
                                        title : '菜单图标',
                                        width : 70,
                                        formatter : function(value) {
                                            if (!value) {
                                                return '';
                                            } else {
                                                return fs(
                                                        '<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>',
                                                        value);
                                            }
                                        }
                                    },
                                    {
                                        field : 'url',
                                        title : '菜单地址',
                                        width : 250,
                                        formatter : function(value) {
                                            if (value) {
                                                return fs('<span title="{0}">{1}</span>',value, value);
                                            }
                                        }
                                    }, {
                                        field : 'seq',
                                        title : '排序',
                                        width : 40
                                    }, {
                                        field : 'pid',
                                        title : '上级菜单ID',
                                        width : 150,
                                        hidden : true
                                    }, {
                                        field : 'ptext',
                                        title : '上级菜单',
                                        width : 150
                                    } ] ],
                            onContextMenu : function(e, row) {
                                e.preventDefault();
                                $(this).treegrid('unselectAll');
                                $(this).treegrid('select', row.id);
                                $('#comn_menuManager_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            },
                            onExpand : function(row) {
                                treegrid.treegrid('unselectAll');
                            },
                            onCollapse : function(row) {
                                treegrid.treegrid('unselectAll');
                            }
                        });

    });

    function comn_menuManager_appendFun() {
        $('#comn_menuManager_addDialog').dialog('open');
        $('#comn_menuManager_addForm').form('clear');
    }
    function comn_menuManager_append() {
        $('#comn_menuManager_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/menuAction!add.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    treegrid.treegrid('reload');
                    $('#comn_menuManager_addDialog').dialog('close');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }

    function comn_menuManager_editFun() {
        var node = treegrid.treegrid('getSelected');
        if (node) {
            $('#comn_menuManager_editDialog').dialog('open');
            $('#comn_menuManager_editForm').form('load', node);
        } else {
            $.messager.show({
                title : '提示',
                msg : '请选中要编辑的记录！'
            });
        }
    }
    function comn_menuManager_edit() {
        //var node = treegrid.treegrid('getSelected');
        //alert("test7   "+ node.id);
        $('#comn_menuManager_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/menuAction!edit.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    treegrid.treegrid('reload');
                    //treegrid.treegrid('expandTo', node.id);
                    $('#comn_menuManager_editDialog').dialog('close');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }

    function comn_menuManager_remove() {
        var node = treegrid.treegrid('getSelected');
        if (node) {
            $.messager
                    .confirm(
                            '询问',
                            '您确定要删除【' + node.text + '】？',
                            function(b) {
                                if (b) {
                                    $.ajax({
                                                url : '${pageContext.request.contextPath}/menuAction!remove.action',
                                                data : {
                                                    id : node.id
                                                },
                                                cache : false,
                                                dataType : 'JSON',
                                                success : function(r) {
                                                    if (r.success) {
                                                        treegrid.treegrid(
                                                                'remove',
                                                                r.returnObject);
                                                    }
                                                    $.messager.show({
                                                        msg : json.msg,
                                                        title : '提示'
                                                    });
                                                }
                                            });
                                }
                            });
        }
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="comn_menuManager_treegrid"></table>
    </div>

    <div id="comn_menuManager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="comn_menuManager_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="comn_menuManager_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="comn_menuManager_editFun();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>
<div id="comn_menuManager_addDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'添加菜单',buttons:[ {
                text : '添加',
                handler : function() {
                    comn_menuManager_append();
                }
            } ]">
    <form id="comn_menuManager_addForm" method="post">
        <table class="tableForm">
            <tr>
                <th>菜单名称</th>
                <td><input name="text" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写菜单名称'" />
                </td>
                <th>排序</th>
                <td><input name="seq" class="easyui-numberspinner" data-options="min:0,max:999,editable:false,required:true,missingMessage:'请选择菜单排序'" value="10" style="width:150px;" />
                </td>
            </tr>
            <tr>
                <th>菜单地址</th>
                <td colspan="3"><textarea name="url" style="height: 50px;width: 97%;"></textarea>
                </td>
            </tr>
            <tr>
                <th>上级菜单</th>
                <td><input name="pid" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/menuAction!getTreeNode.action',lines : true" style="width:160px;">
                </td>
                <th>菜单图标</th>
                <td><input name="iconCls" class="easyui-combobox" data-options="data : iconData,
                                        formatter : function(v) {
                                            return fs('<span class=\' {0}\' style=\'display:inline-block;vertical-align:middle;width:16px;height:16px;\'></span>{1}',v.value,
                    v.value); }" style="width:150px;" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="comn_menuManager_editDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'修改菜单',buttons:[ {
                text : '修改',
                handler : function() {
                    comn_menuManager_edit();
                }
            } ]">
    <form method="post" id="comn_menuManager_editForm">
        <input type="hidden" name="id" />
        <table class="tableForm">
            <tr>
                <th>菜单名称</th>
                <td><input name="text" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写菜单名称'" />
                </td>
                <th>排序</th>
                <td><input name="seq" class="easyui-numberspinner" data-options="min:0,max:999,editable:false,required:true,missingMessage:'请选择菜单排序'" value="10" style="width:150px;" />
                </td>
            </tr>
            <tr>
                <th>菜单地址</th>
                <td colspan="3"><textarea name="url" style="height: 50px;width: 97%;"></textarea>
                </td>
            </tr>
            <tr>
                <th>上级菜单</th>
                <td><input name="pid" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/menuAction!getTreeNode.action',lines : true" style="width:160px;">
                </td>
                <th>菜单图标</th>
                <td><input name="iconCls" class="easyui-combobox" data-options="data : iconData,
                                        formatter : function(v) {
                                            return fs('<span class=\' {0}\' style=\'display:inline-block;vertical-align:middle;width:16px;height:16px;\'></span>{1}',v.value,
                    v.value); }" style="width:150px;" /></td>
            </tr>
        </table>
    </form>
</div>
