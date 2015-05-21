<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var fin_preferential_datagrid;
    $(function() {

        fin_preferential_datagrid = $('#fin_preferential_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/preferentialAction!datagrid.action',
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
                            sortName : 'preName',
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
                                title : '优惠类型',
                                field : 'preName',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                title : '优惠金额',
                                field : 'preferentialFee',
                                width : 150,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                                text : '增加',
                                iconCls : 'icon-add',
                                handler : function() {
                                    fin_preferential_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    fin_preferential_remove();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    fin_preferential_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    fin_preferential_datagrid.datagrid('unselectAll');
                                    fin_preferential_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#fin_preferential_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });

    });

    function fin_preferential_appendFun() {
        $('#fin_preferential_addDialog').dialog('open');
        $('#fin_preferential_addForm').form('clear');
    }
    function fin_preferential_append() {
        var preferentialFee1 = $('#fin_preferential_addForm').find("#preferentialFee").val();
        if (!$.isNumeric(preferentialFee1)) {
            $.messager.show({
                title : '提示',
                msg : '请在优惠金额的输入框中输入金额'
           });
        } else {
            $('#fin_preferential_addForm').form('submit', {
                url : '${pageContext.request.contextPath}/preferentialAction!add.action',
                success : function(d) {
                    var json = $.parseJSON(d);
                    if (json.success) {
                        fin_preferential_datagrid.datagrid('reload');
                        fin_preferential_datagrid.datagrid('unselectAll');
                        fin_preferential_datagrid.datagrid('uncheckAll');
                        $('#fin_preferential_addDialog').dialog('close');
                    }
                    $.messager.show({
                        msg : json.msg,
                        title : '提示'
                    });
                }
            });
        }

    }

    function fin_preferential_editFun() {
        var rows = fin_preferential_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('#fin_preferential_editForm').form('load', rows[0]);
            $('#fin_preferential_editDialog').dialog('open');
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要修改的记录'
            });
        }
    }
    function fin_preferential_edit() {
         var preferentialFee2 = $('#fin_preferential_editForm').find("#preferentialFee").val();
         if (!$.isNumeric(preferentialFee2)) {
             $.messager.show({
                 title : '提示',
                 msg : '请在优惠金额的输入框中输入金额'
            });
         } else {
             $('#fin_preferential_editForm').form('submit', {
                 url : '${pageContext.request.contextPath}/preferentialAction!edit.action',
                 success : function(d) {
                     var json = $.parseJSON(d);
                     if (json.success) {
                         fin_preferential_datagrid.datagrid('reload');
                         fin_preferential_datagrid.datagrid('unselectAll');
                         fin_preferential_datagrid.datagrid('uncheckAll');
                         $('#fin_preferential_editDialog').dialog('close');
                     }
                     $.messager.show({
                         msg : json.msg,
                         title : '提示'
                     });
                 }
             });
         }

    }

    function fin_preferential_remove() {
        var rows = fin_preferential_datagrid.datagrid('getChecked');
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
                                                url : '${pageContext.request.contextPath}/preferentialAction!remove.action',
                                                data : {
                                                    ids : ids.join(',')
                                                },
                                                dataType : 'json',
                                                success : function(obj) {
                                                    if (obj.success) {
                                                        fin_preferential_datagrid
                                                                .datagrid('load');
                                                        fin_preferential_datagrid
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
        <table id="fin_preferential_datagrid"></table>
    </div>

    <div id="fin_preferential_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="fin_preferential_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="fin_preferential_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="fin_preferential_editFun();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>

<div id="fin_preferential_addDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'增加报名优惠',buttons:[ {
                text : '增加',
                handler : function() {
                    fin_preferential_append();
                }
            } ]">
    <form id="fin_preferential_addForm" method="post">
        <table >
            <tr>
                <th style="width: 55px;">优惠类型</th>
                <td><input name="preName" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写优惠类型'" style="width:150px;" maxlength="40"/></td>
                <th style="width: 55px;">优惠金额</th>
                <td><input id="preferentialFee" name="preferentialFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="fin_preferential_editDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'修改报名优惠',buttons:[ {
                text : '修改',
                handler : function() {
                    fin_preferential_edit();
                }
            } ]">
    <form id="fin_preferential_editForm" method="post">
        <input type="hidden" name="id" />
        <table >
            <tr>
                <th style="width: 55px;">优惠类型</th>
                <td><input name="preName" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写优惠类型'" style="width:150px;" maxlength="40"/></td>
                <th style="width: 55px;">优惠金额</th>
                <td><input id="preferentialFee" name="preferentialFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
        </table>
    </form>
</div>
