<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="global" style="height: 100%">
	<div class="conteudo">

		<form action="ConsultarOnibus" style="width: 90%" method="post">
			<h2>Consultar Ônibus</h2>
			<div align="center">
				<label>Placa do veículo (formato AAA-0000)*</label><br>
				<input type="text" maxlength="8" required name="onibus.placa" style="width: 20%">
			</div>
			<div align="center">
				<input type="submit" value="Pesquisar" class="button-green"
					style="width: 20%; display: block; clear: both">
			</div>
		</form>

		<c:if test="${onibusEncontrado == false}">
			<s:property value="mensagem" />
		</c:if>

	</div>
</div>
