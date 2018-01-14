package Documents;

import com.fasterxml.jackson.annotation.JsonRawValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Funcionario extends Document{
    public int NIF;
    public String PrimNome;
    public String UltNome;
    @JsonRawValue
    public String DataNascimento;
    public String IBAN;
    public Cargo Cargo;
    public String Email;
    public ArrayList<String> NrTelemovel;

    public Funcionario() {
        this.NrTelemovel = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "NIF=" + NIF +
                ", primNome='" + PrimNome + '\'' +
                ", ultNome='" + UltNome + '\'' +
                ", dataNascimento=" + DataNascimento +
                ", IBAN='" + IBAN + '\'' +
                ", cargo=" + Cargo +
                ", email='" + Email + '\'' +
                ", contactos=" + NrTelemovel +
                '}';
    }

    public static ArrayList<Document> loadFuncionarios (ResultSet rs) {
        ArrayList<Document> f = new ArrayList<>();
        Funcionario tmp = null;
        String str;
        try {
            while (rs.next()) {
                if (tmp == null || tmp.NIF != rs.getInt("NIF")) {
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
                    } else
                        System.err.println("A BD tem um erro no funcionário " + tmp.NIF);

                    f.add(tmp);
                } else {
                    tmp.NrTelemovel.add(rs.getString("NrTelemovel"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return f;
    }
}
