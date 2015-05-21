<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
var chart;
var colors = Highcharts.getOptions().colors;
$(function() {
    init();
    searchInfoInit();
});
function init() {
}
function searchInfoInit() {
    var classId = $("#director_fractionLine_searchForm").find("input[name^='classId']").val();
    var url = '${pageContext.request.contextPath}/studentAction!combox.action';
    if (classId != "") {
        url = url + '?classId='+classId;
    }
    $("#director_fractionLine_searchForm").find("input[id^='studentIds']").combobox({
        url:url,
        valueField:'id',
        textField:'text',
        multiple:true,
        editable:true,
        panelHeight:'200'
    });
    $("#director_fractionLine_searchForm").find("input[name^='classId']").combobox({
        onChange:function(){
            var classId = $("#director_fractionLine_searchForm").find("input[name^='classId']").val();
            if (classId != "") {
                var url = '${pageContext.request.contextPath}/studentAction!combox.action?classId='+classId;
                $("#director_fractionLine_searchForm").find("input[id^='studentIds']").combobox({
                    url:url,
                    valueField:'id',
                    textField:'text',
                    multiple:true,
                    editable:true,
                    panelHeight:'200'
                });
                $("#director_fractionLine_searchForm").find("input[name^='studentIds']").remove();
            }
        }
    });
}
function director_fractionLine_searchForm() {
    var classId = $("#director_fractionLine_searchForm").find("input[name^='classId']").val();
    if (classId == "") {
        alert("请选择班级");
        return;
    }
    var studentId = $("#director_fractionLine_searchForm").find("input[name^='studentIds']").length;
    console.log(studentId);
    var subjectId = $("#director_fractionLine_searchForm").find("input[name^='subjectId']").val();
    if (subjectId == "") {
        alert("请选择科目");
        return;
    }
    var modeId = $("#director_fractionLine_searchForm").find("input[name^='modeId']").val();
    if (modeId == "") {
        alert("请选择比较方式");
        return;
    }
    var showName = "排名";
    var reversed = true;
    if  (modeId == "107001"){
        showName = "成绩";
        reversed = false;
    }

    $.ajax({
        url: "${pageContext.request.contextPath}/scoreCountAction!showChartLine.action",
        data: $('#director_fractionLine_searchForm').serialize(),
        success: function(data){
            console.log(data);
            showChart('','line','',data.returnObject.dataLst,data.returnObject.grpLst,showName,'','分','chartContent', reversed);
        },
        dataType: "json",
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            if(textStatus = "timeout") {
                alert("请求超时！");
            } else {
                alert("请求错误！");
            }
        }
    });
}

function director_fractionLine_clean_searchForm() {

}
function showChart(title, chartType, name, data, categories, ytext, tipText, dw, chartdiv, reversed) {
    chart = new Highcharts.Chart({
        chart: {
            renderTo: chartdiv,
            type: chartType
        },
        title: {
            text: title
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: ytext
            },
            reversed: reversed,
            min : 0
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            },
            column: {
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: colors[0],
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y +dw;
                    }
                }
            }
        },
        tooltip: {
            formatter: function() {
                var point = this.point,
                s = this.x +':<b>'+ this.y + dw + '</b><br/>';
                s += tipText;
                return s;
            }
        },
        series: data
    });
}
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'过滤条件'" style="height: 139px;overflow: hidden;" align="left">
        <form id="director_fractionLine_searchForm">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>班级</th>
                    <td>
                        <input id = "classId" name="classId" class="easyui-combobox"
                               data-options="url : '${pageContext.request.contextPath}/classInfoAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : true,
                                    panelHeight : '200'" />
                    </td>
                    <th>姓名</th>
                    <td>
                        <input id = "studentIds" name="studentIds" />
                    </td>
                    <th>学科</th>
                    <td>
                      <input name="subjectId" class="easyui-combobox"
                             data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=106',
                             valueField : 'id',
                             textField : 'name',
                             multiple : false,
                             editable : true,
                             panelHeight : '200'" />
                    </td>
                </tr>
                <tr>
                    <th>比较方式</th>
                    <td>
                      <input name="modeId" class="easyui-combobox"
                             data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=107',
                             valueField : 'id',
                             textField : 'name',
                             multiple : false,
                             editable : true,
                             panelHeight : '200'" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="director_fractionLine_searchForm();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="director_fractionLine_clean_searchForm();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false">
        <div id="chartContent" align="center" style="z-index:1;"></div>
    </div>

</div>

