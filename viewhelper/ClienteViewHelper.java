package viewhelper;


import dominio.EntidadeDominio;
import dominio.Estado;
import util.Resultado;
import dominio.Cliente;
import dominio.Cidade;
import dominio.Endereco;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    	Cliente cliente = new Cliente("Cleberson ", "3323498", endereco, "ADS", "Giovanna da Silva MSOL", "Desconhecido","11970762329", null, null, null, null, null);
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


        String nomeCliente= req.getParameter("nome");
        String cpf= req.getParameter("cpf");
        String nomeMae = req.getParameter("nome_mae");
        String nomePai= req.getParameter("nome_pai");
      

      
        int idEndereco = 0;
        
        try {
            idEndereco = Integer.parseInt(idEnderecoStr);
        } catch (Exception e) {}

        
  
        Endereco endereco1 = new Endereco();
        endereco1.setId(idEndereco);
        cliente = new Cliente (nomeCliente, cpf,  endereco1, nomePai, nomeMae, null, nomePai, nomePai, nomePai, nomePai, nomePai, nomePai);
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
}
