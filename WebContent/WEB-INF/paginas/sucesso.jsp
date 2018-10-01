<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/painel.css" />
 <link rel = "shortcut icon" type = "imagem/x-icon" href = "img/beans.png"/>
<title>Sucesso</title>
</head>
<body>
	<div class="borda">
		<div class="painel">
			<div>
			<h1>Sucesso!</h1>
			<h2>Seus dados foram registrados corretamente</h2>
			<form method="post" action="controller">
			<button type="submit" name="enviar" value="ServletRetorno">Voltar</button>
			</form>
			</div>						
		</div>
	</div>
</body>
</html>
