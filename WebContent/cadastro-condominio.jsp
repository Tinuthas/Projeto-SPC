<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br" >
  <head>
    <meta charset="utf-8">
    <title>Cadastro de Condominio</title>
    <link rel="stylesheet" href="css/cadastros.css" />
     <link rel = "shortcut icon" type = "imagem/x-icon" href = "img/beans.png"/>
  </head>
  <body>
  <div class="formulario">

    <a href="index.html" ><img class="logo"  src="img/beans.png" alt="logo"></a>

    <h1>Sistema de Portaria Condominio</h1>
    <h2>Cadastro de Condominio</h2>

    <div class="container">


      <form action="controller" method="post">

        <div class="label">
          <label for="idRazaoSocial">Razão Social: </label><br>
          <label for="idCnpj">CNPJ: </label><br>
          <label for="tipo1" >Tipo do Condominio</label>
          <br><br><label for="idTelefone">Telefone: </label><br>
          <label for="idEmail">Email: </label><br>
          <label for="idLogradouro">Logradouro: </label><br>
          <label for="idNumero">Numero: </label><br>
          <label for="idCep">Cep: </label><br>
          <label for="idEstado">Estado: </label><br>
          <label for="idCidade">Cidade: </label><br>
          <label for="idBairro">Bairro: </label><br>
        </div>
    <div class="input">
      <div class="campo" id="idCampo" >
        <input id="idRazaoSocial" type="text" name="razaoSocial"  maxlength="30" required onblur="validarRazaoSocial(this,'erroCampo');" ><br>
        <input id="idCnpj" type="text" name="cnpj"                maxlength="14" required onblur="validarCnpj(this,'erroCampo');"  ><br>
        <label for="tipo1"><input id="tipo1" type="radio" name="tipo" value="1"  >Apartamento</label><br>
        <label for="tipo2"><input id="tipo2" type="radio" name="tipo" value="2">Casa</label><br>
        <input id="idTelefone" type="text" name="telefone" maxlength="13" required  onblur="validarTelefone(this,'erroCampo');" ><br>
        <input id="idEmail" type="text" name="email"       maxlength="80" required onblur="validarEmail(this,'erroCampo');" ><br>
        <input id="idLogradouro" type="text" name="logradouro"       maxlength="100" required onblur="validarLouradouro(this,'erroCampo');"><br>
        <input id="idNumero" type="text" name="numero"  maxlength="5" required onblur="validarNumero(this,'erroCampo');"><br>
        <input id="idCep" type="text" name="cep" maxlength="8" required onblur="validarCep(this,'erroCampo');"><br>
        <select name="estado" id="idEstado">
          <option value="AL">AL</option>
          <option value="AM">AM</option>
          <option value="AP">AP</option>
          <option value="BA">BA</option>
          <option value="CE">CE</option>
          <option value="DF">DF</option>
          <option value="ES">ES</option>
          <option value="GO">GO</option>
          <option value="MA">MA</option>
          <option value="MG">MG</option>
          <option value="MS">MS</option>
          <option value="MT">MT</option>
          <option value="PA">PA</option>
          <option value="PB">PB</option>
          <option value="PE">PE</option>
          <option value="PI">PI</option>
          <option value="PR">PR</option>
          <option value="RJ">RJ</option>
          <option value="RN">RN</option>
          <option value="RS">RS</option>
          <option value="RO">RO</option>
          <option value="RR">RR</option>
          <option value="SC">SC</option>
          <option value="SE">SE</option>
          <option value="SP">SP</option>
          <option value="TO">TO</option>
        </select><br>
        <input id="idCidade" type="text" name="cidade"  maxlength="30" required onblur="validarCidade(this,'erroCampo');" ><br>
        <input id="idBairro" type="text" name="bairro"  maxlength="30" required onblur="validarBairro(this,'erroCampo');"  ><br>
        <span class="erro" id="erroSenha"></span>
        </div>
      </div>

      <button type="submit" name="enviar" value="CadastroCondominio"  onclick="valida_form_condominio ()">Enviar</button>

      </form>

      <a href="index.html"><button>Voltar</button></a>
	  <div class="enviado">
          <c:if test="${not empty condominio }"><p>Condominio Cadastrado!</p></c:if>
      </div>
    </div>
  </div>
    <!-- DOIS LADOS  -->
    <div class="left"></div>
    <div class="right"></div>
    <script type="text/javascript "src="js/cadastro.js"></script>
  </body>
</html>
