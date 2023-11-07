package dao;

import java.sql.SQLException;
import java.util.List;

import dominio.EntidadeDominio;

public interface IDAO {
	public EntidadeDominio salvar(EntidadeDominio entidade);
	 public EntidadeDominio alterar (EntidadeDominio entidadeDominio)throws SQLException;
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);
	void delete(int id);
	public List<EntidadeDominio> findAll(EntidadeDominio entidadeDominio, String filtroCampo, String filtroValor);
}
