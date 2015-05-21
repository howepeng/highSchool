<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var stu_stuPayment_datagrid;
    $(function() {

        stu_stuPayment_datagrid = $('#stu_stuPayment_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/studentAction!datagridBeforePay.action',
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
                            } , {
                                title : '登记人',
                                field : 'payee',
                                width : 150,
                                sortable : true
                            } , {
                                title : '报名时间',
                                field : 'createdatetime',
                                width : 150,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                                text : '缴费',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    stu_stuPayment_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    stu_stuPayment_datagrid.datagrid('unselectAll');
                                    stu_stuPayment_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#stu_stuPayment_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });

    });


    function stu_stuPayment_editFun() {
        var rows = stu_stuPayment_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录'
            });
        } else if (rows.length == 1) {
            $('<div id="stuPaymentEdit_Open"/>').dialog({
                href : 'jsp/stu/stuPaymentEdit.jsp',
                width : 620,
                height : 520,
                modal : true,
                title : '缴费',
                onLoad: function() {
                    $('#stu_stuPayment_editForm').form('clear');
                    $('#stu_stuPayment_editForm').form('load', rows[0]);
                    stu_stuPayment_classTypeCombox($('#stu_stuPayment_editForm').find("#classType1").val());
                    } ,
                onClose : function() {
                    $(this).dialog('destroy');
                },
                buttons:[ {
                    text : '清空',
                    handler : function() {
                        stu_stuPayment_edit_clear();
                    }
                } ,{
                    text : '缴费',
                    handler : function() {
                        stu_stuPayment_edit();
                    }
                }]
                });
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要显示的记录'
            });
        }
    }
    function stu_stuPayment_edit() {

        var count = Number($('#stu_stuPayment_editForm input[name=cashFee]').val())
                    + Number($('#stu_stuPayment_editForm input[name=bankFee]').val())
                    + Number($('#stu_stuPayment_editForm input[name=lakalaFee]').val())
                    + Number($('#stu_stuPayment_editForm input[name=aliFee]').val());

        if (count == $('#stu_stuPayment_editForm input[name=countFee]').val()) {
            $('#stu_stuPayment_editForm').form('submit', {
                url : '${pageContext.request.contextPath}/studentAction!feePay.action',
                success : function(d) {
                    var json = $.parseJSON(d);
                    if (json.success) {
                        stu_stuPayment_datagrid.datagrid('reload');
                        stu_stuPayment_datagrid.datagrid('unselectAll');
                        stu_stuPayment_datagrid.datagrid('uncheckAll');
                        $('#stuPaymentEdit_Open').dialog('destroy');
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
    function stu_stuPayment_searchFun() {
        stu_stuPayment_datagrid.datagrid('load',
                serializeObject($('#stu_stuPayment_searchForm')));
    }
    function stu_stuPayment_cleanFun() {
        $('#stu_stuPayment_searchForm input').val('');
        stu_stuPayment_datagrid.datagrid('load', {});
    }

    function stu_stuPayment_classTypeCombox() {
        $('#stu_stuPayment_searchForm input').val('');
        stu_stuPayment_datagrid.datagrid('load', {});
    }
    function stu_stuPayment_classTypeCombox(type) {
        $.ajax({
            url : '${pageContext.request.contextPath}/classTypeAction!getClassType.action',
            data : {
                id : type
            },
            cache : false,
            dataType : 'JSON',
            success : function(r) {

                var count;
                // 报名费
                if ($('#stu_stuPayment_editForm').find("#signUpMoneyFlg").val() == "1") {
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_signFee").val("报名费已交");
                    $('#stu_stuPayment_editForm').find("input[name^='signFee']").val("报名费已交");
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_signFee");
                    $('#stu_stuPayment_editForm').find("input[name^='signFee']").attr("readonly","readonly");

                    count = r.countFee-r.signFee;
                } else {
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_signFee").val(r.signFee);
                    $('#stu_stuPayment_editForm').find("input[name^='signFee']").val(r.signFee);
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_signFee");
                    $('#stu_stuPayment_editForm').find("input[name^='signFee']").removeAttr("readonly");

                    count = r.countFee;
                }
                // 住宿费
                if ($('#stu_stuPayment_editForm').find("#stayFlg").val() == "0") {
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_stayFee").val(Number(0));
                    $('#stu_stuPayment_editForm').find("input[name^='stayFee']").val(Number(0));
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_stayFee");
                    $('#stu_stuPayment_editForm').find("input[name^='stayFee']").attr("readonly","readonly");
                    count = count-r.stayFee;
                } else {
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_stayFee").val(r.stayFee);
                    $('#stu_stuPayment_editForm').find("input[name^='stayFee']").val(r.stayFee);
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_stayFee");
                    $('#stu_stuPayment_editForm').find("input[name^='stayFee']").removeAttr("readonly");
                }
                // 晚自习费
                if ($('#stu_stuPayment_editForm').find("#selfstudyNightflg").val() == "0") {
                     $('#stu_stuPayment_editForm').find("#stu_stuPayment_selfFee").val(Number(0));
                     $('#stu_stuPayment_editForm').find("input[name^='selfFee']").val(Number(0));
                     $('#stu_stuPayment_editForm').find("#stu_stuPayment_selfFee");
                     $('#stu_stuPayment_editForm').find("input[name^='selfFee']").attr("readonly","readonly");
                     count = count-r.selfFee;
                } else {
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_selfFee").val(r.selfFee);
                    $('#stu_stuPayment_editForm').find("input[name^='selfFee']").val(r.selfFee);
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_selfFee");
                    $('#stu_stuPayment_editForm').find("input[name^='selfFee']").removeAttr("readonly");
                }
                // 保险费
                if ($('#stu_stuPayment_editForm').find("#secureFlg").val() == "0") {
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_safetyFee").val(Number(0));
                    $('#stu_stuPayment_editForm').find("input[name^='safetyFee']").val(Number(0));
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_safetyFee");
                    $('#stu_stuPayment_editForm').find("input[name^='safetyFee']").attr("readonly","readonly");
                    count = count-r.safetyFee;
                } else {
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_safetyFee").val(r.safetyFee);
                    $('#stu_stuPayment_editForm').find("input[name^='safetyFee']").val(r.safetyFee);
                    $('#stu_stuPayment_editForm').find("#stu_stuPayment_safetyFee");
                    $('#stu_stuPayment_editForm').find("input[name^='safetyFee']").removeAttr("readonly");
                }

                $('#stu_stuPayment_editForm').find('#stu_stuPayment_studyFee').val(r.studyFee);
                $('#stu_stuPayment_editForm input[name=studyFee]').val(r.studyFee);
                $('#stu_stuPayment_editForm').find('#stu_stuPayment_scoreFee').val(r.scoreFee);
                $('#stu_stuPayment_editForm input[name=scoreFee]').val(r.scoreFee);
                $('#stu_stuPayment_editForm').find('#stu_stuPayment_waterFee').val(r.waterFee);
                $('#stu_stuPayment_editForm input[name=waterFee]').val(r.waterFee);
                var stu_stuPayment_preferentialHd = $('#stu_stuPayment_editForm input[name=stu_stuPayment_preferentialHd]');
                var stu_stuPayment_deductionFee = $('#stu_stuPayment_editForm input[name=stu_stuPayment_deductionFee]');
                var countFee = $('#stu_stuPayment_editForm input[name=countFee]');
                var stu_stuPayment_countFee = $('#stu_stuPayment_editForm').find('#stu_stuPayment_countFee');

                stu_stuPayment_countFee.val(count);
                countFee.val(count);
                console.info(stu_stuPayment_preferentialHd.val());
                if(stu_stuPayment_preferentialHd.val()!=""){
                    stu_stuPayment_countFee.val(count-stu_stuPayment_preferentialHd.val());
                    countFee.val(count-stu_stuPayment_preferentialHd.val());
                }else{
                    stu_stuPayment_countFee.val(count);
                    countFee.val(count);
                    stu_stuPayment_preferentialHd.val("0");
                }
                // 扣费
                stu_stuPayment_countFee.val(count-stu_stuPayment_deductionFee.val());
                countFee.val(count-stu_stuPayment_deductionFee.val());

                var arrearFee = $('#stu_stuPayment_editForm').find('#arrearFee');
                arrearFee.val("0");

            }
        });
    }
    function stu_stuPayment_preferentialCombox(combobox) {
        if(combobox.id == "undefined") {
            alert("请选择正确的减免费用");
            return;
        }
        if (combobox.id != "") {
            $.ajax({
                url : '${pageContext.request.contextPath}/preferentialAction!getPreferential.action',
                data : {
                    id : combobox.id
                },
                cache : false,
                dataType : 'JSON',
                success : function(r) {
                    var stu_stuPayment_preferentialHd = $('#stu_stuPayment_editForm input[name=stu_stuPayment_preferentialHd]');
                    stu_stuPayment_preferentialHd.val(r.preferentialFee);

                    stu_stuPayment_feeFun();
                    stu_stuPayment_feeOnChangeFun();
                }
            });
        } else {
            var stu_stuPayment_preferentialHd = $('#stu_stuPayment_editForm input[name=stu_stuPayment_preferentialHd]');
            stu_stuPayment_preferentialHd.val("0");
            stu_stuPayment_feeFun();
            stu_stuPayment_feeOnChangeFun();
        }
    }
    function stu_stuPayment_feeFun(){
        var count = 0;
        if ($('#stu_stuPayment_editForm').find("#signUpMoneyFlg").val() == "0") {
            count = Number(count) + Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_signFee]').val());
        }
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_studyFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_stayFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_selfFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_scoreFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_safetyFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_waterFee]').val());
        count = Number(count) - Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_preferentialHd]').val());
        // 扣费
        var stu_stuPayment_deductionFee = $('#stu_stuPayment_editForm input[name=stu_stuPayment_deductionFee]');
        count = Number(count) - Number(stu_stuPayment_deductionFee.val());
        if (count < 0) {
            count = 0;
            stu_stuPayment_deductionFee = Number(count) + Number(stu_stuPayment_deductionFee.val());
            stu_stuPayment_deductionFee.val(stu_stuPayment_deductionFee);
        }
        var countFee = $('#stu_stuPayment_editForm input[name=stu_stuPayment_countFee]');
        countFee.val(count);
    }
    function stu_stuPayment_feeOnChangeFun(){
        var studyFeeTemp = $('#stu_stuPayment_editForm input[name=studyFee]');
        if(studyFeeTemp.val() != "" && !$.isNumeric(studyFeeTemp.val())) {
             alert("学费请输入数字");
             studyFeeTemp.val("0");
        } else if (studyFeeTemp.val() == ""){
            studyFeeTemp.val("0");
        }
        var stayFeeTemp = $('#stu_stuPayment_editForm input[name=stayFee]');
        if(stayFeeTemp.val() != "" && !$.isNumeric(stayFeeTemp.val())) {
            alert("住宿费请输入数字");
            stayFeeTemp.val("0");
        } else if (stayFeeTemp.val() == ""){
            stayFeeTemp.val("0");
        }
        var selfFeeTemp = $('#stu_stuPayment_editForm input[name=selfFee]');
        if(selfFeeTemp.val() != "" && !$.isNumeric(selfFeeTemp.val())) {
            alert("晚自习费请输入数字");
            selfFeeTemp.val("0");
        } else if (selfFeeTemp.val() == ""){
            selfFeeTemp.val("0");
        }
        var scoreFeeTemp = $('#stu_stuPayment_editForm input[name=scoreFee]');
        if(scoreFeeTemp.val() != "" && !$.isNumeric(scoreFeeTemp.val())) {
            alert("成绩单押金请输入数字");
            scoreFeeTemp.val("0");
        } else if (scoreFeeTemp.val() == ""){
            scoreFeeTemp.val("0");
        }
        var safetyFeeTemp = $('#stu_stuPayment_editForm input[name=safetyFee]');
        if(safetyFeeTemp.val() != "" && !$.isNumeric(safetyFeeTemp.val())) {
            alert("保险费请输入数字");
            safetyFeeTemp.val("0");
        } else if (safetyFeeTemp.val() == ""){
            safetyFeeTemp.val("0");
        }
        var waterFeeTemp = $('#stu_stuPayment_editForm input[name=waterFee]');
        if(waterFeeTemp.val() != "" && !$.isNumeric(waterFeeTemp.val())) {
            alert("水费请输入数字");
            waterFeeTemp.val("0");
        } else if (waterFeeTemp.val() == ""){
            waterFeeTemp.val("0");
        }
        if ($('#stu_stuPayment_editForm').find("#signUpMoneyFlg").val() == "0") {
            var signFeeTemp = $('#stu_stuPayment_editForm input[name=signFee]');
            if(signFeeTemp.val() != "" && !$.isNumeric(signFeeTemp.val())) {
                alert("报名费请输入数字");
                signFeeTemp.val("0");
            } else if (signFeeTemp.val() == ""){
                signFeeTemp.val("0");
            }
        }
        var count = 0;
        if ($('#stu_stuPayment_editForm').find("#signUpMoneyFlg").val() == "0") {
            count = Number(count) + Number($('#stu_stuPayment_editForm input[name=signFee]').val());
        }
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=studyFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=stayFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=selfFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=scoreFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=safetyFee]').val());
        count = Number(count) + Number($('#stu_stuPayment_editForm input[name=waterFee]').val());
        count = Number(count) - Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_preferentialHd]').val());
        // 扣费
        var stu_stuPayment_deductionFee = $('#stu_stuPayment_editForm input[name=stu_stuPayment_deductionFee]');
        count = Number(count) - Number(stu_stuPayment_deductionFee.val());
        if (count < 0) {
            count = 0;
            stu_stuPayment_deductionFee = Number(count) + Number(stu_stuPayment_deductionFee.val());
            stu_stuPayment_deductionFee.val(stu_stuPayment_deductionFee);
        }
        var arrearv = Number($('#stu_stuPayment_editForm input[name=stu_stuPayment_countFee]').val()) - Number(count);
        var arrearFee = $('#stu_stuPayment_editForm').find('#arrearFee');
        var countFee = $('#stu_stuPayment_editForm input[name=countFee]');
        countFee.val(count);
        if(Number(arrearv) > Number(0)){
            arrearFee.css("color","red");
            arrearFee.val(arrearv);
        } else {
            arrearFee.css("color","black");
            arrearFee.val("0");
        }
    }

    function stu_stuPayment_edit_clear() {
        if ($('#stu_stuPayment_editForm').find("#signUpMoneyFlg").val() == "0") {
            $('#stu_stuPayment_editForm input[name=signFee]').val("0");
        }
        $('#stu_stuPayment_editForm input[name=studyFee]').val("0");
        $('#stu_stuPayment_editForm input[name=stayFee]').val("0");
        $('#stu_stuPayment_editForm input[name=selfFee]').val("0");
        $('#stu_stuPayment_editForm input[name=scoreFee]').val("0");
        $('#stu_stuPayment_editForm input[name=safetyFee]').val("0");
        $('#stu_stuPayment_editForm input[name=waterFee]').val("0");
        $('#stu_stuPayment_editForm').find('#arrearFee').val("");
        $('#stu_stuPayment_editForm input[name=countFee]').val("");
        $('#stu_stuPayment_editForm input[name=cashFee]').val("");
        $('#stu_stuPayment_editForm input[name=bankFee]').val("");
        $('#stu_stuPayment_editForm input[name=lakalaFee]').val("");
        $('#stu_stuPayment_editForm input[name=aliFee]').val("");
    }


</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'过滤条件'"
        style="height: 120px; overflow: hidden;" align="left">
        <form id="stu_stuPayment_searchForm">
            <table class="tableForm datagrid-toolbar"
                style="width: 100%; height: 100%;">
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
                        <input name="createdatetimeStart" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>至
                        <input name="createdatetimeEnd" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                    </td>
                     <th>高考总分</th>
                    <td>
                        <input name="fractionCountStart" style="width:75px;" /> 至
                        <input name="fractionCountEnd" style="width:75px;" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);"class="easyui-linkbutton" onclick="stu_stuPayment_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="stu_stuPayment_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false"
        style="overflow: hidden;">
        <table id="stu_stuPayment_datagrid"></table>
    </div>

    <div id="stu_stuPayment_menu" class="easyui-menu"
        style="width: 120px; display: none;">
        <div onclick="stu_stuPayment_editFun();"
            data-options="iconCls:'icon-edit'">缴费</div>
    </div>
</div>
