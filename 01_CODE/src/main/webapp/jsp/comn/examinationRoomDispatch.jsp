<%@ page language="java" pageEncoding="UTF-8"%>
<form id="comn_examinationRoom_DispatchForm" method="post">
    <table>
        <tr>
           <th>专业</th>
           <td>
               <input id="professionalId" name="professionalId" class="easyui-combobox"
                      data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=112',
                      valueField : 'id',
                      textField : 'name',
                      multiple : false,
                      editable : true,
                      panelHeight : '200',
                      required:true,
                      missingMessage:'请填写专业'" />
           </td>
           <th>计算方式</th>
           <td>
               <input id="dispatchType" name="dispatchType" class="easyui-combobox"
                      data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=113',
                      valueField : 'id',
                      textField : 'name',
                      multiple : false,
                      editable : true,
                      panelHeight : '200',
                      required:true,
                      missingMessage:'请填写计算方式'" />
           </td>
        </tr>
        <tr>
           <th>行</th>
           <td>
                <input name="row" style="width:180px;" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写行，最大10'"/>
           </td>
           <th>列</th>
           <td>
                <input name="column" style="width:180px;" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写列，最大10'"/>
           </td>
        </tr>
        <tr>
           <th>考场名称前缀</th>
           <td>
                <input name="name" style="width:180px;" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写考场名称前置'"/>
           </td>
           <th>年级名次排序</th>
           <td>
               <input id="rangeOrder" name="rangeOrder" class="easyui-combobox"
                   data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=114',
                   valueField : 'id',
                   textField : 'name',
                   multiple : false,
                   editable : true,
                   panelHeight : '200',
                   required:true,
                   missingMessage:'请填写年级名次排序'" />
           </td>
        </tr>
        <tr>
           <th>比较月考信息</th>
           <td>
                <input id="monthTimeId1" name="monthTimeId1" class="easyui-combobox" style="width: 160px;"
                  data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/monthInfoAction!combox.action',
                      required:true,
                      missingMessage:'请填写比较月考信息'"/>
           </td>
           <th>计算月考信息</th>
           <td>
                <input id="monthTimeId2" name="monthTimeId2" class="easyui-combobox" style="width: 160px;"
                  data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/monthInfoAction!combox.action',
                      required:true,
                      missingMessage:'请填写计算月考信息'"/>
           </td>
        </tr>
    </table>
</form>
