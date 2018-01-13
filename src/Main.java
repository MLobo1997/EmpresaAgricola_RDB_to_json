import Data_Layer.FuncionariosCorporativosDAO;
import Data_Layer.PlantacoesDAO;
import Data_Layer.ProdutosDAO;
import Documents.Document;
import Documents.Produto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {

    public static StringBuilder toJSON (Document d, StringBuilder str) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));


        if (str == null) {
            str = new StringBuilder();
        }

        try {
            str.append(mapper.writeValueAsString(d));
            str.append('\n');
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return str;
    }
    private static void writeCollection (ArrayList<Document> d, final String filename) {
        StringBuilder str = null;

        for (Document doc : d) {
            str = toJSON(doc, str);
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

    private static void shellExec(String cmd) {
        Process cmdProc;
        try {
            cmdProc = Runtime.getRuntime().exec(cmd);


            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(cmdProc.getInputStream()));
            String line;
            while ((line = stdoutReader.readLine()) != null) {
                System.out.println(line);
            }

            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(cmdProc.getErrorStream()));
            while ((line = stderrReader.readLine()) != null) {
                System.err.println(line);
            }

            if(cmdProc.exitValue() == 0){
                System.out.println("Success!!");
            } else {
                System.err.println("Error!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) {

        writeCollection(FuncionariosCorporativosDAO.getAll(), "corporativos.json");

        writeCollection(ProdutosDAO.getAll(), "produtos.json");

        writeCollection(PlantacoesDAO.getAll(), "plantacoes.json");

        shellExec("mongoimport --db EmpresaAgricola --collection Funcionarios_Corporativos --file corporativos.json");
        shellExec("mongoimport --db EmpresaAgricola --collection Produtos --file produtos.json");
        shellExec("mongoimport --db EmpresaAgricola --collection Plantacoes --file plantacoes.json");
    }
}
