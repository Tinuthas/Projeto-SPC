<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/painel.css" />
 <link rel = "shortcut icon" type = "imagem/x-icon" href = "img/beans.png"/>
<title>Vinculos</title>
</head>
<body>
	<%@include file="sessionless.jsp" %>
	<div class="borda">
		<div class="painel">
			<div class="tipos" >
			<h1>Vinculos</h1>
			
			<c:if test="${not empty listaVinculo}">
			
	
         	<div class="bloco"> 
         		<h2>Código</h2>
         		<c:forEach var="vinculo" items="${listaVinculo}">
					<p>${vinculo.getCodigoUsuario()}</p>
				</c:forEach>
         	</div>
         	<div class="bloco"> 
         		<h2>Nome</h2>
         		<c:forEach var="usuario" items="${listaVinculo}">
					<p>${vinculo.getNome() }</p>
				</c:forEach>
         	</div>
         	<div class="bloco">
         		<h2>CPF</h2>
         		<c:forEach var="vinculo" items="${listaVinculo}">
					<p>${vinculo.getTelefone() }</p>
				</c:forEach>
         	</div>
         	
			</c:if>
			<c:if test="${empty listaVinculo }">
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
			<h1>Cadastrar Vinculo</h1>			
			
			<div class="registro">
			<form method="post" action="controller" class="form-registro">
				<br>
				<label for="idNome">Nome:</label><br>
				<input id="idNome" type="text" name="nome"><br>	
				<label for="idEmail">E-mail:</label><br>
				<input id="idEmail" type="text" name="email"><br>	
				<label for="idCpf">CPF:</label><br>
				<input id="idCpf" type="text" name="cpf"><br>	
				<label for="idSenha">Senha:</label><br>
				<input id="idSenha" type="password" name="senha"><br>	
				<label for="idTelefone">Telefone:</label><br>
				<input id="idtelefone" type="text" name="telefone"><br>
				<label for="idLogradouro">Rua:</label><br>
				<input id="idLogradouro" type="text" name="Logradouro"><br>		
				
				<button type="submit" name="enviar" value="CadastroVinculo">Enviar</button>
			</form>
			<div class="mensagem">
				<c:if test="${not empty vinculoRegistrado }"><p>Vinculo Cadastrado!</p></c:if>
			</div>
			</div>
		</div>
	</div>
</body>
</html>
