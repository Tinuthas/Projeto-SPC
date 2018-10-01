<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Cadastro Funcionario</title>
    <link rel="stylesheet" href="css/cadastros.css" />
     <link rel = "shortcut icon" type = "imagem/x-icon" href = "img/beans.png"/>
  </head>
  <body>
    <div class="formulario">

    <a href="index.html" ><img class="logo"  src="img/beans.png" alt="logo"></a>
  	<h1>Cadastro de Funcionario</h1>
    <h2>Cadastrar informações sobre o Funcionario</h2>

      <div class="container">
        <form action="controller" method="post">

          <div class="label">
            <label for="idNome">Nome: </label><br>
            <label for="idSenha">Senha: </label><br>
            <label for="idEmail">Email: </label><br>
            <label for="idCpf">CPF: </label><br>
            <label for="idTelefone">Telefone: </label><br>
            <label for="idCondominio">Codigo Condominio: </label><br>
            <label for="idRg">RG: </label><br>
            <label for="idRgDig">RG Digito: </label><br>
            <label for="idDataNascimento">Data Nascimento: </label><br>
            <label for="M">Sexo: </label><br><br><br>
            <label for="idDataAdmissao">Data de Admissão: </label><br>
            <label for="idFuncao">Função: </label><br>
          </div>
      <div class="input">
        <div class="campo" id="idCampo" >
    	    <input id="idNome" type="text" name="nome"   maxlength="60" required onblur="validarLetra(this,'erroCampo');" ><br>
    	    <input id="idSenha" type="text" name="senha" maxlength="30" required onblur="validarSenha(this,'erroCampo');" ><br>
    	    <input id="idEmail" type="text" name="email" maxlength="80" required onblur="validarEmail(this,'erroCampo');"><br>
    	    <input id="idCpf" type="text" name="cpf"     maxlength="11" required onblur="validarCpf(this,'erroCampo');" ><br>
    	    <input id="idTelefone" type="text" name="telefone" maxlength="13" required  onblur="validarTelefone(this,'erroCampo');"><br>
    	    <input id="idCondominio" type="text" name="condominio" maxlength="7" required onblur="validarCondominio(this,'erroCampo');" ><br>
    	    <input id="idRg" type="text" name="rg"                 maxlength="8" required onblur="validarRg(this,'erroCampo');" ><br>
    	    <input id="idRgDig" type="text" name="rgDig"           maxlength="2" required onblur="validarRgDig(this,'erroCampo');" ><br>
    	    <input id="idDataNascimento" name="dataNascimento" maxlength="10" required onblur="validarDataNascimento(this,'erroCampo');" /><br>

    	    <label for="M"><input id="M" type="radio" name="sexo" value="M" checked onblur="validarSexo(this,'erroCampo');"> Masculino </label><br>
    	    <label for="F"><input id="F" type="radio" name="sexo" value="F" checked onblur="validarSexo(this,'erroCampo');"> Feminino </label><br>
    	    <label for="O"><input id="O" type="radio" name="sexo" value="O" checked onblur="validarSexo(this,'erroCampo');"> Outros </label><br>

    	    <input id="idDataAdmissao" type="text" name="dataAdmissao" maxlength="10" required onblur="validarDataAdimissao(this,'erroCampo');"><br>
          <input id="idFuncao"       type="text" name="funcao"       maxlength="30" required onblur="validarfuncao(this,'erroCampo');"><br>
          <span class="erro" id="erroSenha"></span>
        </div>
      </div>


    	    <button type="submit" name="enviar" value="ServletFuncionario"  onclick="valida_form_funcionario()">Enviar</button>

        </form>
        <a href="index.html"><button>Voltar</button></a>
     	<div class="enviado">
          <c:if test="${not empty funcionario }"><p>Funcionario Cadastrado!</p></c:if>
        </div>
      </div>
    </div>
    <!-- DOIS LADOS  -->
    <div class="left"></div>
    <div class="right"></div>
    <script type="text/javascript "src="js/cbn adastro.js">
		$("#idDataNascimento").datepicker();
	</script>
  </body>
</html>
