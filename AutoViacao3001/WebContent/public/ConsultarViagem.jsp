<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global" style="height: 100%">
	<div class="conteudo">
		<c:if
			test="${not empty listaDeLocais}">
			<form action="ListarResultadoViagem" style="width: 90%" method="post">
				<h2>Consultar Viagem</h2>
				<p style="color: #e62117">
					<s:hidden name="viagem.idViagem"/>
				</p>
				<div class="caixa-padrao" style="margin-left: 0px; width: 50%">
					<label>Local de Partida / Local de Origem / Distância (KM)*</label><br>
					<s:select listKey="idLocais" listValue="label" headerKey="-1" list="listaDeLocais" name="viagem.locais.idLocais" />
					<br>
				</div>
				<div class="caixa-padrao" style="margin-left: 20px; width: 45%">
					<label>Data/Hora de Partida*</label><br> 
					<s:textfield type="datetime-local" disabled="false" name="viagem.dataHoraPartidaFormatoJSP"/>
				</div>
				<div align="center">
					<input type="submit" value="Pesquisar" class="button-green"
						style="width: 20%; display: block; clear: both">
				</div>
			</form>
		</c:if>

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
					<s:url var="passagemUrl" action="PassagensDaViagem">
						<s:param name="viagem.idViagem" value="%{#attr.tabelaViagens.idViagem}"/>
					</s:url>
					<display:column title="Passagens">
						<s:a href="%{#passagemUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_passagem.png"></s:a>
					</display:column>
				</display:table>
			</div>
		</c:if>
		
		<c:if test="${empty listaDeViagens}">
			<s:property value="mensagem" />
		</c:if>

		<c:if test="${empty listaDeLocais}">
			<p>Não é possível consultar viagens, pois não há locais
				cadastrados na base de dados.</p>
		</c:if>

		<c:if test="${empty listaDeOnibus}">
			<p>Não é possível consultar viagens, pois não há ônibus
				cadastrados na base de dados.</p>
		</c:if>

		<c:if test="${empty listaDeMotoristas}">
			<p>Não é possível consultar viagens, pois não há motoristas
				cadastrados na base de dados.</p>
		</c:if>
	</div>
</div>
