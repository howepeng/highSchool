<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
       $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            lang: 'zh-cn',
            currentTimezone: 'Asia/Beijing',
            defaultDate: new Date(),
            //timezone: currentTimezone,
            selectable: true,
            selectHelper: true,
            select: function(start, end) {
                director_logCalendar_appendFun(start,end);
            },
            editable: false,
            slotMinutes:60,
            eventLimit: true, // allow "more" link when too many events
            loading: function(bool) {
                //$('#loading').toggle(bool);
            },
            events: {
                url: '${pageContext.request.contextPath}/logManagerAction!showCalendar.action',
                error: function() {
                    alert("error");
                    //$('#script-warning').show();
                }
            },
            eventClick: function(event, jsEvent, view) {
                var ids = event.id;
                var start = event.start.format('HH:mm:ss');
                var end = event.end.format('HH:mm:ss');
                var date = event.start.format('YYYY-MM-DD');
                var param = "ids="+ids+"&start="+start+"&end="+end+"&date="+date;
                var curTab = $('#layout_center_tabs').tabs('getSelected');
                $('#layout_center_tabs').tabs('update', {
                    tab: curTab,
                    options: {
                    title: curTab.panel('options').title,
                    href: '${pageContext.request.contextPath}/logManagerAction!gotoLogManager.action?'+param
                    }
                    });
            }
        });
});
function director_logCalendar_appendFun(start,end) {
    $.ajax({
        url : '${pageContext.request.contextPath}/logManagerAction!showAdd.action',
        data : {
            startTime : start.format('HH:mm:ss'),
            endTime : end.format('HH:mm:ss'),
            date : start.format('YYYY-MM-DD')
        },
        dataType : 'json',
        success : function(obj) {
            if (obj.success) {
                $('<div id="director_logCalendar_add_Open" style="5px;"/>').dialog({
                    href : 'jsp/director/logManagerAdd.jsp',
                    width : 550,
                    height :260,
                    modal : true,
                    title : '添加日志',
                    onLoad: function() {
                        $('#director_logManager_addForm').form('clear');
                        $("#director_logManager_addForm").find('#date').val(obj.returnObject.date);
                        $("#director_logManager_addForm").find('#startTime').val(obj.returnObject.startTime);
                        $("#director_logManager_addForm").find('#endTime').val(obj.returnObject.endTime);
                        $("#director_logManager_addForm").find('#showDate').val(obj.returnObject.showDate);
                        $("#director_logManager_addForm").find('#showStartTime').val(obj.returnObject.showStartTime);
                        $("#director_logManager_addForm").find('#showEndTime').val(obj.returnObject.showEndTime);
                    } ,
                    onClose : function() {
                        $(this).dialog('destroy');
                    },
                    buttons:[
                             {
                                 text : '增加',
                                 iconCls : 'icon-add',
                                 handler : function() {
                                     addLog();
                                 }
                             }
                         ]
                    });
            }
        }
    });
}
function addLog() {
    var typeId = $("#director_logManager_addForm").find("input[name^='typeId']");
    if (typeId.val().length == 0) {
        alert("请输入日志类型！");
        return;
    }
    var classTimeId = $("#director_logManager_addForm").find("input[name^='classTimeId']");
    if (classTimeId.val().length == 0) {
        alert("请输入课程！");
        return;
    }
    var date = $("#director_logManager_addForm").find('#showDate');
    if (date.val().length == 0) {
        alert("请输入发生日期！");
        return;
    }
    var startTime = $("#director_logManager_addForm").find('#showStartTime');
    if (startTime.val().length == 0) {
        alert("请输入开始时间！");
        return;
    }
    var endTime = $("#director_logManager_addForm").find('#showEndTime');
    if (endTime.val().length == 0) {
        alert("请输入结束时间！");
        return;
    }
    var classId = $("#director_logManager_addForm").find("input[name^='classId']");
    if (classId.val().length == 0) {
        alert("请输入班级！");
        return;
    }
    var studentId = $("#director_logManager_addForm").find("input[name^='studentId']");
    if (studentId.val().length == 0) {
        alert("请输入学生！");
        return;
    }
    var resultId = $("#director_logManager_addForm").find("input[name^='resultId']");
    if (resultId.val().length == 0) {
        alert("请输入日志结果！");
        return;
    }
    $("#director_logManager_addForm").find('#date').val($("#director_logManager_addForm").find('#showDate').val());
    $("#director_logManager_addForm").find('#startTime').val($("#director_logManager_addForm").find('#showStartTime').val());
    $("#director_logManager_addForm").find('#endTime').val($("#director_logManager_addForm").find('#showEndTime').val());
    $('#director_logManager_addForm').form('submit', {
        url : '${pageContext.request.contextPath}/logManagerAction!add.action',
        success : function(data) {
            var obj = $.parseJSON(data);
            if (obj.success) {
                $('#director_logCalendar_add_Open').dialog('destroy');
            }
            $.messager.show({
                title : '提示',
                msg : obj.msg
            });
        }
    });
}
</script>
<style>

    body {
        margin: 40px 10px;
        padding: 0;
        font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
        font-size: 14px;
    }

    #calendar {
        max-width: 900px;
        margin: 0 auto;
    }

</style>
<form id="director_logManager_CalendarForm" method="post">
    <div id='calendar'></div>
</form>
