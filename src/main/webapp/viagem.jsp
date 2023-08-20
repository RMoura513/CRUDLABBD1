<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>Viagem</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div align="center" class="container">
		<form action="viagem" method="post">
		<p class="title">
			<b>Viagem</b>
		</p>
		<table>
			<tr>
				<td colspan="3">
				<input class="input_data_id" type="number" id="codigo" name="codigo" placeholder="Codigo"
				 value='<c:out value="${viagem.codigo }"></c:out>'>
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Buscar">
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<select class="input_data" id="placa" name="placa">
					 	<option value="0">Escolha uma Placa</option>
					 		<c:forEach var="o" items="${onibuss }">
					 		<c:if test="${(empty viagem) || (o.placa ne viagem.onibusPlaca) }">
					 			<option value="${o.placa }">
					 				<c:out value="${o.placa }" />
							 	</option>
							 </c:if>
							 <c:if test="${o.placa eq viagem.onibusPlaca }">
							 <option value="${o.placa }" selected="selected">
					 				<c:out value="${o.placa }" />
					 		</option>
					 		</c:if>
					 		</c:forEach>
						 </select>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<select class="input_data" type="text" id="motoristaCodigo" name="motoristaCodigo">
					<option value="0">Escolha um Motorista</option>
						 <c:forEach var="m" items="${motoristas }">
						 <c:if test="${(empty viagem) || (m.codigo ne viagem.motoristaCodigo) }">
							 <option value="${m.codigo }">
							 	<c:out value="${m.codigo }" />
							 </option>
							 </c:if>
							 <c:if test="${m.codigo eq viagem.motoristaCodigo }">
							 <option value="${m.codigo }" selected="selected">
							 		<c:out value="${m.codigo }"/>
							 </option>
							 </c:if>
						 </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input class="input_data_id" type="text" id="horaSaida" name="horaSaida" placeholder="Saida"
					value='<c:out value="${viagem.horaSaida }"></c:out>'>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input class="input_data_id" type="text" id="horaChegada" name="horaChegada" placeholder="Chegada"
					value='<c:out value="${viagem.horaChegada }"></c:out>'>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input class="input_data" type="text" id="partida" name="partida" placeholder="Partida"
					value='<c:out value="${viagem.partida }"></c:out>'>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input class="input_data" type="text" id="destino" name="destino" placeholder="Destino"
					value='<c:out value="${viagem.destino }"></c:out>'>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" id="botao" name="botao" value="Cadastrar">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Alterar">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Excluir">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Listar">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Listar Onibus">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Listar Viagem">
				</td>
			</tr>
			
		</table>
		</form>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2><b><c:out value="${erro}" /></b></H2>
		</c:if>
	</div>
		<div align="center">
		<c:if test="${not empty saida }">
			<H3><b><c:out value="${saida}" /></b></H3>
		</c:if>
	</div>
	<br />
	<c:if test="${not empty viagens }">
		<table align = "center" class="table_round">
			<thead>
				<tr>
					<th>Codigo</th>
					<th>Placa</th>
					<th>Motorista</th>
					<th>Saida</th>
					<th>Chegada</th>
					<th>Partida</th>
					<th>Destino</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="v" items="${viagens }">
					<tr>
						<td><c:out value="${v.codigo}" /></td>
						<td><c:out value="${v.onibusPlaca}" /></td>
						<td><c:out value="${v.motoristaCodigo}" /></td>
						<td><c:out value="${v.horaSaida }" /></td>
						<td><c:out value="${v.horaChegada }" /></td>
						<td><c:out value="${v.partida }" /></td>
						<td><c:out value="${v.destino }" /></td>
						
					</tr>
		
				</c:forEach>
				
			</tbody>
		</table>
	</c:if>
</body>
</html>