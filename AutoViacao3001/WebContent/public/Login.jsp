<div id="global" style="height: 100%">
	<form action="Login" id="login" method="post">
		<h2>Login</h2>
		<p style="color:#e62117">
			<s:property value="mensagem" />
		</p>
		<label>CPF*</label>
		<input type="number" maxlength="11" required name="cpf"/>
		<label>Senha*</label>
		<input type="password" maxlength="20" required name="senha"/>
		<center><input type="submit" value="Enviar" class="button-green"/></center>
	</form>
</div>
