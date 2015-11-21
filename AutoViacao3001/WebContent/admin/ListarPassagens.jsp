<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Passagens - Lista de Passagens Cadastradas</h2>
		<c:if test="${not empty listaDePassagens}">
			<div align="center">
				<display:table id="tabelaPassagens" name="listaDePassagens" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="viagem.idViagem" title="ID da Viagem"/>
					<display:column property="numeroPoltrona" title="Nº da Poltrona"/>
					<display:column property="preco" title="Preço"/>
					<display:column property="cliente.cpf" title="CPF do Cliente"/>
					<s:url var="visualizarUrl" action="VisualizarPassagem">
						<s:param name="passagem.id" value="%{#attr.tabelaPassagens.id}"/>
					</s:url>
					<display:column title="Visualizar">
						<s:a href="%{#visualizarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_visualizar.png"></s:a>
					</display:column>
					<s:url var="editarUrl" action="AlterarPassagem">
						<s:param name="passagem.id" value="%{#attr.tabelaPassagens.id}"/>
					</s:url>
					<display:column title="Visualizar">
						<s:a href="%{#editarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_editar.png"></s:a>
					</display:column>
					<s:url var="excluirUrl" action="RemoverPassagem">
						<s:param name="passagem.id" value="%{#attr.tabelaPassagens.id}"/>
					</s:url>
					<display:column title="Remover">
						<s:a href="%{#excluirUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_remover.png"></s:a>
					</display:column>
				</display:table>
				
			</div>
		</c:if>
	
		<c:if test="${empty listaDePassagens}">
			<p>Sem passagens cadastradas.</p>
		</c:if>
	</div>
</div>