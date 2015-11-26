<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Compra de Passagens - Escolha sua Poltrona</h2>
		<c:if test="${not empty fileiras}">
			<table id="onibus">
				<tr>
					<th><img src="${pageContext.request.contextPath}/images/poltrona_motorista.png"></th>
					<th>_____</th>
					<th>_____</th>
					<th>_____</th>
					<th>_____</th>
				</tr>
				<s:iterator value="fileiras" status="status">
					<tr>
						<td>
							<s:if test="%{passagemPoltronaJanelaLadoEsquerdo.cliente.cpf==null}">
								<s:a action="ComprarPassagem">
									<s:param name="fileira.passagemPoltronaJanelaLadoEsquerdo.id" value="passagemPoltronaJanelaLadoEsquerdo.id"/>
									<img src="${pageContext.request.contextPath}/images/poltrona_disponivel.png">
								</s:a>
							</s:if>								
							<s:else>
								<img src="${pageContext.request.contextPath}/images/poltrona_indisponivel.png">
							</s:else>
						</td>
						<td>
							<s:if test="%{passagemPoltronaCorredorLadoEsquerdo.cliente.cpf==null}">
								<s:a action="ComprarPassagem">
									<s:param name="fileira.passagemPoltronaCorredorLadoEsquerdo.id" value="passagemPoltronaCorredorLadoEsquerdo.id"/>
									<img src="${pageContext.request.contextPath}/images/poltrona_disponivel.png">
								</s:a>
							</s:if>								
							<s:else>
								<img src="${pageContext.request.contextPath}/images/poltrona_indisponivel.png">
							</s:else>
						</td>
						<td></td>
						<td>
							<s:if test="%{passagemPoltronaCorredorLadoDireito.cliente.cpf==null}">
								<s:a action="ComprarPassagem">
									<s:param name="fileira.passagemPoltronaCorredorLadoDireito.id" value="passagemPoltronaCorredorLadoDireito.id"/>
									<img src="${pageContext.request.contextPath}/images/poltrona_disponivel.png">
								</s:a>
							</s:if>								
							<s:else>
								<img src="${pageContext.request.contextPath}/images/poltrona_indisponivel.png">
							</s:else>
						</td>
						<td>
							<s:if test="%{passagemPoltronaJanelaLadoDireito.cliente.cpf==null}">
								<s:a action="ComprarPassagem">
									<s:param name="fileira.passagemPoltronaJanelaLadoDireito.id" value="passagemPoltronaJanelaLadoDireito.id"/>
									<img src="${pageContext.request.contextPath}/images/poltrona_disponivel.png">
								</s:a>
							</s:if>								
							<s:else>
								<img src="${pageContext.request.contextPath}/images/poltrona_indisponivel.png">
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</table>
		</c:if>
	
		<c:if test="${empty fileiras}">
			<p>Sem poltronas disponíveis.</p>
		</c:if>
	</div>
</div>
