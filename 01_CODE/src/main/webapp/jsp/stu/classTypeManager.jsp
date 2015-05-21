<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var stu_classType_datagrid;
    $(function() {

        stu_classType_datagrid = $('#stu_classType_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/classTypeAction!datagrid.action',
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
                            sortName : 'classType',
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
                                title : '班级种别',
                                field : 'classType',
                                width : 150,
                                sortable : true
                            }, {
                                title : '专业',
                                field : 'professionalName',
                                width : 120,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                title : '学费',
                                field : 'studyFee',
                                width : 150,
                                sortable : true
                            }, {
                                title : '住宿费',
                                field : 'stayFee',
                                width : 150,
                                sortable : true
                            }, {
                                title : '晚自习费',
                                field : 'selfFee',
                                width : 150,
                                sortable : true
                            }, {
                                title : '报名费',
                                field : 'signFee',
                                width : 150,
                                sortable : true
                            } , {
                                title : '成绩单押金',
                                field : 'scoreFee',
                                width : 150,
                                sortable : true
                            } , {
                                title : '保险费',
                                field : 'safetyFee',
                                width : 150,
                                sortable : true
                            } , {
                                title : '水费',
                                field : 'waterFee',
                                width : 150,
                                sortable : true
                            } , {
                                title : '合计',
                                field : 'countFee',
                                width : 150,
                                sortable : true
                            } ] ],
                            toolbar : [ {
                                text : '增加',
                                iconCls : 'icon-add',
                                handler : function() {
                                    stu_classType_appendFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    stu_classType_remove();
                                }
                            }, '-', {
                                text : '修改',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    stu_classType_editFun();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    stu_classType_datagrid.datagrid('unselectAll');
                                    stu_classType_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#stu_classType_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });

    });

    function stu_classType_appendFun() {
        $('#stu_classType_addDialog').dialog('open');
        $('#stu_classType_addForm').form('clear');
    }
    function stu_classType_append() {
        if($('#stu_classType_addForm input[name=classType]').val() == ""){
            alert("请输入班级类型！");
            return;
        }
        if($('#stu_classType_addForm input[name=studyFee]').val() == ""){
            alert("请输入学费！");
            return;
        }

        if(isNaN($('#stu_classType_addForm input[name=studyFee]').val())){
            alert("学费请输入数字！");
            return;
        }

        if($('#stu_classType_addForm input[name=stayFee]').val() == ""){
            alert("请输入住宿费！");
            return;
        }
        if(isNaN($('#stu_classType_addForm input[name=stayFee]').val())){
            alert("住宿费请输入数字！");
            return;
        }

        if($('#stu_classType_addForm input[name=selfFee]').val() == ""){
            alert("请输入晚自习费！");
            return;
        }
        if(isNaN($('#stu_classType_addForm input[name=selfFee]').val())){
            alert("晚自习费请输入数字！");
            return;
        }

        if($('#stu_classType_addForm input[name=signFee]').val() == ""){
            alert("请输入报名费！");
            return;
        }
        if(isNaN($('#stu_classType_addForm input[name=signFee]').val())){
            alert("报名费请输入数字！");
            return;
        }
        if($('#stu_classType_addForm input[name=scoreFee]').val() == ""){
            alert("请输入成绩单押金！");
            return;
        }
        if(isNaN($('#stu_classType_addForm input[name=scoreFee]').val())){
            alert("成绩单押金请输入数字！");
            return;
        }
        if($('#stu_classType_addForm input[name=safetyFee]').val() == ""){
            alert("请输入保险费！");
            return;
        }
        if(isNaN($('#stu_classType_addForm input[name=safetyFee]').val())){
            alert("保险费请输入数字！");
            return;
        }
        if($('#stu_classType_addForm input[name=waterFee]').val() == ""){
            alert("请输入水费！");
            return;
        }
        if(isNaN($('#stu_classType_addForm input[name=waterFee]').val())){
            alert("水费请输入数字！");
            return;
        }
        $('#stu_classType_addForm').form('submit', {
            url : '${pageContext.request.contextPath}/classTypeAction!add.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    stu_classType_datagrid.datagrid('reload');
                    stu_classType_datagrid.datagrid('unselectAll');
                    stu_classType_datagrid.datagrid('uncheckAll');
                    $('#stu_classType_addDialog').dialog('close');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }

    function stu_classType_editFun() {
        var rows = stu_classType_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('#stu_classType_editForm').form('load', rows[0]);
            $('#stu_classType_editDialog').dialog('open');
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要修改的记录'
            });
        }
    }
    function stu_classType_edit() {
        if($('#stu_classType_editForm input[name=classType]').val() == ""){
            alert("请输入班级类型！");
            return;
        }
        if($('#stu_classType_editForm input[name=studyFee]').val() == ""){
            alert("请输入学费！");
            return;
        }

        if(isNaN($('#stu_classType_editForm input[name=studyFee]').val())){
            alert("学费请输入数字！");
            return;
        }

        if($('#stu_classType_editForm input[name=stayFee]').val() == ""){
            alert("请输入住宿费！");
            return;
        }
        if(isNaN($('#stu_classType_editForm input[name=stayFee]').val())){
            alert("住宿费请输入数字！");
            return;
        }

        if($('#stu_classType_editForm input[name=selfFee]').val() == ""){
            alert("请输入晚自习费！");
            return;
        }
        if(isNaN($('#stu_classType_editForm input[name=selfFee]').val())){
            alert("晚自习费请输入数字！");
            return;
        }

        if($('#stu_classType_editForm input[name=signFee]').val() == ""){
            alert("请输入报名费！");
            return;
        }
        if(isNaN($('#stu_classType_editForm input[name=signFee]').val())){
            alert("报名费请输入数字！");
            return;
        }
        if($('#stu_classType_editForm input[name=scoreFee]').val() == ""){
            alert("请输入成绩单押金！");
            return;
        }
        if(isNaN($('#stu_classType_editForm input[name=scoreFee]').val())){
            alert("成绩单押金请输入数字！");
            return;
        }
        if($('#stu_classType_editForm input[name=safetyFee]').val() == ""){
            alert("请输入保险费！");
            return;
        }
        if(isNaN($('#stu_classType_editForm input[name=safetyFee]').val())){
            alert("保险费请输入数字！");
            return;
        }
        if($('#stu_classType_editForm input[name=waterFee]').val() == ""){
            alert("请输入水费！");
            return;
        }
        if(isNaN($('#stu_classType_editForm input[name=waterFee]').val())){
            alert("水费请输入数字！");
            return;
        }
        $('#stu_classType_editForm').form('submit', {
            url : '${pageContext.request.contextPath}/classTypeAction!edit.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    stu_classType_datagrid.datagrid('reload');
                    stu_classType_datagrid.datagrid('unselectAll');
                    stu_classType_datagrid.datagrid('uncheckAll');
                    $('#stu_classType_editDialog').dialog('close');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }

    function stu_classType_remove() {
        var rows = stu_classType_datagrid.datagrid('getChecked');
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
                                                url : '${pageContext.request.contextPath}/classTypeAction!remove.action',
                                                data : {
                                                    ids : ids.join(',')
                                                },
                                                dataType : 'json',
                                                success : function(obj) {
                                                    if (obj.success) {
                                                        stu_classType_datagrid
                                                                .datagrid('load');
                                                        stu_classType_datagrid
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
        <table id="stu_classType_datagrid"></table>
    </div>

    <div id="stu_classType_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="stu_classType_appendFun();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="stu_classType_remove();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="stu_classType_editFun();" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
</div>

<div id="stu_classType_addDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'增加角色',buttons:[ {
                text : '增加',
                handler : function() {
                    stu_classType_append();
                }
            } ]">
    <form id="stu_classType_addForm" method="post">
        <table >
            <tr>
                <th style="width: 55px;">班级种别</th>
                <td><input name="classType" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写班级种别'" style="width:150px;" maxlength="20"/></td>
                <th style="width: 55px;">专业</th>
                <td>
                    <input id="professionalId" name="professionalId" class="easyui-combobox"
                           data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=112',
                           valueField : 'id',
                           textField : 'name',
                           multiple : false,
                           editable : true,
                           panelHeight : '200'" />
                </td>
            </tr>
            <tr>
                <th style="width: 55px;">学费</th>
                <td><input name="studyFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">住宿费</th>
                <td><input name="stayFee"  style="width:150px;" maxlength="10"/></td>
                <th style="width: 55px;">晚自习费</th>
                <td><input name="selfFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">报名费</th>
                <td><input name="signFee"  style="width:150px;" maxlength="10"/></td>
                <th style="width: 55px;">成绩单押金</th>
                <td><input name="scoreFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">保险费</th>
                <td><input name=safetyFee  style="width:150px;" maxlength="10"/></td>
                <th style="width: 55px;">水费</th>
                <td><input name="waterFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="stu_classType_editDialog" align="center" class="easyui-dialog" style="width:460px;heighe:220px;padding: 5px;"
    data-options="closed:true,modal:true,title:'修改班级种别',buttons:[ {
                text : '修改',
                handler : function() {
                    stu_classType_edit();
                }
            } ]">
    <form id="stu_classType_editForm" method="post">
        <input type="hidden" name="id" />
        <table >
            <tr>
                <th style="width: 55px;">班级种别</th>
                <td><input name="classType" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写班级种别'" style="width:150px;" maxlength="20"/></td>
                <th style="width: 55px;">专业</th>
                <td>
                    <input id="professionalId" name="professionalId" class="easyui-combobox"
                           data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=112',
                           valueField : 'id',
                           textField : 'name',
                           multiple : false,
                           editable : true,
                           panelHeight : '200'" />
                </td>
            </tr>
            <tr>
                <th style="width: 55px;">学费</th>
                <td><input name="studyFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">住宿费</th>
                <td><input name="stayFee"  style="width:150px;" maxlength="10"/></td>
                <th style="width: 55px;">晚自习费</th>
                <td><input name="selfFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">报名费</th>
                <td><input name="signFee"  style="width:150px;" maxlength="10"/></td>
                <th style="width: 55px;">成绩单押金</th>
                <td><input name="scoreFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
            <tr>
                <th style="width: 55px;">保险费</th>
                <td><input name=safetyFee  style="width:150px;" maxlength="10"/></td>
                <th style="width: 55px;">水费</th>
                <td><input name="waterFee"  style="width:150px;" maxlength="10"/></td>
            </tr>
        </table>
    </form>
</div>
