package interfaces;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import trabalhofinal.Dados;
import trabalhofinal.Turmas;

public class InterfacePrincipal extends JFrame {

    private JTabbedPane painel;
    private ArrayList<ConteudoFrame> abas = new ArrayList(); // lista de tabelas para cada turma
    private JLabel text0;
    private JButton button0;
    private JButton button1;
    private int quant_abas = 0;
    private Dados dados = Dados.getInstance();

    public InterfacePrincipal() {
        super("main");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setSize(750, 560);
        setVisible(true);
        setLayout(new FlowLayout());

        button0 = new JButton("Criar turma");
        add(button0);

        button1 = new JButton("Deletar turma");
        add(button1);

        painel = new JTabbedPane();
        add(painel);

        //abas.add(new ConteudoFrame()); // adiciona na lista 1 turma
        //painel.addTab("Turma 1", abas.get(0)); // adiciona a aba interface
        //dados.turmas.add(new Turmas());

        ButtonHandler handler = new ButtonHandler();
        button0.addActionListener(handler);
        button1.addActionListener(handler);
        
        //ConteudoFrame lul = abas.get(0);
        //lul.addAluno("Evandro"); //exemplo para addTeste
        //se for testar, porfavor trocar quant_abas = 1, else =0; obrigado, de nada;

    }

    public void criarTurma() {
        abas.add(new ConteudoFrame());
        painel.addTab("Nova turma", abas.get(abas.size() - 1));
        dados.turmas.add(new Turmas());
        quant_abas++;
       
    }

    public void removerTurma() {
        if (painel.getSelectedIndex() > -1) {
            abas.remove(painel.getSelectedIndex()); // tem que ser get + 1
            dados.turmas.remove(painel.getSelectedIndex()); // nao inverter a ordem
            painel.removeTabAt(painel.getSelectedIndex()); // nao inverter a ordem
            quant_abas--;
        }
    }
    
    public int getAba(){
        return painel.getSelectedIndex();
    }

    private class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent ae) {

            if (ae.getSource() == button0) {
                if (quant_abas < 7) {
                    criarTurma();
                }
            } else {
                if (ae.getSource() == button1) {
                    if (quant_abas > 0) {
                        removerTurma();
                    }
                }
            }
        }
    }
}
