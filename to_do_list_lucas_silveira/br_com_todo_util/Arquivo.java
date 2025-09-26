package to_do_list_lucas_silveira.br_com_todo_util;

import to_do_list_lucas_silveira.br_com_todo_model.Tarefa;
import java.io.*;
import java.util.List;

public class Arquivo {
    private static final String FILE_NAME = "to_do_list_lucas_silveira/tarefas.txt";

    //Função para salvar tarefas em um arquivo txt
    public static void salvarTarefas(List<Tarefa> tarefas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tarefas);
            System.out.println("Tarefas salvas com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas.");
        }
    }

    //Função para carregar tarefas de um arquivo txt
    @SuppressWarnings("unchecked")
    public static List<Tarefa> carregarTarefas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Tarefa>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar tarefas.");
            return null;
        }
    }
}
