package FullParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import MVCClasses.SportSimModel;

import Team.*;
import Players.*;

public class Parser {

    public static SportSimModel parse(String fileName) throws LinhaIncorretaException {
        SportSimModel model = new SportSimModel();
        List<String> linhas = lerFicheiro(fileName);
        Team ultima = null; Player j = null;
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Equipa" -> {
                    Team e = new Team(linhaPartida[1]);
                    model.addTeam(e);
                    ultima = e;
                }
                case "Guarda-Redes", "Defesa", "Medio", "Lateral", "Avancado" -> {
                    j = FootballPlayer.parse(linhaPartida[0], linhaPartida[1]);
                    model.addPlayer(j);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.addPlayer(j); //if no team was parsed previously, file is not well-formed
                }
                case "Jogo" -> {
                    String[] args = linhaPartida[1].split(",");
                    int scoreT1 = Integer.parseInt(args[2]);
                    int scoreT2 = Integer.parseInt(args[3]);
                    Team t1 = model.getTeamByName(args[0]);
                    Team t2 = model.getTeamByName(args[1]);
                    if (scoreT1 > scoreT2) {
                        t1.setWins(t1.getWins() + 1);
                        t2.setLosses(t2.getLosses() + 1);
                    } else if (scoreT2 > scoreT1) {
                        t2.setWins(t2.getWins() + 1);
                        t1.setLosses(t1.getLosses() + 1);
                    } else {
                        t1.setTies(t1.getTies() + 1);
                        t2.setTies(t2.getTies() + 1);
                    }
                }
                default -> throw new LinhaIncorretaException();
            }
        }
        return model;
    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }


}
