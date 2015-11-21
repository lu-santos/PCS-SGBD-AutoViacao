<div id="global">
	<div class="conteudo clearfix">
		<form class="clearfix" style="width: 90%" method="post">
			<h2>Consulta dos dados da Conta</h2>
			<p style="color:#e62117">
				<s:property value="mensagem"/>
			</p>
			<div class="caixa-padrao" style="margin-left: 0px">
				<label>CPF*</label><br>
				<s:textfield disabled="true" name="cliente.cpf"/>
            </div>
			<div class="caixa-padrao" style="width: 50%">
				<label>Nome*</label>
				<s:textfield disabled="true" name="cliente.nome"/>
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Data de Nascimento*</label><br>
				<s:textfield type="date" disabled="true" name="cliente.dataDeNascimento"/><br>
            </div>
 			<div class="caixa-padrao" style="width: 35.5%; margin-left: 0px">
				<label>Endereço*</label>
				<s:textfield disabled="true" name="cliente.endereco"/>
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Bairro*</label><br>
				<s:textfield disabled="true" name="cliente.bairro"/><br>
            </div>
            <div class="caixa-padrao" style="width: 21%">
				<label>Cidade*</label><br>
				<s:textfield disabled="true" name="cliente.cidade"/><br>
            </div>
			<div class="caixa-padrao" style="width: 12%">
				<label>Estado*</label>
				<s:textfield disabled="true" name="cliente.estado"/>
            </div>
            <div class="caixa-padrao" style="width: 21.6%; margin-left: 0px">
				<label>CEP*</label>
				<s:textfield disabled="true" name="cliente.cep"/>
            </div>
            <div class="caixa-padrao" style="width: 22%">
				<label>Telefone Residencial*</label>
				<s:textfield disabled="true" name="cliente.telefoneResidencial"/>
    		</div>
  			 <div class="caixa-padrao" style="width: 22%">
				<label>Telefone Celular*</label>
				<s:textfield disabled="true" name="cliente.telefoneCelular"/><br>
    		</div>
    		<div style="clear: both; width: 22%; padding-top: 15px;">
				<label>Senha*</label>
				<s:textfield disabled="true" name="cliente.senha"/>
    		</div>
   		</form>
	</div>
</div>
