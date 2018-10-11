<c:if test="${(empty  MORADOR) and (empty FUNCIONARIO) and (empty VINCULO) }">
  	<form method="post" name="retorno" action="controller">
  	<input type="hidden" name="enviar" value="Logout" /> 
		<script type="text/javascript">
   			 document.retorno.submit();
		</script>
	</form>
</c:if>