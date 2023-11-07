package util;

import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import lombok.ToString;

@ToString
public class Resultado {
    private String msg;
    private List<EntidadeDominio> resultado = new ArrayList<>();

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<EntidadeDominio> getResultado() {
        return resultado;
    }

    public void setResultado(List<EntidadeDominio> resultado) {
        this.resultado = resultado;
    }

    public void addItemResultado(EntidadeDominio entidade){
        this.resultado.add(entidade);
    }

    public Resultado(String msg, List<EntidadeDominio> resultado) {
        this.msg = msg;
        this.resultado = resultado;
    }

    public Resultado(String msg) {
        this.msg = msg;
    }

    public Resultado(String msg, EntidadeDominio entidadeDominio){
        this.msg = msg;
        this.resultado.add(entidadeDominio);
    }
    public Resultado() {
    }
}
