package interfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import negocio.*;
import soporte.TextFile;

import java.io.File;

public class PrincipalController {
    public Label labelUbicacion;
    public ListView lvwResultados;
    public ComboBox cboDistritos;
    public ComboBox cboSecciones;
    public ComboBox cboCircuitos;
    public Resultados resultados;

    public void cambiarUbicacion(ActionEvent actionEvent)
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccione la ubicación de los datos...");
        directoryChooser.setInitialDirectory(new File(labelUbicacion.getText()));
        File  file = directoryChooser.showDialog(null);
        if (file != null)
            labelUbicacion.setText(file.getPath()); //cambia la ubicacion del campo de texto que contiene la ruta de los archivos por la nueva ruta en caso que el usuario desee cambiar el directorio.


    }

    public void cargarDatos(ActionEvent actionEvent)
    {
        ObservableList observableList;
        //Generamos lista de agrupaciones
        Agrupaciones.leerAgrupaciones(labelUbicacion.getText());
        //Lista de distritos por pais
        Regiones regiones = new Regiones(labelUbicacion.getText());
        observableList = FXCollections.observableArrayList(regiones.getDistritos());
        cboDistritos.setItems(observableList);
        //Procesamos los totales por region
        resultados = new Resultados(labelUbicacion.getText());
        observableList = FXCollections.observableArrayList(resultados.getResultadosRegion("00"));
        //lvwResultados.setItems(observableList);

    }

    public void elegirDistrito(ActionEvent actionEvent)
    {
        //Genera lista de secciones del distrito elegido
        ObservableList observableList;
        Region distrito = (Region) cboDistritos.getValue();
        observableList = FXCollections.observableArrayList(distrito.getSubregiones());
        cboSecciones.setItems(observableList);
        //Mostramos resultados del distrito
        observableList = FXCollections.observableArrayList(resultados.getResultadosRegion(distrito.getCodigo()));


    }

    public void elegirSeccion(ActionEvent actionEvent)
    {
        //Genera lista de circuitos de la seccion elegida
        ObservableList observableList;
        if (cboSecciones.getValue() != null)
        {
            Region seccion = (Region) cboSecciones.getValue();
            observableList = FXCollections.observableArrayList(seccion.getSubregiones());
            cboCircuitos.setItems(observableList);
            //Mostramos resultados del distrito
            observableList = FXCollections.observableArrayList(resultados.getResultadosRegion(seccion.getCodigo()));
            lvwResultados.setItems(observableList);
        }
        else
            cboCircuitos.setItems(null);
    }
    
    public void elegirCircuito(ActionEvent actionEvent) 
    {
        //Genera lista de circuitos de la seccion elegida
        ObservableList observableList;
        if (cboCircuitos.getValue() != null)
        {
            Region circuito = (Region) cboCircuitos.getValue();
            observableList = FXCollections.observableArrayList(circuito.getSubregiones());
            cboCircuitos.setItems(observableList);
            //Mostramos resultados del circuito
            observableList = FXCollections.observableArrayList(resultados.getResultadosRegion(circuito.getCodigo()));
            lvwResultados.setItems(observableList);
        }
        else
            cboCircuitos.setItems(null);
    }
}
