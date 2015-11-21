<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Viagens - Lista de Viagens Cadastradas</h2>
		<c:if test="${not empty listaDeViagens}">
			<div align="center">
				<display:table id="tabelaViagens" name="listaDeViagens" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="dataHoraPartidaFormatada" title="Data/Hora de Partida"/>
					<display:column property="locais.label" title="Partida/Destino/Distância(KM)"/>
					<s:url var="visualizarUrl" action="VisualizarViagem">
						<s:param name="viagem.idViagem" value="%{#attr.tabelaViagens.idViagem}"/>
					</s:url>
					<display:column title="Visualizar">
						<s:a href="%{#visualizarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_visualizar.png"></s:a>
					</display:column>
					<s:url var="editarUrl" action="PrepararAlteracaoViagem">
						<s:param name="viagem.idViagem" value="%{#attr.tabelaViagens.idViagem}"/>
					</s:url>
					<display:column title="Editar">
						<s:a href="%{#editarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_editar.png"></s:a>
					</display:column>
					<s:url var="removerUrl" action="ExcluirViagem">
						<s:param name="viagem.idViagem" value="%{#attr.tabelaViagens.idViagem}"/>
					</s:url>
					<display:column title="Remover">
						<s:a href="%{#removerUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_remover.png"></s:a>
					</display:column>
					<s:url var="passageirosUrl" action="ViagensPassageiros">
						<s:param name="viagem.idViagem" value="%{#attr.tabelaViagens.idViagem}"/>
					</s:url>
					<display:column title="Passageiros">
						<s:a href="%{#passageirosUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_passageiros.png"></s:a>
					</display:column>
					<s:url var="lucroBrutoUrl" action="ViagemLucroBruto">
						<s:param name="viagem.idViagem" value="%{#attr.tabelaViagens.idViagem}"/>
					</s:url>
					<display:column title="Lucro Bruto">
						<s:a href="%{#lucroBrutoUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_lucro.png"></s:a>
					</display:column>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty listaDeViagens}">
			<p>Sem viagens cadastradas.</p>
		</c:if>
	</div>
</div>
