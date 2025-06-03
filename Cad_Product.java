//Cad_Product

package com.mycompany.cad_product;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;


public class Cad_Product {
    
    public static void main(String[] args) {
        // Array(lista) para guardar os produtos cadastrados
        ArrayList<Product> List_Product = new ArrayList<>(); 
        
        //Criando a janela principal
        JFrame window =new JFrame("CADASTRO DE PRODUTOS"); 

        JPanel container=new JPanel();//

        container.setLayout(null);
        
        // Criação do titulo principal e das caixas de texto
        JLabel titulo = new JLabel("Cadastro de Produtos");
        titulo.setFont(new Font("cortana", Font.PLAIN, 30));
        titulo.setBounds(300,30,800,100);
        container.add(titulo);
        
        JLabel nome = new JLabel("NOME: ");
        nome.setFont(new Font("cortana", Font.PLAIN, 25));
        nome.setBounds(100,100,800,100);
        container.add(nome);
        
        JLabel preco = new JLabel("PREÇO: ");
        preco.setFont(new Font("cortana", Font.PLAIN, 25));
        preco.setBounds(85,150,800,100);
        container.add(preco);
        
        JLabel qtd = new JLabel("QUANTIDADE: ");
        qtd.setFont(new Font("cortana", Font.PLAIN, 25));
        qtd.setBounds(12,200,800,100);
        container.add(qtd);
        
        // Criação das caixa de texto 
        TextField text_field_1 =new TextField(500);
        text_field_1.setFont(new Font("Arial", Font.PLAIN, 24));
        text_field_1.setBounds(200, 133, 200, 30);
        window.add(text_field_1);
        
        TextField text_field_2 =new TextField(500);
        text_field_2.setFont(new Font("Arial", Font.PLAIN, 24));
        text_field_2.setBounds(200, 183, 200, 30);
        window.add(text_field_2);
        
        TextField text_field_3 =new TextField(500);
        text_field_3.setFont(new Font("Arial", Font.PLAIN, 24));
        text_field_3.setBounds(200, 233, 200, 30);
        window.add(text_field_3);
        
        // Criação de onde a lista dos produtos cadastrados serão exibidas
        JTextArea areaLista = new JTextArea();
        areaLista.setEditable(false);
        areaLista.setFont(new Font("cortana", Font.PLAIN, 18));
        areaLista.setBounds(90, 300, 800, 400);
        container.add(areaLista);
        
        // Criação dos botoes de cadastrar e editar, com suas ações
        JButton btn_cad=new JButton("Cadastrar");
        
        btn_cad.setFont(new Font("cortana", Font.PLAIN, 20)); 
        btn_cad.setBackground(Color.GREEN);
        btn_cad.setBounds(600,143,130,30);
        window.add(btn_cad);
        
        btn_cad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = text_field_1.getText().trim();
                String precoStr = text_field_2.getText().trim();
                String qtdStr = text_field_3.getText().trim();

                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O nome não pode estar vazio.");
                    return;
                }
                try {
                    double preco = Double.parseDouble(precoStr);
                    int quantidade = Integer.parseInt(qtdStr);

                    if (preco < 0 || quantidade < 0) {
                        JOptionPane.showMessageDialog(null, "Preço e quantidade devem ser positivos.");
                        return;
                    }
                    Product produto = new Product(nome, preco, quantidade);
                    List_Product.add(produto);

                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    
                    text_field_1.setText("");
                    text_field_2.setText("");
                    text_field_3.setText("");
                    
                    StringBuilder sb = new StringBuilder();
                    for (Product p : List_Product) {
                        sb.append(p.toString()).append("\n");
                    }
                    areaLista.setText(sb.toString());
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Preço e quantidade devem ser valores numéricos válidos.");
                }
            }
        });
        
        JButton btn_saveChanges = new JButton("Salvar alterações");
        btn_saveChanges.setFont(new Font("cortana", Font.PLAIN, 20));
        btn_saveChanges.setBackground(Color.GREEN);
        btn_saveChanges.setBounds(600, 243, 200, 30);
        btn_saveChanges.setVisible(false);
        window.add(btn_saveChanges);
        
        JButton btn_edit = new JButton("Editar");
        btn_edit.setFont(new Font("cortana", Font.PLAIN, 20));
        btn_edit.setBackground(Color.CYAN);
        btn_edit.setBounds(600, 193, 130, 30);
        window.add(btn_edit);
        
        final Product[] produtoParaEditar = new Product[1];

        btn_edit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String inputId = JOptionPane.showInputDialog("Digite o ID do produto que deseja editar:");
            if (inputId == null) return;
            try {
                int idProcurado = Integer.parseInt(inputId);
                produtoParaEditar[0] = null;

                for (Product p : List_Product) {
                    if (p.getId() == idProcurado) {
                        produtoParaEditar[0] = p;
                        break;
                    }
                }

                if (produtoParaEditar[0] == null) {
                    JOptionPane.showMessageDialog(null, "Produto com ID " + idProcurado + " não encontrado.");
                    return;
                }

                // Preenche os campos com o produto escolhido
                text_field_1.setText(produtoParaEditar[0].getNome());
                text_field_2.setText(String.valueOf(produtoParaEditar[0].getPreco()));
                text_field_3.setText(String.valueOf(produtoParaEditar[0].getQuantidade()));

                // Mostra botão de salvar
                btn_cad.setVisible(false);
                btn_saveChanges.setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido.");
            }
        }
    });
        btn_saveChanges.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (produtoParaEditar[0] == null) {
                JOptionPane.showMessageDialog(null, "Nenhum produto selecionado para edição.");
                return;
            }

            try {
                String novoNome = text_field_1.getText().trim();
                double novoPreco = Double.parseDouble(text_field_2.getText().trim());
                int novaQtd = Integer.parseInt(text_field_3.getText().trim());

                if (novoNome.isEmpty() || novoPreco < 0 || novaQtd < 0) {
                    JOptionPane.showMessageDialog(null, "Dados inválidos.");
                    return;
                }

                produtoParaEditar[0].setNome(novoNome);
                produtoParaEditar[0].setPreco(novoPreco);
                produtoParaEditar[0].setQuantidade(novaQtd);

                JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");

                StringBuilder sb = new StringBuilder();
                for (Product p : List_Product) {
                    sb.append(p.toString()).append("\n");
                }
                areaLista.setText(sb.toString());

                text_field_1.setText("");
                text_field_2.setText("");
                text_field_3.setText("");
                btn_saveChanges.setVisible(false);

                produtoParaEditar[0] = null;
                
                btn_cad.setVisible(true);
                btn_saveChanges.setVisible(false);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Preço e quantidade devem ser numéricos.");
            }
        }
    });

       
        container.setBackground(Color.GRAY);
        window.add(container);
        
        window.setSize(1000,800);
        
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
