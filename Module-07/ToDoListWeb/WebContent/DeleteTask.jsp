<%@ page import="com.module7.DeleteTaskServlet" %>

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
	
	<body>
		<h3>Choose a task to delete:</h3>
		<form action="DeleteTaskServlet" method="GET">
			<input list="tasks" name="task">
 			<datalist id="tasks">
				<%= DeleteTaskServlet.getList() %>
			</datalist>
			<input type="submit" value="Delete">
		</form>
	
	
	</body>
	
</html>