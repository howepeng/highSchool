<%@ page language="java" pageEncoding="UTF-8"%>
    <form id="stu_stuPayment_editForm" method="post">
        <input type="hidden" name="id" id="stu_stuPayment_editForm_id"/>
        <input type="hidden" name="signUpMoneyFlg" id="signUpMoneyFlg"/>
        <input type="hidden" name="stayFlg" id="stayFlg"/>
        <input type="hidden" name="secureFlg" id="secureFlg"/>
        <input type="hidden" name="selfstudyNightflg" id="selfstudyNightflg"/>
        <input type="hidden" name="classType" id="classType1"/>
        <table>
            <tr>
                <th style="width: 120px;">班级类型</th>
                <td><input name="classTypeName" style="width: 180px;" id="classTypeName" readonly="readonly"/></td>
                <th style="width: 120px;">培训费减免</th>
                <td><input name="preferential" class="easyui-combobox"
                    data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/preferentialAction!combox.action',
                                                                                    onSelect: function(combobox){
                                                                                        stu_stuPayment_preferentialCombox(combobox);
                                                                                    }"
                    style="width: 180px;" /></td>
            </tr>
        </table>
        <br/>
        <table>
            <tr>
                <th style="width: 120px;">应交报名费</th>
                <td><input id="stu_stuPayment_signFee" name="stu_stuPayment_signFee" style="width: 180px;"
                    readonly="readonly" /></td>
                <th style="width: 120px;">缴纳报名费</th>
                <td><input id="signFee" name="signFee" style="width: 180px;"
                    onchange="stu_stuPayment_feeOnChangeFun()" /></td>
            </tr>
            <tr>
                <th style="width: 120px;">应交学费</th>
                <td><input id="stu_stuPayment_studyFee" name="stu_stuPayment_studyFee" style="width: 180px;"
                    readonly="readonly" /></td>
                <th style="width: 120px;">缴纳学费</th>
                <td><input id="studyFee" name="studyFee" style="width: 180px;"
                    onchange="stu_stuPayment_feeOnChangeFun()" /></td>
            </tr>
            <tr>
                <th style="width: 120px;">应交住宿费</th>
                <td><input id="stu_stuPayment_stayFee" name="stu_stuPayment_stayFee" style="width: 180px;"
                    readonly="readonly" /></td>
                <th style="width: 120px;">缴纳住宿费</th>
                <td><input id="stayFee" name="stayFee" style="width: 180px;"
                    onchange="stu_stuPayment_feeOnChangeFun()" /></td>
            </tr>
            <tr>
                <th style="width: 120px;">应交晚自习费</th>
                <td><input id="stu_stuPayment_selfFee" name="stu_stuPayment_selfFee" style="width: 180px;"
                    readonly="readonly" /></td>
                <th style="width: 120px;">缴纳晚自习费</th>
                <td><input id="selfFee" name="selfFee" style="width: 180px;"
                    onchange="stu_stuPayment_feeOnChangeFun()" /></td>
            </tr>
            <tr>
                <th style="width: 120px;">应交成绩单押金</th>
                <td><input id="stu_stuPayment_scoreFee" name="stu_stuPayment_scoreFee" style="width: 180px;"
                    readonly="readonly" /></td>
                <th style="width: 120px;">缴纳成绩单押金</th>
                <td><input id="scoreFee" name="scoreFee" style="width: 180px;"
                    onchange="stu_stuPayment_feeOnChangeFun()" /></td>
            </tr>
            <tr>
                <th style="width: 120px;">应交保险费</th>
                <td><input id="stu_stuPayment_safetyFee" name="stu_stuPayment_safetyFee" style="width: 180px;"
                    readonly="readonly" /></td>
                <th style="width: 120px;">缴纳保险费</th>
                <td><input id="safetyFee" name="safetyFee" style="width: 180px;"
                    onchange="stu_stuPayment_feeOnChangeFun()" /></td>
            </tr>
            <tr>
                <th style="width: 120px;">应交水费</th>
                <td><input id="stu_stuPayment_waterFee" name="stu_stuPayment_waterFee" style="width: 180px;"
                    readonly="readonly" /></td>
                <th style="width: 120px;">缴纳水费</th>
                <td><input id="waterFee" name="waterFee" style="width: 180px;"
                    onchange="stu_stuPayment_feeOnChangeFun()" /></td>
            </tr>
            <tr>
                <th style="width: 120px;">应减免费用</th>
                <td><input id="stu_stuPayment_preferentialHd" name="stu_stuPayment_preferentialHd" style="width: 180px;"
                    readonly="readonly" /></td>
            </tr>
            <tr>
                <th style="width: 120px;">应交全款</th>
                <td><input id="stu_stuPayment_countFee" name="stu_stuPayment_countFee"style="width: 180px;"
                    readonly="readonly" /></td>
                <th style="width: 120px;">总计交付</th>
                <td><input id="countFee" name="countFee" style="width: 180px;"
                    readonly="readonly" /></td>
            </tr>
            <tr>
                <th style="width: 120px;">现金</th>
                <td><input id="cashFee" name="cashFee" style="width: 180px;"/></td>
                <th style="width: 120px;">银行转账</th>
                <td><input id="bankFee" name="bankFee" style="width: 180px;"/></td>
            </tr>
            <tr>
                <th style="width: 120px;">拉卡拉pos机转账</th>
                <td><input id="lakalaFee" name="lakalaFee" style="width: 180px;"/></td>
                <th style="width: 120px;">支付宝转账</th>
                <td><input id="aliFee" name="aliFee" style="width: 180px;"/></td>
            </tr>
            <tr>
                <th style="width: 120px;"></th>
                <td></td>

            </tr>
            <tr>
                <th style="width: 120px;"></th>
                <td></td>
                <th style="width: 120px;">欠费</th>
                <td><input id="arrearFee" name="arrearFee" style="width: 180px;"
                    readonly="readonly" /></td>
            </tr>
        </table>
        <input type="hidden" id="stu_stuPayment_countFee"  name="stu_stuPayment_countFee"/>
        <input type="hidden" id="countFee" />
    </form>
