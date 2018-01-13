package Documents;

import com.fasterxml.jackson.annotation.JsonRawValue;

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

}
