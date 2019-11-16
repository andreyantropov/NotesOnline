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
			<div class = "update-form-container">
				<form method="post">
					<input type = "hidden" value="${note.id}" name = "id" />
					
					<!-- Форма для ввода заголовка -->
					<div class = "update-header-container">
						<input name = "updatedHeader" class = "form-control note-input" placeholder = "Тема" maxlength="100" value="${note.header}" />
					</div>
					
					<!-- Форма для ввода заметки -->
					<div class = "update-note-container">
						<textarea name = "updatedNote" class = "form-control note-textarea" placeholder = "Введите текст сюда" required>${note.note}</textarea>
					</div>
					
					<!-- Кнопка "Сохранить" -->
					<div class = "update-button-container">
						<button type="submit" class="btn btn-primary btn-lg btn-block">Сохранить</button>
					</div>
					
				</form>
			</div>
			
			<!-- Кнопка "Отмена" -->
			<div class = "cancel-container">
				<a href='<c:url value="/index" />'><button type="button" class="btn btn-primary btn-lg btn-block save-note-button">Отмена</button></a>
			</div>
			
		</div>
	</body>
</html>