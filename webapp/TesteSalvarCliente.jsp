<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rafa Shirts | GN</title>
    <link rel="stylesheet" type="text/css" href="styles.css" />
</head>
<body id="body">
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
            <a href="#">Devolu��o</a>
            <a href="cliente.html" class="active">Clientes</a>
        </div>
    </div>
    <div tabindex="0" class="content" onfocus="closeSidebar()" id="content">
        <h1>Novo Cliente </h1>
        
        <div class="centro"> 
			<form action ="ControleClienteSalvar" method="post">
				 <label for="txtNomeCliente">Nome:</label>
		    <input class="form-control" type="text" id="txtNomeCliente" name="txtNomeCliente" />
		
		    <!-- Dados Pessoais -->
		    <label for="txtCPF">CPF:</label>
		    <input class="form-control" type="text" id="txtCPF" name="txtCPF" />
		
		    <label for="txtDataNascimento">Data de Nascimento:</label>
		    <input class="form-control" type="date" id="txtDataNascimento" name="txtDataNascimento" />
		
				    <!-- Endere�o de Cobran�a -->
				    
		 <!--		   <h2>Endere�o de Cobran�a</h2>
		    <label for="txtEnderecoCobranca">Endere�o:</label>
		    <input class="form-control" type="text" id="txtEnderecoCobranca" name="txtEnderecoCobranca" />
		
		    <label for="txtEstadoCobranca">Estado:</label>
		    <input class="form-control" type="text" id="txtEstadoCobranca" name="txtEstadoCobranca" />
		
		    <label for="txtCidadeCobranca">Cidade:</label>
		    <input class="form-control" type="text" id="txtCidadeCobranca" name="txtCidadeCobranca" />
		
		    <label for="txtCEPCobranca">CEP:</label>
		    <input class="form-control" type="text" id="txtCEPCobranca" name="txtCEPCobranca" />
		
		    <label for="txtNumeroCobranca">N�mero:</label>
		    <input class="form-control" type="text" id="txtNumeroCobranca" name="txtNumeroCobranca" />
			 Bot�o para preencher automaticamente 
		    <input type="checkbox" id="chkMesmoEndereco" name="chkMesmoEndereco">
		    <label for="chkMesmoEndereco">Mesmo endere�o de cobran�a e entrega</label>-->
		   
		    <!-- Endere�o de Entrega -->
		    <h2>Endere�o de Entrega</h2>
		    <label for="txtLogradouro">Endere�o:</label>
		    <input class="form-control" type="text" id="txtLogradouro" name="txtLogradouro" />
		    
		     <label for="txTelefone">Telefone:</label>
		    <input class="form-control" type="text" id="txtEnderecoEntrega" name="txtEnderecoEntrega" />
		
		    <label for="txtEstadoEntrega">Estado:</label>
		    <input class="form-control" type="text" id="txtEstadoEntrega" name="txtEstadoEntrega" />
		
		    <label for="txtCidadeEntrega">Cidade:</label>
		    <input class="form-control" type="text" id="txtCidadeEntrega" name="txtCidadeEntrega" />
		    
		   		
		    <label for="txtCEPEntrega">CEP:</label>
		    <input class="form-control" type="text" id="txtCEPEntrega" name="txtCEPEntrega" />
		
		    <label for="txtNumeroEntrega">N�mero:</label>
		    <input class="form-control" type="text" id="txtNumeroEntrega" name="txtNumeroEntrega" />
		
		   

		    <!-- Dados do Cart�o -->
		    <h2>Dados do Cart�o </h2>
		    <label for="txtNumeroCartao">N�mero do Cart�o:</label>
		    <input class="form-control" type="text" id="txtNumeroCartao" name="txtNumeroCartao" />
		
		    <label for="txtDataValidadeCartao">Data de Validade do Cart�o:</label>
		    <input class="form-control" type="text" id="txtDataValidadeCartao" name="txtDataValidadeCartao" />
		    
		    <label for="txtCVV">CVV:</label>
		    <input class="form-control" type="text" id="txtCVV" name="txtCVV" />
		    
			<input type="submit" class="btn btn-primary botao" id="operacao" name="operacao"
						value="Salvar" />
				
			</form>
		</div>
        
        
    </div>
</body>
<script>
    var header           = document.getElementById('header');
    var navigationHeader = document.getElementById('navigation_header');
    var content          = document.getElementById('content');
    var showSidebar      = false;

    function toggleSidebar()
    {
        showSidebar = !showSidebar;
        if(showSidebar)
        {
            navigationHeader.style.marginLeft = '-10vw';
            navigationHeader.style.animationName = 'showSidebar';
            content.style.filter = 'blur(2px)';
        }
        else
        {
            navigationHeader.style.marginLeft = '-100vw';
            navigationHeader.style.animationName = '';
            content.style.filter = '';
        }
    }

    function closeSidebar()
    {
        if(showSidebar)
        {
            showSidebar = true;
            toggleSidebar();
        }
    }

    window.addEventListener('resize', function(event) {
        if(window.innerWidth > 768 && showSidebar) 
        {  
            showSidebar = true;
            toggleSidebar();
        }
    });
	// Fun��o para preencher campos de entrega com os valores dos campos de cobran�a
	   function preencherEnderecoEntrega() {
        if (document.getElementById('chkMesmoEndereco').checked) {
            // Obtenha os valores dos campos de cobran�a
            var enderecoCobranca = document.getElementById('txtEnderecoCobranca').value;
            var estadoCobranca = document.getElementById('txtEstadoCobranca').value;
            var cidadeCobranca = document.getElementById('txtCidadeCobranca').value;
            var cepCobranca = document.getElementById('txtCEPCobranca').value;
            var numeroCobranca = document.getElementById('txtNumeroCobranca').value;

            // Preencha os campos de entrega com os valores de cobran�a
            document.getElementById('txtEnderecoEntrega').value = enderecoCobranca;
            document.getElementById('txtEstadoEntrega').value = estadoCobranca;
            document.getElementById('txtCidadeEntrega').value = cidadeCobranca;
            document.getElementById('txtCEPEntrega').value = cepCobranca;
            document.getElementById('txtNumeroEntrega').value = numeroCobranca;
        } else {
            // Se a caixa n�o estiver marcada, apague os campos de entrega
            document.getElementById('txtEnderecoEntrega').value = '';
            document.getElementById('txtEstadoEntrega').value = '';
            document.getElementById('txtCidadeEntrega').value = '';
            document.getElementById('txtCEPEntrega').value = '';
            document.getElementById('txtNumeroEntrega').value = '';
        }
    }

    // Adicione um ouvinte de evento para a caixa de sele��o
    document.getElementById('chkMesmoEndereco').addEventListener('change', preencherEnderecoEntrega);

    // Chame a fun��o para configurar inicialmente os campos de entrega com base na caixa de sele��o
    preencherEnderecoEntrega();
</script>
</script>
</html>