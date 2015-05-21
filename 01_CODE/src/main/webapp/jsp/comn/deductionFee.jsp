<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var comn_deductionFee_datagrid;
    $(function() {

        comn_deductionFee_datagrid = $('#comn_deductionFee_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/deductionFeeAction!datagrid.action',
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
                                title : '扣费规则',
                                field : 'name',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                title : '扣费金额',
                                field : 'deductionFee',
                                width : 150,
                                sortable : true
                            }, {
                                title : '开始时间',
                                field : 'startDateShow',
                                width : 150,
                                sortable : true
                            }, {
                                title : '结束时间',
                                field : 'endDateShow',
                                width : 150,
                                sortable : true
                            }, {
                                title : '年限',
                                field : 'yearInfoName',
                                width : 150,
                                sortable : true
                            }, {
                                title : '类型',
                                field : 'timeTypeShow',
                                width : 150,
                                sortable : true
                            }, {
                                title : '状态',
                                field : 'statusShow',
                                width : 150,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                                text : '增加',
                                iconCls : 'icon-add',
                                handler : function() {
                                    comn_deductionFee_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_deductionFee_remove();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_deductionFee_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    comn_deductionFee_datagrid.datagrid('unselectAll');
                                    comn_deductionFee_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#comn_deductionFee_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });

    });

    function comn_deductionFee_appendFun() {
        $('#comn_deductionFee_addDialog').dialog('open');
        $('#comn_deductionFee_addForm').form('clear');
    }
    function comn_deductionFee_append() {
        var deductionFee1 = $('#comn_deductionFee_addForm').find("#deductionFee").val();
        if (!$.isNumeric(deductionFee1)) {
            $.messager.show({
                title : '提示',
                msg : '请在扣费金额的输入框中输入金额'
           });
        } else {
            $('#comn_deductionFee_addForm').form('submit', {
                url : '${pageContext.request.contextPath}/deductionFeeAction!add.action',
                success : function(d) {
                    var json = $.parseJSON(d);
                    if (json.success) {
                        comn_deductionFee_datagrid.datagrid('reload');
                        comn_deductionFee_datagrid.datagrid('unselectAll');
                        comn_deductionFee_datagrid.datagrid('uncheckAll');
                        $('#comn_deductionFee_addDialog').dialog('close');
                    }
                    $.messager.show({
                        msg : json.msg,
                        title : '提示'
                    });
                }
            });
        }

    }

    function comn_deductionFee_editFun() {
        var rows = comn_deductionFee_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('#comn_deductionFee_editForm').form('load', {
                id : rows[0].id,
                name : rows[0].name,
                deductionFee : rows[0].deductionFee,
                startDate : rows[0].startDateShow,
                endDate : rows[0].endDateShow,
                yearInfoId : rows[0].yearInfoId,
                status : rows[0].status,
                timeType : rows[0].timeType
            });
            $('#comn_deductionFee_editDialog').dialog('open');
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要修改的记录'
            });
        }
    }
    function comn_deductionFee_edit() {
         var deductionFee2 = $('#comn_deductionFee_editForm').find("#deductionFee").val();
         if (!$.isNumeric(deductionFee2)) {
             $.messager.show({
                 title : '提示',
                 msg : '请在扣费金额的输入框中输入金额'
            });
         } else {
             $('#comn_deductionFee_editForm').form('submit', {
                 url : '${pageContext.request.contextPath}/deductionFeeAction!edit.action',
                 success : function(d) {
                     var json = $.parseJSON(d);
                     if (json.success) {
                         comn_deductionFee_datagrid.datagrid('reload');
                         comn_deductionFee_datagrid.datagrid('unselectAll');
                         comn_deductionFee_datagrid.datagrid('uncheckAll');
                         $('#comn_deductionFee_editDialog').dialog('close');
                     }
                     $.messager.show({
                         msg : json.msg,
                         title : '提示'
                     });
                 }
             });
         }

    }

    function comn_deductionFee_remove() {
        var rows = comn_deductionFee_datagrid.datagrid('getChecked');
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
                                                url : '${pageContext.request.contextPath}/deductionFeeAction!remove.action',
                                                data : {
                                                    ids : ids.join(',')
                                                },
                                                dataType : 'json',
                                                success : function(obj) {
                                                    if (obj.success) {
                                                        comn_deductionFee_datagrid
                                                                .datagrid('load');
                                                        comn_deductionFee_datagrid
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
        <table id="comn_deductionFee_datagrid"></table>
    </div>

    <div id="comn_deductionFee_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="comn_deductionFee_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="comn_deductionFee_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="comn_deductionFee_editFun();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>

<div id="comn_deductionFee_addDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'增加扣费规则',buttons:[ {
                text : '增加',
                handler : function() {
                    comn_deductionFee_append();
                }
            } ]">
    <form id="comn_deductionFee_addForm" method="post">
        <table >
            <tr>
                <th style="width: 55px;">扣费规则</th>
                <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写扣费规则'" style="width:150px;" maxlength="40"/></td>
                <th style="width: 55px;">扣费金额</th>
                <td><input id="deductionFee" name="deductionFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">开始时间</th>
                <td>
                    <input name="startDate" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                </td>
                <th style="width: 55px;">结束时间</th>
                <td>
                    <input name="endDate" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <th style="width: 55px;">年限</th>
                <td>
                   <input name="yearInfoId" class="easyui-combobox"
                        data-options="url : '${pageContext.request.contextPath}/yearAction!combox.action',
                                            valueField : 'id',
                                            textField : 'text',
                                            multiple : false,
                                            editable : true,
                                            panelHeight : '200'" />
                </td>
            </tr>
            <tr>
                <th style="width: 55px;">类型</th>
                <td>
                   <input name="timeType" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=110',
                    valueField : 'id',
                    textField : 'name',
                    multiple : false,
                    editable : true,
                    panelHeight : '200'" />
                </td>
                <th style="width: 55px;">状态</th>
                <td>
                   <input name="status" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=111',
                    valueField : 'id',
                    textField : 'name',
                    multiple : false,
                    editable : true,
                    panelHeight : '200'" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="comn_deductionFee_editDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'修改扣费规则',buttons:[ {
                text : '修改',
                handler : function() {
                    comn_deductionFee_edit();
                }
            } ]">
    <form id="comn_deductionFee_editForm" method="post">
        <input type="hidden" name="id" />
        <table >
            <tr>
                <th style="width: 55px;">扣费规则</th>
                <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写扣费规则'" style="width:150px;" maxlength="40"/></td>
                <th style="width: 55px;">扣费金额</th>
                <td><input id="deductionFee" name="deductionFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">开始时间</th>
                <td>
                    <input name="startDate" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                </td>
                <th style="width: 55px;">结束时间</th>
                <td>
                    <input name="endDate" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <th style="width: 55px;">年限</th>
                <td>
                   <input name="yearInfoId" class="easyui-combobox"
                        data-options="url : '${pageContext.request.contextPath}/yearAction!combox.action',
                                            valueField : 'id',
                                            textField : 'text',
                                            multiple : false,
                                            editable : true,
                                            panelHeight : '200'" />
                </td>
            </tr>
            <tr>
                <th style="width: 55px;">类型</th>
                <td>
                   <input name="timeType" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=110',
                    valueField : 'id',
                    textField : 'name',
                    multiple : false,
                    editable : true,
                    panelHeight : '200'" />
                </td>
                <th style="width: 55px;">状态</th>
                <td>
                   <input name="status" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=111',
                    valueField : 'id',
                    textField : 'name',
                    multiple : false,
                    editable : true,
                    panelHeight : '200'" />
                </td>
            </tr>
        </table>
    </form>
</div>
