<%@ page language="java" pageEncoding="UTF-8"%>
<form id="comn_classDivide_setUserForm" method="post">
    <table>
        <tr>
            <th>班主任</th>
            <td>
                <input id="userId" name="userId" class="easyui-combobox" style="width: 180px;"
                               data-options="valueField:'id',textField:'text',required:true,missingMessage:'请选择班主任',url:'${pageContext.request.contextPath}/userAction!combox.action'"/>
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" />
</form>
