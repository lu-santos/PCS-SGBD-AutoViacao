<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<section class="clearfix" style="margin: 0 auto; width: 90%; text-align: center">
			<p style="text-align: center; margin-bottom: 40px; color: #007f00">
				<s:property value="mensagem"/>
			</p>
			<img src="${pageContext.request.contextPath}/images/icon_gerenciar_viagem.png">
			<h2>Gerenciar Viagens</h2>
			<s:a action="ObterListas">
				Cadastrar Viagens
			</s:a><br>
			<s:a action="ListarViagens">
				Listar Viagens
			</s:a>
		</section>
	</div>
</div>
