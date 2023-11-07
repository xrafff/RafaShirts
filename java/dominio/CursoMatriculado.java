package dominio;

public class CursoMatriculado extends EntidadeDominio {
    private String nome;

    public CursoMatriculado(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}