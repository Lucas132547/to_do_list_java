package to_do_list_lucas_silveira.br_com_todo_model;

import java.io.Serializable; // Import necessário

public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador único para serialização

    private String nome;
    private String descricao;
    private boolean concluida;

    public Tarefa(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.concluida = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void marcarComoConcluida() {
        this.concluida = true;
    }

    @Override
    public String toString() {
        return "Tarefa: " + nome + " | Descrição: " + descricao + " | Concluída: " + (concluida ? "Sim" : "Não");
    }
}
