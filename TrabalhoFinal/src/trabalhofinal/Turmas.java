package trabalhofinal;

import java.util.ArrayList;

public class Turmas {
    public ArrayList<Alunos> aluno = new ArrayList(); // array com todos os alunos
    public ArrayList<Notas> nota = new ArrayList(); // array com todos os tipos de notas
    
    public void addAlunos(Alunos aluno){
        this.aluno.add(aluno);
    }
    
    public void addNota(String nome, float peso){
        this.nota.add(new Notas(nome,peso));
    }
}
