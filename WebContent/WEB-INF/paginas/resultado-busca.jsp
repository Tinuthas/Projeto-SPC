<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/painel.css" />
 <link rel = "shortcut icon" type = "imagem/x-icon" href = "img/beans.png"/>
<title>Resultado da Busca</title>
</head>
<body>
	<div class="borda">
		<div class="painel">
			<div class="tipos" >
			<h1>Resultado da Busca</h1>
			
			<c:if test="${not empty listaUsuario}">
			
	
         	<div class="bloco"> 
         		<h2>Código</h2>
         		<c:forEach var="usuario" items="${listaUsuario}">
					<p>${usuario.getCodigoUsuario()}</p>
				</c:forEach>
         	</div>
         	<div class="bloco"> 
         		<h2>Nome</h2>
         		<c:forEach var="usuario" items="${listaUsuario}">
					<p>${usuario.getNome() }</p>
				</c:forEach>
         	</div>
         	<div class="bloco">
         		<h2>CPF</h2>
         		<c:forEach var="usuario" items="${listaUsuario}">
					<p>${usuario.getCpf() }</p>
				</c:forEach>
         	</div>
         	
			</c:if>
			<c:if test="${empty listaUsuario }">
				<div class="vazio">
					<h2>Nenhum resultado encontrado</h2>
				</div>
			</c:if>
			<form method="post" action="controller">
				<button type="submit" name="enviar" value="Retorno">Voltar</button>
			</form>
			</div>
		</div>
	</div>
	
	<div class="campos" >
		<div class="opcoes">
			<h1>Pesquisar Morador</h1>			
			
			<div class="registro">
			<form method="post" action="controller" class="form-registro">
				<br>
				<label for="idPesquisa">Pesquisar Morador:</label><br>
				<input id="idPesquisa" type="search" name="pesquisa"><br>	
				<button type="submit" name="enviar" value="PesquisarMorador">Enviar</button>
			</form>
			</div>
		</div>
	</div>
</body>
</html>
