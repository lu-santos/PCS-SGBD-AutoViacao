<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Viagens - Lista de Viagens Cadastradas</h2>
		<c:if test="${not empty listaDeViagens}">
			<table style="width: 100%">
				<tr>
					<th>Data/Hora de Partida</th>
					<th>Partida/Destino/Distância(KM)</th>
					<th>Visualizar</th>
					<th>Alterar</th>
					<th>Remover</th>
					<th>Passageiros</th>
					<th>Lucro Bruto</th>
				</tr>
				<s:iterator value="listaDeViagens" status="status">
					<tr style="background-color: ${status.even ? '#EEEEEE ':'#FFFFFF'}">
						<td><s:property value="dataHoraPartidaFormatada"/></td>
						<td><s:property value="labelLocais"/></td>
						<td>
							<s:a action="VisualizarViagem">
								VISUALIZAR
								<s:param name="viagem.idViagem" value="idViagem"/>
							</s:a>
						</td>
						<td>
							<s:a action="PrepararAlteracaoViagem">
								ALTERAR
								<s:param name="viagem.idViagem" value="idViagem"/>
							</s:a>							
						</td>
						<td>
							<s:a action="ExcluirViagem">
								REMOVER
								<s:param name="viagem.idViagem" value="idViagem"/>
							</s:a>							
						</td>
						<td>
							<s:a action="PassageirosViagem">
								PASSAGEIROS
								<s:param name="viagem.idViagem" value="idViagem"/>
							</s:a>	
						</td>
						<td>
							<s:a action="LucroBrutoViagem">
								LUCRO BRUTO
								<s:param name="viagem.idViagem" value="idViagem"/>
							</s:a>	
						</td>
					</tr>
				</s:iterator>
			</table>
		</c:if>
	
		<c:if test="${empty listaDeViagens}">
			<p>Sem viagens cadastradas.</p>
		</c:if>
	</div>
</div>
