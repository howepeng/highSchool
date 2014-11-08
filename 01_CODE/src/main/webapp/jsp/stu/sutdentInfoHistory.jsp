<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.radio {
    text-align: left;
    width: 20%;
}
#stuSignupEdit_Open table td {
    border: 1px solid #CDCDCD;
    text-align: center;
    width: 12.5%;
}

#stuSignupEdit_Open table td input {
    border: 0;
    text-align: left;
    width: 98%;
}
#stuSignupEdit_Open table tr {
    height: 5%;
}
</style>
<script type="text/javascript" charset="utf-8">
    var stu_info_history_datagrid;
    $(function() {
        stu_info_history_datagrid = $('#stu_info_history_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/studentInfoHistory!datagridStudentInfoHistory.action',
                            title : '',
                            iconCls : 'icon-save',
                            pagination : true,
                            pageSize : 10,
                            pageList : [ 10, 20, 30, 40 ],
                            fit : true,
                            fitColumns : false,
                            nowrap : false,
                            border : false,
                            idField : 'id',
                            sortName : 'updatedatetime',
                            sortOrder : 'desc',
                            checkOnSelect : false,
                            selectOnCheck : true,
                            frozenColumns : [  ],
                            columns : [ [ {
                                title : '编号',
                                field : 'id',
                                width : 50,
                                sortable : true,
                                checkbox : true
                            }, {
                                title : '姓名',
                                field : 'name',
                                width : 100
                            }, {
                                title : '身份证号',
                                field : 'idNum',
                                width : 100
                            }, {
                                title : '班级类型',
                                field : 'classTypeName',
                                width : 100
                            }, {
                                title : '学号',
                                field : 'num',
                                width : 100
                            } , {
                                title : '报名时间',
                                field : 'createdatetime',
                                width : 120
                            } , {
                                title : '修改时间',
                                field : 'updatedatetime',
                                width : 120
                            } , {
                                title : '修改者',
                                field : 'userName',
                                width : 60
                            } , {
                                title : '修改类型',
                                field : 'updateType',
                                width : 50
                            } , {
                                title : '修改内容',
                                field : 'updateContent',
                                width : 950
                            } ] ],
                            toolbar : [ ],
                            onRowContextMenu : function(e, rowIndex, rowData) {
                                e.preventDefault();
                                $(this).datagrid('unselectAll');
                                $(this).datagrid('selectRow', rowIndex);
                                $('#stu_manager_menu').menu('show', {
                                    left : e.pageX,
                                    top : e.pageY
                                });
                            }
                        });

    });

    function stu_info_history_searchForm() {
        stu_info_history_datagrid.datagrid('load',
                serializeObject($('#stu_info_history_searchForm')));
    }
</script>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 120px;overflow: hidden;" align="left">
        <form id="stu_info_history_searchForm">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>姓名</th>
                    <td>
                        <input name="name" style="width:180px;"/>
                    </td>
                    <th>班级类型</th>
                    <td>
                        <input id="classType" name="classTypeId" class="easyui-combobox"
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
                    <th>修改时间</th>
                    <td>
                        <input name="updatedatetimeStart" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>至
                        <input name="updatedatetimeEnd" type="text" style="width:75px;" onfocus="WdatePicker()" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="stu_info_history_searchForm();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="stu_manager_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>

        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="stu_info_history_datagrid"></table>
    </div>
    <div id="stu_manager_menu" class="easyui-menu" style="width:120px;display: none;">
    </div>
</div>
