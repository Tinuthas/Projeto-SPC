<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/painel.css" />
 <link rel = "shortcut icon" type = "imagem/x-icon" href = "img/beans.png"/>
<title>Painel de Comunicados</title>
</head>
<body>
	<div class="borda">
		<div class="painel">
			<div class="tipos" >
			<h1>Comunicados</h1>			
			
			<c:if test="${not empty listaComunicado}">
			
	
         	<div class="assunto bloco"> 
         		<h2>Assunto</h2>
         		<c:forEach var="comu" items="${listaComunicado}">
					<p>${comu.getAssunto() }</p>
				</c:forEach>
         	</div>
         	<div class="data bloco"> 
         		<h2>Data</h2>
         		<c:forEach var="comu" items="${listaComunicado}">
					<p>${comu.getData() }</p>
				</c:forEach>
         	</div>
         	<div class="descricao bloco">
         		<h2>Descrição</h2>
         		<c:forEach var="comu" items="${listaComunicado}">
					<p>${comu.getDescricao() }</p>
				</c:forEach>
         	</div>
         	
			</c:if>
			<c:if test="${empty listaComunicado }">
				<div class="vazio">
					<h2>Não têm comunicados</h2>
				</div>
			</c:if>
			<form method="post" action="controller">
				<button type="submit" name="enviar" value="ServletRetorno">Voltar</button>
			</form>
			</div>
		</div>
	</div>
	
	<div class="campos" >
		<div class="opcoes">
			<h1>Escrever Comunicados</h1>			
			
			<div class="registro">
			<form method="post" action="escreverComu" class="form-registro">
				<br>
				<label for="idAssunto">Assunto:</label><br>
				<input id="idAssunto" type="text" name="assunto"><br>
				<label for="idLocal">Local:</label><br>
				<input id="idLocal" type="text" name="local"><br>
				<label for="idDescricao">Descrição:</label><br>
				<textarea id="idDescricao" maxlength="1000" name="descricao"></textarea><br>			
				<button type="submit" name="enviar" value="ServletRegistroEnco">Enviar</button>
			</form>
			</div>
		</div>
	</div>
</body>
</html>
