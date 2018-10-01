function validarLetra(tag,idErro){
	var nome = tag.value;
	var validacao = /^[A-Za-záàâãéèêíïóôõöüúçñÁÀÂÃÉÈÍÏÓÔÕÖÜÚÇÑ' ]+$/;
	var spanErro = document.getElementById(idErro);
	if (!validacao.test(nome)){
		tag.style.border = "1px solid red";
		spanErro.innerHTML = "";
	}else{
		tag.style.border = "1px solid silver";
		spanErro.innerHTML = "";
	}
	if(idNome.value != ""){
	  idParagrafo1.innerHTML = idNome.value;
	}
	if(idSnome.value != ""){
	  idParagrafo2.innerHTML = idSnome.value;
	}
}

function validarCpf(tag,idErro){
	var cpf = tag.value;
	var validacao =/^([\d]{2,3})\.?([\d]{3})\.?([\d]{3})\-?([\d]{2})$/;
	var spanErro = document.getElementById(idErro);
	if (!validacao.test(cpf)){
		tag.style.border = "1px solid red";
		spanErro.innerHTML = "";
	}else{
		tag.style.border = "1px solid silver";
		spanErro.innerHTML = "";
		var cpfFormat = tag.value.replace(validacao, "$1.$2.$3-$4");
		idCpf.value = cpfFormat;
	}
	if(idCpf.value != ""){
	  idParagrafo3.innerHTML = idCpf.value;
	}
}

function validarEmail(tag,idErro){
  var email = tag.value;
  var validacao = /^[A-Za-z0-9_\-\.]+@[A-Za-z0-9_\-\.]{2,}\.[A-Za-z0-9]{2,}(\.[A-Za-z0-9])?/
  var spanErro = document.getElementById(idErro);
  if (!validacao.test(email)){
		tag.style.border = "1px solid red";
		spanErro.innerHTML = "";
	}else{
		tag.style.border = "1px solid silver";
		spanErro.innerHTML = "";
		}
}

function validarTudo(tag,idErro){
  var tudo = tag.value;
  var validacao = /^[a-z0-9_\.-]{1,255}$/ ;
  var spanErro = document.getElementById(idErro);
  if (!validacao.test(tudo)){
		tag.style.border = "1px solid red";
		spanErro.innerHTML = "";
	}else{
		tag.style.border = "1px solid silver";
		spanErro.innerHTML = "";
		}
}

function validarSenha(tag,idErro){
  var senha = tag.value;
  var validacao = /^[A-Za-z0-9]+$/;
  var spanErro = document.getElementById(idErro);
  if (!validacao.test(senha)){
		tag.style.border = "1px solid red";
		spanErro.innerHTML = "";
	}else{
		tag.style.border = "1px solid silver";
		spanErro.innerHTML = "";
		}

	}
	function validarCondominio(tag,idErro){
	  var condominio = tag.value;
	  var validacao = /^[0-9]+$/;
	  var spanErro = document.getElementById(idErro);
	  if (!validacao.test(condominio)){
			tag.style.border = "1px solid red";
			spanErro.innerHTML = "";
		}else{
			tag.style.border = "1px solid silver";
			spanErro.innerHTML = "";
			}
		}

function validarRg(tag,idErro){
	  var rg = tag.value;
	  var validacao = /^[0-9]+$/;
	  var spanErro = document.getElementById(idErro);
	  if (!validacao.test(rg)){
			tag.style.border = "1px solid red";
			spanErro.innerHTML = "";
		}else{
			tag.style.border = "1px solid silver";
			spanErro.innerHTML = "";
			}
}
function validarRgDig(tag,idErro){
	var rgDig = tag.value;
	var validacao = /^[0-9]+$/;
	var spanErro = document.getElementById(idErro);
	if (!validacao.test(rgDig)){
		tag.style.border = "1px solid red";
		spanErro.innerHTML = "";
	}else{
		tag.style.border = "1px solid silver";
		spanErro.innerHTML = "";
		}
}
	function validarDataNascimento(tag,idErro){
	var dataNascimento = tag.value;
	var validacao =/^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/;
	var spanErro = document.getElementById(idErro);
	if (!validacao.test(dataNascimento)){
	tag.style.border = "1px solid red";
	spanErro.innerHTML = "";
}else{
	tag.style.border = "1px solid silver";
	spanErro.innerHTML = "";
	}
}
	function validarSexo(tag,idErro){
	var sexo = tag.value;
	var validacao =  /^[A-Za-záàâãéèêíïóôõöüúçñÁÀÂÃÉÈÍÏÓÔÕÖÜÚÇÑ' ]+$/; ;
	var spanErro = document.getElementById(idErro);
	if (!validacao.test(sexo)){
	tag.style.border = "1px solid red";
	spanErro.innerHTML = "";
}else{
	tag.style.border = "1px solid silver";
	spanErro.innerHTML = "";
	}
}
	function validarNumero(tag,idErro){
	var numero = tag.value;
	var validacao = /^[0-9]+$/;;
	var spanErro = document.getElementById(idErro);
	if (!validacao.test(numero)){
	tag.style.border = "1px solid red";
	spanErro.innerHTML = "";
	}else{
	tag.style.border = "1px solid silver";
	spanErro.innerHTML = "";
	}
}
	function validarTorre(tag,idErro){
	var torre = tag.value;
	var validacao = /^[0-9A-Za-z]+$/;
	var spanErro = document.getElementById(idErro);
	if (!validacao.test(torre)){
	tag.style.border = "1px solid red";
	spanErro.innerHTML = "";
	}else{
	tag.style.border = "1px solid silver";
	spanErro.innerHTML = "";
	}
}
function validarTelefone(tag,idErro){
  var telefone = tag.value;
  var validacao = /^(?:(?:\+|00)?(55)\s?)?(?:\(?([1-9][0-9])\)?\s?)?(?:((?:9\d|[2-9])\d{3})\-?(\d{4}))$/;
  var spanErro = document.getElementById(idErro);
  if (!validacao.test(telefone)){
		tag.style.border = "1px solid red";
		spanErro.innerHTML = "";
	}else{
		tag.style.border = "1px solid silver";
		spanErro.innerHTML = "";
  }
	if(idTelefone.value != ""){
	  idParagrafo4.innerHTML = idTelefone.value;
	}
}

function validarfuncao(tag,idErro){
	var funcao = tag.value;
	var validacao =  /^[A-Za-záàâãéèêíïóôõöüúçñÁÀÂÃÉÈÍÏÓÔÕÖÜÚÇÑ' ]+$/;
	var spanErro = document.getElementById(idErro);
	if (!validacao.test(funcao)){
		tag.style.border = "1px solid red";
		spanErro.innerHTML = "";
	}else{
		tag.style.border = "1px solid silver";
		spanErro.innerHTML = "";
	}
}

function validarDataAdimissao(tag,idErro){
var dataAdmissao = tag.value;
var validacao =/^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/;
var spanErro = document.getElementById(idErro);
if (!validacao.test(dataAdmissao)){
tag.style.border = "1px solid red";
spanErro.innerHTML = "";
}else{
tag.style.border = "1px solid silver";
spanErro.innerHTML = "";
 }

}
function validarRazaoSocial(tag,idErro){
var razaoSocial = tag.value;
var validacao = /^[A-Za-záàâãéèêíïóôõöüúçñÁÀÂÃÉÈÍÏÓÔÕÖÜÚÇÑ' ]+$/;
var spanErro = document.getElementById(idErro);
if (!validacao.test(razaoSocial)){
tag.style.border = "1px solid red";
spanErro.innerHTML = "";
}else{
tag.style.border = "1px solid silver";
spanErro.innerHTML = "";
 }
}
function validarCnpj(tag,idErro){
var cnpj = tag.value;
var validacao =/^[0-9]+$/;
var spanErro = document.getElementById(idErro);
if (!validacao.test(cnpj)){
tag.style.border = "1px solid red";
spanErro.innerHTML = "";
}else{
tag.style.border = "1px solid silver";
spanErro.innerHTML = "";
 }
}
function validarLouradouro(tag,idErro){
var logradouro = tag.value;
var validacao = /^[A-Za-záàâãéèêíïóôõöüúçñÁÀÂÃÉÈÍÏÓÔÕÖÜÚÇÑ' ]+$/;
var spanErro = document.getElementById(idErro);
if (!validacao.test(logradouro)){
tag.style.border = "1px solid red";
spanErro.innerHTML = "";
}else{
tag.style.border = "1px solid silver";
spanErro.innerHTML = "";
 }
}
function validarCep(tag,idErro){
var cep = tag.value;
var validacao =/^[0-9]+$/;
var spanErro = document.getElementById(idErro);
if (!validacao.test(cep)){
tag.style.border = "1px solid red";
spanErro.innerHTML = "";
}else{
tag.style.border = "1px solid silver";
spanErro.innerHTML = "";
 }
}
function validarCidade(tag,idErro){
var cidade = tag.value;
var validacao = /^[A-Za-záàâãéèêíïóôõöüúçñÁÀÂÃÉÈÍÏÓÔÕÖÜÚÇÑ' ]+$/;
var spanErro = document.getElementById(idErro);
if (!validacao.test(cidade)){
tag.style.border = "1px solid red";
spanErro.innerHTML = "";
}else{
tag.style.border = "1px solid silver";
spanErro.innerHTML = "";
 }
}
function validarBairro(tag,idErro){
var bairro = tag.value;
var validacao = /^[A-Za-záàâãéèêíïóôõöüúçñÁÀÂÃÉÈÍÏÓÔÕÖÜÚÇÑ' ]+$/;
var spanErro = document.getElementById(idErro);
if (!validacao.test(bairro)){
tag.style.border = "1px solid red";
spanErro.innerHTML = "";
}else{
tag.style.border = "1px solid silver";
spanErro.innerHTML = "";
 }
}


function valida_form_condominio() {

if (document.getElementById("idRazaoSocial").value == "") {
		alert("Por favor, informe sua Razão Social");
document.getElementById("idRazaoSocial").focus();
//return false;
}
if (document.getElementById("idCnpj").value == "") {
		alert("Por favor, informe o CNPJ");
document.getElementById("idCnpj").focus();
//return false;
}
if (document.getElementById("idTelefone").value == "") {
		alert("Por favor, preencha Campo Telefone");
document.getElementById("idTelefone").focus();
//return false;
}
if (document.getElementById("idEmail").value == "") {
		alert("Por favor, preencha o Campo Email");
document.getElementById("idEmail").focus();
//return false;
}
if (document.getElementById("idLogradouro").value == "") {
		alert("Por favor, informe seu Logradouro");
document.getElementById("idLogradouro").focus();
//return false;
}
if (document.getElementById("idNumero").value == "") {
		alert("Por favor, informe Numero do Apartamento");
document.getElementById("idNumero").focus();
//return false;
}
if (document.getElementById("idCep").value == "") {
		alert("Por favor, informe Numero do CEP");
document.getElementById("idCep").focus();
//return false;
}
if (document.getElementById("idCidade").value == "") {
		alert("Por favor, informe sua Cidade");
document.getElementById("idCidade").focus();
//return false;
}
if (document.getElementById("idBairro").value == "") {
		alert("Por favor, informe seu Bairro");
document.getElementById("idBairro").focus();
//return false;
}

}

function valida_form_funcionario() {

	if (document.getElementById("idNome").value == "") {
			alert("Por favor, preencha seu nome");
	document.getElementById("idNome").focus();
	//return false;
	}

	if (document.getElementById("idSenha").value == "") {
			alert("Por favor, preencha sua Senha");
	document.getElementById("idSenha").focus();
	//return false;
	}
	if (document.getElementById("idEmail").value == "") {
			alert("Por favor, preencha o Campo Email");
	document.getElementById("idEmail").focus();
	//return false;
	}
	if (document.getElementById("idCpf").value == "") {
			alert("Por favor, preencha campo CPF");
	document.getElementById("idCpf").focus();
	//return false;
	}
	if (document.getElementById("idTelefone").value == "") {
			alert("Por favor, preencha Campo Telefone");
	document.getElementById("idTelefone").focus();
	//return false;
	}
	if (document.getElementById("idCondominio").value == "") {
			alert("Por favor, preencha Campo Condominio");
	document.getElementById("idCondominio").focus();
	//return false;
	}
	if (document.getElementById("idRg").value == "") {
			alert("Por favor, preencha Campo RG");
	document.getElementById("idRg").focus();
	//return false;
	}
	if (document.getElementById("idRgDig").value == "") {
			alert("Por favor, informe o Digito do RG");
	document.getElementById("idRgDig").focus();
	//return false;
	}
	if (document.getElementById("idDataNascimento").value == "") {
			alert("Por favor, preencha Campo Data de Nascimento");
	document.getElementById("idDataNascimento").focus();
	//return false;
	}
	if (document.getElementById("idDataAdmissao").value == "") {
		alert("Por favor, informe sua Data de Admissão");
	document.getElementById("idDataAdmissao").focus();
	//return false;
}
	if (document.getElementById("idFuncao").value == "") {
		alert("Por favor, informe sua Função");
	document.getElementById("idFuncao").focus();
	//return false;
	}
}
function valida_form_morador() {

	if (document.getElementById("idNome").value == "") {
			alert("Por favor, preencha seu nome");
	document.getElementById("idNome").focus();
	//return false;
	}

	if (document.getElementById("idSenha").value == "") {
			alert("Por favor, preencha sua Senha");
	document.getElementById("idSenha").focus();
	//return false;
	}
	if (document.getElementById("idEmail").value == "") {
			alert("Por favor, preencha o Campo Email");
	document.getElementById("idEmail").focus();
	//return false;
	}
	if (document.getElementById("idCpf").value == "") {
			alert("Por favor, preencha campo CPF");
	document.getElementById("idCpf").focus();
	//return false;
	}
	if (document.getElementById("idTelefone").value == "") {
			alert("Por favor, preencha Campo Telefone");
	document.getElementById("idTelefone").focus();
	//return false;
	}
	if (document.getElementById("idCondominio").value == "") {
			alert("Por favor, preencha Campo Condominio");
	document.getElementById("idCondominio").focus();
	//return false;
	}
	if (document.getElementById("idRg").value == "") {
			alert("Por favor, preencha Campo RG");
	document.getElementById("idRg").focus();
	//return false;
	}
	if (document.getElementById("idRgDig").value == "") {
			alert("Por favor, informe o Digito do RG");
	document.getElementById("idRgDig").focus();
	//return false;
	}
	if (document.getElementById("idDataNascimento").value == "") {
			alert("Por favor, preencha Campo Data de Nascimento");
	document.getElementById("idDataNascimento").focus();
	//return false;
	}
	if (document.getElementById("idNumero").value == "") {
			alert("Por favor, informe Numero do Apartamento");
	document.getElementById("idNumero").focus();
	//return false;
	}
	if (document.getElementById("idTorre").value == "") {
			alert("Por favor, informe Torre do Apartamento");
	document.getElementById("idTorre").focus();
	//return false;
}

}
