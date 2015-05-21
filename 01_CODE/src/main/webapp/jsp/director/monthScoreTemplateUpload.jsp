<%@ page language="java" pageEncoding="UTF-8"%>
<form id="monthScoreTemplate_uploadForm" method="post" enctype="multipart/form-data">
    <table>
        <tr>
           <th>班级名称</th>
           <td>
               <input id="className" name="className" class="easyui-combobox" style="width:160px;"
                   data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/classInfoAction!combox.action'" />
           </td>
        </tr>
        <tr>
           <th>月考名称</th>
           <td>
               <input id="monthTimeId" name="monthTimeId" class="easyui-combobox" style="width: 160px;"
                      data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/monthInfoAction!combox.action'"/>
           </td>
        </tr>
        <tr>
           <td>文件</td>
           <td>
               <input type="file" name="monthScoreFile"/>
           </td>
        </tr>
    </table>
</form>
