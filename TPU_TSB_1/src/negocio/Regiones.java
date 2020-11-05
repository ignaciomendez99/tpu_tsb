package negocio;

import soporte.TextFile;

import java.util.Collection;

public class Regiones
{
    private TextFile textFileRegiones;
    private Region pais;

    public Regiones(String path)
    {
        textFileRegiones = new TextFile(path + "\\descripcion_regiones.dsv");
        //textFileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        pais = textFileRegiones.identificarRegiones();
        //textFileMesas.totalizadorVotosAgrupacion(tsbHashtableAgrupaciones);
    }

    public Collection getDistritos()
    {
        return pais.getSubregiones();
    }
}
