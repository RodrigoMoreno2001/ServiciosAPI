package com.example.webfinder;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloController {

    public TableView<Web> tabla;
    public TableColumn<Web,Integer> columnaPuerto;
    public TableColumn<Web,String> columnaTitulo;
    public TableColumn<Web,Integer> columnaCodigo;
    public TableColumn<Web,String> columnaMotor;
    public TableColumn<Web,String> columnaServidor;
    public TextField buscarText;


    private ObservableList<Web> webs= FXCollections.observableArrayList();

    public void initialize(){
        columnaPuerto.setCellValueFactory(new PropertyValueFactory<>("puerto"));
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnaMotor.setCellValueFactory(new PropertyValueFactory<>("motor"));
        columnaServidor.setCellValueFactory(new PropertyValueFactory<>("servidor"));
        tabla.setItems(webs);
    }

    public void onBuscarClick(ActionEvent actionEvent) {

        String url = (buscarText.getText().trim().isEmpty())?"localhost":buscarText.getText().trim();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 65535; i++) {

            final int puerto = i;

            executor.submit(() -> {
                Web web = WebFinder.buscarWeb("http://" + url, puerto);
                if (web != null && web.codigo != -1) Platform.runLater(() -> webs.add(web));
            });
        }

        executor.shutdown();

    }
}