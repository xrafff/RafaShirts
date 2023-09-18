package viewhelper;


import dominio.EntidadeDominio;
import dominio.Estado;
import util.Resultado;
import dominio.Cliente;
import dominio.Cidade;
import dominio.Endereco;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;


public class ClienteViewHelper implements IViewHelper {
    @Override
    public EntidadeDominio get(HttpServletRequest req, HttpServletResponse resp) {
    	Estado sp = new Estado("SÃO PAULO");
		Cidade suzano = new Cidade("Suzano", sp);
		Endereco endereco = new Endereco("Av teste", "123",suzano, "08576315");
		endereco.setCep("08576315");
		endereco.setDtCadastro(new Date());
		Cliente cliente = new Cliente("Cleberson", "3323498", endereco, "11970762329", null, null, "1234567890123456", "12/25", "123");
        String idStr = req.getParameter("id");
        int id = 0;
        if(idStr != null){
            id = Integer.parseInt(idStr);
        }
        cliente.setId(id);

        String idEnderecoStr = req.getParameter("cod_endereco");

        if(idEnderecoStr != null){
            Endereco endereco1= new Endereco(idEnderecoStr, idEnderecoStr, null, idEnderecoStr);
            int idEndereco= 0;
            try {
            	idEndereco = Integer.parseInt(idEnderecoStr);
            } catch (Exception e) {}
            endereco1.setId(idEndereco);
            cliente.setEndereco(endereco1);
        }


        if(req.getParameter("operacao") == null){
            return cliente;
        }
        if(req.getParameter("operacao").equals("CONSULTAR")){

            return cliente;
        }
        if(req.getParameter("operacao").equals("EXCLUIR")){
            return cliente;
        }


        String nome= req.getParameter("nome");
        String cpf= req.getParameter("cpf");
        String telefone1 = req.getParameter("telefone1");
        String telefone2 = req.getParameter("telefone2");
        String dataNascimento = req.getParameter("dataNascimento");
        String numCartao = req.getParameter("numCartao");
        String dataValCartao = req.getParameter("dataValCartao");
        String cvvCartao = req.getParameter("cvvCartao");
      

      
        int idEndereco = 0;
        
        try {
            idEndereco = Integer.parseInt(idEnderecoStr);
        } catch (Exception e) {}

        
  
        Endereco endereco1 = new Endereco();
        endereco1.setId(idEndereco);
        cliente = new Cliente(nome, cpf,  endereco1,  telefone1, telefone2,  dataNascimento,  numCartao,  dataValCartao, cvvCartao);
        cliente.setId(id);

        System.out.println(cliente);

        return cliente;
    }

    @Override
    public void set(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) throws ServletException, IOException {

        RequestDispatcher rd = null;
        String operacao = req.getParameter("operacao");
        if(resultado.getMsg().length() == 0){
            rd = req.getRequestDispatcher("index.jsp");
            String idEnderecoStr = req.getParameter("idEndereco");
                int idEndereco = 0;
                try {
                	idEndereco = Integer.parseInt(idEnderecoStr);
                } catch (Exception e) {}

            if(operacao == null){
                operacao = "CONSULTAR";
            }

            if(operacao.equals("SALVAR")){
                resultado.setMsg("Cliente salvo com sucesso!");
                req.setAttribute("menssagem","sucesso");
                resp.sendRedirect("cliente?operacao=CONSULTAR&idCliente="+idEndereco);

            }else if(operacao.equals("CONSULTAR")){
                req.setAttribute("ClienteList",resultado.getResultado());
                if(req.getParameter("id") != null){
                    rd = req.getRequestDispatcher("FormCliente2.html.jsp");
                }else{
                    rd = req.getRequestDispatcher("FormCliente2.html.jsp");
                }
                
            }else if(operacao.equals("ALTERAR")){
                req.setAttribute("cliente",resultado.getResultado());
                req.setAttribute("msg", "Alteração executada com sucesso");
                rd = req.getRequestDispatcher("FormCliente2.html");

            }else if(operacao.equals("EXCLUIR")){
                resp.sendRedirect("cliente?operacao=CONSULTAR&idCliente="+idEndereco);
            }

            if((!operacao.equals("EXCLUIR")) && (!operacao.equals("SALVAR"))){
                rd.forward(req, resp);
            }
        }else{
            req.setAttribute("msg", resultado.getMsg());
            rd = req.getRequestDispatcher("erro.jsp");
            rd.forward(req, resp);
        }

        
    }

	@Override
	public EntidadeDominio get1(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}
}
