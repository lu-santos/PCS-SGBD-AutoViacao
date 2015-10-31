<div id="global">
	<div class="conteudo">
		<form action="AdicionarMotorista" style="width: 90%" method="post">
			<h2>Consulta dos Dados do Motorista</h2>
			<p style="color:#e62117">
				<s:property value="mensagem"/>
			</p>
			<div class="caixa-padrao" style="margin-left: 0px">
				<label>CPF*</label><br>
				<s:textfield disabled="true" name="motorista.cpf"/><br>
            </div>
			<div class="caixa-padrao" style="width: 50%">
				<label>Nome*</label>
				<s:textfield disabled="true" name="motorista.nome"/>
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Data de Nascimento*</label><br>
				<s:textfield type="date" disabled="true" name="motorista.dataDeNascimento"/><br>
            </div>
 			<div class="caixa-padrao" style="width: 35.5%; margin-left: 0px">
				<label>Endereço*</label>
				<s:textfield disabled="true" name="motorista.endereco"/>
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Bairro*</label><br>
				<s:textfield disabled="true" name="motorista.bairro"/><br>
            </div>
  			<div class="caixa-padrao" style="width: 21.6%">
				<label>CEP*</label>
				<s:textfield disabled="true" name="motorista.cep"/>
            </div>
			<div class="caixa-padrao" style="width: 12%">
				<label>Estado*</label>
				<s:textfield disabled="true" name="motorista.estado" id="estados"/>
            </div>
            <div class="caixa-padrao" style="width: 22%; margin-left: 0px">
				<label>Telefone Residencial*</label>
				<s:textfield disabled="true" name="motorista.telefoneResidencial"/>
    		</div>
  			 <div class="caixa-padrao" style="width: 22%">
				<label>Telefone Celular*</label>
				<s:textfield disabled="true" name="motorista.telefoneCelular"/><br>
    		</div>
    		<div class="caixa-padrao" style="width: 21%">
				<label>Salario*</label>
				<s:textfield disabled="true" name="motorista.salario"/>
    		</div>
    		<div class="caixa-padrao" style="width: 21%; margin-left: 0px; float: none">
				<label>Data de Contratação*</label><br>
				<s:textfield type="date" disabled="true" name="motorista.dataDeContratacao"/><br>
            </div>
   		</form>
	</div>
</div>
