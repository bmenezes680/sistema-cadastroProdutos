//Product

package com.mycompany.cad_product;

public class Product {
    private static int contador = 1;

    private int id;
    private String nome;
    private double preco;
    private int quantidade;

    public Product(String nome, double preco, int quantidade) {
        this.id = contador++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public int getId() {
    return id;
    }  
    
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
   
    public int getQuantidade() {
        return quantidade;
    }    
     
    public void setNome(String nome) {
    this.nome = nome;
    }
     
    public void setPreco(double preco) {
    this.preco = preco;
    }
    
    public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | NOME: " + nome + " | PREÇO: R$ " + preco + " | QUANTIDADE: " + quantidade;
    }
}
