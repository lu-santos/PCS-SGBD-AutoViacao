<div id="global">
	<div class="conteudo">
		<form action="EditarCliente" validate="true" style="width: 90%" method="post">
			<h2>Editar dados da Conta</h2>
			<p style="color:#e62117">
				<s:property value="mensagem"/>
			</p>
			<div class="caixa-padrao" style="margin-left: 0px">
				<label>CPF*</label><br>
				<s:textfield readOnly="true" name="cliente.cpf"/>
            </div>
			<div class="caixa-padrao" style="width: 50%">
				<label>Nome*</label>
				<s:textfield disabled="false" maxlength="200" name="cliente.nome"/>
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Data de Nascimento*</label><br>
				<s:textfield type="date" disabled="false" name="cliente.dataDeNascimento"/><br>
            </div>
 			<div class="caixa-padrao" style="width: 35.5%; margin-left: 0px">
				<label>Endereço*</label>
				<s:textfield disabled="false" maxlength="200" name="cliente.endereco"/>
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Bairro*</label><br>
				<s:textfield disabled="false" maxlength="100" name="cliente.bairro"/><br>
            </div>
  			<div class="caixa-padrao" style="width: 21.6%">
				<label>CEP*</label>
				<s:textfield disabled="false" type="number" maxlength="8" name="cliente.cep"/>
            </div>
			<div class="caixa-padrao" style="width: 12%">
				<label>Estado*</label>
				<s:select name="cliente.estado" 
					list="estados"  
					value="cliente.estado" />
            </div>
            <div class="caixa-padrao" style="width: 22%; margin-left: 0px">
				<label>Telefone Residencial*</label>
				<s:textfield disabled="false" type="number" maxlength="10" name="cliente.telefoneResidencial"/>
    		</div>
  			 <div class="caixa-padrao" style="width: 22%">
				<label>Telefone Celular*</label>
				<s:textfield disabled="false" type="number" maxlength="11" name="cliente.telefoneCelular"/><br>
    		</div>
    		<div style="clear: both; width: 22%; padding-top: 15px;">
				<label>Senha*</label>
				<s:textfield disabled="false" maxlength="20" name="cliente.senha"/>
    		</div>
    		<center><input type="submit" value="Alterar" class="button-green" style="width: 20%"></center>
   		</form>
	</div>
</div>
