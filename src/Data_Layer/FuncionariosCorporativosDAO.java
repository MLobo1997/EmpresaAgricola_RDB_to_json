package Data_Layer;

import Documents.Cargo;
import Documents.Document;
import Documents.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionariosCorporativosDAO {


    public static ArrayList<Document> getAll() { // APENAS PARA TESTES, NAO DA PARA USAR O MM NOME
        Funcionario tmp = null;
        String str;
        ArrayList<Document> r = null;
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Funcionarios AS F\n" +
                                                             "LEFT JOIN Contactos AS C\n" +
                                                             "ON F.NIF = C.Funcionarios_NIF\n" +
                                                             "LEFT JOIN Cargos AS Ca\n" +
                                                             "ON Ca.ID = F.Cargo_id\n" +
                                                             "WHERE F.Quintas_ID IS NULL;");
            ResultSet rs = ps.executeQuery();

            r = Funcionario.loadFuncionarios(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return r;
    }

}
