<%@ page language="java" pageEncoding="UTF-8"%>
<form id="comn_dormitoryManager_addForm" method="post">
    <table>
        <tr>
            <th>名称</th>
            <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写名称'" />
            </td>
        </tr>
        <tr>
            <th>人数</th>
            <td>
               <input name="peopleCount" class="easyui-validatebox" />
            </td>
        </tr>
        <tr>
            <th>房间个数</th>
            <td>
               <input name="dormitoryCount" class="easyui-validatebox" />
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" />
</form>
