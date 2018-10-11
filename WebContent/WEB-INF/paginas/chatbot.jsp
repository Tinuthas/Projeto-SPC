<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>ChatBot</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
<link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
 <link rel = "shortcut icon" type = "imagem/x-icon" href = "img/beans.png"/>
</head>
<body>
	<%@include file="sessionless.jsp" %>
    <div class="menu">
    <div class="opcoes">
    	
      <a href="index.html" ><img class="logo"  src="img/beans.png" alt="logo"></a>
    	
      <c:if test="${not empty MORADOR}">
      
      	<form action="controller" method="post">
      	<button type="submit" name="enviar" value="BuscaServico">Serviços</button>
      	</form>
      	<form action="controller" method="post">
      	<button type="submit" name="enviar" value="BuscaVinculo">Vínculos</button>
      	</form>
      </c:if>
      
      <c:if test="${not empty FUNCIONARIO}">
      
      <form action="controller" method="post">
          <button type="submit" name="enviar" value="PesquisarMorador">Pesquisar Morador</button>
      </form>
      
      </c:if> 
      
      <form action="controller" method="post">
          <button type="submit" name="enviar" value="BuscaEncomenda">Encomendas</button>
      </form>
      
      
      <form action="controller" method="post">
         <button type="submit" name="enviar" value="BuscaComunicado">Comunicados</button>
      </form>
      
      
      <a href="index.html"><button>Informações</button></a>
      <br>  
      <form method="post" action="controller">
		 <button type="submit" name="enviar" value="Logout">Voltar</button>
	  </form>
    </div>
 	</div>

	
	<div class="chatbot" >
		
	<div class="borda">
		<c:if test="${not empty MORADOR}"><h1>Bem Vindo Morador</h1></c:if>
		<c:if test="${not empty FUNCIONARIO}"><h1>Bem Vindo Funcionario</h1></c:if>
		<c:if test="${not empty VISITANTE}"><h1>Bem Vindo Visitante</h1></c:if>
		
		<div class="caixa">
		
		<div id="textchat" class="pulse"></div>
		<form method="post">
			<input type="text" id="question" name="question" class="field"
				placeholder="Digite" />
			<button id="sendQuestion">Enviar</button>
		</form>
		<form method="post" action="controller">
		 	<button type="submit" name="enviar" value="Logout">Voltar</button>
	  	</form>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="js/chatbot.js"></script>
</body>
</html>