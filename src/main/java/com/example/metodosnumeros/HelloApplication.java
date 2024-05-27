package com.example.metodosnumeros;

import com.example.metodosnumeros.vistas.Biseccion;
import com.example.metodosnumeros.vistas.GaussJordan;
import com.example.metodosnumeros.vistas.Secante;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Scene escena;
    private Button btnSig;
    private VBox vContenedor1,vContenedor2;
    private Label lblT,lbl0,lbl1,lbl2,lbl3,lbl4,lbl5;

    @Override
    public void start(Stage stage) throws IOException {
        CrearPantalla();
        stage.setTitle("Metodos Numericos");
        stage.setScene(escena);
        btnSig.setOnAction(event ->new SeleccionMetodo());

        stage.show();


    }

    private void CrearPantalla(){
        lblT=new Label("Metodos Númericos");
        lblT.setId("lblT");
        lbl0=new Label("Integrantes");
        lbl0.setId("lbl0");
        lbl1=new Label("Enrique Alejandro Paredes Pati;o");
        lbl2=new Label("Uriel Alejandro Martinez Martinez");
        lbl3=new Label("Bryan Ricardo Corona Juárez");
        lbl4=new Label("Julian Nuñez");
        lbl5=new Label("Emmanuel Blanco Paloblanco");
        btnSig = new Button("Siguiente");
        vContenedor1 = new VBox(lbl0,lbl1,lbl2,lbl3,lbl4,lbl5);
        vContenedor1.setAlignment(Pos.BOTTOM_CENTER);
        vContenedor2= new VBox(lblT,vContenedor1,btnSig);
        vContenedor2.setAlignment(Pos.CENTER);

        escena = new Scene(vContenedor2,500,500);
        escena.getStylesheets()
               .add(getClass().getResource("/estilos/principal.css").toString());
    }

    public static void main(String[] args) {
        launch();
    }
}


class SeleccionMetodo extends Stage {
    private Scene escena;
    private VBox vContenedor,vContenedor2,vContenedor3;
    private HBox hContenedor,hContenedor2,hContenedor3,hContenedor4;
    private Label lblSM,lbl;
    private Button btnSE,btnAbiertos,btnCerrados,btnBiseccion,btnSecante,btnGaussS,btnGaussJ;

    public SeleccionMetodo(){
        CrearUI();
        this.setTitle("Seleccion Metodo");
        btnAbiertos.setOnAction(event->vContenedor2.setVisible(true));

        btnCerrados.setOnAction(event->vContenedor3.setVisible(true));

        btnBiseccion.setOnAction(event -> new Biseccion());
        btnSecante.setOnAction(event -> new Secante());
        btnGaussJ.setOnAction(event -> new GaussJordan());
        btnGaussS.setOnAction(event -> new GaussSeidel());
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        lblSM=new Label("Selecciona el tipo de metodo");
        lbl=new Label("Selecciona el metodo");
        lbl.setVisible(false);
        btnAbiertos=new Button("Métodos Abiertos");
        btnCerrados=new Button("Métodos Cerrados");
        btnBiseccion=new Button("Biseccion");
        btnSecante=new Button("Secante");
        btnGaussJ=new Button("Gauss Jordan");
        btnGaussS=new Button("Gauss Seidel");


        vContenedor2=new VBox(btnBiseccion,btnSecante);
        vContenedor2.setVisible(false);
        vContenedor3=new VBox(btnGaussJ,btnGaussS);
        vContenedor3.setVisible(false);
        hContenedor3=new HBox(vContenedor2);
        hContenedor4=new HBox(vContenedor3);
        hContenedor=new HBox(btnAbiertos,hContenedor3);
        hContenedor.setSpacing(20);
        hContenedor2=new HBox(btnCerrados,hContenedor4);
        hContenedor2.setSpacing(20);
        vContenedor=new VBox(lbl,lblSM,hContenedor,hContenedor2);
        vContenedor.setSpacing(20);
        vContenedor.setPadding(new Insets(20));

        escena = new Scene(vContenedor,500,300);
    }
}
