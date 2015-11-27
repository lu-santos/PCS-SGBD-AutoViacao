<div id="global">
	<div class="conteudo">
		<p style="text-align: center; margin-bottom: 20px; color: #ff0000">
			<s:property value="mensagem" />
		</p>

		<h2>Confirmação de Compra</h2>
		<p>Por favor verifique os dados da passagem antes de confirmar sua
			compra.</p>
		<form action="ConfirmarCompra" style="width: 90%" method="post">
			<ul>
				<li>Valor da Passagem: <b><s:label name="passagem.precoFormatado"></s:label></b></li>
				<li>Origem/Destino/Distância: <b><s:label name="locais.label"></s:label></b></li>
				<li>Data/Hora da Partida: <b><s:label name="viagem.dataHoraPartidaFormatada"></s:label></b></li>
				<li>Data/Hora da Chegada: <b><s:label name="viagem.dataHoraChegadaFormatada"></s:label></b></li>
				<li>Tipo do Leito do Ônibus: <b><s:label name="onibus.tipoLeito"></s:label></b></li>
				<li>Número da Poltrona Escolhida: <b><s:label name="poltrona.numero"></s:label></b></li>
				<li>Nome do(a) Cliente: <b><s:property value="#session.usuario.nome" /></b></li>
			</ul><br>
			<s:hidden name="passagem.id"></s:hidden>		
			<div align="center">
   				<input type="submit" value="Confirmar Compra" class="button-green" style="width: 20%">
   			</div>
		</form>

	</div>
</div>
