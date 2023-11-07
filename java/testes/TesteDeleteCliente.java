package testes;

//import java.util.List;

import dao.ClienteDAO;


public class TesteDeleteCliente {

    public static void main(String[] args) {
        ClienteDAO clienteDao = new ClienteDAO(); 

        
        int id = 21; 

        try {
            clienteDao.delete(id);
            System.out.println("Cliente excluído com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao excluir o cliente: " + e.getMessage());
        }
    }
}