package controle;

import java.util.List;

import dao.ClienteDAO;
import dao.IDAO;
import dominio.Cliente;
import dominio.EntidadeDominio;

public class Fachada implements IFachada {

	public String salvar(EntidadeDominio entidade) {
		Cliente Cliente = (Cliente)entidade;
		StringBuilder sb = new StringBuilder();
		
				
		validaNull(sb, Cliente.validarDados());
		validaNull(sb, Cliente.validarDados());
		
		if(sb.length()==0){
			Cliente.complementarDtCadastro();
			IDAO dao = new ClienteDAO();
			dao.salvar(Cliente);
			return "Cliente SALVO COM SUCESSO!";
		}		
		
		return sb.toString();
	}

	public String alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public String excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validaNull(StringBuilder sb, String msg){
		if(msg != null){
			sb.append(msg);
		}
	}

}
