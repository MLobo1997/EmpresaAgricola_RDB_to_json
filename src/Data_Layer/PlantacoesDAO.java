package Data_Layer;

import Documents.Document;
import Documents.Plantacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlantacoesDAO {

    public static ArrayList<Document> getAll () {
        Connection con = null;
        ArrayList<Document> r = new ArrayList<>();

        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT P.AreaPlantada_m2 AS Area, P.DataInicio AS DataI, P.DataFim AS DataF, L.Designio AS Lote, Q.Nome AS Quinta, Prod.Nome AS Produto\n" +
                                                        "FROM Plantacoes as P\n" +
                                                        "JOIN Lotes AS L\n" +
                                                        "ON L.ID = P.Lotes_ID\n" +
                                                        "JOIN Quintas AS Q\n" +
                                                        "ON Q.ID = L.Quintas_ID\n" +
                                                        "JOIN Produtos AS Prod\n" +
                                                        "ON Prod.ID = P.Produto_ID\n" +
                                                        "ORDER BY P.DataInicio DESC");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Plantacao p = new Plantacao();

                p.AreaPlantada_m2 = rs.getInt("Area");
                p.DataInicio = "ISODate(\"" + rs.getDate("DataI") + "T00:00:00Z\")";
                p.DataFim = null;
                if (rs.getDate("DataF") != null) {
                    p.DataFim = "ISODate(\"" + rs.getDate("DataF") + "T00:00:00Z\")";
                }
                p.Lote = rs.getString("Lote");
                p.Quinta = rs.getString("Quinta");
                p.Produto = rs.getString("Produto");

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
