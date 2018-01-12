package Data_Layer;

import Collections.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Funcionarios_CorporativosDAO {
    private Connection con;


    public ArrayList<Funcionario> getAll() { // APENAS PARA TESTES, NAO DA PARA USAR O MM NOME
        ArrayList<Funcionario> f = new ArrayList<>();
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Funcionarios AS F\n" +
                                                         "LEFT JOIN Contactos AS C\n" +
                                                         "ON F.NIF = C.Funcionarios_NIF\n" +
                                                         "WHERE F.Quintas_ID IS NULL;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f.add(new Funcionario(  rs.getInt("NIF"),
                                        rs.getString("PrimNome"),
                                        rs.getString("UltNome"),
                                        rs.getDate("DataNascimento"),
                                        rs.getString("IBAN"),
                                        null,
                                        rs.getString("Email"),
                                        null));

            }

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

        return f;
    }

}
