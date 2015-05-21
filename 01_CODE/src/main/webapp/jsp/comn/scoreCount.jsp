<%@ page language="java" pageEncoding="UTF-8"%>
<form id="comn_scoreCountForm" method="post">
    <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
       <tr>
         <th>比较月考名称</th>
           <td>
             <input id="monthTime1" name="monthTime1" class="easyui-combobox" style="width: 160px;"
              data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/monthInfoAction!combox.action'"/>
           </td>
           <th>计算月考名称</th>
           <td>
             <input id="monthTime2" name="monthTime2" class="easyui-combobox" style="width: 160px;"
              data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/monthInfoAction!combox.action'"/>
           </td>
         </tr>
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
           <th>学科</th>
           <td>
             <input name="subjectId" class="easyui-combobox"
                    data-options="url : '${pageContext.request.contextPath}/dictionaryAction!combox.action?fatherId=106',
                    valueField : 'id',
                    textField : 'name',
                    multiple : false,
                    editable : true,
                    panelHeight : '200'" />
           </td>
         </tr>
    </table>
</form>
