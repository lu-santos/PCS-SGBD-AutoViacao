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
		<h2 style="text-align: center">Gerenciar Passagens - Lista de Viagens sem Passagens Cadastradas</h2>
		<c:if test="${not empty listaDeViagens}">
			<div align="center">
				<display:table id="tabelaViagensPassagens" name="listaDeViagens" pagesize="20" cellpadding="5px;" cellspacing="5px;" style="align: center;" requestURI="">
					<display:column property="dataHoraPartidaFormatada" title="Data/Hora de Partida"/>
					<display:column property="labelLocais" title="Partida/Destino/Dist�ncia(KM)"/>
					<display:column property="labelOnibus" title="�nibus"/>
					<display:column href="PrepararGerarPassagens" paramId="viagem.idViagem" property="idViagem" title="Gerar Passagens"/>
				</display:table>
			</div>
		</c:if>
	
		<c:if test="${empty listaDeViagens}">
			<p>N�o foi localizada viagem sem passagem cadastrada.</p>
		</c:if>
	</div>
</div>
