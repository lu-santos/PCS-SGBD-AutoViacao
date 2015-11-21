<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global" style="height: 100%">
	<div class="conteudo">
		<form action="ListarEpocaDoAnoComMaisVenda" style="width: 90%" method="post">
			<h2>Consultar Época do Ano com Mais Vendas de Passagens</h2>
			
			<div class="caixa-padrao" style="margin-left: 0px; width: 30%">
				<label>Ano*</label><br>
				<s:select name="ano" 
					list="listaDeAno"  
					value="ano" />
			</div><br><br>
			<div align="center">
				<input type="submit" value="Pesquisar" class="button-green"
					style="width: 20%; display: block; clear: both">
			</div>
		</form>

		<c:if test="${not empty listaDePassagens}">
			<div align="center">
				<display:table id="tabelaPassagens" name="listaDePassagens" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<c:set var="vendaTotal" value="${preco * total}" />  
					<display:column property="viagem.dataHoraPartida.month" title="Mês"/>
					<display:column property="preco" title="Preço da Passagem"/>
					<display:column property="quantidade" title="Quantidade de Vendas"/>
					<display:column property="total" title="Total"/>
				</display:table>
			</div>
		</c:if>
		
		<c:if test="${empty listaDePassagens}">
			<s:property value="mensagem" />
		</c:if>

	</div>
</div>
