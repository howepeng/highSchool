<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var director_gaokaoScore_datagrid;
    var myview = $.extend({}, $.fn.datagrid.defaults.view, {
        renderFooter: function(target, container, frozen){
            //var opts = $.data(target, 'datagrid').options;
            //var rows = $.data(target, 'datagrid').footer || [];
            //console.log(rows);
            var fields = $(target).datagrid('getColumnFields', frozen);
            console.log(fields);
            var table = [''];
            $.ajax({
                url : '${pageContext.request.contextPath}/gaokaoScoreAction!averageClassType.action',
                data : serializeObject($('#director_gaokaoScore_SearchForm')),
                dataType : 'json',
                async:false,
                success : function(obj) {
                    if (obj.success) {
                        console.log(obj.returnObject);
                        table.push('<table class="datagrid-ftable" cellspacing="0" cellpadding="0" border="0"><tbody>');
                        table.push('<tr id="datagrid-row-r8-2-0" datagrid-row-index="0" class="datagrid-row">');
                        table.push('<td field="studentName">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-studentName">年级平均分</div>');
                        table.push('</td>');
                        table.push('<td field="fractionLanguage">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionLanguage">语文：'+obj.returnObject.fractionLanguage+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionMath">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionMath">数学：'+obj.returnObject.fractionMath+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionEnglish">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionEnglish">外语： '+obj.returnObject.fractionEnglish+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionComp1">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionComp1" sytle="width:90px;">历史/物理：'+obj.returnObject.fractionComp1+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionComp2">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionComp2" sytle="width:90px;">地理/化学：'+obj.returnObject.fractionComp2+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionComp3">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionComp3" sytle="width:90px;">政治/生物：'+obj.returnObject.fractionComp3+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionComp_count">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionComp_count" sytle="width:90px;">文/理综：'+obj.returnObject.fractionCompCount+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionCount">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionCount">总分：'+obj.returnObject.fractionCount+'</div>');
                        table.push('</td>');
                        table.push('</tr>');
                        table.push('</tbody></table>');
                        console.log(table);
                    }
                    console.log(obj.msg);
                }
            });
            $.ajax({
                url : '${pageContext.request.contextPath}/gaokaoScoreAction!averageClassName.action',
                data : serializeObject($('#director_gaokaoScore_SearchForm')),
                dataType : 'json',
                async:false,
                success : function(obj) {
                    if (obj.success) {
                        console.log(obj.returnObject);
                        table.push('<table class="datagrid-ftable" cellspacing="0" cellpadding="0" border="0"><tbody>');
                        table.push('<tr id="datagrid-row-r8-2-0" datagrid-row-index="0" class="datagrid-row">');
                        table.push('<td field="studentName">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-studentName">班级平均分</div>');
                        table.push('</td>');
                        table.push('<td field="fractionLanguage">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionLanguage">语文：'+obj.returnObject.fractionLanguage+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionMath">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionMath">数学：'+obj.returnObject.fractionMath+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionEnglish">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionEnglish">外语： '+obj.returnObject.fractionEnglish+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionComp1">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionComp1" sytle="width:90px;">历史/物理：'+obj.returnObject.fractionComp1+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionComp2">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionComp2" sytle="width:90px;">地理/化学：'+obj.returnObject.fractionComp2+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionComp3">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionComp3" sytle="width:90px;">政治/生物：'+obj.returnObject.fractionComp3+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionComp_count">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionComp_count" sytle="width:90px;">文/理综：'+obj.returnObject.fractionCompCount+'</div>');
                        table.push('</td>');
                        table.push('<td field="fractionCount">');
                        table.push('<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c8-fractionCount">总分：'+obj.returnObject.fractionCount+'</div>');
                        table.push('</td>');
                        table.push('</tr>');
                        table.push('</tbody></table>');
                        console.log(table);
                    }
                    console.log(obj.msg);
                }
            });
            $(container).html(table.join(''));
        }
    });
    $(function() {
        director_gaokaoScore_datagrid = $('#director_gaokaoScore_datagrid')
                .datagrid(
                        {
                            url: '${pageContext.request.contextPath}/gaokaoScoreAction!datagrid.action',
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
                            showFooter: true,
                            columns : [ [ {
                                title : '编号',
                                field : 'id',
                                width : 150,
                                sortable : true,
                                checkbox : true
                            }, {
                                title : '学生姓名',
                                field : 'studentName',
                                sortable:true,
                                width : 100
                            }, {
                                title : '考号',
                                field : 'gaokaoNum',
                                sortable:true,
                                width : 100
                            }, {
                                title : '专业',
                                field : 'professionalName',
                                sortable:true,
                                width : 100
                            }, {
                                title : '班级名称',
                                field : 'className',
                                sortable:true,
                                width : 100
                            } , {
                                title : '语文',
                                field : 'fractionLanguage',
                                sortable:true,
                                width : 100
                            } , {
                                title : '数学',
                                field : 'fractionMath',
                                sortable:true,
                                width : 100
                            } , {
                                title : '外语',
                                field : 'fractionEnglish',
                                sortable:true,
                                width : 100
                            } , {
                                title : '历史/物理',
                                field : 'fractionComp1',
                                sortable:true,
                                width : 100
                            } , {
                                title : '地理/化学',
                                field : 'fractionComp2',
                                sortable:true,
                                width : 100
                            } , {
                                title : '政治/生物',
                                field : 'fractionComp3',
                                sortable:true,
                                width : 100
                            } , {
                                title : '文/理综',
                                field : 'fractionComp_count',
                                sortable:true,
                                width : 100
                            } , {
                                title : '总分',
                                field : 'fractionCount',
                                sortable:true,
                                width : 100
                            } , {
                                title : '录取院校',
                                field : 'admissionSchoolName',
                                sortable:true,
                                width : 100
                            }] ],
                            toolbar : [ {
                                text : '成绩模板导出',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    director_gaokaoScoreTemplate_downloadFun();
                                }
                            }, '-', {
                                text : '高考成绩导入',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    director_gaokaoScoreTemplate_uploadFun();
                                }
                            }, '-', {
                                text : '删除',
                                iconCls : 'icon-remove',
                                handler : function() {
                                    director_gaokaoScore_remove();
                                }
                            }, '-', {
                                text : '取消选中',
                                iconCls : 'icon-undo',
                                handler : function() {
                                    director_gaokaoScore_datagrid.datagrid('unselectAll');
                                    director_gaokaoScore_datagrid.datagrid('uncheckAll');
                                }
                            },'-', {
                                text : '导出高考信息',
                                iconCls : 'icon-ok',
                                handler : function() {
                                    gaokao_score_download_All_Info_Fun();
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
        $('#director_gaokaoScore_datagrid').datagrid({
            view:myview
         });
    });

    function director_gaokaoScore_searchFun() {
        var subjectId = $('#director_gaokaoScore_SearchForm').find("input[name^='subjectId']").val();
        var objectId = $('#director_gaokaoScore_SearchForm').find("input[name^='objectId']").val();
        var averageId = $('#director_gaokaoScore_SearchForm').find("input[name^='averageId']").val();
        var professionalId = $('#director_gaokaoScore_SearchForm').find("input[name^='professionalId']").val();
        var className = $('#director_gaokaoScore_SearchForm').find("input[name^='className']").val();
        var fractionCountStart = $('#director_gaokaoScore_SearchForm').find("input[name^='fractionCountStart']").val();
        var fractionCountEnd = $('#director_gaokaoScore_SearchForm').find("input[name^='fractionCountEnd']").val();

        if (objectId == "" && averageId !="") {
            alert("请选择平均分比较对象");
            return;
        }
        if (objectId != "" && averageId == "") {
            alert("请选择平均分");
            return;
        }
        if (objectId != "" && averageId != "") {
            if (subjectId == "") {
                alert("请选择科目");
                return;
            }
            if (professionalId == "") {
                alert("请选择专业");
                return;
            }
            if (className == "") {
                alert("请选择班级名称");
                return;
            }
        }
        if (subjectId != "") {
            if ((fractionCountStart == "" || fractionCountEnd =="")
                    && (objectId == "" || averageId =="")) {
                alert("请设定成绩范围或者平均分");
                return;
            }
       }
        director_gaokaoScore_datagrid.datagrid('load',
                serializeObject($('#director_gaokaoScore_SearchForm')));
    }

    function director_gaokaoScore_cleanFun() {
        $('#director_gaokaoScore_SearchForm input').val('');
        director_gaokaoScore_datagrid.datagrid('load', {});
    }

    function director_gaokaoScore_remove(){
        var rows = $('#director_gaokaoScore_datagrid').datagrid('getChecked');
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
                                        url : '${pageContext.request.contextPath}/gaokaoScoreAction!remove.action',
                                        data : {
                                            ids : ids.join(',')
                                        },
                                        dataType : 'json',
                                        success : function(obj) {
                                            if (obj.success) {
                                                $('#director_gaokaoScore_datagrid').datagrid('load');
                                                $('#director_gaokaoScore_datagrid').datagrid('unselectAll');
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
    function director_gaokaoScoreTemplate_downloadFun() {
        $('<div id="gaokaoScoreTemplateDownload_Open" style="5px;"/>').dialog({
            href : 'jsp/director/gaokaoScoreTemplateDownload.jsp',
            width : 500,
            height :160,
            modal : true,
            title : '高考成绩录入模板导出',
            onLoad: function() {
                $('#gaokaoScoreTemplate_downloadForm').form('clear');
            } ,
            onClose : function() {
                $(this).dialog('destroy');
            },
            buttons:[
                     {
                         text : '导出',
                         iconCls : 'icon-add',
                         handler : function() {
                             gaokaoScoreTemplate_download();
                         }
                     }
                 ]
            });
   }
    function gaokaoScoreTemplate_download() {
        var classId = $('#gaokaoScoreTemplate_downloadForm').find("input[name^='className']").val();
        var className = $('#gaokaoScoreTemplate_downloadForm').find("input[type^='text']").val();
        window.open('${pageContext.request.contextPath}/downloadGaokaoScoreTempleInfoAction.action?classId='+classId+'&className='+className);
        $('#gaokaoScoreTemplateDownload_Open').dialog('destroy');
    }
    function director_gaokaoScoreTemplate_uploadFun() {
        $('<div id="gaokaoScoreTemplateUpload_Open" style="5px;"/>').dialog({
            href : 'jsp/director/gaokaoScoreTemplateUpload.jsp',
            width : 500,
            height :160,
            modal : true,
            title : '高考成绩录入',
            onLoad: function() {
                $('#gaokaoScoreTemplate_uploadForm').form('clear');
            } ,
            onClose : function() {
                $(this).dialog('destroy');
            },
            buttons:[
                     {
                         text : '导入',
                         iconCls : 'icon-add',
                         handler : function() {
                             gaokaoScoreTemplate_upload();
                         }
                     }
                 ]
            });
   }
    function gaokaoScoreTemplate_upload() {
        $('#gaokaoScoreTemplate_uploadForm').form('submit', {
            url : '${pageContext.request.contextPath}/gaokaoScoreAction!uploadClassScore.action',
            success : function(data) {
                var obj = $.parseJSON(data);
                if (obj.success) {
                    $('#director_gaokaoScore_datagrid').datagrid('load');
                    $('#gaokaoScoreTemplateUpload_Open').dialog('destroy');
                }
                $.messager.show({
                    title : '提示',
                    msg : obj.msg
                });
            }
        });
    }
    function gaokao_score_download_All_Info_Fun(){
        var subjectId = $('#director_gaokaoScore_SearchForm').find("input[name^='subjectId']").val();
        var objectId = $('#director_gaokaoScore_SearchForm').find("input[name^='objectId']").val();
        var averageId = $('#director_gaokaoScore_SearchForm').find("input[name^='averageId']").val();
        var professionalId = $('#director_gaokaoScore_SearchForm').find("input[name^='professionalId']").val();
        var className = $('#director_gaokaoScore_SearchForm').find("input[name^='className']").val();
        var fractionCountStart = $('#director_gaokaoScore_SearchForm').find("input[name^='fractionCountStart']").val();
        var fractionCountEnd = $('#director_gaokaoScore_SearchForm').find("input[name^='fractionCountEnd']").val();
        if (objectId == "" && averageId !="") {
            alert("请选择平均分比较对象");
            return;
        }
        if (objectId != "" && averageId == "") {
            alert("请选择平均分");
            return;
        }
        if (objectId != "" && averageId != "") {
            if (subjectId == "") {
                alert("请选择科目");
                return;
            }
            if (professionalId == "") {
                alert("请选择专业");
                return;
            }
            if (className == "") {
                alert("请选择班级名称");
                return;
            }
        }
        if (subjectId != "") {
            if ((fractionCountStart == "" || fractionCountEnd =="")
                    && (objectId == "" || averageId =="")) {
                alert("请设定成绩范围或者平均分");
                return;
            }
       }
        var param = param +"&professionalId="+ professionalId;
        param = param +"&className="+ className;
        param = param +"&subjectId="+ subjectId;
        param = param +"&fractionCountStart="+ fractionCountStart;
        param = param +"&fractionCountEnd="+ fractionCountEnd;
        param = param +"&objectId="+ objectId;
        param = param +"&averageId="+ averageId;
        window.open('${pageContext.request.contextPath}/downloadGaokaoScoreInfoAction.action?'+param);
    }
</script>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 140px;overflow: hidden;" align="left">
        <form id="director_gaokaoScore_SearchForm" method="post">
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
                    <th>班级名称</th>
                    <td>
                        <input id="className" name="className" class="easyui-combobox" style="width:160px;"
                            data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/classInfoAction!combox.action'" />
                    </td>
                </tr>
                <tr>
                    <th>学科</th>
                    <td>
                        <input id="subjectId" name="subjectId" class="easyui-combobox"
                               data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=106',
                               valueField : 'id',
                               textField : 'name',
                               multiple : false,
                               editable : true,
                               panelHeight : '200'" />
                    </td>
                    <th>成绩范围</th>
                    <td>
                        <input name="fractionCountStart" style="width:65px;" /> 至
                        <input name="fractionCountEnd" style="width:65px;" />
                    </td>
                </tr>
                <tr>
                    <th>平均分比较对象</th>
                    <td>
                        <input id="objectId" name="objectId" class="easyui-combobox"
                               data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=109',
                               valueField : 'id',
                               textField : 'name',
                               multiple : false,
                               editable : true,
                               panelHeight : '200'" />
                    </td>
                    <th>平均分</th>
                    <td>
                        <input id="averageId" name="averageId" class="easyui-combobox"
                               data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=108',
                               valueField : 'id',
                               textField : 'name',
                               multiple : false,
                               editable : true,
                               panelHeight : '200'" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="director_gaokaoScore_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="director_gaokaoScore_cleanFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="director_gaokaoScore_datagrid"></table>
    </div>
</div>
