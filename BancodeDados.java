import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BancodeDados {

    // conexão com banco de dados
    public static void database() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemaProdutos", "postgres", "Estacio@123");
            System.out.print("Conectado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
        } finally {
            try {
                if (conexao != null)
                    conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexao");
            }
        }
    }

    // cadastro de produtos
    public static void cadastrar(Product produto) {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemaProdutos", "postgres", "Estacio@123");

            String cadastrar = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";
            PreparedStatement conec = conexao.prepareStatement(cadastrar);
            conec.setString(1, produto.getNome());
            conec.setDouble(2, produto.getPreco());
            conec.setInt(3, produto.getQuantidade());

            conec.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");

            conec.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            try {
                if (conexao != null) conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão");
            }
        }
    }

    // consulta de produtos
    public static List<Product> listarProdutos() {
        List<Product> produtos = new ArrayList<>();
        Connection conexao = null;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemaProdutos", "postgres", "Estacio@123");

            String consulta = "SELECT id, nome, preco, quantidade FROM produtos";
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                produtos.add(p);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao consultar produtos: " + e.getMessage());
        } finally {
            try {
                if (conexao != null) conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro no fechamento de conexão: " + ex.getMessage());
            }
        }

        return produtos;
    }

    // atualizar produto
    public static void atualizar(Product produto) {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemaProdutos", "postgres", "Estacio@123");

            String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto com ID " + produto.getId() + " não encontrado.");
            }

            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        } finally {
            try {
                if (conexao != null) conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

    // excluir produto
    public static void excluir(int id) {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemaProdutos", "postgres", "Estacio@123");

            String sql = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Produto com ID " + id + " não encontrado.");
            }

            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
        } finally {
            try {
                if (conexao != null) conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }
}


