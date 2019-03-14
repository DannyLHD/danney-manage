<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<table class="table table-hover" >
   <tbody>
   <tr>
   <th>工号</th>
   <td>${user.employeeID}</td>
   </tr>
   <tr>
   <th>姓名</th>
   <td>${user.username}</td>
    </tr>
    <tr>
   <th>电话</th>
   <td>${user.phone}</td>
   </tr>
   <tr>
   <th>邮箱</th>
   <td>${user.email}</td>
   </tr>
   <tr>
   <th>子公司</th>
   <td>${user.subsidiary}</td>
   </tr>
   <tr>
   <th>单位</th>
   <td>${user.unit}</td>
   </tr>
   </tbody>
   </table>

