<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Viagens - Lista de Locais Cadastrados</h2>
		<c:if test="${not empty ListaDeLocaisSemDistancia}">
			<div align="center">
				<display:table id="tabelaLocais" name="ListaDeLocaisSemDistancia" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="nome" title="Local"/>
					<s:url var="visualizarUrl" action="VisualizarLocal">
						<s:param name="local.id" value="%{#attr.tabelaLocais.id}"/>
					</s:url>
					<display:column title="Visualizar">
						<s:a href="%{#visualizarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_visualizar.png"></s:a>
					</display:column>
					<s:url var="removerUrl" action="ExcluirLocal">
						<s:param name="local.id" value="%{#attr.tabelaLocais.id}"/>
					</s:url>
					<display:column title="Remover">
						<s:a href="%{#removerUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_remover.png"></s:a>
					</display:column>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty ListaDeLocaisSemDistancia}">
			<p>Sem locais cadastrados.</p>
		</c:if>
	</div>
</div>
