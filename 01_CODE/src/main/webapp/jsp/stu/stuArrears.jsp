<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var stu_stuArrears_datagrid;
    $(function() {
        stu_stuArrears_datagrid = $('#stu_stuArrears_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/studentAction!datagridAfterPay.action',
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
                            sortName : 'createdatetime',
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
                                title : '姓名',
                                field : 'name',
                                width : 150
                            } ] ],
                            columns : [ [ {
                                title : '身份证',
                                field : 'idNum',
                                width : 300
                            }, {
                                title : '高考总分',
                                field : 'fractionCount',
                                width : 150
                            }, {
                                title : '班级类型',
                                field : 'classTypeName',
                                width : 200
                            } , {
                                title : '报名时间',
                                field : 'createdatetime',
                                width : 150
                            }, {
                                title : '欠费',
                                field : 'arrearFee',
                                width : 150
                            } ] ],
                            toolbar : [ {
                                text : '缴费',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    stu_stuArrears_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    stu_stuArrears_datagrid.datagrid('unselectAll');
                                    stu_stuArrears_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#stu_stuArrears_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });

    });


    function stu_stuArrears_editFun() {
        var rows = stu_stuArrears_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录'
            });
        } else if (rows.length == 1) {
            $('#stu_stuArrears_editForm').form('clear');
            $('#stu_stuArrears_editForm').form('load', rows[0]);
            $('#stu_stuArrears_editDialog').dialog('open');
            $('#stu_stuArrears_editForm').find("input[name^='cashPayAgainFee']").val("0");
            $('#stu_stuArrears_editForm').find("input[name^='bankPayAgainFee']").val("0");
            $('#stu_stuArrears_editForm').find("input[name^='lakalaPayAgainFee']").val("0");
            $('#stu_stuArrears_editForm').find("input[name^='aliPayAgainFee']").val("0");
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要显示的记录'
            });
        }
    }
    function stu_stuArrears_edit() {
         var cashPayAgainFee = $('#stu_stuArrears_editForm').find("input[name^='cashPayAgainFee']").val();
         var bankPayAgainFee = $('#stu_stuArrears_editForm').find("input[name^='bankPayAgainFee']").val();
         var lakalaPayAgainFee = $('#stu_stuArrears_editForm').find("input[name^='lakalaPayAgainFee']").val();
         var aliPayAgainFee = $('#stu_stuArrears_editForm').find("input[name^='aliPayAgainFee']").val();
         if(cashPayAgainFee == "" && bankPayAgainFee == "" && lakalaPayAgainFee == ""&& aliPayAgainFee == "") {
             alert("请输入补交金额");
             return;
         }

         if(cashPayAgainFee != "" && !$.isNumeric(cashPayAgainFee)) {
             alert("现金补交金额请输入数字");
             return;
         }
         if(bankPayAgainFee != "" && !$.isNumeric(bankPayAgainFee)) {
             alert("银行转账补交金额请输入数字");
             return;
         }
         if(lakalaPayAgainFee != "" && !$.isNumeric(lakalaPayAgainFee)) {
             alert("拉卡拉pos机补交金额请输入数字");
             return;
         }
         if(aliPayAgainFee != "" && !$.isNumeric(aliPayAgainFee)) {
             alert("支付宝补交金额请输入数字");
             return;
         }

         if (cashPayAgainFee + bankPayAgainFee + lakalaPayAgainFee + aliPayAgainFee == 0){
             alert("请输入补交金额");
             return;
         }
        $('#stu_stuArrears_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/studentAction!arrearsPay.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    stu_stuArrears_datagrid.datagrid('reload');
                    stu_stuArrears_datagrid.datagrid('unselectAll');
                    stu_stuArrears_datagrid.datagrid('uncheckAll');
                    $('#stu_stuArrears_editForm').form('clear');
                    $('#stu_stuArrears_editDialog').dialog('close');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }
    function stu_stuArrears_searchFun() {
        stu_stuArrears_datagrid.datagrid('load',
                serializeObject($('#stu_stuArrears_searchForm')));
    }
    function stu_stuArrears_cleanFun() {
        $('#stu_stuArrears_searchForm input').val('');
        stu_stuArrears_datagrid.datagrid('load', {});
    }




</script>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 120px;overflow: hidden;" align="left">
        <form id="stu_stuArrears_searchForm">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>姓名</th>
                    <td>
                        <input name="name" style="width:180px;"/>
                    </td>
                    <th>报名班级类型</th>
                    <td>
                        <input id="classType" name="classType" class="easyui-combobox"
                               data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/classTypeAction!combox.action'"
                               style="width: 180px;" />
                    </td>
                </tr>
                <tr>
                    <th>报名时间</th>
                    <td>
                        <input name="createdatetimeStart" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                                                                                   至 <input name="createdatetimeEnd" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                    </td>
                     <th>高考总分</th>
                    <td>
                        <input name="fractionCountStart" style="width:75px;" /> 至
                        <input name="fractionCountEnd" style="width:75px;" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);"class="easyui-linkbutton" onclick="stu_stuArrears_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="stu_stuArrears_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="stu_stuArrears_datagrid"></table>
    </div>

    <div id="stu_stuArrears_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="stu_stuArrears_editFun();" data-options="iconCls:'icon-edit'">缴费</div>
    </div>
</div>

<div id="stu_stuArrears_editDialog" align="center" class="easyui-dialog" style="width:320px;heighe:50px;padding: 5px;"
    data-options="closed:true,modal:true,title:'缴费',buttons:[ {
                text : '缴费',
                handler : function() {
                    stu_stuArrears_edit();
                }
            } ]">
    <form id="stu_stuArrears_editForm" method="post">
        <input type="hidden" name="id" />
        <table>
            <tr>
                <th style="width: 120px;">现金补交</th>
                <td><input id="refundFee" name="cashPayAgainFee" style="width:180px;" value="0"/></td>
            </tr>
            <tr>
                <th style="width: 120px;">银行转账补交</th>
                <td><input id="refundFee" name="bankPayAgainFee" style="width:180px;" value="0"/></td>
            </tr>
            <tr>
                <th style="width: 120px;">拉卡拉pos机补交</th>
                <td><input id="refundFee" name="lakalaPayAgainFee" style="width:180px;" value="0"/></td>
            </tr>
            <tr>
                <th style="width: 120px;">支付宝转账补交</th>
                <td><input id="refundFee" name="aliPayAgainFee" style="width:180px;" value="0"/></td>
            </tr>
        </table>
    </form>
</div>
