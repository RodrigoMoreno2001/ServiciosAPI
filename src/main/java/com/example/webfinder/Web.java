package com.example.webfinder;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Web {

    public Integer puerto;
    public String titulo;
    public Integer codigo;
    public String motor;
    public String servidor;

    public Web(Integer puerto,String titulo, Integer codigo,String motor,String servidor){
        this.puerto=puerto;
        this.titulo=titulo;
        this.codigo=codigo;
        this.motor=motor;
        this.servidor=servidor;
    }

    public Web(){}

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Web{" +
                "puerto=" + puerto +
                ", titulo='" + titulo + '\'' +
                ", codigo=" + codigo +
                ", motor='" + motor + '\'' +
                ", servidor='" + servidor + '\'' +
                '}';
    }
}
