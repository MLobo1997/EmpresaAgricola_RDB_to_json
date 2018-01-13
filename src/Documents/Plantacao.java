package Documents;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class Plantacao extends Document{
    public String Quinta;
    public String Lote;
    public String Produto;
    public int AreaPlantada_m2;
    @JsonRawValue
    public String DataInicio;
    @JsonRawValue
    public String DataFim;
}
