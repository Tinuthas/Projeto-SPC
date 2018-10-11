<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8" />
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel = "shortcut icon" type = "imagem/x-icon" href = "img/beans.png"/>
  </head>
  <body>
  <c:if test="${(not empty  MORADOR) or (not empty FUNCIONARIO) or (not empty VINCULO) }">
  	<form method="post" name="retorno" action="controller">
  	<input type="hidden" name="enviar" value="Retorno" /> 
		<script type="text/javascript">
   			 document.retorno.submit();
		</script>
	</form>
   </c:if>
  <div class="login">
    <div class="form">
    
    <form method="post" action="controller" class="login-form">
      <a href="index.html" ><img class="logo"  src="img/beans.png" alt="logo"></a>
      <div class="invalido"><c:if test="${not empty INVALIDO }"><p>Dados  Inválidos<p></c:if></div>
      <input type="text" id="email" name="email" placeholder="E-mail" maxlength="80" >
      <input type="password" id="senha" name="senha" placeholder="Senha" maxlength="30"/>
      <button type="submit" name="enviar" value="Login" >Login</button>
    </form>
    <form action="index.html">
     <a href="index.html"><button>Voltar</button></a>
    </form>
  
  </div>
</div>

  <script type="text/javascript" src="../js/JS.js">

  </script>
  </body>
</html>
