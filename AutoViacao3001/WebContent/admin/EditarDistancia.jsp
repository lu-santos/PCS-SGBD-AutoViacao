<div id="global">
	<div class="conteudo clearfix">
		<p><s:property value="mensagem"/></p>
         <form action="EditarDistancia" >
         	<h2>Alterar Distância</h2>
				<div>
					<table>
						<tr>
							<th>Origem</th>
							<th>Destino</th>
							<th>Distância em Km</th>
						</tr>
						<tr>
							<td>
								<s:textfield readOnly="true" theme="simple" name="localComDistancia.localDeOrigem.nome"/>
							</td>
							<td>
								<s:textfield readOnly="true" theme="simple" name="localComDistancia.localDeDestino.nome"/>
							</td>
							<td>
								<s:textfield type="number" theme="simple" name="localComDistancia.distancia"/>
								<s:textfield type="hidden" theme="simple" name="localComDistancia.idLocais"/>
							</td>
						</tr>
					</table>
					<center><input type="submit" value="Alterar" class="button-green" style="display: block; clear: both"></center>
	            </div>
		</form>
	</div>
</div>
