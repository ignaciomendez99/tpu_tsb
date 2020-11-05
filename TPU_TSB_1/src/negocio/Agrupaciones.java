package negocio;

import soporte.TSBHashtable;
import soporte.TextFile;

import javax.xml.soap.Text;
import java.util.Collection;

public class Agrupaciones
{
    private static TSBHashtable inicial;
    private TSBHashtable conteo;

    public Agrupaciones()
    {
        conteo = new TSBHashtable();
        for (Object o: inicial.values())
        {
            Agrupacion a = (Agrupacion) o;
            conteo.put(a.getCodigo(), new Agrupacion(a.getCodigo(), a.getNombre()));
        }

    }

    public static void leerAgrupaciones(String path)
    {
        TextFile textFileAgrupaciones = new TextFile(path + "\\descripcion_postulaciones.dsv");
        inicial = textFileAgrupaciones.identificarAgrupaciones();
    }

    public Agrupacion getAgrupacion(String codAgrupacion)
    {
      return (Agrupacion) conteo.get(codAgrupacion);
    }


    public Collection getResultados()
    {
        return conteo.values();
    }
}
