package testes;

import java.util.Date;
import dao.ClienteDAO;
import dominio.Cliente;



public class TesteAlterarCliente {
	//Estado sp = new Estado("SÃO PAULO");
    //Cidade suzano = new Cidade("Suzano", sp);
    //Endereco endereco = new Endereco("novo endereço", "123", suzano, "08576315");
    //endereco.setCep("08576315");
    //endereco.setDtCadastro(new Date());

   
	public static void main(String[] args) throws ClassNotFoundException {
        
        ClienteDAO clienteDAO = new ClienteDAO();

        int id = 21;
        
        Cliente clienteExistente = clienteDAO.consultarPorId(id);
       // Endereco endereco;
        //Estado estado = new Estado("estado teste");
        //Cidade cidade = new Cidade("cidade teste", estado);
      
        System.out.println("O cliente que está sendo alterado é: " + clienteExistente.getNome());
        if (clienteExistente != null) {
            clienteExistente.setNome("Joana da Silva");
            clienteExistente.setTelefone1("11930303003");

           
          //  if (clienteExistente.getEndereco() != null) {
                
           //     endereco = clienteExistente.getEndereco();
          //  } else {
                
          //      endereco = new Endereco("Novo endereço", "123", cidade, "08576315");
        //        endereco.setCep("08576315");
          //      endereco.setDtCadastro(new Date());
            }

           
           // clienteExistente.setEndereco(endereco);

          
            if (clienteExistente.getDtCadastro() == null) {
              
                clienteExistente.setDtCadastro(new Date());
            }

            
            clienteDAO.alterar(clienteExistente);

            System.out.println("Cliente Editado com Sucesso! Nome: " + clienteExistente.getNome());
         
    }
}