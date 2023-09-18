package testes;

//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;

import controle.Fachada;
import controle.IFachada;
import dominio.Cliente;
import dominio.Cidade;
import dominio.Endereco;
import dominio.Estado;

public class TesteFachadaSalvarCliente {

	public static void main(String[] args) {
		
		Estado sp = new Estado("SÃO PAULO");
		Cidade suzano = new Cidade("Suzano", sp);
		Endereco endereco = new Endereco("Av teste dois", "123",suzano, "08576315");
		endereco.setCep("08576315");
		endereco.setDtCadastro(new Date());

		Cliente cliente  = new Cliente("Cleberson ", "3323498", endereco, "ADS", "Giovanna da Silva MSOL", "Desconhecido","11970762329", null, null, null, null, null);
		cliente.setDtCadastro(new Date());
		
		if (cliente.getEndereco() != null) {
		    cliente.validarDados();
		}
		IFachada fachada = new Fachada();
		
		System.out.println(fachada.salvar(cliente));
		
	}

}
