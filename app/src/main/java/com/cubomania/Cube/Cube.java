package com.cubomania.Cube;

import android.os.Parcel;
import android.os.Parcelable;

public class Cube implements Parcelable{

    private int id;
    private String nome;
    private String tamanho;
    private String tipo;
    private String dificuldade;
    private String imagem;
    private double preco;
    private int quantidade;

    public Cube() {
        setQuantidade(1);
    }

    public Cube(Parcel source) {
        id = source.readInt();
        nome = source.readString();
        tamanho = source.readString();
        tipo = source.readString();
        dificuldade = source.readString();
        imagem = source.readString();
        preco = source.readDouble();
        quantidade = source.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(tamanho);
        dest.writeString(tipo);
        dest.writeString(dificuldade);
        dest.writeString(imagem);
        dest.writeDouble(preco);
        dest.writeInt(quantidade);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public Object createFromParcel(Parcel source) {
            return new Cube(source);
        }

        @Override
        public Cube[] newArray(int size) {
            return new Cube[size];
        }
    };
}
