package fachada;

import util.Resultado;
import dominio.EntidadeDominio;

public interface IFachada {
    public Resultado salvar(EntidadeDominio entidade);
    public Resultado alterar(EntidadeDominio entidade);
    public Resultado excluir(EntidadeDominio entidade);
    public Resultado visualizar(EntidadeDominio entidade);
}
