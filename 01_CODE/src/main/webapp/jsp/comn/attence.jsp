<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        oneClass();
    });
    function oneClass() {
        var s = "";
        var s2 = "";
        var columns = "";
        $.ajax({
            url : '${pageContext.request.contextPath}/classTimeAction!combox.action',
            data : null,
            dataType : 'json',
            async:false,
            success : function(obj) {
                columns = obj;
            }
        });
        s = s + "<th colspan=\"1\">&nbsp;</th>";
        s2 = s2 + "<th data-options=\"field:'date',width:80,align:'right'\">日期</th>";
        if (columns.length > 0) {
            for (var i = 0;i < columns.length; i++) {
                s = s + "<th colspan=\"3\">"+columns[i].text+"</th>";
                s2 = s2 + "<th data-options=\"field:'"+columns[i].id+"_1"+"',width:80,align:'right'\">应</th>";
                s2 = s2 + "<th data-options=\"field:'"+columns[i].id+"_2"+"',width:80,align:'right'\">实</th>";
                s2 = s2 + "<th data-options=\"field:'"+columns[i].id+"_3"+"',width:80,align:'right'\">迟</th>";
            }
            $("#column_1").append(s);
            $('#column_2').append(s2);
        }
    }
    function comn_attenceManager_searchFun() {
        var classId = $('#comn_attenceManager_searchForm').find("input[name^='classId']").val();
        if (classId == "") {
            alert("请选择班级");
            return;
        }
        var date = $('#comn_attenceManager_searchForm').find("input[name^='date']").val();
        if (date == "") {
            alert("请选择月份");
            return;
        }
        $('#comn_attenceManager_datagrid').datagrid('load',
                serializeObject($('#comn_attenceManager_searchForm')));
    }
    function comn_attenceManager_clean_searchFun() {
        $('#comn_attenceManager_searchForm input').val('');
        $('#comn_attenceManager_datagrid').datagrid('load', {});
    }
    function comn_attenceManager_downloadFun() {
        var classId = $('#comn_attenceManager_searchForm').find("input[name^='classId']").val();
        if (classId == "") {
            alert("请选择班级");
            return;
        }
        var date = $('#comn_attenceManager_searchForm').find("input[name^='date']").val();
        if (date == "") {
            alert("请选择月份");
            return;
        }
        var param = "&date="+ date;
        param = param + "&classId="+ classId;
        window.open('${pageContext.request.contextPath}/downloadAttenceAction.action?'+param);
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'过滤条件'" style="height: 90px;overflow: hidden;" align="left">
        <form id="comn_attenceManager_searchForm">
            <input type="hidden" name="isReturnCalendar" value="${isReturnCalendar}">
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
                    <th>月份</th>
                    <td>
                        <input name="date" type="text" style="width:75px;" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" value=""/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_attenceManager_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_attenceManager_clean_searchFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="comn_attenceManager_datagrid" class="easyui-datagrid" title="月出勤"
                data-options="singleSelect:true,
                              url:'${pageContext.request.contextPath}/attenceAction!datagrid.action',
                              method:'get',
                              iconCls : 'icon-save',
                              fit : true,
                              fitColumns : true,
                              nowrap : false,
                              border : false,
                              idField : 'date',
                              checkOnSelect : false,
                              selectOnCheck : true,
                              showFooter: true,
                              toolbar : [ {
                                text : '导出',
                                iconCls : 'icon-edit',
                                handler : function() {
                                    comn_attenceManager_downloadFun();
                                }
                              }, '-' ]">
            <thead>
              <tr id ="column_1"></tr>
              <tr id ="column_2"></tr>
            </thead>
        </table>
    </div>
</div>
