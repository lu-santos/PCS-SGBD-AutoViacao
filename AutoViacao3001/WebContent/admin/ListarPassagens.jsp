<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<style type="text/css">
	tr.odd 
	{
	        background-color: #fff
	}
	
	tr.tableRowEven,tr.even 
	{
	        background-color: #eee
	}
</style>

<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Passagens - Lista de Passagens Cadastradas</h2>
		<c:if test="${not empty listaDePassagens}">
			<div align="center">
				<display:table id="tabelaPassagens" name="listaDePassagens" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="idViagem" title="ID da Viagem"/>
					<display:column property="numeroPoltrona" title="Nº da Poltrona"/>
					<display:column property="preco" title="Preço"/>
					<display:column property="cpfCliente" title="CPF do Cliente"/>
					<display:column href="VisualizarPassagem" paramId="passagem.id" property="id" title="Visualizar"/>
					<display:column href="AlterarPassagem" paramId="passagem.id" property="id" title="Alterar"/>
					<display:column href="RemoverPassagem" paramId="passagem.id" property="id" title="Remover"/>
				</display:table>
				
			</div>
		</c:if>
	
		<c:if test="${empty listaDePassagens}">
			<p>Sem passagens cadastradas.</p>
		</c:if>
	</div>
</div>