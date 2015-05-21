<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var fin_crashReceipt_datagrid;
    $(function() {

        fin_crashReceipt_datagrid = $('#fin_crashReceipt_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/financeAction!datagridBeforePay.action',
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
                            sortName : 'text',
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
                                title : '收款原因',
                                field : 'text',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                title : '收款详细原因',
                                field : 'detail',
                                width : 150,
                                sortable : true
                            }, {
                                title : '金额',
                                field : 'money',
                                width : 150,
                                sortable : true
                            } , {
                                title : '收款人',
                                field : 'payee',
                                width : 150,
                                sortable : true
                            } , {
                                title : '收款时间',
                                field : 'createdatetime',
                                width : 150,
                                sortable : true
                            }  ] ],
                            toolbar : [ {
                                text : '收款',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    fin_crashReceipt_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    fin_crashReceipt_datagrid.datagrid('unselectAll');
                                    fin_crashReceipt_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#fin_crashReceipt_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });

    });


    function fin_crashReceipt_editFun() {
        var rows = fin_crashReceipt_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录'
            });
        } else if (rows.length == 1) {
            $('#fin_crashReceipt_editForm').form('load', rows[0]);
            $('#fin_crashReceipt_editDialog').dialog('open');
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要收款的记录'
            });
        }
    }
    function fin_crashReceipt_edit() {
        $('#fin_crashReceipt_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/financeAction!crashReceipt.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    fin_crashReceipt_datagrid.datagrid('reload');
                    fin_crashReceipt_datagrid.datagrid('unselectAll');
                    fin_crashReceipt_datagrid.datagrid('uncheckAll');
                    $('#fin_crashReceipt_editDialog').dialog('close');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }


</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="fin_crashReceipt_datagrid"></table>
    </div>

    <div id="fin_crashReceipt_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="fin_crashReceipt_editFun();" data-options="iconCls:'icon-edit'">收款</div>
    </div>
</div>

<div id="fin_crashReceipt_editDialog" align="center" class="easyui-dialog" style="width:460px;heighe:420px;padding: 5px;"
    data-options="closed:true,modal:true,title:'收款',buttons:[ {
                text : '收款',
                handler : function() {
                    fin_crashReceipt_edit();
                }
            } ]">
    <form id="fin_crashReceipt_editForm" method="post">
        <input type="hidden" name="id" />
        <table >
            <tr>
                <th style="width: 55px;">收款原因</th>
                <td><input name="text" style="width:150px;" readonly="readonly" /></td>
                <th style="width: 55px;">金额</th>
                <td><input name="money"  style="width:150px;" readonly="readonly" /></td>
            </tr>
            <tr>
                <th style="width: 55px;">收款人</th>
                <td><input name="payee" style="width:150px;" readonly="readonly" /></td>
                <th style="width: 55px;">收款时间</th>
                <td><input name="createdatetime" style="width:150px;" readonly="readonly" /></td>
            </tr>
            <tr>
                <th>收款详细原因</th>
                <td colspan="3"><textarea name="detail" style="height: 150px;width: 97%;" readonly="readonly"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
