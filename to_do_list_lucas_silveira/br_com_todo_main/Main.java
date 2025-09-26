package to_do_list_lucas_silveira.br_com_todo_main;

import to_do_list_lucas_silveira.br_com_todo_model.Tarefa;
import to_do_list_lucas_silveira.br_com_todo_model.TarefaPrioritaria;
import to_do_list_lucas_silveira.br_com_todo_service.Gerenciador;
import to_do_list_lucas_silveira.br_com_todo_util.Arquivo;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static boolean desejaContinuar(Scanner scanner) {
        System.out.println("\nDeseja voltar ao menu inicial ou sair?");
        System.out.println("1. Voltar ao menu");
        System.out.println("2. Sair do sistema");
        System.out.print("Escolha uma opção: ");
    
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
    
        if (escolha == 2) {
            System.out.println("Saindo do sistema...");
            return false; 
        }
    
        return true; // Volta ao menu
        }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gerenciador gerenciador = new Gerenciador();

        // Carrega as tarefas ao iniciar
        List<Tarefa> tarefas = Arquivo.carregarTarefas();
        if (tarefas != null) {
                gerenciador.setTarefas(tarefas);
        }

        int opcao = 0;
        do {

            Gerenciador.limparTela();

            System.out.println("\n############# TO DO LIST #############");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Remover tarefa");
            System.out.println("3. Editar tarefa");
            System.out.println("4. Visualizar tarefas");
            System.out.println("5. Marcar como concluída");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer4

            Gerenciador.limparTela();



            switch (opcao) {
                case 1:
                    System.out.print("Nome da tarefa: ");
                    String nome = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.println("É uma tarefa prioritaria? (1.Sim 2.Não)");

                    boolean prioritaria = scanner.nextInt() == 1;
                    scanner.nextLine();
                    
                    Tarefa novaTarefa;
                        if (prioritaria) {
                            novaTarefa = new TarefaPrioritaria(nome, descricao, true);
                        } else {
                            novaTarefa = new Tarefa(nome, descricao);
                        }


                    gerenciador.adicionarTarefa(novaTarefa); // Chama o método do Gerenciador

                    Gerenciador.limparTela();

                    Arquivo.salvarTarefas(gerenciador.getTarefas()); // Salva após adicionar

                    if (!desejaContinuar(scanner)) {
                        opcao = 6; // Força a saída do loop
                    }

                    Gerenciador.limparTela();

                    break;
                case 2:

                    System.out.println("\n### LISTA DE TAREFAS ###");
                    gerenciador.listarTarefas(true);    

                    System.out.print("\nNome da tarefa a ser removida: ");
                    String tarefaRemover = scanner.nextLine();
                    gerenciador.removerTarefa(tarefaRemover); // Chama o método do Gerenciador
                    Arquivo.salvarTarefas(gerenciador.getTarefas()); // Salva após remover

                    if (!desejaContinuar(scanner)) {
                        opcao = 6; // Força a saída do loop
                    }

                    Gerenciador.limparTela();

                    break;

                case 3:

                    System.out.println("\n### LISTA DE TAREFAS ###");
                    gerenciador.listarTarefas(true);    

                    System.out.print("Nome da tarefa a ser editada: ");
                    String tarefaEditar = scanner.nextLine();
                    System.out.print("Novo nome da tarefa: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Nova descrição: ");
                    String novaDescricao = scanner.nextLine();
                    gerenciador.editarTarefa(tarefaEditar, novoNome, novaDescricao); // Chama o método do Gerenciador
                    Arquivo.salvarTarefas(gerenciador.getTarefas()); // Salva após editar

                    if (!desejaContinuar(scanner)) {
                        opcao = 6; // Força a saída do loop
                    }

                    Gerenciador.limparTela();

                    break;

                case 4:

                    int opcao4;
                    boolean opcaoValida = false;
                
                    do {
                        System.out.println("Deseja visualizar também as tarefas concluídas?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não");
                    
                        try {
                            opcao4 = scanner.nextInt(); // Lê a opção do usuário
                            Gerenciador.limparTela();

                            if (opcao4 == 1) {
                                System.out.println("\n### LISTA DE TAREFAS ###");
                                gerenciador.listarTarefas(true); // Chama o método do Gerenciador
                                opcaoValida = true;
                            } else if (opcao4 == 2) {
                                System.out.println("\n### LISTA DE TAREFAS ###");
                                gerenciador.listarTarefas(false); // Chama o método do Gerenciador
                                opcaoValida = true;
                            } else {
                                System.out.println("Opção inválida. Por favor, escolha 1 ou 2.");
                            }
                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, digite um número.");
                            scanner.nextLine(); // Limpa o buffer do scanner para evitar loop infinito
                        }
                    } while (!opcaoValida); // Continua até o usuário digitar uma opção válida

                    if (!desejaContinuar(scanner)) {
                        opcao = 6; // Força a saída do loop
                    }

                    break;
                case 5:

                    System.out.println("\n### LISTA DE TAREFAS ###");
                    gerenciador.listarTarefas(false);

                    System.out.print("Nome da tarefa para concluir: ");
                    String nomeTarefa = scanner.nextLine();
                    gerenciador.marcarComoConcluida(nomeTarefa); // Chama o método do Gerenciador
                    Arquivo.salvarTarefas(gerenciador.getTarefas()); // Salva após alteração
                    
                    if (!desejaContinuar(scanner)) {
                        opcao = 6; // Força a saída do loop
                    }

                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }
}

