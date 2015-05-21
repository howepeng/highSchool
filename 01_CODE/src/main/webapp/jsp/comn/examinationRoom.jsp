<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var comn_examinationRoom_datagrid;
    $(function() {
        comn_examinationRoom_datagrid = $('#comn_examinationRoom_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/examinationRoomAction!datagrid.action',
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
                            sortName : 'id',
                            sortOrder : 'desc',
                            checkOnSelect : false,
                            selectOnCheck : true,
                            showFooter: true,
                            columns : [ [ {
                                title : '编号',
                                field : 'id',
                                width : 150,
                                sortable : true,
                                checkbox : true
                            }, {
                                title : '专业',
                                field : 'professionalName',
                                sortable:true,
                                width : 100
                            }, {
                                title : '计算方式',
                                field : 'dispatchTypeName',
                                sortable:true,
                                width : 100
                            }, {
                                title : '行',
                                field : 'row',
                                sortable:true,
                                width : 100
                            }, {
                                title : '列',
                                field : 'column',
                                sortable:true,
                                width : 100
                            } , {
                                title : '考场名称前缀',
                                field : 'name',
                                sortable:true,
                                width : 100
                            } , {
                                title : '年级名次排序',
                                field : 'rangeOrderName',
                                sortable:true,
                                width : 100
                            } , {
                                title : '比较月考信息',
                                field : 'monthTimeName1',
                                sortable:true,
                                width : 100
                            } , {
                                title : '计算月考信息',
                                field : 'monthTimeName2',
                                sortable:true,
                                width : 100
                            } , {
                                title : '文件名',
                                field : 'fileName',
                                sortable:true,
                                width : 100
                            }] ],
                            toolbar : [ {
                                text : '分配考场',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_examinationRoom_dispatchFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    comn_examinationRoom_remove();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    comn_examinationRoom_datagrid.datagrid('unselectAll');
                                    comn_examinationRoom_datagrid.datagrid('uncheckAll');
                                }
                            },'-', {
                                text : '导出上次分配结果',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    comn_examinationRoom_download_Last_Info_Fun();
                                }
                            },'-', {
                                text : '导出新分配结果',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    comn_examinationRoom_download_New_Info_Fun();
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

    function comn_examinationRoom_searchFun() {
        comn_examinationRoom_datagrid.datagrid('load',
                serializeObject($('#comn_examinationRoom_SearchForm')));
    }

    function comn_examinationRoom_cleanFun() {
        $('#comn_examinationRoom_SearchForm input').val('');
        comn_examinationRoom_datagrid.datagrid('load', {});
    }

    function comn_examinationRoom_remove(){
        var rows = $('#comn_examinationRoom_datagrid').datagrid('getChecked');
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
                                        url : '${pageContext.request.contextPath}/examinationRoomAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#comn_examinationRoom_datagrid').datagrid('load');
                                                $('#comn_examinationRoom_datagrid').datagrid('unselectAll');
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
    function comn_examinationRoom_dispatchFun() {
        $('<div id="comn_examinationRoomDispatch_Open" style="5px;"/>').dialog({
            href : 'jsp/comn/examinationRoomDispatch.jsp',
            width : 600,
            height :200,
            modal : true,
            title : '分配考场',
            onLoad: function() {
                $('#comn_examinationRoom_DispatchForm').form('clear');
                } ,
            onClose : function() {
                $(this).dialog('destroy');
            },
            buttons:[
                     {
                         text : '分配',
                         iconCls : 'icon-add',
                         handler : function() {
                             comn_examinationRoom_dispatch();
                         }
                     }
                 ]
            });
    }
    function comn_examinationRoom_dispatch() {
        var professionalId = $('#comn_examinationRoom_DispatchForm').find("input[name^='professionalId']").val();
        if(professionalId == "" || professionalId =="undefined") {
            alert("请选择专业");
            return;
        }
        var dispatchType = $('#comn_examinationRoom_DispatchForm').find("input[name^='dispatchType']").val();
        if(dispatchType == "" || dispatchType =="undefined") {
            alert("请选择计算方式");
            return;
        }
        var row = $('#comn_examinationRoom_DispatchForm').find("input[name^='row']").val();
        if(row == "" || row =="undefined") {
            alert("请选择行");
            return;
        }
        if(Number(row) > 10) {
            alert("行不能大于10");
            return;
        }
        var column = $('#comn_examinationRoom_DispatchForm').find("input[name^='column']").val();
        if(column == "" || column =="undefined") {
            alert("请选择列");
            return;
        }
        if(Number(column) > 10) {
            alert("列不能大于10");
            return;
        }
        var name = $('#comn_examinationRoom_DispatchForm').find("input[name^='name']").val();
        if(name == "" || name =="undefined") {
            alert("请选择考场名称前缀");
            return;
        }
        var rangeOrder = $('#comn_examinationRoom_DispatchForm').find("input[name^='rangeOrder']").val();
        if(rangeOrder == "" || rangeOrder =="undefined") {
            alert("请选择年级名次排序");
            return;
        }
        var monthTimeId1 = $('#comn_examinationRoom_DispatchForm').find("input[name^='monthTimeId1']").val();
        if(monthTimeId1 == "" || monthTimeId1 =="undefined") {
            alert("请选择比较月考信息");
            return;
        }
        var monthTimeId2 = $('#comn_examinationRoom_DispatchForm').find("input[name^='monthTimeId2']").val();
        if(monthTimeId2 == "" || monthTimeId2 =="undefined") {
            alert("请选择计算月考信息");
            return;
        }
        var param = "professionalId="+professionalId;
        param = param +"&dispatchType="+ dispatchType;
        param = param +"&row="+ row;
        param = param +"&column="+ column;
        param = param +"&name="+ name;
        param = param +"&rangeOrder="+ rangeOrder;
        param = param +"&monthTimeId1="+ monthTimeId1;
        param = param +"&monthTimeId2="+ monthTimeId2;
        window.open('${pageContext.request.contextPath}/downloadExaminationRoomAction.action?'+param);
        comn_examinationRoom_datagrid.datagrid('load',
                serializeObject($('#comn_examinationRoom_datagrid')));
        $('#comn_examinationRoomDispatch_Open').dialog('destroy');
    }
    function comn_examinationRoom_download_Last_Info_Fun() {
        var rows = $('#comn_examinationRoom_datagrid').datagrid('getChecked');
        if (rows.length ==1 ) {
            var param = "professionalId="+rows[0].professionalId;
            param = param +"&dispatchType="+ rows[0].dispatchType;
            param = param +"&row="+ rows[0].row;
            param = param +"&column="+ rows[0].column;
            param = param +"&name="+ rows[0].name;
            param = param +"&rangeOrder="+ rows[0].rangeOrder;
            param = param +"&monthTimeId1="+ rows[0].monthTimeId1;
            param = param +"&monthTimeId2="+ rows[0].monthTimeId2;
            param = param +"&attachid="+ rows[0].id;
            param = param +"&isNew=0";
            window.open('${pageContext.request.contextPath}/downloadExaminationRoomAction.action?'+param);
            comn_examinationRoom_datagrid.datagrid('load',
                    serializeObject($('#comn_examinationRoom_datagrid')));
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选一条记录'
            });
        }
    }
    function comn_examinationRoom_download_New_Info_Fun() {
        var rows = $('#comn_examinationRoom_datagrid').datagrid('getChecked');
        if (rows.length ==1 ) {
            var param = "professionalId="+rows[0].professionalId;
            param = param +"&dispatchType="+ rows[0].dispatchType;
            param = param +"&row="+ rows[0].row;
            param = param +"&column="+ rows[0].column;
            param = param +"&name="+ rows[0].name;
            param = param +"&rangeOrder="+ rows[0].rangeOrder;
            param = param +"&monthTimeId1="+ rows[0].monthTimeId1;
            param = param +"&monthTimeId2="+ rows[0].monthTimeId2;
            param = param +"&attachid="+ rows[0].id;
            window.open('${pageContext.request.contextPath}/downloadExaminationRoomAction.action?'+param);
            comn_examinationRoom_datagrid.datagrid('load',
                    serializeObject($('#comn_examinationRoom_datagrid')));
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选一条记录'
            });
        }
    }
</script>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 140px;overflow: hidden;" align="left">
        <form id="comn_examinationRoom_SearchForm" method="post">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                   <th>专业</th>
                   <td>
                       <input id="professionalId" name="professionalId" class="easyui-combobox"
                              data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=112',
                              valueField : 'id',
                              textField : 'name',
                              multiple : false,
                              editable : true,
                              panelHeight : '200'" />
                   </td>
                   <th>计算方式</th>
                   <td>
                       <input id="dispatchType" name="dispatchType" class="easyui-combobox"
                              data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=113',
                              valueField : 'id',
                              textField : 'name',
                              multiple : false,
                              editable : true,
                              panelHeight : '200'" />
                   </td>
                </tr>
                <tr>
                   <th>比较月考信息</th>
                   <td>
                        <input id="monthTimeId1" name="monthTimeId1" class="easyui-combobox" style="width: 160px;"
                          data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/monthInfoAction!combox.action'"/>
                   </td>
                   <th>计算月考信息</th>
                   <td>
                        <input id="monthTimeId2" name="monthTimeId2" class="easyui-combobox" style="width: 160px;"
                          data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/monthInfoAction!combox.action'"/>
                   </td>
                </tr>
                <tr>
                   <th>考场名称前缀</th>
                   <td>
                        <input name="name" style="width:180px;" class="easyui-validatebox"/>
                   </td>
                   <th>年级名次排序</th>
                   <td>
                       <input id="rangeOrder" name="rangeOrder" class="easyui-combobox"
                           data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=114',
                           valueField : 'id',
                           textField : 'name',
                           multiple : false,
                           editable : true,
                           panelHeight : '200'" />
                   </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_examinationRoom_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_examinationRoom_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="comn_examinationRoom_datagrid"></table>
    </div>
</div>
