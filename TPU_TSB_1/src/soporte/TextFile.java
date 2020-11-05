package soporte;

import negocio.Agrupacion;
import negocio.Region;
import negocio.Resultados;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFile
{
    private File file;

    public TextFile(String filePath)
    {
        file = new File(filePath);
    }

    public String readHeader()
    {
        String linea = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                scanner.nextLine();
                break;
            }
        } catch (FileNotFoundException e) {
            System.out.println("¡No se pudo leer el archivo!");
        }
        return linea;

    }

    public TSBHashtable identificarAgrupaciones()
    {
        String linea = "", campos[];
        TSBHashtable tsbHashtable = new TSBHashtable(10);
        Agrupacion agrupacion;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                linea = scanner.nextLine();
                campos = linea.split("\\|");
                if (campos[0].compareTo("000100000000000") == 0)
                {
                    agrupacion = new Agrupacion(campos[2], campos[3]);
                    tsbHashtable.put(agrupacion.getCodigo(), agrupacion);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("¡No se pudo leer el archivo!");
        }

        return tsbHashtable;
    }

    public void totalizadorVotosAgrupacion(TSBHashtable tsbHashtableAgrupaciones)
    {
        String linea = "", campos[];
        Agrupacion agrupacion;
        int votos;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                linea = scanner.nextLine();
                campos = linea.split("\\|");
                if (campos[4].compareTo("000100000000000") == 0)
                {
                    agrupacion = (Agrupacion) tsbHashtableAgrupaciones.get(campos[5]);
                    votos = Integer.parseInt(campos[6]);
                    agrupacion.sumarVotos(votos);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("¡No se pudo leer el archivo!");
        }

    }

    public Region identificarRegiones()
    {
        String linea = "", campos[], codigo, nombre;
        Region pais = new Region("00", "Argentina");
        Region distrito, seccion;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                linea = scanner.nextLine();
                campos = linea.split("\\|");
                codigo = campos[0];
                nombre = campos[1];
                switch (codigo.length()) {
                    case 2:
                        //Distrito
                        distrito = pais.getOrPutSubregion(codigo);
                        distrito.setNombre(nombre);
                        break;
                    case 5:
                        //Seccion
                        distrito = pais.getOrPutSubregion(codigo.substring(0, 2));
                        seccion = distrito.getOrPutSubregion(codigo);
                        seccion.setNombre(nombre);
                        break;
                    case 11:
                        //Circuito
                        distrito = pais.getOrPutSubregion(codigo.substring(0, 2));
                        seccion = distrito.getOrPutSubregion(codigo.substring(0, 5));
                        seccion.agregarSubregion(new Region(codigo, nombre));
                        break;



                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("¡No se pudo leer el archivo!");
        }

        return pais;
    }

    public void sumarVotosPorRegion(Resultados resultados)
    {
        String linea = "", campos[];
        int votos;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                linea = scanner.nextLine();
                campos = linea.split("\\|");
                if (campos[4].compareTo("000100000000000") == 0)
                {
                    votos = Integer.parseInt(campos[6]);
                    //Acumulamos los votos del país
                    resultados.sumarVotos("00", votos, campos[5]);
                    //Acumulamos los votos del distrito, seccion y circuito de la mesa
                    for (int i = 0; i < 3; i++){
                        resultados.sumarVotos(campos[i], votos, campos[5]);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("¡No se pudo leer el archivo!");
        }
    }
}
