<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
$("#director_logManager_addForm").find('#classTimeId').combobox({
    onChange:function(){
        var classTimeId = $("#director_logManager_addForm").find("input[name^='classTimeId']").val();
        var startTime = $("#director_logManager_addForm").find('#startTime').val();
        var endTime = $("#director_logManager_addForm").find('#endTime').val();
        $.ajax({
            url : '${pageContext.request.contextPath}/logManagerAction!changeClassTime.action',
            data : {
                classTimeId : classTimeId,
                startTime : startTime,
                endTime : endTime,
            },
            dataType : 'json',
            success : function(obj) {
                if (obj.success) {
                    $("#director_logManager_addForm").find('#showStartTime').val(obj.returnObject.showStartTime);
                    $("#director_logManager_addForm").find('#showEndTime').val(obj.returnObject.showEndTime);
                }
            }
        });
    }
});
</script>
<form id="director_logManager_addForm" method="post">
    <table>
        <tr>
            <th>日志类型</th>
            <td><input id = "typeId" name="typeId" class="easyui-combobox"
                data-options="url : '${pageContext.request.contextPath}/logTypeAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : false,
                                    panelHeight : 'auto'" />
            </td>
            <th>课程</th>
            <td><input id = "classTimeId" name="classTimeId" class="easyui-combobox"
                data-options="url : '${pageContext.request.contextPath}/classTimeAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : false,
                                    panelHeight : 'auto'" />
            </td>
        </tr>
        <tr>
            <th>发生日期</th>
            <td>
               <input id = "showDate" name="showDate" type="text" style="width:125px;" onfocus="WdatePicker()" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <th>开始时间</th>
            <td>
               <input id = "showStartTime" name="showStartTime" type="text" style="width:125px;"  readonly="readonly"/>
            </td>
            <th>结束时间</th>
            <td>
               <input id = "showEndTime" name="showEndTime" type="text" style="width:125px;"  readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <th>班级</th>
            <td><input id = "classId" name="classId" class="easyui-combobox"
                data-options="url : '${pageContext.request.contextPath}/logResultAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : false,
                                    panelHeight : 'auto'" />
            </td>
            <th>学生</th>
            <td><input id = "studentId" name="studentId" class="easyui-combobox"
                data-options="url : '${pageContext.request.contextPath}/studentAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : true,
                                    panelHeight : 'auto'" />
            </td>
        </tr>
        <tr>
            <th>日志结果</th>
            <td><input id = "resultId" name="resultId" class="easyui-combobox"
                data-options="url : '${pageContext.request.contextPath}/logResultAction!combox.action',
                                    valueField : 'id',
                                    textField : 'text',
                                    multiple : false,
                                    editable : false,
                                    panelHeight : 'auto'" />
            </td>
        </tr>
        <tr>
            <th>备注</th>
            <td>
                <textarea cols="20" name="remark"></textarea>
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" />
    <input type="hidden" id = "date" name="date" />
    <input type="hidden" id = "startTime" name="startTime" />
    <input type="hidden" id = "endTime" name="endTime" />
</form>
