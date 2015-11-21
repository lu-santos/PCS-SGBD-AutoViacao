<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Passagens - Lista de Viagens sem Passagens Cadastradas</h2>
		<c:if test="${not empty listaDeViagens}">
			<div align="center">
				<display:table id="tabelaViagensPassagens" name="listaDeViagens" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="dataHoraPartidaFormatada" title="Data/Hora de Partida"/>
					<display:column property="locais.label" title="Partida/Destino/Distância(KM)"/>
					<display:column property="onibus.label" title="Ônibus"/>
					<s:url var="gerarPassagemUrl" action="PrepararGerarPassagens">
						<s:param name="viagem.idViagem" value="%{#attr.tabelaViagensPassagens.idViagem}"/>
					</s:url>
					<display:column title="Gerar Passagens">
						<s:a href="%{#gerarPassagemUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_passagem.png"></s:a>
					</display:column>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty listaDeViagens}">
			<p>Não foi localizada viagem sem passagem cadastrada.</p>
		</c:if>
	</div>
</div>
