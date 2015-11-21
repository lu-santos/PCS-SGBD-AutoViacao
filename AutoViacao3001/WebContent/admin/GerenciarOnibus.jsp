<div id="global" style="height: 100%">
	<div class="conteudo clearfix">
		<section class="clearfix" style="margin: 0 auto; width: 90%; text-align: center">
			<p style="text-align: center; margin-bottom: 40px; color: #007f00">
				<s:property value="mensagem"/>
			</p>
			<img src="${pageContext.request.contextPath}/images/icon_gerenciar_onibus.png">
			<h2>Gerenciar Ônibus</h2>
			<a href="${pageContext.request.contextPath}/admin/cadastroOnibus.jsp">
				Cadastrar Ônibus
			</a><br>
			<s:a action="ListarOnibus">
				Listar Ônibus
			</s:a>
		</section>
	</div>
</div>
