<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Relatório de Viagens do Ônibus</h2>
		<h3>Dados do Ônibus</h3>
			<ul>
				<li>Modelo - Placa: <s:label name="onibus.label"></s:label></li>
			</ul>
			<hr>
		<c:if test="${fn:length(onibus.viagens) >= 1}">
			<p style="color: #777">A tabela pode ser exportada como .csv, .xls, .xml ou .pdf.</p>
			<div align="center">
				<display:table id="tabelaViagensOnibus" name="onibus.viagens" pagesize="20" export="true" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="dataHoraPartidaFormatada" title="Data/Hora de Partida"/>
					<display:column property="dataHoraChegadaFormatada" title="Data/Hora de Chegada"/>
					<display:column property="locais.label" title="Partida/Destino/Distância(KM)"/>
					<display:column property="onibus.label" title="Ônibus"/>
					<display:setProperty name="export.pdf" value="true" />
					<display:setProperty name="export.pdf.filename" value="viagens_onibus.pdf"/>
					<display:setProperty name="export.excel.filename" value="viagens_onibus.xls"/>
					<display:setProperty name="export.csv.filename" value="viagens_onibus.csv"/>
					<display:setProperty name="export.xml.filename" value="viagens_onibus.xml"/>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${fn:length(onibus.viagens) == 0}">
			<p>Sem viagens cadastradas para o ônibus selecionado.</p>
		</c:if>
	</div>
</div>