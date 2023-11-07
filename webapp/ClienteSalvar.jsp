<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rafa Shirts | GN</title>
    <link rel="stylesheet" type="text/css" href="styles.css" />
</head>
<body>
<div class="header" id="header">
    <button onclick="toggleSidebar()" class="btn_icon_header">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
        </svg>
    </button>
    <div class="logo_header">
        <img src="logo.png" alt="Logo" class="img_logo_header">
    </div>
    <div class="navigation_header" id="navigation_header">
        <button onclick="toggleSidebar()" class="btn_icon_header">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
            </svg>
        </button>
        <a href="#" >Home</a>
        <a href="venda.html">Venda</a>
        <a href="#">Troca</a>
        <a href="#">Devolução</a>
        <a href="cliente.jsp" class="active">Clientes</a>
    </div>
</div>
<div tabindex="0" class="content" onfocus="closeSidebar()" id="content">
    <h1>Novo Cliente</h1>
    <div class="centro">
        <form action= ControleClienteSalvar id="clienteForm" method="post">
        
            <label for="txtCliente">Nome:</label>
            <input class="form-control" type="text" id="txtCliente" name="txtCliente" />

            <!-- Dados Pessoais -->
            <label for="txtCPF">CPF:</label>
            <input class="form-control" type="text" id="txtCPF" name="txtCPF" />

            <label for="txtDataNascimento">Data de Nascimento:</label>
            <input class="form-control" type="date" id="txtDataNascimento" name="txtDataNascimento" />

            <!-- Endereço de Entrega -->
            <h2>Endereço de Entrega</h2>
            <label for="txtLogradouro">Endereço:</label>
            <input class="form-control" type="text" id="txtLogradouro" name="txtLogradouro" />

            <label for="txtTelefone1">Telefone1:</label>
            <input class="form-control" type="text" id="txtTelefone1" name="txtTelefone1" />

            <label for="txtTelefone2">Telefone2:</label>
            <input class="form-control" type="text" id="txtTelefone2" name="txtTelefone2" />

            <label for="txtEstado">Estado:</label>
            <input class="form-control" type="text" id="txtEstado" name="txtEstado" />

            <label for="txtCidade">Cidade:</label>
            <input class="form-control" type="text" id="txtCidade" name="txtCidade" />

            <label for="txtNumero">Número:</label>
            <input class="form-control" type="text" id="txtNumero" name="txtNumero" />

            <!-- Dados do Cartão -->
            <h2>Dados do Cartão</h2>
            <label for="txtNumCartao">Número do Cartão:</label>
            <input class="form-control" type="text" id="txtNumCartao" name="txtNumCartao" />

            <label for="txtDataValCartao">Data de Validade do Cartão:</label>
            <input class="form-control" type="text" id="txtDataValCartao" name="txtDataValCartao" />

            <label for="txtCvvCartao">CVV:</label>
            <input class="form-control" type="text" id="txtCvvCartao" name="txtCvvCartao" />

           	<input type="submit" class="btn btn-primary botao" id="operacao" name="operacao"
						value="Salvar" />
        </form>
        <div id="mensagemDiv"></div>
    </div>
</div>

<script>
    var header = document.getElementById('header');
    var navigationHeader = document.getElementById('navigation_header');
    var content = document.getElementById('content');
    var showSidebar = false;

    function toggleSidebar() {
        showSidebar = !showSidebar;
        if (showSidebar) {
            navigationHeader.style.marginLeft = '-10vw';
            navigationHeader.style.animationName = 'showSidebar';
            content.style.filter = 'blur(2px)';
        } else {
            navigationHeader.style.marginLeft = '-100vw';
            navigationHeader.style.animationName = '';
            content.style.filter = '';
        }
    }

    function closeSidebar() {
        if (showSidebar) {
            showSidebar = true;
            toggleSidebar();
        }
    }

    window.addEventListener('resize', function(event) {
        if (window.innerWidth > 768 && showSidebar) {
            showSidebar = true;
            toggleSidebar();
        }
    });

    function salvarCliente() {
    	console.log("Enviando dados para a servlet...");

        var formData = new FormData(document.getElementById("clienteForm"));

        fetch("ControleClienteSalvar", {
            method: "POST",
            body: formData
        })
        .then(response => response.text())
        .then(data => {
            if (data === "Cliente salvo com sucesso") {
                document.getElementById("mensagemDiv").innerHTML = data;
                document.getElementById("clienteForm").reset();
            } else {
                document.getElementById("mensagemDiv").innerHTML = "Erro: " + data;
            }
        })
        .catch(error => {
            console.error("Erro:", error);
        });
    }
</script>
</body>
</html>