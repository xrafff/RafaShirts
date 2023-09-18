package command;

import util.Resultado;
import dominio.EntidadeDominio;

public class SalvarCMD extends AbstractCommand{

    @Override
    public Resultado executar(EntidadeDominio entidade) {
        return this.fachada.salvar(entidade);
    }
}
