<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
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
					<s:url var="visualizarUrl" action="VisualizarCliente">
						<s:param name="cliente.cpf" value="%{#attr.tabelaClientes.cpf}"/>
					</s:url>
					<display:column title="Visualizar">
						<s:a href="%{#visualizarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_visualizar.png"></s:a>
					</display:column>
					<s:url var="editarUrl" action="PrepararAlteracaoCliente">
						<s:param name="cliente.cpf" value="%{#attr.tabelaClientes.cpf}"/>
					</s:url>
					<display:column title="Editar">
						<s:a href="%{#editarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_editar.png"></s:a>
					</display:column>
					<s:url var="removerUrl" action="ExcluirCliente">
						<s:param name="cliente.cpf" value="%{#attr.tabelaClientes.cpf}"/>
					</s:url>
					<display:column title="Remover">
						<s:a href="%{#removerUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_remover.png"></s:a>
					</display:column>
					<s:url var="historicoDeCompraUrl" action="HistoricoDeCompra">
						<s:param name="cliente.cpf" value="%{#attr.tabelaClientes.cpf}"/>
					</s:url>
					<display:column title="Histórico de Compras">
						<s:a href="%{#historicoDeCompraUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_passagem.png"></s:a>
					</display:column>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty clientes}">
			<p>Sem clientes cadastrados.</p>
		</c:if>
	</div>
</div>
