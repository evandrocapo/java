package trabalhofinal;

import interfaces.InterfacePrincipal;
import java.util.ArrayList;

public class Dados {
   private static Dados instance; // instancia
   public ArrayList<Turmas> turmas = new ArrayList(); // turmas
   public static InterfacePrincipal janela;
    
    public static Dados getInstance(){
        if(instance == null){
            instance = new Dados();
        }
        return instance;
    }
    
    public static InterfacePrincipal getInterface(){
        if(janela == null){
            janela = new InterfacePrincipal();
        }
        return janela;
    }
    
    public void setValoresNotas(int turma, int aluno, int numero_prova,float nota){
        turmas.get(turma).aluno.get(aluno).setNotas(numero_prova, nota);
    }
    
    public float getMedia(int turma, int aluno){
        return turmas.get(turma).aluno.get(aluno).getMedia();
    }
    
}
