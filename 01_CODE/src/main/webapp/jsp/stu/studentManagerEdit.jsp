<%@ page language="java" pageEncoding="UTF-8"%>

    <form id="stu_stuSignup_form2" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" />
            <input type="hidden" name="num" id="num">
            <input type="hidden" name="photoId" id="photoId">
            <table style="width: 100%;height: 100%;">
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
                        <img alt="" src="" name="photoImg" id="photoImg" height="110" width="110">
                    </td>
                </tr>
                <tr style="height: 30px;">
                  <td>民族</td>
                  <td>
                      <input name="nation"/>
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
                               <td style="border:none;" id="studentManagerClassType">
                               <input id="classType" name="classType" class="easyui-combobox"
                                   data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/classTypeAction!combox.action'"
                                   style="width: 180px;" />
                               </td>
                         </tr>
                      </table>
                    </td>
                </tr>
                <tr style="height: 30px;">
                    <td><span style="color:red">*</span>身份证号</td>
                    <td colspan="2"><input id= "idNum" name="idNum"  maxlength="18"/>
                    </td>
                    <td>本人电话</td>
                    <td><input id="tel" name="tel"  maxlength="20"/>
                    </td>
                    <td>家庭电话</td>
                    <td><input name="homeTel"  maxlength="20"/>
                    </td>
                </tr>
                <tr style="height: 30px;">
                   <td><span style="color:red">*</span>毕业学校</td>
                   <td colspan="2"><input id="graduateSchool" name="graduateSchool"  maxlength="50"/>
                   </td>
                   <td>家庭住址</td>
                   <td colspan="3"><input name="address"  maxlength="100"/>
                   </td>
                </tr>
                <tr>
                    <td>父亲姓名</td>
                    <td><input name="fatherName" />
                    </td>
                    <td>电话</td>
                    <td><input id="fatherTel" name="fatherTel"  maxlength="20"/>
                    </td>
                    <td>工作单位</td>
                    <td colspan="3"><input name="fatherWork"  maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td>母亲姓名</td>
                    <td><input name="motherName" />
                    </td>
                    <td>电话</td>
                    <td><input id="motherTel" name="motherTel"  maxlength="20"/>
                    </td>
                    <td>工作单位</td>
                    <td colspan="3"><input name="motherWork"  maxlength="50"/>
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
                    <td><input name="className"  maxlength="20"/>
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
                                  <input class="radio" type="checkbox" name="bankSignUpMoneyFlg" value="1" />
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>银行转账交付</label>
                              </td>
                          </tr>
                          <tr>
                              <td style="border:none">
                                  <input class="radio" type="checkbox" name="lakalaSignUpMoneyFlg" value="1" />
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>拉卡拉pos机转账交付</label>
                              </td>
                              <td style="border:none">
                                  <input class="radio" type="checkbox" name="aliSignUpMoneyFlg" value="1" />
                              </td>
                              <td style="border:none;width:50px;">
                                  <label>支付宝转账交付</label>
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
                        <input name="dormitoryNum"  maxlength="20"/>
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
                <tr style="height: 30px">
                    <td>已上传的入学视频</td>
                    <td colspan="2">
                     <input name="oldFileName" readonly="readonly"/>
                    </td>
                    <td>已上传的成绩单上传</td>
                    <td colspan="2">
                     <input name="oldReportFileName" readonly="readonly"/>
                    </td>
                    <td>已上传的身份证上传</td>
                    <td colspan="2">
                     <input name="oldIdFileName" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">备注</td>
                </tr>
                <tr>
                    <td colspan="8"><textarea rows="2" cols="100" name="remark"></textarea>
                    </td>
                </tr>
            </table>
        </form>
