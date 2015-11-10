<div id="global">
	<div class="conteudo clearfix">
		<p><s:property value="mensagem"/></p>
		<form action="AdicionarLocal" style="width: 30%; float: left" method="post">
			<h2>Cadastro de Local</h2>
			<div>
				<label>Nome*</label><br>
				<s:textfield name="local.nome" />
				<s:set var="idDoLocalDeOrigem" value="local.id"/>
            </div>
            <center><input type="submit" value="Cadastrar" class="button-green" style="display: block; clear: both"></center>
         </form>
         <form action="AdicionarDistancia" style="width: 50%; float: right;">
         	<h2>Cadastro de Distância</h2>
            <c:if test="${fn:length(listaDeLocaisSemDistancia) >= 1}">            
				<div>
					<table>
						<tr>
							<th>Destino</th>
							<th>Distância em Km</th>
						</tr>
						<tr>
							<td>
								<s:select listKey="id"
											listValue="nome" 
											list="listaDeLocaisSemDistancia" 
											headerKey="-1"
											name="localComDistancia.idLocalDestino"
											theme="simple" style="margin-top:0px"/>
							</td>
							<td>
								<s:textfield theme="simple" type="hidden" name="localComDistancia.idLocalOrigem" value="%{#idDoLocalDeOrigem}"/>
								<s:textfield type="number" theme="simple" value="0" name="localComDistancia.distancia"/>
							</td>
						</tr>
					</table>
					<center><input type="submit" value="Adicionar" class="button-green" style="display: block; clear: both"></center>
	            </div>
	        </c:if>
	        <c:if test="${fn:length(listaDeLocaisSemDistancia) == 0}">
	        	<p>Cadastre mais de um local para cadastrar distância</p>
	        </c:if>
		</form>
	</div>
</div>
