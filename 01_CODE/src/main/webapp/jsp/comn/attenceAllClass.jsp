<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        allClass();
    });
    function allClass() {
        var s = "";
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
        s = s + "<th data-options=\"field:'className',width:80,align:'right'\">班级</th>";
        if (columns.length > 0) {
            for (var i = 0;i < columns.length; i++) {
                s = s + "<th data-options=\"field:'"+columns[i].id+"',width:80,align:'right'\">"+columns[i].text+"</th>";
            }
            s = s + "<th data-options=\"field:'delay',width:80,align:'right'\">迟到总人次</th>";
            $("#column_all_1").append(s);
        }
    }
    function comn_attenceAllClassManager_searchFun() {
        var date = $('#comn_attenceAllClassManager_searchForm').find("input[name^='date']").val();
        if (date == "") {
            alert("请选择月份");
            return;
        }
        $('#comn_attenceAllClassManager_datagrid').datagrid('load',
                serializeObject($('#comn_attenceAllClassManager_searchForm')));
    }
    function comn_attenceAllClassManager_clean_searchFun() {
        $('#comn_attenceAllClassManager_searchForm input').val('');
        $('#comn_attenceAllClassManager_datagrid').datagrid('load', {});
    }
    function comn_attenceAllClassManager_downloadFun() {
        var date = $('#comn_attenceAllClassManager_searchForm').find("input[name^='date']").val();
        if (date == "") {
            alert("请选择月份");
            return;
        }
        var param = "&date="+ date;
        window.open('${pageContext.request.contextPath}/downloadAttenceAction.action?'+param);
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'过滤条件'" style="height: 90px;overflow: hidden;" align="left">
        <form id="comn_attenceAllClassManager_searchForm">
            <input type="hidden" name="isReturnCalendar" value="${isReturnCalendar}">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>月份</th>
                    <td>
                        <input name="date" type="text" style="width:75px;" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" value=""/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_attenceAllClassManager_searchFun();">过滤</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="comn_attenceAllClassManager_clean_searchFun();">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="comn_attenceAllClassManager_datagrid" class="easyui-datagrid" title="月出勤汇总"
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
                                    comn_attenceAllClassManager_downloadFun();
                                }
                              }, '-' ]">
            <thead>
              <tr id ="column_all_1"></tr>
            </thead>
        </table>
    </div>
</div>
