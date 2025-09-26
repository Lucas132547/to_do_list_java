package to_do_list_lucas_silveira.br_com_todo_service;

import to_do_list_lucas_silveira.br_com_todo_model.Tarefa;
import to_do_list_lucas_silveira.br_com_todo_model.TarefaPrioritaria;

import java.util.ArrayList;
import java.util.List;

public class Gerenciador {
    
    private List<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa) ;
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void removerTarefa(String nome) {
        tarefas.removeIf(t -> t.getNome().equalsIgnoreCase(nome));
        System.out.println("Tarefa removida!");
    }

    public void editarTarefa(String nome, String novoNome, String novaDescricao) {
        for (Tarefa t : tarefas) {
            if (t.getNome().equalsIgnoreCase(nome)) {
                t.setNome(novoNome);
                t.setDescricao(novaDescricao);
                System.out.println("Tarefa editada com sucesso!");
                return;
            }
        }
        System.out.println("Tarefa não encontrada.");
    }

     public void listarTarefas(boolean incluirConcluidas) {
        for (Tarefa t : tarefas) {
            if (incluirConcluidas || !t.isConcluida()) {
                if (t instanceof TarefaPrioritaria) {
                    System.out.println(t);
                } else {
                    System.out.println(t);
                }
            }
        }
    }

    public void marcarComoConcluida(String nome) {
        for (Tarefa t : tarefas) {
            if (t.getNome().equalsIgnoreCase(nome)) {
                t.marcarComoConcluida();
                System.out.println("Tarefa marcada como concluída!");
                return;
            }
        }
        System.out.println("Tarefa não encontrada.");
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}
