package interfaces;

import arquivos.AbrirArquivosJFileChooser;
import arquivos.SalvarArquivosJFileChooser;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import trabalhofinal.Dados;
import trabalhofinal.Turmas;

public class ConteudoFrame extends JPanel {

    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JScrollPane scrollpane;
    private DefaultTableModel modelo = new DefaultTableModel();
    private JTable tabela;
    private JPanel panel1;
    private JPanel panel2;
    private GridBagConstraints c = new GridBagConstraints();

    public ConteudoFrame() {
        setLayout(new GridBagLayout());

        //organizar o botão 1
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        //

        button0 = new JButton("Importar arquivo");
        add(button0, c);

        // organizar o botão 2        
        c.gridx = 1;
        //
        button1 = new JButton("Exportar turma");
        add(button1, c);

        ///*
        // organizar o botão 2        
        c.gridx = 2;
        //
        button2 = new JButton("Criar avaliação");
        add(button2, c);

        // organizar o botão 2        
        c.gridx = 3;
        //
        button3 = new JButton("Finalizar tabela");
        add(button3, c);

        // */
        tabela = new JTable(modelo);
        scrollpane = new JScrollPane(tabela);

        modelo.addColumn("Nome");
        tabela.getTableHeader().setReorderingAllowed(false);

        // organizar a tabela
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 5;
        c.ipadx = 250; // tamanho total da TABELA;
        //
        add(scrollpane, c);

        ButtonHandler handler = new ButtonHandler();
        button0.addActionListener(handler);
        button1.addActionListener(handler);
        button2.addActionListener(handler);
        button3.addActionListener(handler);
    }

    public void addAluno(String nome) {
        modelo.addRow(new Object[]{nome, null});
    }

    public void addAvaliacao(String nome) {
        modelo.addColumn(nome);
    }

    public void removerAvaliacao() {
        tabela.removeColumn(tabela.getColumnModel().getColumn(tabela.getSelectedColumn()));
    }

    private class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == button0) {
                Dados dados = Dados.getInstance();
                AbrirArquivosJFileChooser file = new AbrirArquivosJFileChooser();
                int a = file.abrir();
                int turma = Dados.getInterface().getAba(); // pega o numero da aba, correspondente a turma;

                if (a == 0) {
                    try {
                        file.salvar(); // pega todos os valores
                    } catch (Exception ex) {
                        System.out.println("Erro ao abrir o arquivo");
                    }

                    file.descompactar(turma);

                    for (int i = 0; i < dados.turmas.get(turma).aluno.size(); i++) {
                        addAluno(dados.turmas.get(turma).aluno.get(i).nome);
                    }

                    button0.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Arquivo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                if (ae.getSource() == button1) {
                    Dados dados = Dados.getInstance();
                    SalvarArquivosJFileChooser file = new SalvarArquivosJFileChooser();
                    int a = file.abrir();

                    if (a == 0) {
                        try {
                            file.exportar(); // pega todos os valores
                        } catch (Exception ex) {
                            System.out.println("Erro ao exportar o arquivo");
                        }
                    } 
            }else {
                    if (ae.getSource() == button2) {
                        // tabela.removeColumn(tabela.getColumnModel().getColumn(tabela.getColumnCount()-1));
                        String nome = JOptionPane.showInputDialog("Qual o nome da avaliacao?");
                        if (nome != null) {
                            int opcao = JOptionPane.showConfirmDialog(null, "O nome da avaliacao é: " + nome + " ?");
                            if (opcao == JOptionPane.YES_OPTION) {
                                Dados dados = Dados.getInstance();
                                int turma = Dados.getInterface().getAba();

                                String nome_peso = JOptionPane.showInputDialog("Qual o peso da avaliacao?");
                                opcao = JOptionPane.showConfirmDialog(null, "O peso da avaliacao é: " + nome_peso + " ?");

                                if (opcao == JOptionPane.YES_OPTION) {

                                    float peso;
                                    boolean ok;

                                    try {
                                        peso = Float.parseFloat(nome_peso);
                                        ok = true;
                                    } catch (NumberFormatException ex) {
                                        peso = 0;
                                        ok = false;
                                        JOptionPane.showMessageDialog(null, "Erro, nota não criada.");
                                    }

                                    if (ok) {
                                        addAvaliacao(nome);
                                        //dados.turmas.add(new Turmas()); // bug erro meu, nao tem add turma em add nota lul
                                        dados.turmas.get(turma).addNota(nome, peso);

                                       // System.out.println(turma);
                                       // System.out.println("nome da materia: " + dados.turmas.get(turma).nota.get(0).nome);
                                       // System.out.println("peso: " + dados.turmas.get(turma).nota.get(0).peso);
                                    }

                                }
                            }
                        }

                    } else {
                        if (ae.getSource() == button3) {
                            Dados dados = Dados.getInstance();
                            int turma = Dados.getInterface().getAba();
                            int i, j;

                            if (button2.isEnabled()) {
                                modelo.addColumn("Média Geral");
                            }
                            button2.setEnabled(false);

                            try {
                                for (i = 0; i < dados.turmas.get(turma).aluno.size(); i++) {
                                    dados.turmas.get(turma).aluno.get(i).notas.clear(); // limpa todas as notas
                                    dados.turmas.get(turma).aluno.get(i).nome = modelo.getValueAt(i, 0).toString();
                                    for (j = 1; j < dados.turmas.get(turma).nota.size() + 1; j++) { // +1 pq é menor e etc, e pra nao precisar mudar o J
                                        String valor = modelo.getValueAt(i, j).toString();
                                        dados.turmas.get(turma).aluno.get(i).notas.add(Float.valueOf(valor));
                                       // System.out.println("I = " + i + " J = " + j);
                                       // System.out.println(modelo.getValueAt(i, j));
                                    }
                                   // System.out.println("");
                                }

                                for (i = 0; i < dados.turmas.get(turma).aluno.size(); i++) {
                                    dados.turmas.get(turma).aluno.get(i).calcularMedia(dados.turmas.get(turma).nota);
                                }

                                for (i = 0; i < modelo.getRowCount(); i++) {
                                    modelo.setValueAt(dados.getMedia(turma, i), i, modelo.getColumnCount() - 1); // i = linhas e alunos;
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Insira numeros em todas as tabelas.");
                            }
                        }
                    }
                }
        }
    }
}
}
