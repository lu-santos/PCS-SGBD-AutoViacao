<div id="global">
	<div class="conteudo">
		<form action="EditarMotorista" validate="true" style="width: 90%" method="post">
			<h2>Editar dados de Motorista</h2>
			<p style="color:#e62117">
				<s:property value="mensagem"/>
			</p>
			<div class="caixa-padrao" style="margin-left: 0px">
				<label>CPF*</label><br>
				<s:textfield readOnly="true" name="motorista.cpf"/>
            </div>
			<div class="caixa-padrao" style="width: 50%">
				<label>Nome*</label>
				<s:textfield disabled="false" maxlength="200" name="motorista.nome"/>
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Data de Nascimento*</label><br>
				<s:textfield type="date" disabled="false" name="motorista.dataDeNascimento"/><br>
            </div>
 			<div class="caixa-padrao" style="width: 35.5%; margin-left: 0px">
				<label>Endere�o*</label>
				<s:textfield disabled="false" maxlength="200" name="motorista.endereco"/>
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Bairro*</label><br>
				<s:textfield disabled="false" maxlength="100" name="motorista.bairro"/><br>
            </div>
  			<div class="caixa-padrao" style="width: 21.6%">
				<label>CEP*</label>
				<s:textfield disabled="false" type="number" maxlength="8" name="motorista.cep"/>
            </div>
			<div class="caixa-padrao" style="width: 12%">
				<label>Estado*</label>
				<s:select name="motorista.estado" 
					list="estados"  
					value="motorista.estado" />
            </div>
            <div class="caixa-padrao" style="width: 22%; margin-left: 0px">
				<label>Telefone Residencial*</label>
				<s:textfield disabled="false" type="number" maxlength="10" name="motorista.telefoneResidencial"/>
    		</div>
  			 <div class="caixa-padrao" style="width: 22%">
				<label>Telefone Celular*</label>
				<s:textfield disabled="false" type="number" maxlength="11" name="motorista.telefoneCelular"/><br>
    		</div>
    		<div class="caixa-padrao" style="width: 21%">
				<label>Salario*</label>
				<s:textfield disabled="false" type="number" maxlength="10" name="motorista.salario"/>
    		</div>
    		<div class="caixa-padrao" style="width: 21%; margin-left: 0px">
				<label>Data de Contrata��o*</label><br>
				<s:textfield disabled="false" type="date" name="motorista.dataDeContratacao"/><br>
            </div>
    		<center><input type="submit" value="Alterar" class="button-green" style="width: 20%; display: block; clear: both"></center>
   		</form>
	</div>
</div>
