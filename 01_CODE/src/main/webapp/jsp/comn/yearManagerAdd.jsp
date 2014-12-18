<%@ page language="java" pageEncoding="UTF-8"%>
<form id="comn_yearManager_addForm" method="post">
    <table>
        <tr>
            <th>学年名称</th>
            <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写课程名称'" />
            </td>
        </tr>
        <tr>
            <th>是否是当前学年</th>
            <td>
               <input name="isDefault" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=105',
                                        valueField : 'id',
                                        textField : 'name',
                                        multiple : false,
                                        editable : false,
                                        panelHeight : 'auto'" />
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" />
</form>
