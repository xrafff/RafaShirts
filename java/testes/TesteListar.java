package testes;
import java.util.List;
import dao.ClienteDAO;
import dominio.Cliente;
import dominio.EntidadeDominio;

public class TesteListar {
    public static void main(String[] args) {
        testFindAllWithFilters("cpf", "123123");
    }

    public static void testFindAllWithFilters(String filtroCampo, String filtroValor) {
        ClienteDAO clienteDAO = new ClienteDAO();

        List<EntidadeDominio> clientesEncontrados = clienteDAO.findAll(new Cliente(), filtroCampo, filtroValor);

        for (EntidadeDominio entidade : clientesEncontrados) {
            Cliente clienteEncontrado = (Cliente) entidade;
            System.out.println("ID: " + clienteEncontrado.getId());
            System.out.println("Nome: " + clienteEncontrado.getNome());
            System.out.println("CPF: " + clienteEncontrado.getCpf());
            System.out.println("----------------------------------");
        }
    }
}
