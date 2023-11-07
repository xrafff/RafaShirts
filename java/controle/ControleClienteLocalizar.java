package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Endereco;
import dominio.Estado;

/**
 * Servlet implementation class ControleCliente
 */@SuppressWarnings("unused")
 	@WebServlet("/ControleClienteLocalizar")
	
	
	public class ControleClienteLocalizar { 
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {
		    String txtPesquisa = request.getParameter("txtPesquisa");

		    // Verifique se o txtPesquisa é um número (nesse caso, consideramos como ID)
		    int id = -1; // Valor padrão para indicar pesquisa por nome
		    try {
		        id = Integer.parseInt(txtPesquisa);
		    } catch (NumberFormatException e) {
		        id = -1;
		    }

		    IFachada fachada = new Fachada();
		    Cliente cliente = null;
		    ClienteDAO clienteDAO = new ClienteDAO();
		    
		    if (id != -1) {
		        // Pesquisa por ID
		    	
		    	cliente = clienteDAO.consultarPorId(id);		       
		    

		    if (cliente != null) {
		        // Cliente encontrado, preencha os campos no formulário de alteração
		    } else {
		        // Cliente não encontrado, exiba uma mensagem de erro
		        String mensagem = "Cliente não encontrado";
		        request.setAttribute("mensagem", mensagem);
		        request.getRequestDispatcher("clienteLocalizar.jsp").forward(request, response);
		    }
		    }
	 }
 }
	
	
