<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Relat�rio de Viagens do Motorista</h2>
		<h3>Dados do Motorista</h3>
			<ul>
				<li>Nome: <s:label name="motorista.nome"></s:label></li>
				<li>CPF: <s:label name="motorista.cpf"></s:label></li>
				<li>Data de Nascimento: <s:label name="motorista.dataDeNascimentoFormatada"></s:label></li>
				<li>Data de Contrata��o: <s:label name="motorista.dataDeContratacaoFormatada"></s:label></li>
				<li>Telefones: <s:label name="motorista.telefoneResidencial"></s:label> / <s:label name="motorista.telefoneCelular"></s:label></li>
			</ul>
			<hr>
		<c:if test="${fn:length(motorista.viagens) >= 1}">
			<p style="color: #777">A tabela pode ser exportada como .csv, .xls, .xml ou .pdf.</p>
			<div align="center">
				<display:table id="tabelaViagensMotorista" name="motorista.viagens" pagesize="20" export="true" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="dataHoraPartidaFormatada" title="Data/Hora de Partida"/>
					<display:column property="dataHoraChegadaFormatada" title="Data/Hora de Chegada"/>
					<display:column property="locais.label" title="Partida/Destino/Dist�ncia(KM)"/>
					<display:column property="onibus.label" title="�nibus"/>
					<display:setProperty name="export.pdf" value="true" />
					<display:setProperty name="export.pdf.filename" value="viagens_motorista.pdf"/>
					<display:setProperty name="export.excel.filename" value="viagens_motorista.xls"/>
					<display:setProperty name="export.csv.filename" value="viagens_motorista.csv"/>
					<display:setProperty name="export.xml.filename" value="viagens_motorista.xml"/>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${fn:length(motorista.viagens) == 0}">
			<p>Sem viagens cadastradas para o motorista selecionado.</p>
		</c:if>
	</div>
</div>