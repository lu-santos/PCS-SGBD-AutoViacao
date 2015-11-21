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
  			<div class="caixa-padrao" style="width: 21.6%">
				<label>CEP*</label>
				<input type="number" maxlength="8" required name="motorista.cep">
            </div>
			<div class="caixa-padrao" style="width: 12%">
				<label>Estado*</label>
				<s:select name="motorista.estado" 
					list="estados"  
					value="motorista.estado" />
            </div>
            <div class="caixa-padrao" style="width: 25%; margin-left: 0px">
				<label>Cidade*</label>
				<input type="text" maxlength="50" name="motorista.cidade">
    		</div>
            <div class="caixa-padrao" style="width: 21%">
				<label>Telefone Residencial*</label>
				<input type="number" maxlength="10" required name="motorista.telefoneResidencial">
    		</div>
  			 <div class="caixa-padrao" style="width: 21%">
				<label>Telefone Celular*</label>
				<input type="number" maxlength="11" required name="motorista.telefoneCelular"><br>
    		</div>
    		<div class="caixa-padrao" style="width: 22%">
				<label>Salario*</label>
				<input type="number" maxlength="10" required name="motorista.salario">
    		</div>
    		<div class="caixa-padrao" style="margin-left: 0px; width: 20%;">
				<label>Data de Contratação*</label><br>
				<input type="date" required name="motorista.dataDeContratacao"><br>
            </div>
   			<center><input type="submit" value="Cadastrar" class="button-green" style="width: 20%; display: block; clear: both"></center>
		</form>
	</div>
</div>
