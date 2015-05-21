<%@ page language="java" pageEncoding="UTF-8"%>
    <form id="comn_logTypeManager_editForm" method="post">
        <table>
           <tr>
                <th>日志类型名称</th>
                <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写类型名称'" />
                </td>
                <th>方式</th>
                <td><input name="typeId" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=102',
                                        valueField : 'id',
                                        textField : 'name',
                                        multiple : false,
                                        editable : true,
                                        panelHeight : '200'" />
                </td>
            </tr>
            <tr>
                <th>模式</th>
                <td><input name="modeId" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=101',
                                        valueField : 'id',
                                        textField : 'name',
                                        multiple : false,
                                        editable : true,
                                        panelHeight : '200'" />
                </td>
                <th>满N次</th>
                <td><input name="count" class="easyui-validatebox"/>
                </td>
                <th>积分</th>
                <td><input name="score" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写积分'" />
                </td>
            </tr>
            <tr>
                <th>计入考勤</th>
                <td>
                    <select id="attence" name="attence" style="width: 180px;">
                        <option value="0" SELECTED>否</option>
                        <option value="1">是</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td><input name="remark" class="easyui-validatebox"/>
                </td>
            </tr>
        </table>
        <input type="hidden" name="id" />
    </form>
