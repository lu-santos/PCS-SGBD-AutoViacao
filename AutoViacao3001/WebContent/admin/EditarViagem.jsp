<div id="global">
	<div class="conteudo">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem" />
		</p>
		<c:if
			test="${not empty listaDeLocais && not empty listaDeOnibus && not empty listaDeMotoristas}">
			<form action="EditarViagem" style="width: 90%" method="post">
				<h2>Editar Dados da Viagem</h2>
				<p style="color: #e62117">
					<s:property value="mensagem" />
					<s:hidden name="viagem.idViagem"/>
				</p>
				<div class="caixa-padrao" style="margin-left: 0px; width: 90%">
					<label>Local de Partida / Local de Origem / Distância (KM)*</label><br>
					<s:select listKey="idLocais" listValue="label" headerKey="-1" list="listaDeLocais" name="viagem.idLocais" />
					<br>
				</div>
				<div class="caixa-padrao" style="margin-left: 0px; width: 35%">
					<label>Data/Hora de Partida*</label><br> 
					<s:textfield type="datetime-local" disabled="false" name="viagem.dataHoraPartidaFormatoJSP"/>
				</div>
				<div class="caixa-padrao" style="width: 35%">
					<label>Data/Hora de Chegada*</label><br>
					<s:textfield type="datetime-local" disabled="false" name="viagem.dataHoraChegadaFormatoJSP"/>
				</div>
				<div class="caixa-padrao" style="width: 40%; margin-left: 0px">
					<label>Ônibus*</label><br>
					<s:select listKey="idOnibus" listValue="modelo + ' - ' + placa" headerKey="-1" list="listaDeOnibus" name="viagem.idOnibus" />
					<br>
				</div>
				<div class="caixa-padrao" style="width: 50%">
					<label>Motorista*</label><br>
					<s:select listKey="cpf" listValue="nome" list="listaDeMotoristas" name="viagem.cpf"/>
					<br>
					<br>
				</div>
				<div align="center">
					<input type="submit" value="Alterar" class="button-green"
						style="width: 20%; display: block; clear: both">
				</div>
			</form>
		</c:if>

		<c:if test="${empty listaDeLocais}">
			<p>Não é possível editar viagens, pois não há locais
				cadastrados na base de dados.</p>
		</c:if>

		<c:if test="${empty listaDeOnibus}">
			<p>Não é possível editar viagens, pois não há ônibus
				cadastrados na base de dados.</p>
		</c:if>

		<c:if test="${empty listaDeMotoristas}">
			<p>Não é possível editar viagens, pois não há motoristas
				cadastrados na base de dados.</p>
		</c:if>
	</div>
</div>
