package negocio;

public class Agrupacion
{
    private String codigo;
    private String nombre;
    private int votos;

    public Agrupacion(String codigo, String nombre)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        votos = 0;
    }


    public String getCodigo()
    {
        return codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void sumarVotos(int cantidad)
    {
        votos += cantidad;
    }

    @Override
    public String toString() {
        return "Agrupacion{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", votos=" + votos +
                '}';
    }
}
