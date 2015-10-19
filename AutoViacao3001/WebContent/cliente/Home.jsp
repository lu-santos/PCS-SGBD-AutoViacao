<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<c:if test="${mensagem == 'Exclusão realizada com sucesso'}">
			<p style="text-align: center">Conta Encerrada!</p>
		</c:if>
		<c:if test="${mensagem != 'Exclusão realizada com sucesso'}">
			<section class="clearfix" style="margin: 0 auto; width: 90%;">
			<p style="text-align: center; margin-bottom: 40px; color: #007f00">
				<s:property value="mensagem"/>
			</p>
			<div class="icones-home">
				<s:a action="PrepararAlteracaoCliente">
					<img src="../images/icon_editar.png">
					<p>Editar Conta</p>
					<s:param name="cliente.cpf" value="#session.usuario.cpf"/>
				</s:a>
			</div>
			<div class="icones-home">
				<s:a action="VisualizarCliente">
					<img src="${pageContext.request.contextPath}/images/icon_visualizar.png">
					<p>Visualizar Conta</p>
					<s:param name="cliente.cpf" value="#session.usuario.cpf"/>
				</s:a>
			</div>
			<div class="icones-home">
				<s:a action="ExcluirCliente">
					<img src="${pageContext.request.contextPath}/images/icon_remover.png">
					<p>Remover Conta</p>
					<s:param name="cliente.cpf" value="#session.usuario.cpf"/>
				</s:a>
			</div>
		</section>
		</c:if>
	</div>
</div>
