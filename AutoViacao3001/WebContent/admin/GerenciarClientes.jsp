<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<section class="clearfix" style="margin: 0 auto; width: 90%; text-align: center">
			<p style="text-align: center; margin-bottom: 40px; color: #007f00">
				<s:property value="mensagem"/>
			</p>
			<img src="${pageContext.request.contextPath}/images/icon_gerenciar_cliente.png">
			<h2>Gerenciar Clientes</h2>
			<s:a action="PaginaDeCadastrarCliente">
				Cadastrar Cliente
			</s:a><br>
			<s:a action="ListarClientes">
				Listar Clientes
			</s:a>
		</section>
	</div>
</div>
