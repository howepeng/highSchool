<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.radio {
    text-align: left;
    width: 20%;
}
#stu_stuSignup_layout table td {
    border: 1px solid #CDCDCD;
    text-align: center;
    width: 12.5%;
}
#stu_stuSignup_layout table td input {
    border: 0;
    text-align: left;
    width: 98%;
}
#stu_stuSignup_layout table tr {
    height: 5%;
}
.num{
    border: 1 !important;
}
</style>

<script type="text/javascript">
    $(function() {
        getNum();
        photoImgHide();
    });
    function stu_stuSignup_subFun() {
        var num = $("#stu_stuSignup_form").find('#num');
        var num1 = $("#stu_stuSignup_form").find('#num1');
        var num2 = $("#stu_stuSignup_form").find('#num2');
        if(num2.val().length==0){
            num2.val("XXX");
        }
        var num3 = $("#stu_stuSignup_form").find('#num3');
        var num4 = $("#stu_stuSignup_form").find('#num4');
        if(num4.val().length==0){
            num4.val("XXXX");
        }
        num.val(num1.val()+num2.val()+num3.val()+num4.val());

        var name = $("#stu_stuSignup_form").find('#name');
        if (name.val().length == 0) {
            alert("请输入姓名！");
            return;
        }
        var idNum = $("#stu_stuSignup_form").find('#idNum');
        if (idNum.val().length == 0) {
            alert("请输入身份证号！");
            return;
        }
        var classType = $("#stu_stuSignup_form").find("input[name^='classType']");
        if (classType.val().length == 0) {
            alert("请选择班级类型！");
            return;
        }
        var graduateSchool = $("#stu_stuSignup_form").find('#graduateSchool');
        if (graduateSchool.val().length == 0) {
            alert("请输入毕业学校！");
            return;
        }
        var bankSignUpMoneyFlg = $("#stu_stuSignup_form").find('#bankSignUpMoneyFlg');
        var lakalaSignUpMoneyFlg = $("#stu_stuSignup_form").find('#lakalaSignUpMoneyFlg');
        var aliSignUpMoneyFlg = $("#stu_stuSignup_form").find('#aliSignUpMoneyFlg');
        var checkedCount = 0;
        if (bankSignUpMoneyFlg.prop("checked") == true){
            checkedCount = checkedCount+1;
        }
        if (lakalaSignUpMoneyFlg.prop("checked") == true){
            checkedCount = checkedCount+1;
        }
        if (aliSignUpMoneyFlg.prop("checked") == true){
            checkedCount = checkedCount+1;
        }
        if(checkedCount > 1) {
            alert("银行交付，拉卡拉pos机交付，支付宝交付 只能选一个");
            return;
        }

        $('<div id="submiting" style="5px;"/>').dialog({
            href : 'jsp/comn/onload.jsp',
            width : 200,
            height :100,
            modal : true,
            title : '提交中',
            closed: false,
            closable: false,
            onLoad: function() {
                } ,
            onClose : function() {
                $(this).dialog('destroy');
            }
        });
        $('#stu_stuSignup_form').form(
                'submit',
                {
                    url : '${pageContext.request.contextPath}/stuSignupAction!signup.action',
                    success : function(data) {
                        var obj = $.parseJSON(data);
                        if (obj.success) {
                            stu_stuSignup_cleanFun();
                            getNum();
                            photoImgHide();
                        }
                        $('#submiting').dialog('destroy');
                        $.messager.show({
                            title : '提示',
                            msg : obj.msg
                        });
                    }
                });
    }
    function stu_stuSignup_cleanFun() {
        $('#stu_stuSignup_form').form('clear');
        $("#stu_stuSignup_form").find("input[name='wlqf'][value='91']").prop("checked",true);
        $("#stu_stuSignup_form").find("input[name='studentType'][value='0']").prop("checked",true);
        $("#stu_stuSignup_form").find("input[name='sex'][value='0']").prop("checked",true);
        $("#stu_stuSignup_form").find("input[name='stayFlg'][value='0']").prop("checked",true);
        photoImgHide();
    }
    function getNum(){
        //var htmlobj = $.ajax({url:"${pageContext.request.contextPath}/stuSignupAction!getNum.action",async:false});
        $.post("${pageContext.request.contextPath}/stuSignupAction!getNum.action",{},callback);
        var num3 = $("#stu_stuSignup_form").find('#num3');
        var wlqf = $("#stu_stuSignup_form").find('#wlqf');
        num3.val(wlqf.val());
        var num2 = $("#stu_stuSignup_form").find('#num2');
        num2.css('border','1');
        var num4 = $("#stu_stuSignup_form").find('#num4');
        num4.css('border','1');
    }
    function callback(data) {
        var num = $("#stu_stuSignup_form").find('#num1');
        var json = eval("("+data+")");
        num.val(json["num"]);
        //$.each(json, function(index, objVal) {
            //遍历对象数组，index是数组的索引号，objVal是遍历的一个对象。
            //val["属性"]可取到对应的属性值。
            //$("<option>").attr("value", objVal["id"]).html(objVal["name"]).appendTo(select);
        //});
    }
    function onChange_wlqf(data) {
        var num3 = $("#stu_stuSignup_form").find('#num3');
        num3.val(data);
    }

    function ajaxFileUpload()
    {
        $('<div id="ajaxFileUpload" style="5px;"/>').dialog({
            href : 'jsp/comn/onload.jsp',
            width : 200,
            height :100,
            modal : true,
            title : '提交中',
            closed: false,
            closable: false,
            onLoad: function() {
                } ,
            onClose : function() {
                $(this).dialog('destroy');
            }
        });
        $('#stu_stuSignup_form')
        .form(
                'submit',
                {
                    url : '${pageContext.request.contextPath}/stuSignupAction!uploadPhoto.action',
                    success : function(data) {
                        var obj = $.parseJSON(data);
                        if (obj.success) {
                            $("#stu_stuSignup_form").find('#photoImg').attr("src",obj.returnObject[1]);
                            $("#stu_stuSignup_form").find('#photoId').val(obj.returnObject[0]);
                            photoImgShow();
                        }
                        $('#ajaxFileUpload').dialog('destroy');
                        $.messager.show({
                            title : '提示',
                            msg : obj.msg
                        });
                    }
                });
        return false;
    }
    function photoImgShow() {
        $("#stu_stuSignup_form").find('#photoImg').show();
        $("#stu_stuSignup_form").find('#deletePhotoBtn').show();
        $("#stu_stuSignup_form").find("input[name^='photoFile']").hide();
        $("#stu_stuSignup_form").find('#uploadPhotoBtn').hide();
        $("#stu_stuSignup_form").find("input[name^='photoFile']").val("");
    }

    function photoImgHide() {
        $("#stu_stuSignup_form").find('#photoImg').hide();
        $("#stu_stuSignup_form").find('#deletePhotoBtn').hide();
        $("#stu_stuSignup_form").find("input[name^='photoFile']").show();
        $("#stu_stuSignup_form").find('#uploadPhotoBtn').show();
    }

    function photoDelete() {
        photoImgHide();
        $("#stu_stuSignup_form").find('#photoId').val("");
    }
</script>
<div id="stu_stuSignup_layout" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <form id="stu_stuSignup_form" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" />
            <input type="hidden" name="num" id="num">
            <input type="hidden" name="photoId" id="photoId">
            <input type="hidden" name="yearId" id="yearId">
            <table cellspacing="0" cellpadding="0" style="width: 100%;height: 100%;">
               <tr>
                    <td><span style="color:red">*</span>姓名</td>
                    <td>
                        <input id="name" name="name" maxlength="10"/>
                    </td>
                    <td colspan="2">
                      <table>
                          <tr>
                              <td style="border:none">
                                  <input class="radio" type="radio" id="wlqf" name="wlqf" value="91" onclick="onChange_wlqf('91');" checked="checked"/>
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>文科</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="radio" id="wlqf" name="wlqf" value="95" onclick="onChange_wlqf('95');"/>
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>理科</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="radio" id="wlqf" name="wlqf" value="93" onclick="onChange_wlqf('93');"/>
                              </td>
                              <td style="border:none;width:100px;">
                                  <label>艺术文科</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="radio" id="wlqf" name="wlqf" value="97" onclick="onChange_wlqf('97');"/>
                              </td>
                              <td style="border:none;width:100px;">
                                  <label>艺术理科</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="radio" id="wlqf" name="wlqf" value="94" onclick="onChange_wlqf('94');"/>
                              </td>
                              <td style="border:none;width:100px;">
                                  <label>体育文科</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="radio" id="wlqf" name="wlqf" value="98" onclick="onChange_wlqf('98');"/>
                              </td>
                              <td style="border:none;width:100px;">
                                  <label>体育理科</label>
                              </td>
                          </tr>
                      </table>
                   </td>
                   <td>
                      <table>
                          <tr>
                              <td style="border:none">
                                  <input class="radio" type="radio" id="studentType" name="studentType" value="0" checked="checked"/>
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>复读</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="radio" id="studentType" name="studentType" value="1"/>
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>应届</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="radio" id="studentType" name="studentType" value="2"/>
                              </td>
                              <td style="border:none;width:100px;">
                                  <label>往届</label>
                              </td>
                          </tr>
                      </table>
                    </td>
                    <td colspan="2">
                     <table>
                            <tr>
                                <td style="border:none;width:50px;">考生号</td>
                                <td style="border:none;width:150px;">
                                    <input type="text" id="num1" name="num1" maxlength="5" style="width:35px">
                                    <input type="text" id="num2" name="num2" maxlength="3" style="width:30px;border:1px solid #f00!important;"/>
                                    <input type="text" id="num3" name="num3" maxlength="2" style="width:20px"/>
                                    <input type="text" id="num4" name="num4" maxlength="4" style="width:30px;border:1px solid #f00!important;"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td rowspan="4">
                        <input type="file" name="photoFile"/>
                        <a id="uploadPhotoBtn" href="javascript:void(0);" class="easyui-linkbutton" onclick="return ajaxFileUpload();">上传照片</a>
                        <a id="deletePhotoBtn" href="javascript:void(0);" class="easyui-linkbutton" onclick="return photoDelete();">删除照片</a>
                        <img alt="" src="" name="photoImg" id="photoImg" height="150" width="150">
                    </td>
                </tr>
                <tr style="height: 50px;">
                  <td>民族</td>
                  <td>
                      <input name="nation" maxlength="20"/>
                  </td>
                  <td>性别</td>
                    <td>
                      <table>
                          <tr>
                              <td style="border:none">
                                  <input class="radio" type="radio" name="sex" value="0" checked="checked"/>
                              </td>
                              <td style="border:none">
                                   <label>男</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="radio" name="sex" value="1"/>
                              </td>
                              <td style="border:none">
                                  <label>女</label>
                              </td>
                          </tr>
                      </table>
                    </td>
                    <td>
                    <table>
                    <tr>
                    <td style="border:none;width:100px;">
                        <input class="radio" type="checkbox" name="signedFlg" value="1" />
                    </td>
                    <td style="border:none;width:100px;">
                        <label>签约情况</label>
                    </td>
                    </tr>
                    </table>
                    </td>
                    <td colspan="2">
                      <table>
                          <tr>
                               <td style="border:none;width: 120px;"><span style="color:red">*</span>班级类型</td>
                               <td style="border:none;" id="stuSignupClassType">
                               <input id="classType" name="classType" class="easyui-combobox"
                                   data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/classTypeAction!combox.action'"
                                   style="width: 180px;" />
                               </td>
                         </tr>
                      </table>
                    </td>
                </tr>
                <tr style="height: 50px;">
                    <td>籍贯</td>
                    <td colspan="2"><input id= "nativePlace" name="nativePlace" maxlength="18" value="天津"/> </td>
                    <td><span style="color:red">*</span>身份证号</td>
                    <td colspan="3"><input id= "idNum" name="idNum" maxlength="18"/>
                    </td>
                </tr>
                <tr style="height: 50px;">
                    <td>本人电话</td>
                    <td colspan="2"><input id="tel" name="tel" maxlength="20"/>
                    </td>
                    <td>家庭电话</td>
                    <td colspan="3"><input name="homeTel" maxlength="20"/>
                    </td>
                </tr>
                <tr style="height: 50px;">
                   <td><span style="color:red">*</span>毕业学校</td>
                   <td colspan="2"><input id="graduateSchool" name="graduateSchool" maxlength="50"/>
                   </td>
                   <td>家庭住址</td>
                   <td colspan="4"><input name="address" maxlength="100"/>
                   </td>
                </tr>
                <tr>
                    <td>父亲姓名</td>
                    <td><input name="fatherName" maxlength="10"/>
                    </td>
                    <td>电话</td>
                    <td><input id="fatherTel" name="fatherTel" maxlength="20"/>
                    </td>
                    <td>工作单位</td>
                    <td colspan="3"><input name="fatherWork" maxlength="50" />
                    </td>
                </tr>
                <tr>
                    <td>母亲姓名</td>
                    <td><input name="motherName" />
                    </td>
                    <td>电话</td>
                    <td><input id="motherTel" name="motherTel" maxlength="20"/>
                    </td>
                    <td>工作单位</td>
                    <td colspan="3"><input name="motherWork" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">高考成绩</td>
                </tr>
                <tr>
                    <td rowspan="2">语文</td>
                    <td rowspan="2">数学</td>
                    <td rowspan="2">外语</td>
                    <td colspan="3">文/理综</td>
                    <td rowspan="2">综合总分</td>
                    <td rowspan="2">总分</td>
                </tr>
                <tr>
                    <td>历史/物理</td>
                    <td>地理/化学</td>
                    <td>政治/生物</td>
                </tr>
                <tr>
                    <td><input name="fractionLanguage" maxlength="5"/>
                    </td>
                    <td><input name="fractionMath" maxlength="5"/>
                    </td>
                    <td><input name="fractionEnglish" maxlength="5"/>
                    </td>
                    <td><input name="fractionComp1" maxlength="5"/>
                    </td>
                    <td><input name="fractionComp2" maxlength="5"/>
                    </td>
                    <td><input name="fractionComp3" maxlength="5"/>
                    </td>
                    <td><input name="fractionCompCount" maxlength="5"/>
                    </td>
                    <td><input id="fractionCount" name="fractionCount" maxlength="5"/>
                    </td>
                </tr>
                <tr>
                    <td>班级</td>
                    <td><input name="classId" maxlength="20"/>
                    </td>
                    <td >
                      <table>
                          <tr>
                              <td style="border:none">
                                  <input class="radio" type="checkbox" name="signUpMoneyFlg" value="1"/>
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>报名费</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="checkbox" name="bankSignUpMoneyFlg" id = "bankSignUpMoneyFlg" value="1" />
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>银行交付</label>
                              </td>
                          </tr>
                          <tr>
                              <td style="border:none">
                                  <input class="radio" type="checkbox" name="lakalaSignUpMoneyFlg" id = "lakalaSignUpMoneyFlg" value="1" />
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>拉卡拉pos机交付</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="checkbox" name="aliSignUpMoneyFlg" id = "aliSignUpMoneyFlg" value="1" />
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>支付宝交付</label>
                              </td>
                          </tr>
                      </table>
                    </td>
                    <td>
                      <table>
                          <tr>
                              <td style="border:none">
                                  <input class="radio" type="radio" name="stayFlg" value="1" />
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>住宿</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="radio" name="stayFlg" value="0" checked="checked"/>
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>走读</label>
                              </td>
                          </tr>
                      </table>
                    </td>
                    <td>
                        <label>宿舍号</label>
                    </td>
                    <td>
                        <input id="dormitoryNum" name="dormitoryNum" class="easyui-combobox"
                                   data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/dormitoryAction!combox.action'"
                                   style="width: 180px;" />
                    </td>
                    <td colspan="2">
                      <table>
                          <tr>
                              <td style="border:none">
                                  <input class="radio" type="checkbox" name="selfstudyNightflg" value="1"/>
                              </td>
                              <td style="border:none;width:100px;">
                                  <label>晚自习 </label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="checkbox" name="selfstudyNoonflg" value="1"/>
                              </td>
                              <td style="border:none;width:100px;">
                                  <label>午&nbsp;&nbsp;休 </label>
                              </td>
                          </tr>
                      </table>
                    </td>
                </tr>
                <tr>
                    <td >学号</td>
                    <td><input name="stuNum" maxlength="20"/>
                    </td>
                    <td>预约缴费时间</td>
                    <td><input name="intention" maxlength="20"/>
                    </td>
                    <td >艺术生类型</td>
                    <td><input name="artType" maxlength="20"/>
                    </td>
                    <td colspan="2">
                      <table>
                          <tr>
                              <td style="border:none">
                                  <input class="radio" type="checkbox" name="secureFlg" value="1"/>
                              </td>
                              <td style="border:none;width:100px;">
                                  <label>保&nbsp;&nbsp;险 </label>
                              </td>
                              <td style="border:none">

                              </td>
                              <td style="border:none;width:100px;">
                                  <label></label>
                              </td>
                          </tr>
                      </table>
                    </td>
                </tr>
                <tr style="height: 30px">
                    <td>入学视频</td>
                    <td colspan="2">
                        <input type="file" name="video"/>
                    </td>
                    <td>成绩单上传</td>
                    <td colspan="2">
                        <input type="file" name="reportFile"/>
                    </td>
                    <td>身份证上传</td>
                    <td colspan="2">
                        <input type="file" name="idNUmFile"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">备注</td>
                </tr>
                <tr>
                    <td colspan="8"><textarea rows="2" cols="100" name="remark"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="stu_stuSignup_subFun();">提交</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="stu_stuSignup_cleanFun();getNum();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>


