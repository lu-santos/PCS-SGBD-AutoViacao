<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Lucro Bruto da Viagem</h2>
		<h3>Dados da Viagem</h3>
			<ul>
				<li>Partida/Destino/Distância(KM): <s:label name="viagem.locais.label"></s:label></li>
				<li>Data/Hora de Partida : <s:label name="viagem.dataHoraPartidaFormatada"></s:label></li>
				<li>Data/Hora de Chegada : <s:label name="viagem.dataHoraChegadaFormatada"></s:label></li>
				<li>Ônibus : <s:label name="viagem.onibus.label"></s:label></li>
				<li>Capacidade do Ônibus : <s:label name="viagem.onibus.capacidade"></s:label></li>
			</ul>
			<hr>			
		<c:if test="${not empty viagem.passageiros}">
			<p>Total de passagens vendidas: <s:property value="viagem.passageiros.size"/></p>
			<p>Valor de cada passagem: <s:property value="valorDaPassagem"/></p>
			<p>Lucro Bruto: <s:property value="lucroBruto"/></p>
		</c:if>
	
		<c:if test="${empty viagem.passageiros}">
			<p>Total de passagens vendidas: 0</p>
			<p>Valor de cada passagem: <s:property value="valorDaPassagem"/></p>
			<p>Lucro Bruto: 0</p>
		</c:if>
	</div>
</div>