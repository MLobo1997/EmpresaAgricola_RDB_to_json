package Documents;


import com.fasterxml.jackson.annotation.JsonRawValue;

public class PlantacaoComDataFim extends Plantacao{
    @JsonRawValue
    public String DataFim;

    public PlantacaoComDataFim (Plantacao p) {
        super(p);
    }

}
