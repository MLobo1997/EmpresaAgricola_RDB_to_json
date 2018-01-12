package Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Funcionario {
    public int NIF;
    public String primNome;
    public String ultNome;
    public Date dataNascimento;
    public String IBAN;
    public Cargo cargo;
    public String email;
    public ArrayList<String> nrTelemovel;

    public Funcionario() {
        this.nrTelemovel = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "NIF=" + NIF +
                ", primNome='" + primNome + '\'' +
                ", ultNome='" + ultNome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", IBAN='" + IBAN + '\'' +
                ", cargo=" + cargo +
                ", email='" + email + '\'' +
                ", contactos=" + nrTelemovel +
                '}';
    }

    public StringBuilder toJSON (StringBuilder str) {
        ObjectMapper mapper = new ObjectMapper();

        if (str == null) {
            str = new StringBuilder();
        }

        try {
            str.append(mapper.writeValueAsString(this));
            str.append('\n');
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return str;
    }
}
