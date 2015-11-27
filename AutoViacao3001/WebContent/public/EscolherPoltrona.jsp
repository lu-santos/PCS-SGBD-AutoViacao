<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem" />
		</p>
		<h2 style="text-align: center">Compra de Passagens - Escolha sua Poltrona</h2>

		<c:if test="${not empty fileiras}">
			<div style="float:left; width: 50%">
				<h3>Legenda</h3>
				<ul style="list-style-type: none">
					<li><img src="${pageContext.request.contextPath}/images/poltrona_disponivel.png"> Poltrona disponível</li>
					<li><img src="${pageContext.request.contextPath}/images/poltrona_indisponivel.png"> Poltrona indisponível</li>
				</ul><br>
				<h3>Viagem</h3>
				<ul>
					<li>Partida/Destino/Distância(KM): <b><s:label name="viagem.locais.label"></s:label></b></li>
					<li>Data/Hora de Partida: <b><s:label name="viagem.dataHoraPartidaFormatada"></s:label></b></li>
					<li>Data/Hora de Chegada: <b><s:label name="viagem.dataHoraChegadaFormatada"></s:label></b></li>
				</ul><br>
				<h3>Ônibus</h3>
				<ul>
					<li>Tipo do Leito: <b><s:label name="onibus.tipoLeito"></s:label></b></li>
					<li>Possui Ar-condicionado? <s:if test="%{onibus.arCondicionado==true}"><b>Sim</b></s:if><s:else><b>Não</b></s:else></li>
					<li>Possui Banheiro? <s:if test="%{onibus.banheiro}"><b>Sim</b></s:if><s:else><b>Não</b></s:else></li>
					<li>Possui DVD? <s:if test="%{onibus.dvd}"><b>Sim</b></s:if><s:else><b>Não</b></s:else></li>
					<li>Possui Frigobar? <s:if test="%{onibus.frigobar}"><b>Sim</b></s:if><s:else><b>Não</b></s:else></li>
				</ul><br>
			</div>
			<div style="float: right; width: 50%">
				<div align="center">
					<table id="onibus">
						<tr>
							<th><img
								src="${pageContext.request.contextPath}/images/poltrona_motorista.png"></th>
							<th>_____</th>
							<th>_____</th>
							<th>_____</th>
							<th>_____</th>
						</tr>
						<s:iterator value="fileiras" status="status">
							<tr>
								<td><s:if
										test="%{passagemPoltronaJanelaLadoEsquerdo!=null}">
										<s:if
											test="%{passagemPoltronaJanelaLadoEsquerdo.cliente.cpf==null}">
											<s:a action="ComprarPassagem">
												<s:param name="passagem.id"
													value="passagemPoltronaJanelaLadoEsquerdo.id" />
												<img
													src="${pageContext.request.contextPath}/images/poltrona_disponivel.png">
											</s:a>
										</s:if>
										<s:else>
											<img
												src="${pageContext.request.contextPath}/images/poltrona_indisponivel.png">
										</s:else>
									</s:if></td>
								<td><s:if
										test="%{passagemPoltronaCorredorLadoEsquerdo!=null}">
										<s:if
											test="%{passagemPoltronaCorredorLadoEsquerdo.cliente.cpf==null}">
											<s:a action="ComprarPassagem">
												<s:param name="passagem.id"
													value="passagemPoltronaCorredorLadoEsquerdo.id" />
												<img
													src="${pageContext.request.contextPath}/images/poltrona_disponivel.png">
											</s:a>
										</s:if>
										<s:else>
											<img
												src="${pageContext.request.contextPath}/images/poltrona_indisponivel.png">
										</s:else>
									</s:if></td>
								<td></td>
								<td><s:if
										test="%{passagemPoltronaCorredorLadoDireito!=null}">
										<s:if
											test="%{passagemPoltronaCorredorLadoDireito.cliente.cpf==null}">
											<s:a action="ComprarPassagem">
												<s:param name="passagem.id"
													value="passagemPoltronaCorredorLadoDireito.id" />
												<img
													src="${pageContext.request.contextPath}/images/poltrona_disponivel.png">
											</s:a>
										</s:if>
										<s:else>
											<img
												src="${pageContext.request.contextPath}/images/poltrona_indisponivel.png">
										</s:else>
									</s:if></td>
								<td><s:if test="%{passagemPoltronaJanelaLadoDireito!=null}">
										<s:if
											test="%{passagemPoltronaJanelaLadoDireito.cliente.cpf==null}">
											<s:a action="ComprarPassagem">
												<s:param name="passagem.id"
													value="passagemPoltronaJanelaLadoDireito.id" />
												<img
													src="${pageContext.request.contextPath}/images/poltrona_disponivel.png">
											</s:a>
										</s:if>
										<s:else>
											<img
												src="${pageContext.request.contextPath}/images/poltrona_indisponivel.png">
										</s:else>
									</s:if></td>
							</tr>
						</s:iterator>
					</table>
				</div>

			</div>
		</c:if>
		<c:if test="${empty fileiras}">
			<p>Sem poltronas disponíveis.</p>
		</c:if>
	</div>
</div>
