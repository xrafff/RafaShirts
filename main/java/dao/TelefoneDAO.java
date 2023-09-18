package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Conexao;
import dominio.EntidadeDominio;
import dominio.Telefone;

public class TelefoneDAO {
		public EntidadeDominio salvar(EntidadeDominio entidade) {
		    PreparedStatement pst = null;
		    Telefone telefone = (Telefone) entidade;
	
		    Connection connection = null;
			try {
		        connection = Conexao.getConnectionPostgres();
		        connection.setAutoCommit(false);
	
		        StringBuilder sql = new StringBuilder();
		        sql.append("INSERT INTO tb_telefone(tipo, numero) VALUES (?, ?)");
	
		        pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
	
		        pst.setString(1, telefone.getTipo());
		        pst.setString(2, telefone.getNumero());
	
		        pst.executeUpdate();
	
		        ResultSet rs = pst.getGeneratedKeys();
		        int idTelefone = 0;
		        if (rs.next()) {
		            idTelefone = rs.getInt(1);
		        }
		        telefone.setId(idTelefone);
	
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
		
		public EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
		    PreparedStatement pst = null;
		    Telefone telefone = (Telefone) entidadeDominio;
		    StringBuilder sql = new StringBuilder();
		    sql.append("UPDATE tb_telefone SET tipo=?, numero=? WHERE id=?");

		    Connection connection = null;
			try {
		        connection = Conexao.getConnectionPostgres();
		        connection.setAutoCommit(false);

		        pst = connection.prepareStatement(sql.toString());

		        pst.setString(1, telefone.getTipo());
		        pst.setString(2, telefone.getNumero());
		        pst.setInt(3, telefone.getId());

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

		    return telefone;
		}
			public void delete(int id) {
			    PreparedStatement pst = null;
			    String sql = "DELETE FROM tb_telefone WHERE id = ?";
	
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
			    List<EntidadeDominio> telefoneList = new ArrayList<>();
			    String sql = "SELECT * FROM tb_telefone";

			    Connection connection = null;
				try {
			        connection = Conexao.getConnectionPostgres();

			        pst = connection.prepareStatement(sql);

			        // Executando query
			        pst.execute();

			        ResultSet rst = pst.getResultSet();
			        while (rst.next()) {
			            Telefone telefone = new Telefone(sql, sql);
			            telefone.setId(rst.getInt("id"));
			            telefone.setTipo(rst.getString("tipo"));
			            telefone.setNumero(rst.getString("numero"));
			            telefoneList.add(telefone);
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

			    return telefoneList;
			}
}

