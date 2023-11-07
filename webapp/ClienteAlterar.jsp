<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alterar Cliente | Rafa Shirts</title>
    <link rel="stylesheet" type="text/css" href="styles.css" />
</head>
<body>
    <div class="header" id="header">
        <!-- Seu código para o cabeçalho -->
    </div>
    <div tabindex="0" class="content" onfocus="closeSidebar()" id="content">
        <h1>Alterar Cliente</h1>
        <form id="clienteForm" method="post" action="ControleClienteAlterar">
            <input type="hidden" name="Id" value="<%= request.getParameter("Id") %>">
            
            <label for="txtCliente">Nome:</label>
            <input class="form-control" type="text" id="txtCliente" name="txtCliente" value="<%= request.getParameter("txtCliente") %>">
            
            <!-- Outros campos de formulário com valores atuais do cliente -->
            
            <input type="submit" class="btn btn-primary botao" id="operacao" value="Alterar" />
        </form>
        <div id="mensagemDiv"></div>
    </div>
</body>
<!-- Seu código JavaScript para o menu lateral -->
</html>