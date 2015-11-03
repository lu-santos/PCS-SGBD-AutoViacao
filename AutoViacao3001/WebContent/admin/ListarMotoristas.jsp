<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<style type="text/css">
	tr.odd 
	{
	        background-color: #fff
	}
	
	tr.tableRowEven,tr.even 
	{
	        background-color: #eee
	}
</style>

<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Motoristas - Lista de Motoristas Cadastrados</h2>
		<c:if test="${not empty motoristas}">
			<div align="center">
				<display:table id="tabelaMotoristas" name="motoristas" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="cpf" title="CPF"/>
					<display:column property="nome" title="Nome"/>
					<display:column property="dataDeNascimentoFormatada" title="Data de Nascimento"/>
					<display:column href="VisualizarMotorista" paramId="motorista.cpf" property="cpf" title="Visualizar"/>
					<display:column href="PrepararAlteracaoMotorista" paramId="motorista.cpf" property="cpf" title="Alterar"/>
					<display:column href="ExcluirMotorista" paramId="motorista.cpf" property="cpf" title="Remover"/>
					<display:column href="ViagensMotorista" paramId="motorista.cpf" property="cpf" title="Viagens"/>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty motoristas}">
			<p>Sem motoristas cadastrados.</p>
		</c:if>
	</div>
</div>
