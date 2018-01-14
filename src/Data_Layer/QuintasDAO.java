package Data_Layer;

import Documents.Document;
import Documents.Endereco;
import Documents.Funcionario;
import Documents.Quinta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuintasDAO {

    private static ArrayList<Document> getAllFuncionarios (int Quinta) {
        ArrayList<Document> r = null;
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Funcionarios AS F\n" +
                                                        "LEFT JOIN Contactos AS C\n" +
                                                        "ON F.NIF = C.Funcionarios_NIF\n" +
                                                        "LEFT JOIN Cargos AS Ca\n" +
                                                        "ON Ca.ID = F.Cargo_id\n" +
                                                        "WHERE Quintas_id = ?");
            ps.setInt(1, Quinta);
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

    public static ArrayList<Document> getAll () {
        ArrayList<Document> r = new ArrayList<>();
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Quintas");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Quinta q = new Quinta();

                q.AreaUtil_m2 = rs.getInt("AreaUtil_m2");
                q.Endereco = new Endereco();
                q.Endereco.CodLocalidade = rs.getInt("CodLocalidade");
                q.Endereco.CodRua = rs.getInt("CodRua");
                q.Endereco.NrPorta = rs.getInt("NrPorta");
                q.Nome = rs.getString("Nome");
                q.Funcionarios = getAllFuncionarios(rs.getInt("ID"));
                q.Lotes = null;
                q.Maquinas = null;

                r.add(q);
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
        return r;
    }
}
