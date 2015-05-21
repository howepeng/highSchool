<%@ page language="java" pageEncoding="UTF-8"%>

    <form id="stu_stuSignup_year_info" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" />
        <table>
            <tr>
                <th style="width: 120px;">年限</th>
                <td>
                   <input name="yearId" class="easyui-combobox"
                        data-options="url : '${pageContext.request.contextPath}/yearAction!combox.action',
                                            valueField : 'id',
                                            textField : 'text',
                                            multiple : false,
                                            editable : true,
                                            panelHeight : '200'" />
                </td>
            </tr>
        </table>
    </form>
