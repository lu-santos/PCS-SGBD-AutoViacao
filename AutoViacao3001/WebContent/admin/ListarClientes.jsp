<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Clientes - Lista de Clientes Cadastrados</h2>
		<c:if test="${not empty clientes}">
			<table>
				<tr>
					<th>Cpf</th>
					<th>Nome</th>
					<th>Data de Nascimento</th>
					<th>Visualizar</th>
					<th>Alterar</th>
					<th>Remover</th>
					<th>Histórico de Compras</th>
				</tr>
				<s:iterator value="clientes" status="status">
					<tr style="background-color: ${status.even ? '#EEEEEE ':'#FFFFFF'}">
						<td><s:property value="cpf"/></td>
						<td><s:property value="nome"/></td>
						<td><s:property value="dataDeNascimento"/></td>
						<td>
							<s:a action="VisualizarCliente">
								VISUALIZAR
								<s:param name="cliente.cpf" value="cpf"/>
							</s:a>
						</td>
						<td>
							<s:a action="PrepararAlteracaoCliente">
								ALTERAR
								<s:param name="cliente.cpf" value="cpf"/>
							</s:a>
						</td>
						<td>
							<s:a action="ExcluirCliente">
								REMOVER
								<s:param name="cliente.cpf" value="cpf"/>
							</s:a>
						</td>
						<td>
							<s:a action="HistoricoDeCompra">
								HISTÓRICO
								<s:param name="cliente.cpf" value="cpf"/>
							</s:a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</c:if>
	
		<c:if test="${empty clientes}">
			<p>Sem clientes cadastrados.</p>
		</c:if>
	</div>
</div>
