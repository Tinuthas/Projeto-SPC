<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/painel.css" />
 <link rel = "shortcut icon" type = "imagem/x-icon" href = "img/beans.png"/>
<title>Painel de Encomenda</title>
</head>
<body>
	<div class="borda">
		<div class="painel">
			<div class="tipos" >
			<h1>Encomendas</h1>
			
			<c:if test="${not empty listaEncomenda}">
			
	
         	<div class="bloco"> 
         		<h2>Tipo</h2>
         		<c:forEach var="enco" items="${listaEncomenda}">
					<%-- <c:when test="${enco.getTipoEncomenda() == 1}"><p>Encomenda</p></c:when>
					<c:when test="${enco.getTipoEncomenda() == 2}"><p>Carta</p></c:when>
					<c:when test="${enco.getTipoEncomenda() == 3}"><p>Sedex</p></c:when> --%>
					<p>${enco.getTipoEncomenda() }</p>
				</c:forEach>
         	</div>
         	<div class="bloco"> 
         		<h2>Tamanho</h2>
         		<c:forEach var="enco" items="${listaEncomenda}">
					<p>${enco.getTamanho() }</p>
				</c:forEach>
         	</div>
         	<div class="bloco">
         		<h2>Data</h2>
         		<c:forEach var="enco" items="${listaEncomenda}">
					<p>${enco.getEntrega() }</p>
				</c:forEach>
         	</div>
         	
			</c:if>
			<c:if test="${empty listaEncomenda }">
				<div class="vazio">
					<h2>Não têm encomendas</h2>
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
		
		<c:if test="${not empty FUNCIONARIO}">
			<h1>Registrar Encomendas</h1>			
			
			<div class="registro">
			<form method="post" action="registrarEnco" class="form-registro">
				<br>
				<label for="idCodigo">Código Morador:</label><br>
				<input id="idCodigo" type="text" name="codigo"><br>
				<label for="idTamanho">Tamanho:</label><br>
				<input id="idTamanho" type="text" name="tamanho"><br>
				<select name="tipo" id="idTipo">
					<option value="1">Encomenda</option>
          			<option value="2">Cartas</option>
          			<option value="3">Sedex</option>     			
				</select><br>
				<button type="submit" name="enviar" value="ServletRegistroEnco">Enviar</button>
			</form>
			</div>
		</c:if>
		</div>
	</div>
	
</body>
</html>
