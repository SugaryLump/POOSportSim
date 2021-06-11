package MVCClasses;
import Team.*;
import Players.*;

import java.util.TreeSet;

import Auxiliar.Pair;
import Game.Game;
import Game.Simulator;

import java.util.Scanner;

public class SportSimView {
    private final Scanner input;
    
    
    public SportSimView() {
        this.input = new Scanner(System.in);
    }

    public void welcomeMessage(){
        System.out.println("Bem vindo ao simulador de desportos.");
    }

    public String viewTeamSelect() {
        System.out.println("Insira o nome de uma equipa para a sua simulação:");

        return (input.nextLine());
    }

    public void game_stats(Simulator s){
	    System.out.println("""
			    Estado da Simulação

			    Resultado-""");
	    System.out.print(s.get_game().getGameScore().getL());
	    System.out.print(s.get_game().getTeams().getL().getTeamName());
	    System.out.println("\n");
	    System.out.print(s.get_game().getGameScore().getR());
	    System.out.print(s.get_game().getTeams().getR().getTeamName());
    }

    public void printResults(Game g){
	    //dar print do resultado final com o numero de "eventos"
    }

    public void teamNotFound(){
	    //Dar print de um erro a dizer que a equpa nao existe;
    }

    public void printGame(Pair<Team,Team> teams){
	    //Dar print as estatisticas de modo Fancy-ish
    }

    public char getPause(){
	    //Durante a simulação o jogo pode ser "pausado", mas ainda nao foi implementado nada nesse estado
	    //pedir ao utilizador se quer pausar o jogo e se sim dar retunr de um char (1 if yes)
        return askForString("Pretende pausar o jogo?", 1, 1).charAt(0);
    }

    public char viewSim() {
        System.out.println("""
                Selecione uma das opções abaixo com o seu índice.

                1- Selecionar equipas
                2- Inicializar a simulação
                Q- Saír
                """);

        return (input.nextLine().charAt(0));
    }

    public void printCredits() {System.out.println("Alexandre Flores, A93220\nRita Lino, A93196\nMiguel Gomes, A93294");}

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
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------\n" +
                "#  Nome                                      G     V   D   E");
        for (Team team : teams) {
            System.out.printf("%d- %-40s  %-5d %-3d %-3d %-3d%n",
                    i++,
                    team.getTeamName(),
                    team.getTeamGlobalAbillity(),
                    team.getWins(),
                    team.getLosses(),
                    team.getTies());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------\n");
    }

    public char viewPlayerMenu() {
        System.out.println("(1)Criar   (2)Editar/Visualizar   (3)Remover   (4)Ordenar   (5)Filtrar   (Q)Regressar");
        return (input.nextLine().charAt(0));
    }

    public char viewTeamPlayerMenu() {
        System.out.println("(1)Adicionar   (2)Editar/Visualizar   (3)Remover   (4)Ordenar   (5)Filtrar   (Q)Regressar");
        return (input.nextLine().charAt(0));
    }

    public void printPlayersTable(TreeSet<Player> players) {
        int i = 1;
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "#  Nome                                                Desporto              Equipa                                     Ida. Habilidade");
        for (Player p : players) {
            System.out.printf("%d- %-50s  %-20s  %-40s  %-3d %-3d%n",
                    i++,
                    p.getName(),
                    p.getSport(),
                    p.currentTeam(),
                    p.getAge(),
                    p.overallAbility());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    public char viewEditTeam(Team t) {
        System.out.println("Habilidade Global: " + t.getTeamGlobalAbillity() +
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

    public char viewFilterPlayers(PlayerFilter filter) {
        System.out.printf("""
                            Opções de filtro:
                            (1)Nome: %s
                            (2)Desporto: %s
                            (3)Equipa: %s
                            (4)Idade: %s
                            (R)Remover filtro
                            (q)Cancelar
                            (Q)Confirmar
                            """
                , filter.getPlayerName()
                , filter.getSport()
                , filter.getTeamName()
                , filter.getAgeBounds().toString());

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

    public void printPlayerOrders() {
        System.out.println("(1)Nome\n(2)Desporto\n(3)Equipa\n(4)Idade\n(5)Habilidade Geral");
    }

    public int viewSportSelection() {
        System.out.println("(1)Futebol");
        return (askForInt("Selecione um desporto.", 1, 1));
    }

    public String viewEditFootballPlayer(FootballPlayer p) {
        System.out.println("Desporto: " + p.getSport() +
                "Habilidade Geral: " + p.overallAbility() +
                "\nEquipas: " + p.getHistory().toString()  +
                "\n(1)Nome: " + p.getName() +
                "\n(2)Idade: " + p.getAge() +
                "\n(3)Posição: " + p.getPosition() +
                "\n\nEstatísticas" +
                "\n(4)Velocidade: " + p.getVelocity() +
                "\n(5)Resistência: " + p.getResistance() +
                "\n(6)Destreza: " + p.getDexterity() +
                "\n(7)Impulso: " + p.getImpulse() +
                "\n(8)Jogo de Cabeça: " + p.getHeadGame() +
                "\n(9)Remate: " + p.getShootingSkill() +
                "\n(10)Passe: " + p.getPassingSkill() +
                "\n(11)Cruzamento: " + p.getCrossingSkill() +
                "\n(12)Elasticidade: " + p.getElasticity() +
                "\n(13)Recuperação de Bola: " + p.getBallRecuperation() +
                "\n(14)Força " + p.getStrength() +
                "\n(15)Receção " + p.getReceptionSkill() +
                "\n\n(q)Cancelar\n(Q)Guardar e saír");
        return (input.nextLine());
    }

    public String viewFootballPositions() {
        System.out.println("(1)Avancado\n" +
                "(2)Defesa\n" +
                "(3)Lateral\n" +
                "(4)Guarda-Redes\n" +
                "(5)Medio");
        String pos[] = {"Avancado", "Defesa", "Lateral", "Guarda-Redes", "Medio"};
        return pos[askForInt("Selecione uma posição.", 1, 5) - 1];
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

    public static void unrecognizedCommandError() {
        System.out.println("Comando não reconhecido.");
    }

    public static void nameExistsError() {System.out.println("Esse nome já existe.");}

    public static void noValidPlayersError() {System.out.println("Nenhum jogador pode ser adicionado.");}

    public static void playerNotFoundError(String player) {System.out.println(player + " não foi encontrado.");}

    public static void playerAlreadyInTeamError(String player) {System.out.println(player + " já está numa equipa");}

    public static void gameNotLoadedError(){
        System.out.println("Nenhuma simulação está carregada");
    }

    public static void showException (Exception e) {System.out.println(e.toString());}
}
