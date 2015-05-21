<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var stu_stuRefun_datagrid;
    $(function() {
        stu_stuRefun_datagrid = $('#stu_stuRefun_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/studentAction!datagridReturnPay.action',
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
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                title : '身份证',
                                field : 'idNum',
                                width : 300,
                                sortable : true
                            }, {
                                title : '高考总分',
                                field : 'fractionCount',
                                width : 150,
                                sortable : true
                            }, {
                                title : '班级类型',
                                field : 'classTypeName',
                                width : 200,
                                sortable : true
                            }, {
                                title : '报名费',
                                field : 'signFee',
                                width : 150,
                                sortable : true
                            }, {
                                title : '合计交付',
                                field : 'countFee',
                                width : 150,
                                sortable : true
                            } , {
                                title : '报名时间',
                                field : 'createdatetime',
                                width : 150,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                                text : '退款',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    stu_stuRefun_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    stu_stuRefun_datagrid.datagrid('unselectAll');
                                    stu_stuRefun_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#stu_stuRefun_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });

    });


    function stu_stuRefun_editFun() {
        var rows = stu_stuRefun_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录'
            });
        } else if (rows.length == 1) {
            $('#stu_stuRefun_editForm').form('clear');
            $('#stu_stuRefun_editForm').form('load', rows[0]);
            $('#stu_stuRefun_editDialog').dialog('open');
            $('#stu_stuRefun_editForm').find('#cashRefundFee').val("0");
            $('#stu_stuRefun_editForm').find('#bankRefundFee').val("0");
            $('#stu_stuRefun_editForm').find('#lakalaRefundFee').val("0");
            $('#stu_stuRefun_editForm').find('#aliRefundFee').val("0");
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要显示的记录'
            });
        }
    }
    function stu_stuRefun_edit() {
        var cashRefundFee = $('#stu_stuRefun_editForm').find('#cashRefundFee').val();
        var bankRefundFee = $('#stu_stuRefun_editForm').find('#bankRefundFee').val();
        var lakalaRefundFee = $('#stu_stuRefun_editForm').find('#lakalaRefundFee').val();
        var aliRefundFee = $('#stu_stuRefun_editForm').find('#aliRefundFee').val();
        var countFee = $('#stu_stuRefun_editForm').find('#countFee').val();
        var signFee = $('#stu_stuRefun_editForm').find('#signFee').val();
        if(cashRefundFee == "" && bankRefundFee == "" && lakalaRefundFee == "" && aliRefundFee == "") {
            alert("请输入退款金额");
            return;
        }
        if(cashRefundFee != "" && !$.isNumeric(cashRefundFee)) {
            alert("现金退款金额请输入数字");
            return;
        }
        if(bankRefundFee != "" && !$.isNumeric(bankRefundFee)) {
            alert("银行转账退款金额请输入数字");
            return;
        }
        if(lakalaRefundFee != "" && !$.isNumeric(lakalaRefundFee)) {
            alert("拉卡拉pos机转账退款金额请输入数字");
            return;
        }
        if(aliRefundFee != "" && !$.isNumeric(aliRefundFee)) {
            alert("支付宝转账退款金额请输入数字");
            return;
        }
        if (cashRefundFee + bankRefundFee + lakalaRefundFee + aliRefundFee == 0){
            alert("请输入退款金额");
            return;
        }
        if (cashRefundFee + bankRefundFee + lakalaRefundFee + aliRefundFee > countFee){
            alert("退款金额要小于支付总金额");
            return;
        }
        if ((Number(countFee) - (Number(cashRefundFee) + Number(bankRefundFee) + Number(lakalaRefundFee) + Number(aliRefundFee))) < Number(signFee)) {
            alert("报名费不能退");
            return;
        }
        $('#stu_stuRefun_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/studentAction!refunPay.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    stu_stuRefun_datagrid.datagrid('reload');
                    stu_stuRefun_datagrid.datagrid('unselectAll');
                    stu_stuRefun_datagrid.datagrid('uncheckAll');
                    $('#stu_stuRefun_editDialog').dialog('close');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }
    function stu_stuRefun_searchFun() {
        stu_stuRefun_datagrid.datagrid('load',
                serializeObject($('#stu_stuRefun_searchForm')));
    }
    function stu_stuRefun_cleanFun() {
        $('#stu_stuRefun_searchForm input').val('');
        stu_stuRefun_datagrid.datagrid('load', {});
    }
</script>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 120px;overflow: hidden;" align="left">
        <form id="stu_stuRefun_searchForm">
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
                        <a href="javascript:void(0);"class="easyui-linkbutton" onclick="stu_stuRefun_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="stu_stuRefun_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="stu_stuRefun_datagrid"></table>
    </div>

    <div id="stu_stuRefun_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="stu_stuRefun_editFun();" data-options="iconCls:'icon-edit'">退款</div>
    </div>
</div>

<div id="stu_stuRefun_editDialog" align="center" class="easyui-dialog" style="width:320px;heighe:50px;padding: 5px;"
    data-options="closed:true,modal:true,title:'退款',buttons:[ {
                text : '退款',
                handler : function() {
                    stu_stuRefun_edit();
                }
            } ]">
    <form id="stu_stuRefun_editForm" method="post">
        <input type="hidden" name="id" />
        <input type="hidden" id="countFee" name="countFee"/>
        <input type="hidden" id="signFee" name="signFee"/>
        <table>
            <tr>
                <th style="width: 120px;">现金退款</th>
                <td><input id="cashRefundFee" name="cashRefundFee" style="width:180px;" value="0"/></td>
            </tr>
            <tr>
                <th style="width: 120px;">银行转账退款</th>
                <td><input id="bankRefundFee" name="bankRefundFee" style="width:180px;" value="0"/></td>
            </tr>
            <tr>
                <th style="width: 120px;">拉卡拉pos机转账退款</th>
                <td><input id="lakalaRefundFee" name="lakalaRefundFee" style="width:180px;" value="0"/></td>
            </tr>
            <tr>
                <th style="width: 120px;">支付宝转账退款</th>
                <td><input id="aliRefundFee" name="aliRefundFee" style="width:180px;" value="0"/></td>
            </tr>
        </table>
    </form>
</div>
