<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Motoristas - Lista de Motoristas Cadastrados</h2>
		<c:if test="${not empty funcionarios}">
			<table>
				<tr>
					<th>Cpf</th>
					<th>Nome</th>
					<th>Data de Nascimento</th>
					<th>Visualizar</th>
					<th>Alterar</th>
					<th>Remover</th>
					<th>Viagens Realizadas</th>
				</tr>
				<s:iterator value="funcionarios" status="status">
					<tr style="background-color: ${status.even ? '#EEEEEE ':'#FFFFFF'}">
						<td><s:property value="cpf"/></td>
						<td><s:property value="nome"/></td>
						<td><s:property value="dataDeNascimento"/></td>
						<td>
							<s:a action="VisualizarFuncionario">
								VISUALIZAR
								<s:param name="funcionario.cpf" value="cpf"/>
							</s:a>
						</td>
						<td>
							<s:a action="PrepararAlteracaoFuncionario">
								ALTERAR
								<s:param name="funcionario.cpf" value="cpf"/>
							</s:a>
						</td>
						<td>
							<s:a action="ExcluirFuncionario">
								REMOVER
								<s:param name="funcionario.cpf" value="cpf"/>
							</s:a>
						</td>
						<td>
							<s:a action="HistoricoDeViagens">
								VIAGENS
								<s:param name="funcionario.cpf" value="cpf"/>
							</s:a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</c:if>
	
		<c:if test="${empty funcionarios}">
			<p>Sem motoristas cadastrados.</p>
		</c:if>
	</div>
</div>