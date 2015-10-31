<div id="global">
	<div class="conteudo">
		<form action="AdicionarOnibus" style="width: 90%" method="post">
			<h2>Cadastro de Ônibus</h2>
			<p style="color:#e62117">
				<s:property value="mensagem"/>
			</p>
			<div class="caixa-padrao" style="margin-left: 0px; width: 30%">
				<label>Placa*</label><br>
				<input type="text" maxlength="50" required name="onibus.placa"><br>
            </div>
			<div class="caixa-padrao" style="width: 30%">
				<label>Modelo*</label>
				<input type="text" maxlength="50" required name="onibus.modelo">
            </div>
			<div class="caixa-padrao" style="width: 32%">
				<label>Fabricante*</label><br>
				<input type="text" required name="onibus.fabricante"><br>
            </div>
 			<div class="caixa-padrao" style="width: 13.3%; margin-left: 0px">
				<label>Ano*</label>
				<input type="number" required name="onibus.ano">
            </div>
			<div class="caixa-padrao" style="width: 13.3%">
				<label>Capacidade*</label><br>
				<input type="number" required name="onibus.capacidade"><br>
            </div>
            <div class="caixa-padrao" style="width: 20%; float: none">
				<label>Tipo*</label><br>
				<select name="onibus.tipoLeito">
					<option value="LEITO">LEITO</option>
					<option value="SEMI-LEITO">SEMI-LEITO</option>
					<option value="EXECUTIVO">EXECUTIVO</option>
					<option value="CONVENCIONAL">CONVENCIONAL</option>
				</select>
            </div>
  			<div style="width: 21.6%; float: none; display: block; margin-left: 0px; margin-top: 15px">
				<label>O ônibus possui:</label><br><br>
				<s:checkbox name="onibus.arCondicionado" value="onibus.arCondicionado" fieldValue="true"/><label>Ar Condicionado</label>
				<br>
				<s:checkbox name="onibus.banheiro" value="onibus.banheiro" fieldValue="true"/><label>Banheiro</label> 
				<br>
				<s:checkbox name="onibus.frigobar" value="onibus.frigobar" fieldValue="true"/><label>Frigobar</label> 
				<br>
				<s:checkbox name="onibus.dvd" value="onibus.dvd" fieldValue="true"/><label>Dvd</label>
    		</div>
   			<center><input type="submit" value="Cadastrar" class="button-green" style="width: 20%; display: block; clear: both"></center>
		</form>
	</div>
</div>
