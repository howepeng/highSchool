<%@ page language="java" pageEncoding="UTF-8"%>
<form id="monthScoreTemplate_downloadForm" method="post">
    <table>
        <tr>
           <th>班级名称</th>
           <td>
               <input id="className" name="className" class="easyui-combobox" style="width:160px;"
                   data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/classInfoAction!combox.action'" />
           </td>
        </tr>
    </table>
</form>
