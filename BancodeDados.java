import java.sql.*;

public class BancodeDados{
    public static void database(){
        Connection conexao = null;
            try{
                conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemaProdutos", "postgres", "123456");
                System.out.print("Conectado com sucesso!");
            } catch (SQLException e){
                System.out.println("Erro ao conectar");
            }
        finally{
            try{
                // Fechar a conexão se ela foi aberta
                if (conexao != null){
                    conexao.close();
                }
            } catch (SQLException ex){         
                System.out.println("Erro ao fechar conexao");
            }
        }
    }

    //CADASTRAR
    public static void cadastrar(Product produto){
        Connection conexao = null;
        
        try{
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemaProdutos", "postgres", "123456");

            if(conexao != null){
                String cadastrar = ("INSERT INTO produtos (nome, preco, quantidade) VALUES (?,?,?);");
                PreparedStatement conec = conexao.prepareStatement(cadastrar);
                conec.setString(1,produto.getNome());
                conec.setDouble(2,produto.getPreco());
                conec.setInt(3,produto.getQuantidade());

                conec.executeUpdate();
                System.out.println("Produto cadastrado com sucesso!");

                conec.close();
                conexao.close();
            }
                
        } catch(SQLException e){
            System.out.println("Erro para conectar.");
        }
    }
}

