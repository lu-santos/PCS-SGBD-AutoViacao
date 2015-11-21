<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
	<div>
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Ônibus - Lista de Ônibus Cadastrados</h2>
		<c:if test="${not empty listaDeOnibus}">
			<div align="center">
				<display:table id="tabelaOnibus" name="listaDeOnibus" pagesize="3" cellpadding="5px;" cellspacing="5px;" style="align: center; margin-top: 20px" requestURI="">
					<display:column property="placa" title="Placa"/>
					<display:column property="modelo" title="Modelo"/>
					<display:column property="fabricante" title="Fabricante"/>
					<s:url var="visualizarUrl" action="VisualizarOnibus">
						<s:param name="onibus.idOnibus" value="%{#attr.tabelaOnibus.idOnibus}"/>
					</s:url>
					<display:column title="Visualizar">
						<s:a href="%{#visualizarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_visualizar.png"></s:a>
					</display:column>
					<s:url var="editarUrl" action="PrepararAlteracaoOnibus">
						<s:param name="onibus.idOnibus" value="%{#attr.tabelaOnibus.idOnibus}"/>
					</s:url>
					<display:column title="Editar">
						<s:a href="%{#editarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_editar.png"></s:a>
					</display:column>
					<s:url var="removerUrl" action="ExcluirOnibus">
						<s:param name="onibus.idOnibus" value="%{#attr.tabelaOnibus.idOnibus}"/>
					</s:url>
					<display:column title="Remover">
						<s:a href="%{#removerUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_remover.png"></s:a>
					</display:column>
					<s:url var="viagemUrl" action="ViagensOnibus">
						<s:param name="onibus.idOnibus" value="%{#attr.tabelaOnibus.idOnibus}"/>
					</s:url>
					<display:column title="Viagem">
						<s:a href="%{#viagemUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_viagem.png"></s:a>
					</display:column>
				</display:table>
			</div>
		</c:if>
		<c:if test="${empty listaDeOnibus}">
			<p>Sem ônibus cadastrados.</p>
		</c:if>
	</div>
</div>
