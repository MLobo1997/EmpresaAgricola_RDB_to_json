package Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

public class Funcionario {
    public int NIF;
    public String primNome;
    public String ultNome;
    public Date dataNascimento;
    public String IBAN;
    public Cargo cargo;
    public String email;
    public String [] contactos;

    public Funcionario(int NIF, String primNome, String ultNome, Date dataNascimento, String IBAN, Cargo cargo, String email, String[] contactos) {
        this.NIF = NIF;
        this.primNome = primNome;
        this.ultNome = ultNome;
        this.dataNascimento = dataNascimento;
        this.IBAN = IBAN;
        this.cargo = cargo;
        this.email = email;
        this.contactos = contactos;
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
                ", contactos=" + Arrays.toString(contactos) +
                '}';
    }

    public String toJSON () {
        String str = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            str = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return str;
    }
}
