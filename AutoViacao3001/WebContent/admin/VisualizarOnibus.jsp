<div id="global">
	<div class="conteudo">
		<form style="width: 90%" method="post">
			<h2>Consulta dos Dados do �nibus</h2>
			<p style="color:#e62117">
				<s:property value="mensagem"/>
			</p>
			<div class="caixa-padrao" style="margin-left: 0px; width: 30%">
				<label>Placa</label><br>
				<s:textfield disabled="true" name="onibus.placa" /><br>
            </div>
			<div class="caixa-padrao" style="width: 30%">
				<label>Modelo</label>
				<s:textfield disabled="true" name="onibus.modelo" />
            </div>
			<div class="caixa-padrao" style="width: 32%">
				<label>Fabricante</label><br>
				<s:textfield disabled="true" name="onibus.fabricante" /><br>
            </div>
 			<div class="caixa-padrao" style="width: 13.3%; margin-left: 0px">
				<label>Ano</label>
				<s:textfield disabled="true" name="onibus.ano" />
            </div>
			<div class="caixa-padrao" style="width: 13.3%">
				<label>Capacidade</label><br>
				<s:textfield disabled="true" name="onibus.capacidade" /><br>
            </div>
            <div class="caixa-padrao" style="width: 20%; float: none">
				<label>Tipo</label><br>
				<s:textfield disabled="true" name="onibus.tipoLeito" />
            </div>
  			<div style="width: 21.6%; float: none; display: block; margin-left: 0px; margin-top: 15px">
				<label>O �nibus possui:</label><br><br>
				<s:checkbox disabled="true" name="onibus.arCondicionado" value="onibus.arCondicionado" fieldValue="true"/><label>Ar Condicionado</label>
				<br>
				<s:checkbox disabled="true" name="onibus.banheiro" value="onibus.banheiro" fieldValue="true"/><label>Banheiro</label> 
				<br>
				<s:checkbox disabled="true" name="onibus.frigobar" value="onibus.frigobar" fieldValue="true"/><label>Frigobar</label> 
				<br>
				<s:checkbox disabled="true" name="onibus.dvd" value="onibus.dvd" fieldValue="true"/><label>DVD</label>
    		</div>
		</form>
	</div>
</div>
