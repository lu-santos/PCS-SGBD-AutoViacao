<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Clientes - Lista de Compras do Cliente <s:property value="cliente.nome"/></h2>
		<c:if test="${not empty cliente.passagens}">
			<table>
				<tr>
					<th>Número Da Poltrona</th>
					<th>Preço</th>
					<th>id Viagem</th>
				</tr>
				<s:iterator value="cliente.passagens" status="status">
					<tr style="background-color: ${status.even ? '#EEEEEE ':'#FFFFFF'}">
						<td><s:property value="numeroPoltrona"/></td>
						<td><s:property value="preco"/></td>
						<td><s:property value="idViagem"/></td>
					</tr>
				</s:iterator>
			</table>
		</c:if>
		<c:if test="${empty cliente.passagens}">
			<p>Sem compras cadastrados.</p>
		</c:if>
	</div>
</div>
