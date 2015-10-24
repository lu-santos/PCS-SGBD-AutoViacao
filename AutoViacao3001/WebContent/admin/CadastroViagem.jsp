<div id="global">
	<div class="conteudo">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<c:if test="${not empty listaDeLocais && not empty listaDeOnibus && not empty listaDeMotoristas}">
			<form action="AdicionarViagem" style="width: 90%" method="post">
				<h2>Cadastro de Viagem</h2>
				<p style="color:#e62117">
					<s:property value="mensagem"/>
				</p>
				<div class="caixa-padrao" style="margin-left: 0px; width: 45%">
					<label>Local de Partida*</label><br>
					<s:select listKey="id" listValue="nome" headerKey="-1" list="listaDeLocais" name="viagem.idLocalPartida"/><br>
	            </div>
				<div class="caixa-padrao" style="width: 45%">
					<label>Local de Chegada*</label><br>
					<s:select listKey="id" listValue="nome" headerKey="-1" list="listaDeLocais" name="viagem.idLocalDestino"/><br><br>
	            </div>
				<div class="caixa-padrao" style="margin-left: 0px; width: 25%">
					<label>Data/Hora de Partida*</label><br>
					<input type="datetime-local" required name="viagem.dataHoraPartida">
				</div>
				<div class="caixa-padrao" style="width: 25%">
					<label>Data/Hora de Chegada*</label><br>
					<input type="datetime-local" required name="viagem.dataHoraChegada">
	            </div>
	            <div class="caixa-padrao" style="width: 25%">
					<label>Distância (em KM)*</label><br>
					<input type="number" width=10 required name="viagem.distancia"><br>
	    		</div>  
	  			 <div class="caixa-padrao" style="width: 40%; margin-left: 0px">
					<label>Ônibus*</label><br>
					<s:select listKey="idOnibus" listValue="modelo + ' - ' + placa" headerKey="-1" list="listaDeOnibus" name="viagem.idOnibus"/><br>
	    		</div>
	    		<div class="caixa-padrao" style="width: 50%">
					<label>Motorista*</label><br>
					<s:select listKey="cpf" listValue="nome" headerKey="-1" list="listaDeMotoristas" name="viagem.cpfMotorista"/><br>
	    		</div>
	   			<div align="center">
	   				<input type="submit" value="Cadastrar" class="button-green" style="width: 20%">
	   			</div>
			</form>
		</c:if>
		
		<c:if test="${empty listaDeLocais}">
			<p>Não é possível cadastrar viagens, pois não há locais cadastrados na base de dados.</p>
		</c:if>
		
		<c:if test="${empty listaDeOnibus}">
			<p>Não é possível cadastrar viagens, pois não há ônibus cadastrados na base de dados.</p>
		</c:if>
		
		<c:if test="${empty listaDeMotoristas}">
			<p>Não é possível cadastrar viagens, pois não há motoristas cadastrados na base de dados.</p>
		</c:if>
	</div>
</div>
