package Data_Layer;

import Documents.Document;
import Documents.Produto;

import java.lang.annotation.Documented;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO{

    public static ArrayList<Document> getAll() {
        ArrayList<Document> r = new ArrayList<>();
        Connection con = null;

        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Produtos;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();

                p.Nome = rs.getString("Nome");
                p.pH_Ideal = rs.getDouble("pH_ideal");
                p.Humidade_Ideal = rs.getInt("Humidade_Ideal");
                p.DuracaoCresc_dias = rs.getInt("DuracaoCresc_dias");
                p.Valor_euros_m2 = rs.getInt("Valor_euros_m2");

                r.add(p);
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
