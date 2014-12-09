<%@ page language="java" pageEncoding="UTF-8"%>
<form id="comn_classTimeManager_addForm" method="post">
    <table>
        <tr>
            <th>课程名称</th>
            <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写课程名称'" />
            </td>
        </tr>
        <tr>
            <th>时间</th>
            <td>
               <input name="startTime" type="text" style="width:75px;" onfocus="WdatePicker({dateFmt:'HH:mm:ss',realDateFmt:'HH:mm:ss'})" readonly="readonly"/>至
               <input name="endTime" type="text" style="width:75px;" onfocus="WdatePicker({dateFmt:'HH:mm:ss',realDateFmt:'HH:mm:ss'})" readonly="readonly"/>
            </td>
        </tr>
    </table>
</form>
