package negocio;

import soporte.TSBHashtable;

import java.util.Collection;

public class Region
{
    private String codigo;
    private String nombre;
    private TSBHashtable tsbHashtableSubregiones;

    public Region(String codigo, String nombre)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        tsbHashtableSubregiones = new TSBHashtable();
    }

    public void agregarSubregion(Region region)
    {
        tsbHashtableSubregiones.put(region.codigo, region);
    }

    public Collection getSubregiones()
    {
        return tsbHashtableSubregiones.values();
    }

    public Region getSubregion(String codigo)
    {
        return (Region) tsbHashtableSubregiones.get(codigo);
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "(" + codigo + ")" + " | " + nombre;
    }

    public Region getOrPutSubregion(String codigo)
    {
        Region subregion = (Region) tsbHashtableSubregiones.get(codigo);
        if (subregion == null)
            tsbHashtableSubregiones.put(codigo, new Region(codigo, ""));
        return (Region) tsbHashtableSubregiones.get(codigo);

    }
}
