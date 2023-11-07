package command;

import util.Resultado;
import dominio.EntidadeDominio;

public class VisualizarCMD extends  AbstractCommand{
    @Override
    public Resultado executar(EntidadeDominio entidade) {
        return this.fachada.visualizar(entidade);
    }
}
