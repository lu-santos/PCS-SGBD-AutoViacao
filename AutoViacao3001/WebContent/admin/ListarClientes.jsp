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
		<h2 style="text-align: center">Gerenciar Clientes - Lista de Clientes Cadastrados</h2>
		<c:if test="${not empty clientes}">
			<div align="center">
				<display:table id="tabelaClientes" name="clientes" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="cpf" title="CPF"/>
					<display:column property="nome" title="Nome"/>
					<display:column property="dataDeNascimento" title="Data de Nascimento"/>
					<display:column href="VisualizarCliente" paramId="cliente.cpf" property="cpf" title="Visualizar"/>
					<display:column href="PrepararAlteracaoCliente" paramId="cliente.cpf" property="cpf" title="Alterar"/>
					<display:column href="ExcluirCliente" paramId="cliente.cpf" property="cpf" title="Remover"/>
					<display:column href="HistoricoDeCompra" paramId="cliente.cpf" property="cpf" title="Histórico de Compras"/>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty clientes}">
			<p>Sem clientes cadastrados.</p>
		</c:if>
	</div>
</div>
