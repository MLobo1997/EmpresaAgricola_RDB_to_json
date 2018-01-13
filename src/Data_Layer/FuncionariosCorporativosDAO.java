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
        ArrayList<Document> f = new ArrayList<>();
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
            int i = 0;
            while (rs.next()) {
                if (tmp == null ||  tmp.NIF != rs.getInt("NIF")) {
                    tmp = new Funcionario();

                    tmp.NIF = rs.getInt("NIF");
                    tmp.PrimNome = rs.getString("PrimNome");
                    tmp.UltNome = rs.getString("UltNome");
                    tmp.DataNascimento = "ISODate(\"" + rs.getDate("DataNascimento") + "T00:00:00Z\")";
                    tmp.IBAN = rs.getString("IBAN");
                    tmp.Cargo = null;
                    tmp.Email = rs.getString("Email");

                    if ((str = rs.getString("NrTelemovel")) != null) {
                        tmp.NrTelemovel.add(str);
                    }

                    if ((str = rs.getString("Nome")) != null) {
                        tmp.Cargo = new Cargo();
                        tmp.Cargo.Nome = str;
                        tmp.Cargo.Salario_euros = rs.getInt("Salario_euros");
                    }
                    else
                        System.err.println("A BD tem um erro no funcionário " + tmp.NIF);

                    f.add(tmp);
                    i++;
                }
                else {
                    tmp.NrTelemovel.add(rs.getString("NrTelemovel"));
                }

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
