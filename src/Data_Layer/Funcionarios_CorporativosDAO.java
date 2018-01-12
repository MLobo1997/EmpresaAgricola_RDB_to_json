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
        String str;
        ArrayList<Funcionario> f = new ArrayList<>();
        ArrayList<String> contactos = new ArrayList<>();
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Funcionarios AS F\n" +
                                                         "LEFT JOIN Contactos AS C\n" +
                                                         "ON F.NIF = C.Funcionarios_NIF\n" +
                                                         "WHERE F.Quintas_ID IS NULL;");
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                if (i == 0 || f.get(i - 1).NIF != rs.getInt("NIF")) {
                    f.add(i, new Funcionario());

                    contactos = new ArrayList<>();

                    f.get(i).NIF = rs.getInt("NIF");
                    f.get(i).primNome = rs.getString("PrimNome");
                    f.get(i).ultNome = rs.getString("UltNome");
                    f.get(i).dataNascimento = rs.getDate("DataNascimento");
                    f.get(i).IBAN = rs.getString("IBAN");
                    f.get(i).cargo = null;
                    f.get(i).email = rs.getString("Email");

                    if ((str = rs.getString("NrTelemovel")) != null) {
                        f.get(i).nrTelemovel.add(str);
                    }

                    i++;
                }
                else {
                    f.get(i - 1).nrTelemovel.add(rs.getString("NrTelemovel"));
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
