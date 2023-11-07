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
		
		
		
			
		Cliente cleberson  = new Cliente("José", "3323498", endereco, "11970762329", "15/25/1980", "15/25/1980", "123450123456", "12/25", "123");
		cleberson.setDtCadastro(new Date());
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cleberson);
		
		System.out.println(cleberson.getId());
		
	}

}
