<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function loginFun(){
    $('#comn_login_loginForm').form('submit',{
        url : '${pageContext.request.contextPath}/userAction!doNotNeedSession_login.action',
        success : function(data) {
            var obj = $.parseJSON(data);
            if(obj.success){
                $('#user_login_loginDialog').dialog('close');
                window.location.href='${pageContext.request.contextPath}/userAction!doNotNeedSession_main.action';
            }
            $.messager.show({
                title:'提示',
                msg:obj.msg
            });
        }
    });
}
$(function(){
    $('#comn_login_loginForm input').bind("keyup",function(event){
        if(event.keyCode=='13'){
            loginFun();
        }
    });
    window.setTimeout(function(){
        $('#comn_login_loginForm input[name=name]').focus();
    },0);
});
</script>
<div id="user_login_loginDialog" class="easyui-dialog"
    data-options="title:'登陆',modal:true,closable:false,buttons:[{
                text:'注册',
                iconCls:'icon-edit',
                handler:function(){
                    $('#comn_register_regForm input').val('');
                    $('#comn_register_regDialog').dialog('open');
                }
            },{
                text:'登陆',
                iconCls:'icon-help',
                handler:function(){
                    loginFun();
                }
            }]">
    <form id="comn_login_loginForm" method="post">
        <table>
            <tr>
                <th>登录名</th>
                <td><input name="username" class="easyui-validatebox" value="admin" data-options="required:true,missingMessage:'请输入用户名'"/>
                </td>
            </tr>
            <tr>
                <th>密码</th>
                <td><input type="password" name="password" class="easyui-validatebox" value="test"  data-options="required:true,missingMessage:'请输入密码'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
