<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Relatório de Passageiros da Viagem</h2>
		<h3>Dados da Viagem</h3>
			<ul>
				<li>Partida/Destino/Distância(KM): <s:label name="viagem.locais.label"></s:label></li>
				<li>Data/Hora de Partida : <s:label name="viagem.dataHoraPartidaFormatada"></s:label></li>
				<li>Data/Hora de Chegada : <s:label name="viagem.dataHoraChegadaFormatada"></s:label></li>
				<li>Ônibus : <s:label name="viagem.onibus.label"></s:label></li>
				<li>Capacidade do Ônibus : <s:label name="viagem.onibus.capacidade"></s:label></li>
			</ul>
			<hr>
		<c:if test="${fn:length(viagem.passageiros) >= 1}">
			<p style="color: #777">A tabela pode ser exportada como .csv, .xls, .xml ou .pdf.</p>
			<div align="center">
				<display:table id="tabelaViagensOnibus" name="viagem.passageiros" pagesize="20" export="true" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="cliente.cpf" title="CPF"/>
					<display:column property="cliente.nome" title="Nome"/>
					<display:column property="numeroPoltrona" title="Número da Poltrona"/>
					<display:setProperty name="export.pdf" value="true" />
					<display:setProperty name="export.pdf.filename" value="viagens_passageiros.pdf"/>
					<display:setProperty name="export.excel.filename" value="viagens_passageiros.xls"/>
					<display:setProperty name="export.csv.filename" value="viagens_passageiros.csv"/>
					<display:setProperty name="export.xml.filename" value="viagens_passageiros.xml"/>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${fn:length(viagem.passageiros) == 0}">
			<p>Sem passageiros cadastradas para a viagem selecionada.</p>
		</c:if>
	</div>
</div>