package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.Conexao;

public abstract class JdbcDao  implements IDAO{

    protected Connection connection;
    protected String tableName;
    protected String idColumn;
    protected boolean closeConnection = true;

    public JdbcDao(Connection connection, String tableName, String idColumn) {
        this.connection = connection;
        this.tableName = tableName;
        this.idColumn = idColumn;
    }

    public JdbcDao(String tableName, String idColumn) {
        this.tableName = tableName;
        this.idColumn = idColumn;
    }

    protected void openConnection(){
        try {
            if(this.connection == null || this.connection.isClosed()){
            	connection = Conexao.getConnectionPostgres();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM " + tableName + " WHERE " + idColumn + "=?";

        try {
            openConnection();
            //Para capturar os erro e em caso de erro dar o rollback
            this.connection.setAutoCommit(false);

            // Chamando DAOS Dependentes

            //Criando PreparedStatement
            pst = connection.prepareStatement(sql);

            //Setando variaveis do PreparedStatement
            pst.setInt(1,id);

            //Executando query
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
