<div id="global" style="height: 100%">
	<div class="conteudo">
		<form class="clearfix" style="width: 90%" method="post">
			<h2>Consulta dos Dados da Viagem</h2>
			<p style="color: #e62117">
				<s:property value="mensagem" />
			</p>
			<div class="caixa-padrao" style="margin-left: 0px; width: 90%">
				<label>Local de Partida / Local de Origem / Distância (KM)</label><br>
				<s:property value="viagem.locais.label" />
				<br>
			</div>
			<div class="caixa-padrao" style="margin-left: 0px; width: 35%">
				<label>Data/Hora de Partida</label><br>
				<s:property value="viagem.dataHoraPartidaFormatada" />
			</div>
			<div class="caixa-padrao" style="width: 35%">
				<label>Data/Hora de Chegada</label><br>
				<s:property value="viagem.dataHoraChegadaFormatada" />
				<br>
			</div>
			<div class="caixa-padrao" style="width: 35%; margin-left: 0px">
				<label>Ônibus</label><br>
				<s:property value="viagem.onibus.label" />
			</div>
			<div class="caixa-padrao" style="width: 50%">
				<label>Motorista</label><br>
				<s:property value="viagem.motorista.nome" />
				<br>
			</div>
		</form>
	</div>
</div>