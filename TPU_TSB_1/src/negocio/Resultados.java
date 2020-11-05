package negocio;

import soporte.TSBHashtable;
import soporte.TextFile;

import java.util.Collection;

public class Resultados
{
    private TSBHashtable hashtableResultados;

    public Resultados(String path)
    {
        hashtableResultados = new TSBHashtable();
        TextFile textFileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        textFileMesas.sumarVotosPorRegion(this);

    }

    public void sumarVotos(String codRegion, int votos, String codAgrupacion)
    {
        //Se busca la region en la tabla y la creamos en el caso que no exista.
        if (hashtableResultados.get(codRegion) == null)
            hashtableResultados.put(codRegion, new Agrupaciones());

            //Actualizamos el total de votos
            //int cantActualVotosRegion = (int) hashtableResultados.get(codRegion);
            //hashtableResultados.put(codRegion, cantActualVotosRegion + votos);
        Agrupaciones agrupaciones = (Agrupaciones) hashtableResultados.get(codRegion);
        agrupaciones.getAgrupacion(codAgrupacion).sumarVotos(votos);


    }

    public Collection getResultadosRegion(String codRegion)
    {
        Agrupaciones agrupaciones = (Agrupaciones) hashtableResultados.get(codRegion);
        return agrupaciones.getResultados();
    }
}
