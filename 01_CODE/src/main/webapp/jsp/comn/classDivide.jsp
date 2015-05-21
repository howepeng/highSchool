<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var comn_classDivide_datagrid;
    $(function() {
        comn_classDivide_datagrid = $('#comn_classDivide_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/classInfoAction!datagridClassInfo.action',
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
                                title : '班级名称',
                                field : 'name',
                                width : 150,
                                sortable : true
                            } ] ],
                            columns : [ [ {
                                title : '年限',
                                field : 'yearId',
                                width : 100,
                                sortable : true
                            }, {
                                title : '班级类型',
                                field : 'classType',
                                width : 100,
                                sortable : true
                            }, {
                                title : '分班方式',
                                field : 'classMode',
                                width : 150,
                                sortable : true
                            } , {
                                title : '班主任',
                                field : 'userId',
                                width : 150,
                                sortable : true
                            }] ],
                            toolbar : [ {
                                text : '分班',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_classDivide_addFun();
                                }
                            }, '-', {
                                text : '设定班主任',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_classDivide_setFun();
                                }
                            }, '-', {
                                text : '删除班级',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_classDivide_remove();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    comn_classDivide_datagrid.datagrid('unselectAll');
                                    comn_classDivide_datagrid.datagrid('uncheckAll');
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

    function comn_classDivide_searchFun() {
        comn_classDivide_datagrid.datagrid('load',
                serializeObject($('#comn_classDivide_SearchForm')));
    }

    function comn_classDivide_cleanFun() {
        $('#comn_classDivide_SearchForm input').val('');
        comn_classDivide_datagrid.datagrid('load', {});
    }

    function comn_classDivide_addFun() {
        $('<div id="classDivideAdd_Open" style="5px;"/>').dialog({
            href : 'jsp/comn/classDivideAdd.jsp',
            width : 600,
            height :200,
            modal : true,
            title : '添加分班规则',
            onLoad: function() {
                $('#class_divideForm').form('clear');
                } ,
            onClose : function() {
                $(this).dialog('destroy');
            },
            buttons:[
                     {
                         text : '分班',
                         iconCls : 'icon-add',
                         handler : function() {
                             comn_classDivide_divideFun();
                         }
                     }
                 ]
            });
    }

    function comn_classDivide_divideFun() {
        $('#class_divideForm').form('submit', {
            url : '${pageContext.request.contextPath}/classDivideAction!divide.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    comn_classDivide_datagrid.datagrid('load',
                            serializeObject($('#comn_classDivide_datagrid')));
                    $('#classDivideAdd_Open').dialog('destroy');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }

    function comn_classDivide_setFun() {

        var rows = $('#comn_classDivide_datagrid').datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录进行编辑'
            });
        } else if (rows.length == 1) {
            $('<div id="classDivideSetUser_Open" style="5px;"/>').dialog({
                href : 'jsp/comn/classDivideSetUser.jsp',
                width : 400,
                height :125,
                modal : true,
                title : '设定班主任',
                onLoad: function() {
                    $('#comn_classDivide_setUserForm').form('load', {
                        id : rows[0].id,
                        userId : rows[0].userId
                    });
                    } ,
                onClose : function() {
                    $(this).dialog('destroy');
                },
                buttons:[
                         {
                             text : '设定',
                             iconCls : 'icon-add',
                             handler : function() {
                                 comn_classDivide_setUserFun();
                             }
                         }
                     ]
                });

        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要修改的记录'
            });
        }

    }

    function comn_classDivide_setUserFun() {
        $('#comn_classDivide_setUserForm').form('submit', {
            url : '${pageContext.request.contextPath}/classInfoAction!edit.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    comn_classDivide_datagrid.datagrid('load',
                            serializeObject($('#comn_classDivide_datagrid')));
                    $('#classDivideSetUser_Open').dialog('destroy');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }

    function comn_classDivide_remove(){
        var rows = $('#comn_classDivide_datagrid').datagrid('getChecked');
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
                                    $.ajax({
                                        url : '${pageContext.request.contextPath}/classInfoAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#comn_classDivide_datagrid').datagrid('load');
                                                $('#comn_classDivide_datagrid').datagrid('unselectAll');
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

    function classes_divide_classTypeCombox(combobox) {
        $('#class_divideForm').find("input[name^='classPrefixion']").val(combobox.text);
    }
</script>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 110px;overflow: hidden;" align="left">
        <form id="comn_classDivide_SearchForm" method="post">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>年限</th>
                    <td>
                        <input id="yearId" name="yearId" class="easyui-combobox" style="width: 160px;"
                               data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/yearAction!combox.action'"/>
                    </td>
                    <th>班级类型</th>
                    <td>
                        <input id="classType" name="classType" class="easyui-combobox"
                               data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/classTypeAction!combox.action',
                                                                                           onSelect: function(combobox){
                                                                                               classes_divide_classTypeCombox(combobox);
                                                                                        }"
                               style="width: 160px;" />
                    </td>
                </tr>
                <tr>
                    <th>分班方式</th>
                    <td>
                        <input id="classMode" name="classMode" class="easyui-combobox" style="width:160px;"
                            data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=104',
                                                valueField : 'id',
                                                textField : 'name',
                                                multiple : false,
                                                editable : true,
                                                panelHeight : '200'" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_classDivide_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_classDivide_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="comn_classDivide_datagrid"></table>
    </div>
</div>
