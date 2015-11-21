<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<section class="clearfix" style="margin: 0 auto; width: 90%; text-align: center">
			<p style="text-align: center; margin-bottom: 40px; color: #007f00">
				<s:property value="mensagem"/>
			</p>
			<img src="${pageContext.request.contextPath}/images/icon_gerenciar_passagem.png">
			<h2>Gerenciar Passagens</h2>
			<s:a action="ListarViagensParaGerarPassagens">
				Gerar Passagens
			</s:a><br>
			<s:a action="ListarPassagens">
				Listar Passagens
			</s:a><br>
			<s:a action="ConsultarEpocaDoAnoComMaisVendas">
				Consultar Época do Ano com mais Venda de Passagens
			</s:a><br>
		</section>
	</div>
</div>
