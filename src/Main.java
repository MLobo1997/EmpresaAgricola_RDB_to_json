import Collections.Funcionario;
import Data_Layer.Funcionarios_CorporativosDAO;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void writeCorporativos (ArrayList<Funcionario> f) {
        StringBuilder str = null;
        final String filename = "corporativos.json";

        for (Funcionario func : f) {
            str = func.toJSON(str);
        }

        try {
            FileWriter fw = new FileWriter(filename);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(str);

            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) {
        Funcionarios_CorporativosDAO f = new Funcionarios_CorporativosDAO();

        Main.writeCorporativos(f.getAll());
    }
}
