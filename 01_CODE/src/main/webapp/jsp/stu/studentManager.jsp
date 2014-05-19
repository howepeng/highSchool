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
    var stu_manager_datagrid;
    $(function() {
        stu_manager_datagrid = $('#stu_manager_datagrid')
                .datagrid(
                        {
                            url : '${pageContext.request.contextPath}/studentAction!datagridStudent.action',
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
                                title : '姓名',
                                field : 'name',
                                width : 150
                            } ] ],
                            columns : [ [ {
                                title : '性别',
                                field : 'sexContent',
                                width : 150
                            }, {
                                title : '学科',
                                field : 'wlqfContent',
                                width : 150
                            },{
                                title : '身份证号',
                                field : 'idNum',
                                width : 150
                            }, {
                                title : '班级类型',
                                field : 'classTypeName',
                                width : 200
                            },{
                                title : '高考总分',
                                field : 'fractionCount',
                                width : 150
                            } , {
                                title : '报名时间',
                                field : 'createdatetime',
                                width : 150
                            } ] ],
                            toolbar : [  {
                                text : '编辑',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    stu_manager_editFun();
                                }
                            },'-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    stu_manager_removeFun();
                                }
                            }, '-', {
                                text : '下载入学视频',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    stu_manager_downloadFun();
                                }
                            }, '-', {
                                text : '下载身份证',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    stu_manager_downloadFun_byID();
                                }
                            }, '-', {
                                text : '下载成绩单',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    stu_manager_downloadFun_byReport();
                                }
                            },'-', {
                                text : '导出学生信息',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    stu_manager_download_Info_Fun();
                                }
                            },'-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    stu_manager_datagrid.datagrid('unselectAll');
                                    stu_manager_datagrid.datagrid('uncheckAll');
                                }
                            }, '-' ],
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

    function stu_manager_searchFun() {
        stu_manager_datagrid.datagrid('load',
                serializeObject($('#stu_manager_searchForm')));
    }
    function stu_manager_cleanFun() {
        $('#stu_manager_searchForm input').val('');
        stu_manager_datagrid.datagrid('load', {});
    }

    function stu_manager_downloadFun(){
        var rows = stu_manager_datagrid.datagrid('getChecked');
        if (rows.length >= 1) {
            for(var row in rows) {
                window.open('${pageContext.request.contextPath}/download.action?attachid='+rows[row].id);
            }
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要显示的记录'
            });
        }
    }

    function stu_manager_downloadFun_byID(){
        var rows = stu_manager_datagrid.datagrid('getChecked');
        if (rows.length >= 1) {
            for(var row in rows) {
                window.open('${pageContext.request.contextPath}/downloadIDFileAction.action?attachid='+rows[row].id);
            }
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要显示的记录'
            });
        }
    }

    function stu_manager_downloadFun_byReport(){
        var rows = stu_manager_datagrid.datagrid('getChecked');
        if (rows.length >= 1) {
            for(var row in rows) {
                window.open('${pageContext.request.contextPath}/downloadReportFileAction.action?attachid='+rows[row].id);
            }
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要显示的记录'
            });
        }
    }

    function stu_manager_download_Info_Fun(){
        var rows = stu_manager_datagrid.datagrid('getChecked');
        if (rows.length >= 1) {
            for(var row in rows) {
                window.open('${pageContext.request.contextPath}/downloadStudentInfoAction.action?attachid='+rows[row].id);
            }
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要显示的记录'
            });
        }
    }
    function stu_manager_editFun() {
        var rows = stu_manager_datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            $.messager.show({
                title : '提示',
                msg : '请选择一条记录'
            });
        } else if (rows.length == 1) {
            $('<div id="stuSignupEdit_Open" style="5px;"/>').dialog({
                href : 'jsp/stu/studentManagerEdit.jsp',
                width : 1024,
                height :600,
                modal : true,
                title : '修改学生信息',
                onLoad: function() {
                    $('#stu_stuSignup_form2').form('clear');
                    $('#stu_stuSignup_form2').form('load', rows[0]);
                    if (rows[0].num != "undefined") {
                        $("#stu_stuSignup_form2").find('#num1').val(rows[0].num.substr(0,5));
                        $("#stu_stuSignup_form2").find('#num2').val(rows[0].num.substr(5,3));
                        $("#stu_stuSignup_form2").find('#num3').val(rows[0].num.substr(8,2));
                        $("#stu_stuSignup_form2").find('#num4').val(rows[0].num.substr(10,4));
                    }
                    if (rows[0].photoId != "undefined") {
                        $("#stu_stuSignup_form2").find('#photoImg').attr("src",rows[0].photoImgSrc);
                        $("#stu_stuSignup_form2").find('#photoId').val(rows[0].photoId);
                        photoImgShow();
                    }else {
                        photoImgHide();
                    }
                    } ,
                onClose : function() {
                    $(this).dialog('destroy');
                },
                buttons:[ {
                    text : '修改',
                    handler : function() {
                        stu_manager_edit();
                    }
                } ]
                });
        } else {
            $.messager.show({
                title : '提示',
                msg : '请勾选要显示的记录'
            });
        }
    }
    function stu_manager_edit() {
        var num = $("#stu_stuSignup_form2").find('#num');
        var num1 = $("#stu_stuSignup_form2").find('#num1');
        var num2 = $("#stu_stuSignup_form2").find('#num2');
        var num3 = $("#stu_stuSignup_form2").find('#num3');
        var num4 = $("#stu_stuSignup_form2").find('#num4');
        num.val(num1.val()+num2.val()+num3.val()+num4.val());
        if (num.val().length != 14) {
            alert("考生号输入不正确！");
            return;
        }
        var name = $("#stu_stuSignup_form2").find('#name');
        if (name.val().length == 0) {
            alert("请输入姓名！");
            return;
        }
        var idNum = $("#stu_stuSignup_form2").find('#idNum');
        if (idNum.val().length == 0) {
            alert("请输入身份证号！");
            return;
        }
        var fractionCount = $("#stu_stuSignup_form2").find('#fractionCount');
        if (fractionCount.val().length == 0) {
            alert("请输入总分！");
            return;
        }
        var tel = $("#stu_stuSignup_form2").find('#tel');
        var fatherTel = $("#stu_stuSignup_form2").find('#fatherTel');
        var motherTel = $("#stu_stuSignup_form2").find('#motherTel');
        if (tel.val().length == 0
                && fatherTel.val().length == 0
                && motherTel.val().length == 0) {

            alert("本人电话，父亲电话，母亲电话请至少输入一个！");
            return;
        }
        var classType = $("#stu_stuSignup_form2").find("input[name^='classType']");
        if (classType.val().length == 0) {
            alert("请选择班级类型！");
            return;
        }
        var graduateSchool = $("#stu_stuSignup_form2").find('#graduateSchool');
        if (graduateSchool.val().length == 0) {
            alert("请输入毕业学校！");
            return;
        }
        $('#stu_stuSignup_form2').form('submit', {
            url : '${pageContext.request.contextPath}/stuSignupAction!signup.action',
            success : function(d) {
                var json = $.parseJSON(d);
                if (json.success) {
                    stu_manager_datagrid.datagrid('reload');
                    stu_manager_datagrid.datagrid('unselectAll');
                    stu_manager_datagrid.datagrid('uncheckAll');
                    $('#stu_stuSignup_form2').form('clear');
                    $('#stuSignupEdit_Open').dialog('destroy');
                }
                $.messager.show({
                    msg : json.msg,
                    title : '提示'
                });
            }
        });
    }
    function stu_manager_removeFun() {
        var rows = stu_manager_datagrid.datagrid('getChecked');
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
                                                url : '${pageContext.request.contextPath}/studentAction!remove.action',
                                                data : {
                                                    ids : ids.join(',')
                                                },
                                                dataType : 'json',
                                                success : function(obj) {
                                                    if (obj.success) {
                                                        stu_manager_datagrid
                                                                .datagrid('load');
                                                        stu_manager_datagrid
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
    function stu_stuSignup_cleanFun() {
        $('#stu_stuSignup_form2').form('clear');
        photoImgHide();
    }
    function getNum(){
        //var htmlobj = $.ajax({url:"${pageContext.request.contextPath}/stuSignupAction!getNum.action",async:false});
        $.post("${pageContext.request.contextPath}/stuSignupAction!getNum.action",{name:"userName",test:"test123"},callback);
        var num3 = $("#stu_stuSignup_form2").find('#num3');
        var wlqf = $("#stu_stuSignup_form2").find('#wlqf');
        num3.val(wlqf.val());
        var num2 = $("#stu_stuSignup_form2").find('#num2');
        num2.css('border','1');
        var num4 = $("#stu_stuSignup_form2").find('#num4');
        num4.css('border','1');
    }
    function callback(data) {
        var num = $("#stu_stuSignup_form2").find('#num1');
        var json = eval("("+data+")");
        num.val(json["num"]);
        //$.each(json, function(index, objVal) {
            //遍历对象数组，index是数组的索引号，objVal是遍历的一个对象。
            //val["属性"]可取到对应的属性值。
            //$("<option>").attr("value", objVal["id"]).html(objVal["name"]).appendTo(select);
        //});
    }
    function onChange_wlqf(data) {
        var num3 = $("#stu_stuSignup_form2").find('#num3');
        num3.val(data);
    }
    function ajaxFileUpload()
    {
        $('#stu_stuSignup_form2')
        .form(
                'submit',
                {
                    url : '${pageContext.request.contextPath}/stuSignupAction!uploadPhoto.action',
                    success : function(data) {
                        var obj = $.parseJSON(data);
                        if (obj.success) {
                            $("#stu_stuSignup_form2").find('#photoImg').attr("src",obj.returnObject[1]);
                            $("#stu_stuSignup_form2").find('#photoId').val(obj.returnObject[0]);
                            photoImgShow();
                        }
                        $.messager.show({
                            title : '提示',
                            msg : obj.msg
                        });
                    }
                });
        return false;
    }
    function photoImgShow() {
        $("#stu_stuSignup_form2").find('#photoImg').show();
        $("#stu_stuSignup_form2").find('#deletePhotoBtn').show();
        $("#stu_stuSignup_form2").find("input[name^='photoFile']").hide();
        $("#stu_stuSignup_form2").find('#uploadPhotoBtn').hide();
        $("#stu_stuSignup_form2").find("input[name^='photoFile']").val("");
    }

    function photoImgHide() {
        $("#stu_stuSignup_form2").find('#photoImg').hide();
        $("#stu_stuSignup_form2").find('#deletePhotoBtn').hide();
        $("#stu_stuSignup_form2").find("input[name^='photoFile']").show();
        $("#stu_stuSignup_form2").find('#uploadPhotoBtn').show();
    }

    function photoDelete() {
        photoImgHide();
        $("#stu_stuSignup_form2").find('#photoId').val("");
    }
</script>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 120px;overflow: hidden;" align="left">
        <form id="stu_manager_searchForm">
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
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="stu_manager_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="stu_manager_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>

        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="stu_manager_datagrid"></table>
    </div>
    <div id="stu_manager_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="stu_manager_editFun();" data-options="iconCls:'icon-edit'">编辑</div>
        <div onclick="stu_manager_removeFun();" data-options="iconCls:'icon-remove'">删除</div>
        <div onclick="stu_manager_downloadFun();" data-options="iconCls:'icon-ok'">下载入学视频</div>
        <div onclick="stu_manager_downloadFun_byID();" data-options="iconCls:'icon-ok'">下载身份证</div>
        <div onclick="stu_manager_downloadFun_byReport();" data-options="iconCls:'icon-ok'">下载成绩单</div>
        <div onclick="stu_manager_download_Info_Fun();" data-options="iconCls:'icon-ok'">导出学生信息</div>
    </div>
</div>
