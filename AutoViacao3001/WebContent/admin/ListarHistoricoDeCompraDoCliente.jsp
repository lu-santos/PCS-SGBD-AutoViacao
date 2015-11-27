<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Clientes - Lista de Compras do(a) Cliente <s:property value="cliente.nome"/></h2>
		<c:if test="${not empty cliente.passagens}">
			<p style="color: #777">A tabela pode ser exportada como .csv, .xls ou .xml.</p>
			<div align="center">
				<display:table id="tabelaComprasViagens" name="cliente.passagens" export="true" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:setProperty name="export.excel.filename" value="historico_cliente.xls"/>
					<display:setProperty name="export.csv.filename" value="historico_cliente.csv"/>
					<display:setProperty name="export.xml.filename" value="historico_cliente.xml"/>
					<display:column property="poltrona.numero" title="Número Da Poltrona"/>
					<display:column property="precoFormatado" title="Preço"/>
					<display:column property="viagem.locais.label" title="Viagem"/>
				</display:table>
			</div>
		</c:if>
		<c:if test="${empty cliente.passagens}">
			<p>Sem compras cadastrados.</p>
		</c:if>
	</div>
</div>
