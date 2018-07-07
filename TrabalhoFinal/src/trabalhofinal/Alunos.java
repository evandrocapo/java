package trabalhofinal;

import java.util.ArrayList;

public class Alunos {
    public String nome; // nome do aluno
    public ArrayList<Float> notas = new ArrayList(); // notas de cada prova
    public float media;
    
    public Alunos(String nome){
        this.nome = nome;
    }
    
    public void addQuantNota(int quant_notas){
        notas.clear();
        for(int i=0;i<quant_notas;i++){
        notas.add(new Float(0));
        }
    }

    public float getMedia() {
        return media;
    }

    public void setNotas(int numero_nota,float nota) {
        notas.set(numero_nota, nota);
    }
    
    public void calcularMedia(ArrayList<Notas> nota_principal){
        media = 0;
        for(int i =0; i < notas.size();i++){
            media += nota_principal.get(i).peso * notas.get(i);
        }
    }
}
