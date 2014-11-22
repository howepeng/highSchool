<%@ page language="java" pageEncoding="UTF-8"%>
    <form id="comn_classTimeManager_addForm" method="post">
        <table>
            <tr>
                <th>课程名称</th>
                <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写课程名称'" />
                </td>
            </tr>
            <tr>
                <th>开始时间</th>
                <td><input name="startDicId" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=100',
                                        valueField : 'id',
                                        textField : 'name',
                                        multiple : false,
                                        editable : false,
                                        panelHeight : 'auto'" />
                </td>
            </tr>
            <tr>
                <th>结束时间</th>
                <td><input name="endDicId" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=100',
                                        valueField : 'id',
                                        textField : 'name',
                                        multiple : false,
                                        editable : false,
                                        panelHeight : 'auto'" />
                </td>
            </tr>
        </table>
    </form>
