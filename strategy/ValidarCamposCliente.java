package strategy;


import dominio.EntidadeDominio;
import dominio.Cliente;


public class ValidarCamposCliente implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        if(cliente.getCpf() != null &&
        		cliente.getNome().length() > 0 &&
        		cliente.getcursoMatriculado().length()  > 0 &&
        		cliente.getNomeMae().length()  > 0 &&
        		cliente.getNomePai().length() >0 ){
            return "";
        }else{
            return "Erro validacao cliente. Todos os campos obrigatorios precisam ser preenchidos!;";
        }

    }
}
