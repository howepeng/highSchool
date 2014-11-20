  <%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>奥宇信息化管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.4/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.4/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="jslib/uploadify/uploadify.css" type="text/css"></link>
<link rel='stylesheet' href='jslib/fullcalendar-2.1.1/fullcalendar.css'  />
<link rel='stylesheet' href='jslib/fullcalendar-2.1.1/fullcalendar.print.css'  media='print' />
<script type="text/javascript" src="jslib/jquery-easyui-1.3.4/jquery.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src='jslib/fullcalendar-2.1.1/moment.min.js'></script>
<script type="text/javascript" src='jslib/fullcalendar-2.1.1/fullcalendar.min.js'></script>
<script type="text/javascript" src='jslib/fullcalendar-2.1.1/lang-all.js'></script>
<script type="text/javascript" src="jslib/hsUtil.js"></script>
<script type="text/javascript" src="jslib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jslib/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
function exit() {
     $.post("${pageContext.request.contextPath}/userAction!logOut.action",{},callback);
}
function callback(data) {
    var obj = $.parseJSON(data);
    if(obj.success){
        window.location.href="${pageContext.request.contextPath}/index.jsp";
    }
}
</script>

</head>
<body class="easyui-layout">

    <div data-options="region:'north'" style="text-align:right;height:35px;">
        <div style="text-align:right;height:5px;"> </div>
        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="exit();">退出</a>
    </div>
    <div data-options="region:'south'" style="height:20px;"></div>
    <!-- <div data-options="region:'east',title:'日历',split:true" style="width:200px;">
        <jsp:include page="/jsp/layout/east.jsp"></jsp:include>
    </div> -->
    <div data-options="region:'west'" style="width:200px;">
        <jsp:include page="/jsp/layout/west.jsp"></jsp:include>
    </div>
    <div data-options="region:'center',title:'欢迎使用奥宇信息化管理系统'">
        <jsp:include page="/jsp/layout/center.jsp"></jsp:include>
    </div>
</body>
</html>
