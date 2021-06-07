package Interpreter;
import Team.*;
import Players.*;
import Auxiliar.*;
import Auxiliar.Comparators.*;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SportSimView {
    private final Scanner input;
    
    
    public SportSimView() {
        this.input = new Scanner(System.in);
    }

    public void welcomeMessage(){
        System.out.println("Bem vindo ao simulador de desportos.");
    }


    public char viewMainMenu() {
        System.out.println("""
                Selecione uma das opções abaixo com o seu índice.

                1- Executar simulação
                2- Equipas
                3- Jogadores
                4- Importar...
                5- Exportar...
                6- Créditos
                Q- Saír
                """);

        return (input.nextLine().charAt(0));
    }

    public char viewTeamMenu() {
        System.out.println("(1)Criar   (2)Editar/Visualizar   (3)Remover   (4)Ordenar   (5)Filtrar   (Q)Regressar");
        return (input.nextLine().charAt(0));
    }

    public void printTeamsTable(TreeSet<Team> teams) {
        int i = 1;
        System.out.println("----------------------------------\n" +
                "#  Nome                  G     V   D   E");
        for (Team team : teams) {
            System.out.printf("%d- %-20s  %-5d %-3d %-3d %-3d%n",
                    i++,
                    team.getTeamName(),
                    team.getTeamGlobalAbillity(),
                    team.getWins(),
                    team.getLosses(),
                    team.getTies());
        }
        System.out.println("----------------------------------");
    }

    public char viewEditTeam(Team t) {
        System.out.println("ID: " + t.getTeamID() +
                "\nHabilidade Global: " + t.getTeamGlobalAbillity() +
                "\n(1)Nome: " + t.getTeamName() +
                "\n(2)Vitórias: " + t.getWins() +
                "\n(3)Derrotas: " + t.getLosses() +
                "\n(4)Empates: " + t.getTies() +
                "\n(5)Jogadores..." +
                "\n\n(q)Cancelar\n(Q)Guardar e saír");
        return (input.nextLine().charAt(0));
    }

    public char viewFilterTeams(TeamFilter filter) {
        System.out.printf("""
                            Opções de filtro:
                            (1)Nome: %s
                            (2)Habilidade Global: %s
                            (3)Rácio de vitórias: %s
                            (4)Vitórias: %s
                            (5)Derrotas: %s
                            (6)Empates: %s
                            (7)Jogador: %s
                            (R)Remover filtro
                            (q)Cancelar
                            (Q)Confirmar
                            """
                , filter.getTeamName()
                , filter.getScoreBounds().toString()
                , filter.getWinRatio().toString()
                , filter.getWinBounds().toString()
                , filter.getLoseBounds().toString()
                , filter.getTieBounds().toString()
                , filter.getPlayerName());

        return input.nextLine().charAt(0);
    }

    private void printPlayers (TreeSet<Player> players) {
        int i = 1;
        for (Player player : players) {
            System.out.printf("%d- %-15s %-20s  %-3d\n",
                    i++,
                    "PLAYERSPORTHERE",
                    player.getName(),
                    player.overallAbility());
        }
    }

    public boolean exitConfirm () {
        System.out.println("Foram realizadas alterações que ainda não foram guardadas.\n" +
                "Tem a certeza que quer fechar o programar? (Y/N)");
        return (input.nextLine().toUpperCase().charAt(0) == 'Y');
    }

    public void printTeamOrders() {
        System.out.println("(1)Nome\n(2)Vitórias\n(3)Derrotas\n(4)Empates");
    }

    public int askForInt(String query, int min, int max){
        System.out.println(query);
        int r;
        boolean except = false;
        try {
            r = Integer.parseInt(input.nextLine());
        }
        catch (NumberFormatException e) {
            r = min;
            except = true;
        }
        while (except || r > max || r < min) {
            System.out.printf("Número inválido.\n%s\n", query);
            except = false;
            try {
                r = Integer.parseInt(input.nextLine());
            }
            catch (NumberFormatException e) {
                r = min;
                except = true;
            }
        }

        return r;
    }

    public String askForString(String query, int min, int max) {
        System.out.println(query);
        String name = input.nextLine();

        while (name.length() > max || name.length() < min) {
            System.out.printf("O comprimento tem de ser entre %d e %d caractéres.\n%s\n", min, max, query);
            name = input.nextLine();
        }
        return name;
    }

    public void printUnrecognizedCommandError() {
        System.out.println("Comando não reconhecido.");
    }

    public void teamNameExistsError() {System.out.println("The team name already exists.");}
}
