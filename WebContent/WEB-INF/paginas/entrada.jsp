<%@page import="org.apache.taglibs.standard.tag.common.xml.ForEachTag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
	<meta charset="UTF-8" />
    <link rel="stylesheet" href="css/login.css">
    <link rel = "shortcut icon" type = "imagem/x-icon" href = "../../img/logo.ico"/>
  </head>
  <body>
  <section class="login">
    <div class="form">
    <div class="login-form">
    	
      <a href="index.html" ><img class="logo"  src="img/beans.png" alt="logo"></a>
      
      <a href="chatbot.html"><button>Chatbot</button></a><br>
    	
      <c:if test="${not empty MORADOR}">
      	
      	<a href="agendar-servico.jsp"><button>Agendar Serviço</button></a>
      
      	<form action="servico" method="post">
      	<button type="submit" name="enviar" value="servico">Serviços</button>
      	</form>
      </c:if>
      <c:if test="${not empty FUNCIONARIO}">
      
      <a href="registrar-encomenda.jsp"><button>Registrar Encomenda</button></a>
      <br>
      <a href="pesquisar-morador.jsp"><button>Pesquisar Morador</button></a>
      
      </c:if> 
      
      <form action="encomenda" method="post">
          <button type="submit" name="enviar" value="encomenda">Encomendas</button>
      </form>
      
      <a href="escrever-comunicado.jsp"><button>Escrever Comunicado</button></a><br>
      
      <form action="comunicado" method="post">
         <button type="submit" name="enviar" value="comunicado">Comunicados</button>
      </form>
      
      
      <a href="index.html"><button>Informações</button></a>
      <br>  
      <form method="post" action="logout">
		 <button type="submit" name="enviar" value="ServletLogout">Voltar</button>
	  </form>
    </div>
  </div>
</section>
  </body>
</html>
