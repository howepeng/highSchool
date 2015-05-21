<%@ page language="java" pageEncoding="UTF-8"%>
    <form id="comn_userManager_editForm" method="post">
        <table>
            <tr>
                <th>登录名称</th>
                <td><input name="username" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写登录名称'" />
                </td>
                <th>姓名</th>
                <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写姓名'" />
                </td>
            </tr>
            <tr>
                <th>所属角色</th>
                <td><input name="roleIds" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/roleAction!combox.action',
                                        valueField : 'id',
                                        textField : 'name',
                                        multiple : true,
                                        editable : true,
                                        panelHeight : '200'" />
                </td>
            </tr>
            <tr>
                <th>密码</th>
                <td><input name="password" type="password" /></td>
                <td colspan="2" style="text-align: left;">如果不修改请留空</td>
            </tr>
        </table>
        <input type="hidden" name="id" />
    </form>
