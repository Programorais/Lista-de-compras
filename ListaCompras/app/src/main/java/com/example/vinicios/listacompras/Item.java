package com.example.vinicios.listacompras;

/**
 * Created by vinicios on 28/07/16.
 */
public class Item {

    private     String  nome;
    private     String  Comentario;
    private     boolean importante;

    public Item(String nome, String comentario, boolean importante){
        this.Comentario = comentario;
        this.nome = nome;
        this.importante = importante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public boolean isImportante() {
        return importante;
    }

    public void setImportante(boolean importante) {
        this.importante = importante;
    }
}
