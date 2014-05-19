<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var fin_crashHistory_datagrid;
    $(function() {
        fin_crashHistory_datagrid = $('#fin_crashHistory_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/financeAction!datagridAfterPay.action',
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
                                width : 120
                            } ] ],
                            columns : [ [ {
                                title : '身份证',
                                field : 'idNum',
                                width : 550
                            }, {
                                title : '班级类型',
                                field : 'classTypeName',
                                width : 250
                            }, {
                                title : '学费',
                                field : 'studyFee',
                                width : 200
                            } , {
                                title : '住宿费',
                                field : 'stayFee',
                                width : 250
                            } , {
                                title : '晚自习费',
                                field : 'selfFee',
                                width : 300
                            } , {
                                title : '成绩单押金',
                                field : 'scoreFee',
                                width : 300
                            } , {
                                title : '保险费',
                                field : 'safetyFee',
                                width : 250
                            } , {
                                title : '水费',
                                field : 'waterFee',
                                width : 200
                            } , {
                                title : '报名费',
                                field : 'signFee',
                                width : 250
                            } , {
                                title : '减免费用',
                                field : 'preferentialFee',
                                width : 250
                            } , {
                                title : '现金',
                                field : 'cashFee',
                                width : 200
                            } , {
                                title : '转账',
                                field : 'transferFee',
                                width : 200
                            } , {
                                title : '退款',
                                field : 'refundFee',
                                width : 200
                            } , {
                                title : '补交费 ',
                                field : 'payAgainFee',
                                width : 250
                            } , {
                                title : '总计交付',
                                field : 'countPayFee',
                                width : 300
                            } , {
                                title : '欠费',
                                field : 'arrearFee',
                                width : 200
                            } , {
                                title : '收款人',
                                field : 'payee',
                                width : 250
                            } , {
                                title : '收款时间',
                                field : 'createdatetime',
                                width : 400
                            }   ] ],
                            toolbar : [ {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    fin_crashHistory_editFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    fin_crashHistory_deleteFun();
                                }
                            }, '-', {
                                text : '导出收费明细',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    get_crashHistory_report_Fun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    fin_crashHistory_datagrid.datagrid('unselectAll');
                                    fin_crashHistory_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#fin_crashHistory_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });
    });

    function fin_crashHistory_editFun() {
        var rows = fin_crashHistory_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录'
            });
        } else if (rows.length == 1) {
            $('#fin_crashHistory_editForm').form('load', rows[0]);
            $('#fin_crashHistory_editDialog').dialog('open');
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要显示的记录'
            });
        }
    }

    function fin_crashHistory_deleteFun() {
        var rows = fin_crashHistory_datagrid.datagrid('getChecked');
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
                                                url : '${pageContext.request.contextPath}/financeAction!remove.action',
                                                data : {
                                                    ids : ids.join(',')
                                                },
                                                dataType : 'json',
                                                success : function(obj) {
                                                    if (obj.success) {
                                                        fin_crashHistory_datagrid.datagrid('load');
                                                        fin_crashHistory_datagrid.datagrid('unselectAll');
                                                        fin_crashHistory_datagrid.datagrid('uncheckAll');
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
    function fin_crashHistory_close() {
        $('#fin_crashHistory_editForm').form('clear');
        $('#fin_crashHistory_editDialog').dialog('close');
    }
    function fin_crashHistory_searchFun() {
        fin_crashHistory_datagrid.datagrid('load',
                serializeObject($('#fin_crashHistory_searchForm')));

    }

    function fin_crashHistory_cleanFun() {
        $('#fin_crashHistory_searchForm input').val('');
        fin_crashHistory_datagrid.datagrid('load', {});
    }
    function get_crashHistory_report_Fun() {
        var txt = $('#fin_crashHistory_searchForm').find("#reportDate").val();
        if (txt != "") {
            window.open('${pageContext.request.contextPath}/downloadcrashHistoryAction.action?attachid='+txt);
        } else {
            alert("请选择导出收费明细日期");
        }
    }
    function fin_crashHistory_edit() {

        var count = Number($('#fin_crashHistory_editForm input[name=cashFee]').val())
                    + Number($('#fin_crashHistory_editForm input[name=transferFee]').val());

        if (count == $('#fin_crashHistory_editForm input[name=countPayFee]').val()) {
            $('#fin_crashHistory_editForm').form('submit', {
                url : '${pageContext.request.contextPath}/financeAction!crashReceipt.action',
                success : function(d) {
                    var json = $.parseJSON(d);
                    if (json.success) {
                        fin_crashHistory_datagrid.datagrid('reload');
                        fin_crashHistory_datagrid.datagrid('unselectAll');
                        fin_crashHistory_datagrid.datagrid('uncheckAll');
                        $('#fin_crashHistory_editForm').form('clear');
                        $('#fin_crashHistory_editDialog').dialog('close');
                    }
                    $.messager.show({
                        msg : json.msg,
                        title : '提示'
                    });
                }
            });
        } else {
            $.messager.show({
                title : '提示',
                msg : '现金与转账的合算不等于总计交付'
            });
        }
    }
    function fin_crashHistory_feeOnChangeFun(){
        var studyFeeTemp = $('#fin_crashHistory_editForm input[name=studyFee]');
        if(studyFeeTemp.val() == "" || !$.isNumeric(studyFeeTemp.val())) {
             alert("学费请输入数字");
             studyFeeTemp.val("0");
        }
        var stayFeeTemp = $('#fin_crashHistory_editForm input[name=stayFee]');
        if(stayFeeTemp.val() == "" || !$.isNumeric(stayFeeTemp.val())) {
            alert("住宿费请输入数字");
            stayFeeTemp.val("0");
        }
        var selfFeeTemp = $('#fin_crashHistory_editForm input[name=selfFee]');
        if(selfFeeTemp.val() == "" || !$.isNumeric(selfFeeTemp.val())) {
            alert("晚自习费请输入数字");
            selfFeeTemp.val("0");
        }
        var scoreFeeTemp = $('#fin_crashHistory_editForm input[name=scoreFee]');
        if(scoreFeeTemp.val() == "" || !$.isNumeric(scoreFeeTemp.val())) {
            alert("成绩单押金请输入数字");
            scoreFeeTemp.val("0");
        }
        var safetyFeeTemp = $('#fin_crashHistory_editForm input[name=safetyFee]');
        if(safetyFeeTemp.val() == "" || !$.isNumeric(safetyFeeTemp.val())) {
            alert("保险费请输入数字");
            safetyFeeTemp.val("0");
        }
        var waterFeeTemp = $('#fin_crashHistory_editForm input[name=waterFee]');
        if(waterFeeTemp.val() == "" || !$.isNumeric(waterFeeTemp.val())) {
            alert("水费请输入数字");
            waterFeeTemp.val("0");
        }
        var signFeeTemp = $('#fin_crashHistory_editForm input[name=signFee]');
        if(signFeeTemp.val() == "" || !$.isNumeric(signFeeTemp.val())) {
            alert("报名费请输入数字");
            signFeeTemp.val("0");
        }
        var preferentialFeeTemp = $('#fin_crashHistory_editForm input[name=preferentialFee]');
        if(preferentialFeeTemp.val() == "" || !$.isNumeric(preferentialFeeTemp.val())) {
            alert("减免费用请输入数字");
            preferentialFeeTemp.val("0");
        }
        var cashPayAgainFeeTemp = $('#fin_crashHistory_editForm input[name=cashPayAgainFee]');
        if(cashPayAgainFeeTemp.val() == "" || !$.isNumeric(cashPayAgainFeeTemp.val())) {
            alert("现金补交费请输入数字");
            cashPayAgainFeeTemp.val("0");
        }
        var transferPayAgainFeeTemp = $('#fin_crashHistory_editForm input[name=transferPayAgainFee]');
        if(transferPayAgainFeeTemp.val() == "" || !$.isNumeric(transferPayAgainFeeTemp.val())) {
            alert("转账补交费请输入数字");
            transferPayAgainFeeTemp.val("0");
        }
        var arrearFeeTemp = $('#fin_crashHistory_editForm input[name=arrearFee]');
        if(arrearFeeTemp.val() == "" || !$.isNumeric(arrearFeeTemp.val())) {
            alert("欠费请输入数字");
            arrearFeeTemp.val("0");
        }
        var cashRefundFeeTemp = $('#fin_crashHistory_editForm input[name=cashRefundFee]');
        if(cashRefundFeeTemp.val() == "" || !$.isNumeric(cashRefundFeeTemp.val())) {
            alert("现金退款请输入数字");
            cashRefundFeeTemp.val("0");
        }
        var transferRefundFeeTemp = $('#fin_crashHistory_editForm input[name=transferRefundFee]');
        if(transferRefundFeeTemp.val() == "" || !$.isNumeric(transferRefundFeeTemp.val())) {
            alert("转账退款请输入数字");
            transferRefundFeeTemp.val("0");
        }
        var count = 0;
        count = Number(count) + Number($('#fin_crashHistory_editForm input[name=studyFee]').val());
        //alert("studyFee"+count);
        count = Number(count) + Number($('#fin_crashHistory_editForm input[name=stayFee]').val());
        //alert("stayFee"+count);
        count = Number(count) + Number($('#fin_crashHistory_editForm input[name=selfFee]').val());
        //alert("selfFee"+count);
        count = Number(count) + Number($('#fin_crashHistory_editForm input[name=scoreFee]').val());
        //alert("scoreFee"+count);
        count = Number(count) + Number($('#fin_crashHistory_editForm input[name=safetyFee]').val());
        //alert("safetyFee"+count);
        count = Number(count) + Number($('#fin_crashHistory_editForm input[name=waterFee]').val());
        //alert("waterFee"+count);
        count = Number(count) + Number($('#fin_crashHistory_editForm input[name=signFee]').val());
        //alert("signFee"+count);
        count = Number(count) + Number($('#fin_crashHistory_editForm input[name=cashPayAgainFee]').val());
        //alert("cashPayAgainFee"+count);
        count = Number(count) + Number($('#fin_crashHistory_editForm input[name=transferPayAgainFee]').val());
        //alert("transferPayAgainFee"+count);
        //count = Number(count) - Number($('#fin_crashHistory_editForm input[name=arrearFee]').val());
        //alert("arrearFee"+count);
        count = Number(count) - Number($('#fin_crashHistory_editForm input[name=preferentialFee]').val());
        //alert("preferentialFee"+count);
        count = Number(count) - Number($('#fin_crashHistory_editForm input[name=cashRefundFee]').val());
        //alert("cashRefundFee"+count);
        count = Number(count) - Number($('#fin_crashHistory_editForm input[name=transferRefundFee]').val());
        //alert("transferRefundFee"+count);
        $('#fin_crashHistory_editForm input[name=countPayFee]').val(count);
    }
</script>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 145px;overflow: hidden;" align="left">
        <form id="fin_crashHistory_searchForm">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>姓名</th>
                    <td>
                        <input name="name" style="width:180px;"/>
                    </td>
                    <th>收款人</th>
                    <td><input name="payee" style="width:100px;" />
                    </td>
                </tr>
                <tr>
                    <th>报名班级类型</th>
                    <td>
                        <input id="classType" name="classType" class="easyui-combobox"
                               data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/classTypeAction!combox.action'"
                               style="width: 180px;" />
                    </td>
                </tr>
                <tr>
                    <th>收款时间</th>
                    <td>
                        <input name="createdatetimeStart" onfocus="WdatePicker()" readonly="readonly" style="width: 155px;" />至
                        <input name="createdatetimeEnd" onfocus="WdatePicker()" readonly="readonly" style="width: 155px;" />
                    </td>
                    <th></th>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="fin_crashHistory_searchFun();">过滤</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="fin_crashHistory_cleanFun();">取消</a>
                    </td>
                </tr>
                <tr>
                    <th>导出收费明细日期</th>
                    <td>
                        <input id="reportDate" name="reportDate" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="fin_crashHistory_datagrid"></table>
    </div>

    <div id="fin_crashHistory_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="fin_crashHistory_editFun();" data-options="iconCls:'icon-edit'">编辑</div>
        <div onclick="fin_crashHistory_deleteFun();" data-options="iconCls:'icon-remove'">编辑</div>
    </div>
</div>

<div id="fin_crashHistory_editDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'收款',buttons:[ {
                text : '提交',
                handler : function() {
                    fin_crashHistory_edit();
                }
               },{
                text : '关闭',
                handler : function() {
                    fin_crashHistory_close();
                }
            } ]">
    <form id="fin_crashHistory_editForm" method="post">
        <input type="hidden" name="id" />
        <table >
            <tr>
                <th style="width: 55px;">学费</th>
                <td><input name="studyFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
                <th style="width: 55px;">住宿费</th>
                <td><input name="stayFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">晚自习费</th>
                <td><input name="selfFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
                <th style="width: 55px;">成绩单押金</th>
                <td><input name="scoreFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">保险费</th>
                <td><input name="safetyFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
                <th style="width: 55px;">水费</th>
                <td><input name="waterFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">报名费</th>
                <td><input name="signFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
                <th style="width: 55px;">减免费用</th>
                <td><input name="preferentialFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">现金补交费 </th>
                <td><input name="cashPayAgainFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
                <th style="width: 55px;">转账补交费 </th>
                <td><input name="transferPayAgainFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">现金退款</th>
                <td><input name="cashRefundFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
                <th style="width: 55px;">转账退款</th>
                <td><input name="transferRefundFee" style="width:150px;"
                onchange="fin_crashHistory_feeOnChangeFun()"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">总计交付</th>
                <td><input name="countPayFee" style="width:150px;" readonly="readonly"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">现金</th>
                <td><input name="cashFee" style="width:150px;"/></td>
                 <th style="width: 55px;">转账</th>
                <td><input name="transferFee" style="width:150px;"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">欠费</th>
                <td>
                    <input name="arrearFee" style="width:150px;" onchange="fin_crashHistory_feeOnChangeFun()"/>
                </td>
            </tr>
        </table>
    </form>
</div>
