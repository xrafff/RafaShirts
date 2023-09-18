package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.Conexao;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;

public class ClienteDAO implements IDAO {
	private Connection connection = null;

	public EntidadeDominio salvar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;


		try {
			connection = Conexao.getConnectionPostgres();

			connection.setAutoCommit(false);

			EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
			enderecoDAO.salvar(cliente.getEndereco());

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_cliente(nome, cpf, dt_cadastro, cod_endereco, dt_nascimento, telefone1, telefone2, num_cartao, data_val_cartao, cvv_cartao) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			Timestamp time = new Timestamp(cliente.getDtCadastro().getTime());
			pst.setTimestamp(3, time);
			pst.setInt(4, cliente.getEndereco().getId());
			pst.setString(5, cliente.getDataNascimento());
			pst.setString(6, cliente.getTelefone1());
			pst.setString(7, cliente.getTelefone2());
			pst.setString(8, cliente.getNumCartao());
			pst.setString(9, cliente.getDataValCartao());
			pst.setString(10, cliente.getCVVCartao());

			pst.executeUpdate();
			

			ResultSet rs = pst.getGeneratedKeys();
			int idCli = 0;
			if (rs.next())
				idCli = rs.getInt(1);
			cliente.setId(idCli);

			
			
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entidade;

	}



	public EntidadeDominio alterar(EntidadeDominio entidade) {
	    PreparedStatement pst = null;
	    Cliente cliente = (Cliente) entidade;

	    try {
	        connection = Conexao.getConnectionPostgres();
	        connection.setAutoCommit(false);

	        
	        if (cliente.getEndereco() != null) {
	            EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
	            enderecoDAO.salvar(cliente.getEndereco());
	        }

	        
	        StringBuilder sql = new StringBuilder();
	        sql.append("UPDATE tb_cliente SET ");
	        sql.append("nome=?, cpf=?, dt_cadastro=?,  dt_nascimento=?, ");
	        sql.append("telefone1=?, telefone2=?, num_cartao=?, data_val_cartao=?, cvv_cartao=? ");
	        sql.append("WHERE id=?"); 

	        pst = connection.prepareStatement(sql.toString());

	        pst.setString(1, cliente.getNome());
	        pst.setString(2, cliente.getCpf());
	        Timestamp time = new Timestamp(cliente.getDtCadastro().getTime());
	        pst.setTimestamp(3, time);
	     //   pst.setInt(4, cliente.getEndereco().getId());
	        pst.setString(4, cliente.getDataNascimento());
	        pst.setString(5, cliente.getTelefone1());
	        pst.setString(6, cliente.getTelefone2());
	        pst.setString(7, cliente.getNumCartao());
	        pst.setString(8, cliente.getDataValCartao());
	        pst.setString(9, cliente.getCVVCartao());
	        pst.setInt(10, cliente.getId()); 

	        pst.executeUpdate();

	        connection.commit();
	    } catch (Exception e) {
	        try {
	            connection.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }

	        e.printStackTrace();
	    } finally {
	        try {
	            pst.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return cliente;
	}

	
	
	public Cliente consultarPorId(int id) throws ClassNotFoundException {
	    String sql = "SELECT * FROM tb_cliente WHERE id=?";
	    Cliente cliente = null;

	    try (Connection connection = Conexao.getConnectionPostgres();
	         PreparedStatement pst = connection.prepareStatement(sql)) {

	        // Definindo o ID a ser pesquisado
	        pst.setInt(1, id);

	        // Executando a consulta
	        try (ResultSet rst = pst.executeQuery()) {
	            if (rst.next()) {
	               
	                cliente = new Cliente();
	                cliente.setId(rst.getInt("id"));
	                cliente.setNome(rst.getString("nome"));
	                cliente.setCpf(rst.getString("cpf"));
	               
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }

	    return cliente;
	}


	
	public void delete(int id) {
	    PreparedStatement pst = null;
	    String sql = "DELETE FROM tb_cliente WHERE id = ?";

	    Connection connection = null;
	    try {
	        connection = Conexao.getConnectionPostgres();
	        connection.setAutoCommit(false);

	        pst = connection.prepareStatement(sql);
	        pst.setInt(1, id);
	        pst.execute();

	        connection.commit();
	    } catch (Exception e) {
	        try {
	            connection.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            pst.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}



	public List<EntidadeDominio> findAll(EntidadeDominio entidadeDominio) {
	    PreparedStatement pst = null;
	    Cliente cliente = (Cliente) entidadeDominio;
	    List<EntidadeDominio> clienteList = new ArrayList<>();
	    String sql = "SELECT * FROM tb_cliente";

	    try {
	        connection = Conexao.getConnectionPostgres();

	        // Aplicando filtro com ID do cliente caso tenha sido definido
	        if (cliente != null && cliente.getId() > 0) {
	            sql = sql + " WHERE id=?";
	            pst = connection.prepareStatement(sql);
	            pst.setInt(1, cliente.getId());
	        } else {
	            pst = connection.prepareStatement(sql);
	        }

	        System.out.println(pst.toString());

	       
	        pst.execute();

	        ResultSet rst = pst.getResultSet();
	        while (rst.next()) {
	            Cliente clienteEncontrado = new Cliente();
	            clienteEncontrado.setId(rst.getInt("id"));
	            clienteEncontrado.setNome(rst.getString("nome"));
	            clienteEncontrado.setCpf(rst.getString("cpf"));
	            clienteEncontrado.setDtCadastro(rst.getTimestamp("dt_cadastro"));
	            clienteEncontrado.setEndereco(new Endereco()); 
	            clienteEncontrado.setDataNascimento(rst.getString("dt_nascimento"));
	            clienteEncontrado.setTelefone1(rst.getString("telefone1"));
	            clienteEncontrado.setTelefone2(rst.getString("telefone2"));
	            clienteEncontrado.setNumCartao(rst.getString("num_cartao"));
	            clienteEncontrado.setDataValCartao(rst.getString("data_val_cartao"));
	            clienteEncontrado.setCVVCartao(rst.getString("cvv_cartao"));
	            clienteList.add(clienteEncontrado);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            pst.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return clienteList;
	}



	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
}
