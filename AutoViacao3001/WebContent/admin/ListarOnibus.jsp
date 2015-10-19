<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Ônibus - Lista de Ônibus Cadastrados</h2>
		<c:if test="${not empty listaDeOnibus}">
			<table>
				<tr>
					<th>Placa</th>
					<th>Modelo</th>
					<th>Fabricante</th>
					<th>Visualizar</th>
					<th>Alterar</th>
					<th>Remover</th>
					<th>Viagens</th>
				</tr>
				<s:iterator value="listaDeOnibus" status="status">
					<tr style="background-color: ${status.even ? '#EEEEEE ':'#FFFFFF'}">
						<td><s:property value="placa"/></td>
						<td><s:property value="modelo"/></td>
						<td><s:property value="fabricante"/></td>
						<td>
							<s:a action="VisualizarOnibus">
								VISUALIZAR
								<s:param name="onibus.idOnibus" value="idOnibus"/>
							</s:a>
						</td>
						<td>
							<s:a action="PrepararAlteracaoOnibus">
								ALTERAR
								<s:param name="onibus.idOnibus" value="idOnibus"/>
							</s:a>
						</td>
						<td>
							<s:a action="ExcluirOnibus">
								REMOVER
								<s:param name="onibus.idOnibus" value="cpf"/>
							</s:a>
						</td>
						<td>
							<s:a action="ViagensDoOnibus">
								VIAGENS
								<s:param name="onibus.idOnibus" value="idOnibus"/>
							</s:a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</c:if>
	
		<c:if test="${empty listaDeOnibus}">
			<p>Sem ônibus cadastrados.</p>
		</c:if>
	</div>
</div>
