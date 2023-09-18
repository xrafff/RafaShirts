package testes;

//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;

import dao.ClienteDAO;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Endereco;
import dominio.Estado;


public class TesteSalvarClienteComEndereco {

	public static void main(String[] args) {
		Estado sp = new Estado("SÃO PAULO");
		Cidade suzano = new Cidade("Suzano", sp);
		Endereco endereco = new Endereco("Av teste", "123",suzano, "08576315");
		endereco.setCep("08576315");
		endereco.setDtCadastro(new Date());
		
		
		
			
		Cliente carlos = new Cliente("Carlos", "12345678910", endereco, "medicina", "fulaninha", "jorge", null, null, null, null, null, null);
		carlos.setDtCadastro(new Date());
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(carlos);
		
		System.out.println(carlos.getId());
		
	}

}
