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
	<div>
		<p style="text-align: center; margin-bottom: 20px; color: #007f00">
			<s:property value="mensagem"/>
		</p>
		<h2 style="text-align: center">Gerenciar Ônibus - Lista de Ônibus Cadastrados</h2>
		<c:if test="${not empty listaDeOnibus}">
			<div align="center">
				<display:table id="tabelaOnibus" name="listaDeOnibus" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="placa" title="Placa"/>
					<display:column property="modelo" title="Modelo"/>
					<display:column property="fabricante" title="Fabricante"/>
					<display:column href="VisualizarOnibus" paramId="onibus.idOnibus" property="idOnibus" title="Visualizar"/>
					<display:column href="PrepararAlteracaoOnibus" paramId="onibus.idOnibus" property="idOnibus" title="Alterar"/>
					<display:column href="ExcluirOnibus" paramId="onibus.idOnibus" property="idOnibus" title="Remover"/>
					<display:column href="ViagensDoOnibus" paramId="onibus.idOnibus" property="idOnibus" title="Viagens"/>
				</display:table>
			</div>
		</c:if>
		<c:if test="${empty listaDeOnibus}">
			<p>Sem ônibus cadastrados.</p>
		</c:if>
	</div>
</div>
