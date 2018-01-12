import Collections.Funcionario;
import Data_Layer.Funcionarios_CorporativosDAO;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main (String [] args) {
        Funcionarios_CorporativosDAO f = new Funcionarios_CorporativosDAO();

        ArrayList<Funcionario> func = f.getAll();
        for(Funcionario l : func) {
            System.out.println(l.toJSON());
        }
    }
}
