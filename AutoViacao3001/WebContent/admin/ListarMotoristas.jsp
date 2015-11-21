<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
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
					<s:url var="visualizarUrl" action="VisualizarMotorista">
						<s:param name="motorista.cpf" value="%{#attr.tabelaMotoristas.cpf}"/>
					</s:url>
					<display:column title="Visualizar">
						<s:a href="%{#visualizarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_visualizar.png"></s:a>
					</display:column>
					<s:url var="editarUrl" action="PrepararAlteracaoMotorista">
						<s:param name="motorista.cpf" value="%{#attr.tabelaMotoristas.cpf}"/>
					</s:url>
					<display:column title="Editar">
						<s:a href="%{#editarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_editar.png"></s:a>
					</display:column>
					<s:url var="removerUrl" action="ExcluirMotorista">
						<s:param name="motorista.cpf" value="%{#attr.tabelaMotoristas.cpf}"/>
					</s:url>
					<display:column title="Remover">
						<s:a href="%{#removerUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_remover.png"></s:a>
					</display:column>
					<s:url var="viagemUrl" action="ViagensMotorista">
						<s:param name="motorista.cpf" value="%{#attr.tabelaMotoristas.cpf}"/>
					</s:url>
					<display:column title="Viagens">
						<s:a href="%{#viagemUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_viagem.png"></s:a>
					</display:column>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty motoristas}">
			<p>Sem motoristas cadastrados.</p>
		</c:if>
	</div>
</div>
