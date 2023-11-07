package testes;

import java.util.Date;

import dao.EnderecoDAO;
import dominio.Cidade;
import dominio.Endereco;
import dominio.Estado;

public class TesteAlterarEndereco {
    public static void main(String[] args) {

    	Endereco endereco = new Endereco();
        endereco.setId(7); // Defina o ID do endereço que deseja atualizar
        endereco.setCidade(new Cidade("Nova Cidade", new Estado("Novo Estado")));
        endereco.setLogradouro("Nova Rua");
        endereco.setNumero("1231");
        endereco.setDtCadastro(new Date());
        endereco.setCep("12345678");

        // Criar uma instância do EnderecoDAO
        EnderecoDAO enderecoDAO = new EnderecoDAO();

        try {
            // Chamar o método alterar
            enderecoDAO.alterar(endereco);
            System.out.println("Endereço atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar o endereço: " + e.getMessage());
        }
    }
}