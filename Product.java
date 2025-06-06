public class Product {
    private static int contador = 1;

    private int id;
    private String nome;
    private double preco;
    private int quantidade;

    public Product() {
    }

    public Product(String nome, double preco, int quantidade) {
        this.id = contador++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
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
        return String.format("ID: %d | NOME: %s | PREÇO: R$ %.3f | QUANTIDADE: %d", id, nome, preco, quantidade);
    }
}

