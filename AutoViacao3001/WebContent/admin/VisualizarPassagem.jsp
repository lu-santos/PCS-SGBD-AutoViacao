<div id="global">
	<div class="conteudo">
		<form action="AdicionarMotorista" style="width: 90%" method="post">
			<h2>Consulta dos Dados da Passagem</h2>
			<p style="color: #e62117">
				<s:property value="mensagem" />
			</p>
			<div class="caixa-padrao" style="margin-left: 0px; width: 20%">
				<label>ID</label><br>
				<s:textfield disabled="true" name="passagem.id" />
			</div>
			<div class="caixa-padrao" style="width: 10%">
				<label>Poltrona</label>
				<s:textfield disabled="true" name="passagem.poltrona.numero" />
			</div>
			<div class="caixa-padrao" style="width: 20%">
				<label>Preço</label><br>
				<s:textfield disabled="true" name="passagem.precoFormatado" />
			</div>
			<div class="caixa-padrao" style="width: 30%">
				<label>Data/Hora Partida</label><br>
				<s:textfield disabled="true"
					name="passagem.viagem.dataHoraPartidaFormatada" />
				<br>
			</div>
			<div class="caixa-padrao" style="width: 80%; margin-left: 0px">
				<label>Viagem</label>
				<s:textfield disabled="true" name="passagem.viagem.locais.label" />
				<br>
			</div>
			<div class="caixa-padrao" style="width: 65%; margin-left: 0px">
				<label>Nome do(a) Cliente</label>
				<s:if test="%{passagem.cliente.nome!=null}">
					<s:textfield disabled="true" name="passagem.cliente.nome" />
				</s:if>
				<s:else>
					<s:textfield disabled="true" value="Passagem sem comprador" />
				</s:else>
			</div>
			<div class="caixa-padrao" style="width: 30%">
				<label>CPF do(a) Cliente</label><br>
				<s:if test="%{passagem.cliente.cpf!=null}">
					<s:textfield disabled="true" name="passagem.cliente.cpf" />
				</s:if>
				<s:else>
					<s:textfield disabled="true" value="Passagem sem comprador" />
				</s:else>
			</div>
			<br><br><br><br><br><br><br><br><br><br><br><br>
		</form>
	</div>
</div>
