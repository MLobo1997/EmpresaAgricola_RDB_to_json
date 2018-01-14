package Documents;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class Lote extends Document{
    public String Designio;
    public double pH;
    public int Humidade_perc;
    public boolean EmDescanso;
    @JsonRawValue
    public String InicioDescanso;
    public int Area_m2;
}
