package command;

import util.Resultado;
import dominio.EntidadeDominio;

public class ExcluirCMD extends AbstractCommand{
    @Override
    public Resultado executar(EntidadeDominio entidade) {
        return this.fachada.excluir(entidade);
    }
}
