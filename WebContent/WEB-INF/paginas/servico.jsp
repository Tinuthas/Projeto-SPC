<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/painel.css" />
<title>Painel de Serviços</title>
</head>
<body>
	<%@include file="sessionless.jsp" %>
	<div class="borda">
		<div class="painel">
			<div class="tipos" >
			<h1>Serviços Agendados</h1>			
			
			<c:if test="${not empty listaServico}">
			
	
         	<div class="assunto bloco"> 
         		<h2>Codigo</h2>
         		<c:forEach var="serv" items="${listaServico}">
					<p>${serv.getCodigoServico() }</p>
				</c:forEach>
         	</div>
         	<div class="data bloco"> 
         		<h2>Data</h2>
         		<c:forEach var="serv" items="${listaServico}">
					<p>${serv.getInicio() }</p>
				</c:forEach>
         	</div>
         	<div class="descricao bloco">
         		<h2>Termino</h2>
         		<c:forEach var="serv" items="${listaServico}">
					<p>${serv.getTermino() }</p>
				</c:forEach>
         	</div>
         	
			</c:if>
			<c:if test="${empty listaServico}">
				<div class="vazio">
					<h2>Não têm serviços</h2>
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
			<h1>Agendar Serviço</h1>			
			
			<div class="registro">
			<form method="post" action="controller" class="form-registro">
				<br>
				<label for="idDataInicio">Data de Início:</label><br>
				<input id="idDataInicio" type="text" name="dataInicio"><br>
				<label for="idHoraInicio">Hora de Início:</label><br>
				<input id="idHoraInicio" type="time" name="horaInicio"><br>
				<label for="idDataTermino">Data de Término:</label><br>
				<input id="idDataTermino" type="text" name="dataInicio"><br>
				<label for="idHoraTermino">Hora de Término:</label><br>
				<input id="idHoraTermino" type="time" name="horaInicio"><br>
				<select name="tipo" id="idTipo">
					<option value="1">Churrasqueira</option>
          			<option value="2">Salão de Festa</option>
          			<option value="3">Sala de Jogos</option>
          			<option value="4">Academia</option>
          			<option value="5">Brinquedoteca</option>
          			<option value="6">Sauna</option>      			
				</select><br>
				<button type="submit" name="enviar" value="AgendarServico">Enviar</button>
			</form>
			</div>
		</div>
	</div>
</body>
</html>
