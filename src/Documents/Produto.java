package Documents;

public class Produto extends Document{
    public String Nome;
    public double pH_Ideal;
    public int Humidade_Ideal;
    public int DuracaoCresc_dias;
    public double Valor_euros_m2;

    @Override
    public String toString() {
        return "Produto{" +
                "Nome='" + Nome + '\'' +
                ", pH_Ideal=" + pH_Ideal +
                ", Humidade_Ideal=" + Humidade_Ideal +
                ", DuracaoCresc_dias=" + DuracaoCresc_dias +
                ", Valor_euros_m2=" + Valor_euros_m2 +
                '}';
    }
}
