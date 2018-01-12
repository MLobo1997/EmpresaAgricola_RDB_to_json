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
    public ArrayList<String> contactos;

    public Funcionario(int NIF, String primNome, String ultNome, Date dataNascimento, String IBAN, Cargo cargo, String email, String contacto) {
        this.NIF = NIF;
        this.primNome = primNome;
        this.ultNome = ultNome;
        this.dataNascimento = dataNascimento;
        this.IBAN = IBAN;
        this.cargo = cargo;
        this.email = email;
        this.contactos = new ArrayList<>();
        contactos.add(contacto);
    }

    void addContacto (String c) {
        contactos.add(c);
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
                ", contactos=" + contactos +
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
