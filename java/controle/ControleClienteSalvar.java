package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Endereco;
import dominio.Estado;

/**
 * Servlet implementation class ControleCliente
 */

@WebServlet("/ControleClienteSalvar")
public class ControleClienteSalvar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public ControleClienteSalvar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String txtEstado = request.getParameter("txtEstado");
		String txtCidade = request.getParameter("txtCidade");
		String txtLogradouro = request.getParameter("txtLogradouro");
		String txtNumero = request.getParameter("txtNumero");
		String txtTelefone1 = request.getParameter("txtTelefone1");
		String txtTelefone2 = request.getParameter("txtTelefone2");
		String txtDataNascimento = request.getParameter("txtDataNascimento");
		String txtNumCartao = request.getParameter("txtNumCartao");
		String txtDataValCartao = request.getParameter("txtDataValCartao");
		String txtCvvCartao = request.getParameter("txtCvvCartao");

	        // Verifique se a data de nascimento não é nula ou vazia antes de tentar analisá-la
	     
		Estado estado = new Estado(txtEstado);
		Cidade cidade = new Cidade(txtCidade, estado);
		
		Endereco endereco = new Endereco(txtLogradouro, txtNumero, cidade, txtNumero);
		endereco.setDtCadastro(new Date());
		
	
		String txtCliente = request.getParameter("txtCliente");
		String txtCPF = request.getParameter("txtCPF");
		
				
		Cliente cliente = new Cliente(txtCliente, txtCPF, endereco, txtTelefone1, txtTelefone2, txtDataNascimento,txtNumCartao, txtDataValCartao, txtCvvCartao );

		cliente.setDtCadastro(new Date());			
		
		IFachada fachada = new Fachada();
		System.out.println("Recebendo dados...");
		String msg = fachada.salvar(cliente);
		
		
		System.out.println("Nome do Cliente: " + txtCliente);

		PrintWriter out = response.getWriter();
		out.print(msg);
		
		response.sendRedirect("ClienteSalvar.jsp");
	}

	
	
}
