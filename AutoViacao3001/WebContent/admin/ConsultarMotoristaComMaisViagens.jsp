<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global" style="height: 100%">
	<div class="conteudo">
		<form action="ListarMotoristasComMaisViagens" style="width: 90%" method="post">
			<h2>Consultar Motorista com Mais Viagens</h2>
			
			<div class="caixa-padrao" style="margin-left: 0px; width: 50%">
				<label>Data/Hora de Partida*</label><br>
				<s:textfield type="datetime-local" disabled="false" name="viagem.dataHoraPartidaFormatoJSP"/>
				<br>
			</div>
			<div class="caixa-padrao" style="margin-left: 20px; width: 45%">
				<label>Data/Hora de Chegada*</label><br> 
				<s:textfield type="datetime-local" disabled="false" name="viagem.dataHoraChegadaFormatoJSP"/>
			</div>
			<div align="center">
				<input type="submit" value="Pesquisar" class="button-green"
					style="width: 20%; display: block; clear: both">
			</div>
		</form>

		<c:if test="${not empty motoristas}">
			<div align="center">
				<display:table id="tabelaMotorista" name="motoristas" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="cpf" title="CPF"/>
					<display:column property="nome" title="Nome"/>
					<display:column property="dataDeContratacao" title="Data de Contratação"/>
					<s:url var="visualizarUrl" action="VisualizarMotorista">
						<s:param name="motorista.cpf" value="%{#attr.tabelaMotorista.cpf}"/>
					</s:url>
					<display:column title="Visualizar">
						<s:a href="%{#visualizarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_visualizar.png"></s:a>
					</display:column>
				</display:table>
			</div>
		</c:if>
		
		<c:if test="${empty motoristas}">
			<s:property value="mensagem" />
		</c:if>

	</div>
</div>
