<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global" sytle="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Clientes - Lista de Compras do Cliente <s:property value="cliente.nome"/></h2>
		<c:if test="${not empty cliente.passagens}">
			<div align="center">
				<display:table id="tabelaComprasViagens" name="cliente.passagens" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="numeroPoltrona" title="Número Da Poltrona"/>
					<display:column property="preco" title="Preço"/>
					<display:column property="viagem.locais.label" title="Viagem"/>
				</display:table>
			</div>
		</c:if>
		<c:if test="${empty cliente.passagens}">
			<p>Sem compras cadastrados.</p>
		</c:if>
	</div>
</div>
