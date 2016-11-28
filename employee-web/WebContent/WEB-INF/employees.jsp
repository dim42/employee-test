<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
                       "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employees</title>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.5.js" defer="defer"></script>
<script type="text/javascript">
	$(function() {
		$('table input:text').hide().blur(function() {
			showLabel(this);
		});

		$('table div').click(function() {
			showInput(this);
		});
	});

	function insert() {
		window.location = "CreateServlet";
	}

	function updateEmployee(id, obj) {
		$.post("UpdateServlet",
				{id:id, name:obj.name, value:obj.value},
				function(data) {
					if(obj.name !== 'departmentId')
						$('#' + inputToLabel(obj.id)).html(obj.value == '' ? '__________' : obj.value);
				});
	}

	function deleteEmployee(id) {
		window.location = "DeleteServlet?id=" + encodeURIComponent(id);
	}
	
	function showInput(obj) {
		$('#' + obj.id).hide();
		var inputObj = '#' + labelToInput(obj.id);
		$(inputObj).show();
		$(inputObj).focus();
	}
	
	function showLabel(obj) {
		$('#' + obj.id).hide();
		$('#' + inputToLabel(obj.id)).show();
	}

	function inputToLabel(data) {
		return data.replace('Input', 'Label');
	}

	function labelToInput(data) {
		return data.replace('Label', 'Input');
	}

	const ENTER_KEY_CODE = 13;
	function updateOnEnter(event, id, obj) {
		if(event.keyCode === ENTER_KEY_CODE)
			updateEmployee(id, obj);
	}
</script>
</head>
<body>
	<h3>Employees:</h3>
	<form action="SearchServlet" method="post">
		Name <input name="employeeFio" type="text"><br>
		Department <input type="text"><br>
		<input type="submit" value="Search">
	</form>
	<br>
	<h4>Click on element to edit it</h4>
	<table>
		<thead>
			<tr>
				<th>FIO</th>
				<th>Department</th>
				<th>Phone</th>
				<th>Salary</th>
				<th></th>
			</tr>
		</thead>
		<c:forEach items="${employees}" var="employee">
			<tr>
				<td width="250">
					<div id="fioLabel${employee.id}"><c:out value="${employee.fio}" default="__________" /></div>
					<%-- empty or null custom tag! --%>
					<input id="fioInput${employee.id}" type="text" name="fio" value="${employee.fio}" onchange="updateEmployee(${employee.id}, this)">
				</td>
				<td>
					<select name="departmentId" onchange="updateEmployee(${employee.id}, this)">
						<option value="0" />
						<c:forEach items="${departments}" var="department">
							<option value="${department.id}" ${department.id == employee.departmentId ? 'selected' : ''}>${department.name}</option>
						</c:forEach>
					</select></td>
				<td width="200">
					<div id="phoneLabel${employee.id}">
						<c:out value="${employee.phone == null || employee.phone == '' ? '__________' : employee.phone}" default="__________" /></div>
					<input id="phoneInput${employee.id}" type="text" name="phone" value="${employee.phone}" onchange="updateEmployee(${employee.id}, this)">
				</td>
				<td width="200">
					<div id="salaryLabel${employee.id}"><c:out value="${employee.salary}" default="__________" /></div>
					<input id="salaryInput${employee.id}" type="text" name="salary" value="${employee.salary}" onchange="updateEmployee(${employee.id}, this)">
				</td>
				<td>
					<input type="button" value="Удалить" onclick="deleteEmployee(${employee.id})">
				</td>
			</tr>
		</c:forEach>
	</table>

	<input type="button" value="Insert" onclick="insert()">
<span>Version: 1</span>
</body>
</html>
