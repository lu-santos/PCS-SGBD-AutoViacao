<div id="global">
	<div class="conteudo">
		<form action="AdicionarMotorista" style="width: 90%" method="post">
			<h2>Cadastro de Motorista</h2>
			<p style="color:#e62117">
				<s:property value="mensagem"/>
			</p>
			<div class="caixa-padrao" style="margin-left: 0px">
				<label>CPF*</label><br>
				<input type="number" maxlength="11" required name="motorista.cpf"><br>
            </div>
			<div class="caixa-padrao" style="width: 50%">
				<label>Nome*</label>
				<input type="text" maxlength="200" required name="motorista.nome">
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Data de Nascimento*</label><br>
				<input type="date" required name="motorista.dataDeNascimento"><br>
            </div>
 			<div class="caixa-padrao" style="width: 35.5%; margin-left: 0px">
				<label>Endereço*</label>
				<input type="text" maxlength="200" required name="motorista.endereco">
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Bairro*</label><br>
				<input type="text" maxlength="100" required name="motorista.bairro"><br>
            </div>
            <div class="caixa-padrao" style="width: 21%">
				<label>Cidade*</label><br>
				<input type="text" maxlength="50" required name="motorista.cidade"><br>
            </div>
			<div class="caixa-padrao" style="width: 12%">
				<label>Estado*</label>
				<select name="motorista.estado" id="estados">
    				<option value="AC">AC</option>
					<option value="AL">AL</option>
					<option value="AM">AM</option>
					<option value="AP">AP</option>
					<option value="BA">BA</option>
					<option value="CE">CE</option>
					<option value="DF">DF</option>
					<option value="ES">ES</option>
					<option value="GO">GO</option>
					<option value="MA">MA</option>
					<option value="MG">MG</option>
					<option value="MS">MS</option>
					<option value="MT">MT</option>
					<option value="PA">PA</option>
					<option value="PB">PB</option>
					<option value="PE">PE</option>
					<option value="PI">PI</option>
					<option value="PR">PR</option>
					<option value="RJ">RJ</option>
					<option value="RN">RN</option>
					<option value="RO">RO</option>
					<option value="RR">RR</option>
					<option value="RS">RS</option>
					<option value="SC">SC</option>
					<option value="SE">SE</option>
					<option value="SP">SP</option>
					<option value="TO">TO</option>
				</select>
            </div>
            <div class="caixa-padrao" style="width: 21.6%; margin-left: 0px">
				<label>CEP*</label>
				<input type="number" maxlength="8" required name="motorista.cep">
            </div>
            <div class="caixa-padrao" style="width: 20%">
				<label>Telefone Residencial*</label>
				<input type="number" maxlength="10" required name="motorista.telefoneResidencial">
    		</div>
  
  			 <div class="caixa-padrao" style="width: 20%">
				<label>Telefone Celular*</label>
				<input type="number" maxlength="11" required name="motorista.telefoneCelular"><br>
    		</div>
    		<div class="caixa-padrao" style="width: 10%">
				<label>Salario*</label>
				<input type="number" maxlength="10" required name="motorista.salario">
    		</div>
    		<div class="caixa-padrao" style="width: 15%;">
				<label>Contratação*</label><br>
				<input type="date" required name="motorista.dataDeContratacao"><br>
            </div>
   			<div align="center">
   				<input type="submit" value="Cadastrar" class="button-green" style="width: 20%; display: block; clear: both">
   			</div>
		</form>
	</div>
</div>
