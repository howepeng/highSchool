<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function regFun(){
	$('#comn_register_regForm').form('submit',{
		url : '${pageContext.request.contextPath}/userAction!doNotNeedSession_register.action',
		success : function(data) {
			var obj = $.parseJSON(data);
			if(obj.success){
				$('#comn_register_regDialog').dialog('close');
			}
			$.messager.show({
				title:'提示',
				msg:obj.msg
			});
		}
	});
}
$(function(){
	$('#comn_register_regForm input').bind("keyup",function(event){
		if(event.keyCode=='13'){
			regFun();
		}
	});
});

</script>
<div id="comn_register_regDialog" style="width: 240px" class="easyui-dialog"
	data-options="title:'登陆',modal:true,closed:true,buttons:[{
				text:'注册',
				iconCls:'icon-edit',
				handler:function(){
					regFun();
				}
			}]">
	<form id="comn_register_regForm" method="post">
		<table>
			<tr>
				<th>登录名</th>
				<td><input name="username" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入用户名'" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="password" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入密码'" />
				</td>
			</tr>
			<tr>
				<th>确认密码</th>
				<td><input type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请确认密码',validType:'eqPwd[\'#comn_register_regForm input[name=password]\']'" />
				</td>
			</tr>
		</table>
	</form>
</div>