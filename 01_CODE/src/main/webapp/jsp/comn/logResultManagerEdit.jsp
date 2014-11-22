<%@ page language="java" pageEncoding="UTF-8"%>
    <form id="comn_classTimeManager_editForm" method="post">
        <table>
            <tr>
                <th>名称</th>
                <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写名称'" />
                </td>
            </tr>
        </table>
        <input type="hidden" name="id" />
    </form>
