package Documents;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class Plantacao extends Document{
    public String Quinta;
    public String Lote;
    public String Produto;
    public int AreaPlantada_m2;
    public double Despesa_euros;
    @JsonRawValue
    public String DataInicio;

    public Plantacao() {
    }

    public Plantacao (Plantacao p) {
        this.Quinta = p.Quinta;
        this.Lote = p.Lote;
        this.Produto = p.Produto;
        this.AreaPlantada_m2 = p.AreaPlantada_m2;
        this.Despesa_euros = p.Despesa_euros;
        this.DataInicio = p.DataInicio;
    }
}
