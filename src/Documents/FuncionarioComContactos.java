package Documents;

import java.util.ArrayList;

public class FuncionarioComContactos extends Funcionario{
    public ArrayList<String> NrTelemovel;

    public FuncionarioComContactos (Funcionario f) {
        super(f);
        NrTelemovel = new ArrayList<>();
    }
}
