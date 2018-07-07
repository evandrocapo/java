package arquivos;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import trabalhofinal.Dados;

public class SalvarArquivosJFileChooser {
    private File fileName;
    private Formatter output;
    private Scanner arq;
    private String texto;
    private Dados dados = Dados.getInstance();
    
    
    public SalvarArquivosJFileChooser(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Nomes", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        //System.out.println(result);
        fileName = fileChooser.getSelectedFile();
        //System.out.println(fileName);
    }
    
    public int abrir() {
        if (fileName == null) {
            return -1;
        } else {
            return 0;
        }
    }
    
    public void exportar() {
        try {
            output = new Formatter(fileName);
            String nome;
            String notas;

            for (int i = 0; i < dados.turmas.get(Dados.getInterface().getAba()).aluno.size(); i++) {
                nome = dados.turmas.get(Dados.getInterface().getAba()).aluno.get(i).nome;
                notas = dados.turmas.get(Dados.getInterface().getAba()).aluno.get(i).notas.toString();
                
                System.out.println(nome);
                System.out.println(notas.toString());
                output.format("NOME = %s, NOTAS = %s, MEDIA = %f; \n", nome,notas,dados.turmas.get(Dados.getInterface().getAba()).aluno.get(i).media);
            }
        } catch (IOException ioe) {
            System.out.println("Erro");
        } finally {
            output.close();
        }
    }

}
