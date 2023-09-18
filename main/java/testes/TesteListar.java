package testes;
import java.util.List;
import dao.ClienteDAO;
import dominio.Cliente;
import dominio.EntidadeDominio;


public class TesteListar {
    public static void main(String[] args) {
        
        ClienteDAO clienteDAO = new ClienteDAO();

        
        Cliente filtroCliente = new Cliente();
        filtroCliente.setId(1); 

        List<EntidadeDominio> clientesEncontrados = clienteDAO.findAll(filtroCliente);

        
        for (EntidadeDominio entidade : clientesEncontrados) {
            Cliente clienteEncontrado = (Cliente) entidade;
            System.out.println("ID: " + clienteEncontrado.getId());
            System.out.println("Nome: " + clienteEncontrado.getNome());
            System.out.println("CPF: " + clienteEncontrado.getCpf());
            System.out.println("----------------------------------");
        }
    }
    }