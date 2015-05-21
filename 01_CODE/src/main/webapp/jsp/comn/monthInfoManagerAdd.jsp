<%@ page language="java" pageEncoding="UTF-8"%>
<form id="comn_monthInfoManager_addForm" method="post">
    <table>
        <tr>
            <th>月考名称</th>
            <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写月考名称'" />
            </td>
        </tr>
        <tr>
            <th>代表数值</th>
            <td><input name="value" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写代表数值'" />
        </tr>
    </table>
</form>
