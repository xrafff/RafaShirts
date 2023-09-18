package command;

import util.Resultado;
import dominio.EntidadeDominio;

public interface ICommand {
    public Resultado executar(EntidadeDominio entidade);
}
