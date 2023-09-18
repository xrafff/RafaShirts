package command;

import util.Resultado;
import dominio.EntidadeDominio;

public class AlterarCMD extends AbstractCommand{
    @Override
    public Resultado executar(EntidadeDominio entidade) {
        return this.fachada.alterar(entidade);
    }
}
