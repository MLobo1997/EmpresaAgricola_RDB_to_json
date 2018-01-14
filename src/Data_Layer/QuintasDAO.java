package Data_Layer;

import Documents.*;

import javax.print.Doc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuintasDAO {

    private static ArrayList<Document> getAllLotes (int Quinta) {
        ArrayList<Document> r = new ArrayList<>();
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Lotes WHERE Quintas_ID = ?");
            ps.setInt(1, Quinta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Lote l = new Lote();

                l.Area_m2 = rs.getInt("Area_m2");
                l.Designio = rs.getString("Designio");
                l.EmDescanso = rs.getBoolean("EmDescanso");
                l.Humidade_perc = rs.getInt("Humidade_perc");
                l.pH = rs.getDouble("pH");
                l.InicioDescanso = "ISODate(\"" + rs.getDate("InicioDescanso") + "T00:00:00Z\")";

                r.add(l);
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


    private static ArrayList<Document> getAllMaquinas (int Quinta) {
        ArrayList<Document> r = new ArrayList<>();
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM QuintasTemMaquinas AS QTM\n" +
                    "JOIN Maquinas AS M\n" +
                    "ON M.ID = QTM.Maquinas_ID\n" +
                    "WHERE QTM.Quintas_ID = ?");
            ps.setInt(1, Quinta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Maquina m = new Maquina();

                m.Classe = rs.getString("Classe").charAt(0);
                m.DespesaMensal_euros = rs.getInt("DespesaMensal_euros");
                m.Modelo = rs.getString("Modelo");
                m.Tipo = rs.getString("Tipo");
                m.Quantas = rs.getInt("Quantas");

                r.add(m);
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
                q.Lotes = getAllLotes(rs.getInt("ID"));
                q.Maquinas = getAllMaquinas(rs.getInt("ID"));

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
