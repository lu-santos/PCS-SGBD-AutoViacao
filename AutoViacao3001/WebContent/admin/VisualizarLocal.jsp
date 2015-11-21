<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Viagens - Distâncias Cadastradas <br> Local: <s:property value="local.nome"/></h2>
		<form action="EditarLocal" method="post" style="width: 50%">
			<h2>Editar Nome do Local</h2>
			<div>
				<label>Nome do Local*</label><br>
				<s:textfield theme="simple" type="hidden" name="local.id"/>
				<s:textfield name="local.nome" />
				<s:set var="idDoLocalDeOrigem" value="local.id"/>
            </div>
            <center><input type="submit" value="Alterar" class="button-green" style="display: block; clear: both"></center>
         </form>
		
		<form action="AdicionarDistanciaPaginaEditar" style="width: 50%; margin-top:20px">
         	<h2>Cadastrar Distância</h2>
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
											name="localComDistancia.localDeDestino.id"
											theme="simple" style="margin-top:0px"/>
							</td>
							<td>
								<s:textfield theme="simple" type="hidden" name="localComDistancia.localDeOrigem.id" value="%{#idDoLocalDeOrigem}"/>
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
		
		<c:if test="${not empty locais}">
			<div align="center">
				<display:table id="tabelaLocais" name="locais" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="localDeOrigem.nome" title="Origem"/>
					<display:column property="localDeDestino.nome" title="Destino"/>
					<display:column property="distancia" title="Distância"/>
					<s:url var="editarUrl" action="PrepararAlteracaoDistancia">
						<s:param name="localComDistancia.idLocais" value="%{#attr.tabelaLocais.idLocais}"/>
					</s:url>
					<display:column title="Editar">
						<s:a href="%{#editarUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_editar.png"></s:a>
					</display:column>
					<s:url var="removerUrl" action="ExcluirDistancia">
						<s:param name="localComDistancia.idLocais" value="%{#attr.tabelaLocais.idLocais}"/>
					</s:url>
					<display:column title="Remover">
						<s:a href="%{#removerUrl}"><img src="${pageContext.request.contextPath}/images/icone_tabela_remover.png"></s:a>
					</display:column>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty locais}">
			<p>Local <s:property value="local.nome"/></p>
			<p>Sem locais com distância cadastrados.</p>
		</c:if>
	</div>
</div>
