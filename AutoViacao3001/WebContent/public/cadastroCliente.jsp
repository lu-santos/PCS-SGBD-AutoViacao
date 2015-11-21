<div id="global">
	<div class="conteudo">
		<form action="AdicionarCliente" style="width: 90%" method="post">
			<h2>Cadastro de Cliente</h2>
			<p style="color:#e62117">
				<s:property value="mensagem"/>
			</p>
			<div class="caixa-padrao" style="margin-left: 0px">
				<label>CPF*</label><br>
				<input type="number" maxlength="11" required name="cliente.cpf"><br>
            </div>
			<div class="caixa-padrao" style="width: 50%">
				<label>Nome*</label>
				<input type="text" maxlength="200" required name="cliente.nome">
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Data de Nascimento*</label><br>
				<input type="date" required name="cliente.dataDeNascimento"><br>
            </div>
 			<div class="caixa-padrao" style="width: 35.5%; margin-left: 0px">
				<label>Endereço*</label>
				<input type="text" maxlength="200" required name="cliente.endereco">
            </div>
			<div class="caixa-padrao" style="width: 21%">
				<label>Bairro*</label><br>
				<input type="text" maxlength="100" required name="cliente.bairro"><br>
            </div>
            <div class="caixa-padrao" style="width: 21%">
				<label>Cidade*</label><br>
				<input type="text" maxlength="50" required name="cliente.cidade"><br>
            </div>
            <div class="caixa-padrao" style="width: 12%">
				<label>Estado*</label>
				<s:select name="cliente.estado" 
					list="estados"  
					value="cliente.estado" />
            </div>
  			<div class="caixa-padrao" style="width: 21.6%; margin-left: 0px">
				<label>CEP*</label>
				<input type="number" maxlength="8" required name="cliente.cep">
            </div>			
            <div class="caixa-padrao" style="width: 22%">
				<label>Telefone Residencial*</label>
				<input type="number" maxlength="10" required name="cliente.telefoneResidencial">
    		</div>
  			 <div class="caixa-padrao" style="width: 22%">
				<label>Telefone Celular*</label>
				<input type="number" maxlength="11" required name="cliente.telefoneCelular"><br>
    		</div>
    		<div style="clear: both; width: 22%; padding-top: 15px;">
				<label>Senha*</label>
				<input type="password" maxlength="20" required name="cliente.senha">
    		</div>
   			<div align="center">
   				<input type="submit" value="Cadastrar" class="button-green" style="width: 20%">
   			</div>
		</form>
	</div>
</div>
