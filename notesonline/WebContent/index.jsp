<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Заметки</title>
		<link rel="shortcut icon" href = "resources/images/logo.png" type="image/x-icon">
		<meta charset = "UTF-8">
		<meta name = "viewport" content = "width = device-width, initial-scale = 1">
		<link href="https://fonts.googleapis.com/css?family=Caveat&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
		<link rel = "stylesheet" href = "resources/css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src = "resources/js/bootstrap.min.js"></script>
	</head>
	<body>
		<header>
			<div class = "header-container">
				<a class = "nav-link" href = '<c:url value="/index" />'><h1>Заметки</h1></a>
			</div>
		</header>
		<div class = "main-container">
		
			<!-- Кнопка "Создать новую заметку" -->
			<div class = "create-container">
				<a href='<c:url value="/create" />'><button type="button" class="btn btn-primary btn-lg btn-block save-note-button">+ Создать заметку</button></a>
			</div>
			
			<!-- Форма поиска -->
			<div class = "search-container">
				<form method="post" action = '<c:url value ="/index"/>'>
					<input name="search" class="form-control" id = "search-input" value = "${search}"/>
					<button type="submit" class = "btn btn-outline-secondary" id = "search-button"><span class="glyphicon glyphicon-search"></span></button>
					<div class = "float-default"></div>
				</form>
			</div>
			
			<!-- Список заметок -->
			<div class="list-group">
				<c:forEach var="note" items="${notes}">
					<div class="list-group-item list-group-item-action flex-column align-items-start">
						<div class="d-flex w-100 justify-content-between note-header-container">
      						<h3 class="mb-1">${note.header}</h3>
    					</div>
    					<p class="mb-1 note-container">${note.note}</p>
    					<div class = "lg-list-button-container md-list-button-container sm-list-button-container">
							<form method="post" action='<c:url value="/delete" />' style="display:inline;">
        						<input type="hidden" name="id" value="${note.id}">
        						<button type="submit" class="btn btn-primary">Удалить</button>
    						</form>
						</div>
						<div class = "lg-list-button-container md-list-button-container sm-list-button-container">
							<a href='<c:url value="/update?id=${note.id}" />'><button type="button" class="btn btn-primary">Открыть</button></a>
						</div>
						<div class = "float-default"></div>
					</div>
				</c:forEach>
			</div>
			
		</div>
	</body>
</html>