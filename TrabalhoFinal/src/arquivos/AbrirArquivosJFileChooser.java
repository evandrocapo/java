package arquivos;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import trabalhofinal.Alunos;
import trabalhofinal.Dados;

public class AbrirArquivosJFileChooser extends JFrame {

    private File fileName;
    private Formatter output;
    private Scanner arq;
    private String texto;
    private Dados dados = Dados.getInstance();

    public AbrirArquivosJFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Nomes", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        //System.out.println(result);
        fileName = fileChooser.getSelectedFile();
        //System.out.println(fileName);

        abrir();
        //salvar();
    }

    public int abrir() {
        if (fileName == null) {
            return -1;
        } else {
            return 0;
        }
    }

    public void salvar() throws FileNotFoundException {
        try {
            arq = new Scanner(fileName);
            texto = null;
            texto = arq.nextLine();

        } catch (IOException ioe) {
            System.out.println("Erro");
        } finally {
            arq.close();
        }

    }

    public void descompactar(int turma) {
        String[] nomes = texto.split(",\\s");

        //System.out.println(Arrays.toString(nomes));
        //System.out.println(nomes.length);

        for (int i = 0; i < nomes.length; i++) {
            dados.turmas.get(turma).addAlunos(new Alunos(nomes[i].toString()));
        }

    }

    

}
