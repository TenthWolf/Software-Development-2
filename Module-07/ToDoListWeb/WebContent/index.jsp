<%@ page import="com.module7.IndexServlet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>ToDoList</title>
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
		<h3>Tasks to Complete:</h3>
		<%= IndexServlet.fillTable() %>
	</body>
</html>