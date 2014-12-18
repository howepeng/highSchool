<%@ page language="java" pageEncoding="UTF-8"%>
<form id="class_divideForm" method="post">
    <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
        <tr>
            <th>年限</th>
            <td>
                <input id="yearId" name="yearId" class="easyui-combobox" style="width: 180px;"
                               data-options="valueField:'id',textField:'text',required:true,missingMessage:'请选择年限',url:'${pageContext.request.contextPath}/yearAction!combox.action'"/>
            </td>
            <th>班级类型</th>
            <td>
                <input id="classType" name="classType" class="easyui-combobox"
                               data-options="valueField:'id',textField:'text',required:true,missingMessage:'请选择班级类型',url:'${pageContext.request.contextPath}/classTypeAction!combox.action',
                                                                                           onSelect: function(combobox){
                                                                                               classes_divide_classTypeCombox(combobox);
                                                                                        }"
                               style="width: 180px;" />
            </td>
        </tr>
        <tr>
            <th>分班数</th>
            <td>
                <input name="classNum" style="width:180px;"  class="easyui-validatebox" data-options="required:true,missingMessage:'请填写分半数'"/>
            </td>
            <th>分班方式</th>
            <td>
                <input name="classModeId" class="easyui-combobox" style="width:180px;"
                    data-options="required:true,missingMessage:'请选择分班方式',url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=104',
                                        valueField : 'id',
                                        textField : 'name',
                                        multiple : false,
                                        editable : false,
                                        panelHeight : 'auto'" />
            </td>
        </tr>
        <tr>
            <th>班级名称前缀</th>
            <td>
                <input name="classPrefixion" style="width:180px;" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写班级名称前缀'"/>
            </td>
        </tr>
    </table>
</form>
