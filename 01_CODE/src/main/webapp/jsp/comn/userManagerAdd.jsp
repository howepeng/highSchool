<%@ page language="java" pageEncoding="UTF-8"%>
    <form id="comn_userManager_addForm" method="post">
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
                <td><input name="password" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写登录密码'" />
                </td>
                <th>重复密码</th>
                <td><input type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请再次填写密码',validType:'eqPwd[\'#comn_userManager_addForm input[name=password]\']'" />
                </td>
            </tr>
        </table>
    </form>
