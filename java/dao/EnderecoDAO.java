
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
import dominio.Endereco;
import dominio.EntidadeDominio;



public class EnderecoDAO implements IDAO {	
	private Connection connection;
	private boolean ctrlTransaction = true;
	
	public EnderecoDAO(){}
	
	public EnderecoDAO(Connection connection){
		this.connection = connection;
	}
	
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		
		PreparedStatement pst=null;
		Endereco end = (Endereco)entidade;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_endereco(nome_cidade_fk, logradouro, numero, dt_cadastro, cep) ");
		sql.append("VALUES (?, ?, CAST(? AS INTEGER), ?, ?)");

		try {
		    if (connection == null) {
		        connection = Conexao.getConnectionPostgres();
		    } else {
		        ctrlTransaction = false;
		    }

		    connection.setAutoCommit(false);

		    pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

		    pst.setString(1, end.getCidade().getNome());
		    pst.setString(2, end.getLogradouro());
		    pst.setString(3, end.getNumero());
		    pst.setTimestamp(4, new Timestamp(end.getDtCadastro().getTime()));
		    pst.setString(5, end.getCep());

		    pst.executeUpdate();
					
			ResultSet rs = pst.getGeneratedKeys();
			int idEnd=0;
			if(rs.next())
				idEnd = rs.getInt(1);
			end.setId(idEnd);
			
			connection.commit();					
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		}finally{
			if(ctrlTransaction){
				try {
					pst.close();
					if(ctrlTransaction)
						connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		return entidade;
	}

	public EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
	    PreparedStatement pst = null;
	    Endereco endereco = (Endereco) entidadeDominio;
	    StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE tb_endereco SET nome_cidade_fk=?, logradouro=?, ");
	    sql.append("numero=?, dt_cadastro=?, cep=? WHERE cod_endereco=?");
	    try {
	        connection = Conexao.getConnectionPostgres();
	        // Para capturar os erros e, em caso de erro, fazer o rollback
	        this.connection.setAutoCommit(false);

	        // Chamando DAOs Dependentes

	        // Criando PreparedStatement
	        pst = connection.prepareStatement(sql.toString());

	        // Setando variáveis do PreparedStatement

	        pst.setString(1, endereco.getCidade().getNome());
	        pst.setString(2, endereco.getLogradouro());
	        pst.setInt(3, Integer.parseInt(endereco.getNumero()));
	        pst.setTimestamp(4, new Timestamp(endereco.getDtCadastro().getTime()));
	        pst.setString(5, endereco.getCep());
	        pst.setInt(6, endereco.getId());

	        // Executando query
	        pst.executeUpdate();

	        this.connection.commit();

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

	    return endereco;
	}
	
	@SuppressWarnings("unchecked")
	public List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio) {
        PreparedStatement pst = null;
        String sql = "SELECT * FROM tb_endereco WHERE cod_endereco=?";
        EntidadeDominio entidade= null;

        try {
        	connection = Conexao.getConnectionPostgres();

            // Chamando DAOS Dependentes

            //Criando PreparedStatement
            pst = connection.prepareStatement(sql);

            //Setando variaveis do PreparedStatement
            pst.setInt(1,entidadeDominio.getId());

            //Executando query
            pst.execute();

            ResultSet rst = pst.getResultSet();
            if(rst.next()){
                rst.getInt("cod_endereco");
                rst.getString("nome_cidade_fk");
                rst.getString("cpf");
                rst.getString("numero");
                rst.getString("cep");
                                             
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

        return (List<EntidadeDominio>) entidade;
    }

	
	public List<EntidadeDominio> findAll(EntidadeDominio entidadeDominio, String filtroCampo, String filtroValor) {
	    PreparedStatement pst = null;
	    Endereco endereco = (Endereco) entidadeDominio;
	    List<EntidadeDominio> enderecoList = new ArrayList<>();
	    String sql = "SELECT * FROM tb_endereco";

	    try {
	        connection = Conexao.getConnectionPostgres();

	        // Verifique se um filtro foi especificado
	        if (filtroCampo != null && filtroValor != null && !filtroCampo.isEmpty() && !filtroValor.isEmpty()) {
	            // Aplique o filtro com base no campo e no valor especificados
	            if (filtroCampo.equals("nome_cidade_fk")) {
	                sql = sql + " WHERE cod_cidade_fk IN (SELECT cod_cidade FROM tb_cidade WHERE " + filtroCampo + " = ?)";
	            } else {
	                sql = sql + " WHERE " + filtroCampo + " = ?";
	            }
	            pst = connection.prepareStatement(sql);
	            pst.setString(1, filtroValor);
	        } else if (endereco != null && endereco.getId() > 0) {
	            // Se não houver um filtro, use o ID para a pesquisa
	            sql = sql + " WHERE cod_endereco=?";
	            pst = connection.prepareStatement(sql);
	            pst.setInt(1, endereco.getId());
	        } else {
	            pst = connection.prepareStatement(sql);
	        }

	        System.out.println(pst.toString());

	        pst.execute();

	        ResultSet rst = pst.getResultSet();
	        while (rst.next()) {
	            Endereco enderecoEncontrado = new Endereco();
	            enderecoEncontrado.setId(rst.getInt("cod_endereco"));
	            enderecoEncontrado.setCep(rst.getString("cep"));
	            enderecoEncontrado.setNumero(rst.getString("numero"));
	            enderecoList.add(enderecoEncontrado);
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

	    return enderecoList;
	}

	public void delete(int id) {
	    PreparedStatement pst = null;
	    String sql = "DELETE FROM tb_endereco WHERE cod_endereco = ?";

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
	
	


	

}
