<%@ page import="com.module7.AddTaskServlet" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>AddTask</title>
		<link href="mystyle.css" rel="stylesheet" type="text/css" />
		<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
		
	</head>
	
	<header>
		<nav id="NavBar">
			<ul>
				<li><h1><a href="index.jsp">ToDoList</a></h1></li>				
				<li><a href="AddTask.jsp">Add a Task</a></li>
				<li><a href="DeleteTask.jsp">Delete a Task</a></li>
			</ul>
		</nav>
	</header>
	
	<body id="TaskForm">
	
		<h3>Enter Task Information:</h3>
	
		<form action="AddTaskServlet" method="GET">
		
			<table>
				<tbody>
					<tr>
						<td>New Task:</td>
						<td><input type="text" name="newTask" value="" size ="50"/></td>
					</tr>
					
					<tr>
						<td>Date:</td>
						<td><input type="date" name="newDate" size="25"/></td>
					</tr>
					
					<tr>
						<td><input type="submit" name="addButton" value="Add"/></td>						
					</tr>
			
				</tbody>
			
			</table>
			
		</form>
	</body>
</html>