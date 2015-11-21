<div id="global">
	<div class="conteudo">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem" />
		</p>
		<form action="GerarPassagens" style="width: 90%" method="post">
			<h2>Gerar Passagens para a Viagem</h2>
			<p style="color: #e62117">
				<s:property value="mensagem" />
				<s:hidden name="viagem.onibus.idOnibus"/>
				<s:hidden name="viagem.idViagem"/>
			</p>
			<div class="caixa-padrao" style="margin-left: 0px; width: 90%">
				<label>Local de Partida / Local de Origem / Distância (KM)</label><br>
				<s:textfield disabled="true" name="viagem.locais.label" />
				<br>
			</div>
			<div class="caixa-padrao" style="margin-left: 0px; width: 35%">
				<label>Data/Hora de Partida</label><br>
				<s:textfield disabled="true" name="viagem.dataHoraPartidaFormatada" />
			</div>
			<div class="caixa-padrao" style="width: 35%">
				<label>Data/Hora de Chegada</label><br>
				<s:textfield disabled="true" name="viagem.dataHoraChegadaFormatada" />
			</div>
			<div class="caixa-padrao" style="width: 40%; margin-left: 0px">
				<label>Ônibus</label><br>
				<s:textfield disabled="true" name="viagem.onibus.label" />
				<br>
			</div>
			<div class="caixa-padrao" style="width: 30%">
				<label>Preço das Passagens (R$)*</label><br>
				<input type="text" required name="passagem.preco" style="width: 50%">
				<br>
				<br>
			</div>
			<div align="center">
				<input type="submit" value="Gerar" class="button-green"
					style="width: 20%; display: block; clear: both">
			</div>
		</form>
	</div>
</div>
