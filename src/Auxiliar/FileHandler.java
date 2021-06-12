package Auxiliar;

import MVCClasses.SportSimModel;
import MVCClasses.SportSimView;
import Players.*;
import Team.*;
import Auxiliar.Comparators.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class FileHandler {
    public static void exportModelToFile(SportSimModel model, String filename) throws IOException {
        File obj = new File(filename);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(obj));
        oos.writeObject(model);
        oos.close();
    }

    public static SportSimModel importModelFromFile(String filename) throws IOException, ClassNotFoundException {
        File obj = new File(filename);
        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(obj));
        return (SportSimModel)oos.readObject();
    }
}
