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
		<h2 style="text-align: center">Gerenciar Viagens - Lista de Viagens Cadastradas</h2>
		<c:if test="${not empty listaDeViagens}">
			<div align="center">
				<display:table id="tabelaViagens" name="listaDeViagens" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="dataHoraPartidaFormatada" title="Data/Hora de Partida"/>
					<display:column property="labelLocais" title="Partida/Destino/Distância(KM)"/>
					<display:column href="VisualizarViagem" paramId="viagem.idViagem" property="idViagem" title="Visualizar"/>
					<display:column href="PrepararAlteracaoViagem" paramId="viagem.idViagem" property="idViagem" title="Alterar"/>
					<display:column href="ExcluirViagem" paramId="viagem.idViagem" property="idViagem" title="Remover"/>
					<display:column href="PassageirosViagem" paramId="viagem.idViagem" property="idViagem" title="Passageiros"/>
					<display:column href="LucroBrutoViagem" paramId="viagem.idViagem" property="idViagem" title="Lucro Bruto"/>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty listaDeViagens}">
			<p>Sem viagens cadastradas.</p>
		</c:if>
	</div>
</div>
