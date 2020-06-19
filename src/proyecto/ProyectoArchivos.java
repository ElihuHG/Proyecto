/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joepo
 */
public class ProyectoArchivos {

    private final String NomArchivo = "Final.tesoem";
    List<Atributos> Datos = new ArrayList<>();

    public boolean VerificaArch() {
        File archivo = new File(NomArchivo);
        if (!archivo.exists()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean Grabar(List<Atributos> Datos) {
        try {
            FileWriter archivo = new FileWriter(NomArchivo, true);
            try (BufferedWriter bw = new BufferedWriter(archivo)) {
                for (Atributos Dato : Datos) {

                    bw.write(ConvierteGson(Dato) + "\n");
                }
                bw.close();
            }
            archivo.close();
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    public boolean Leer() {

        String Cadena = "";
        try {
            FileReader archivo = new FileReader(NomArchivo);
            BufferedReader br = new BufferedReader(archivo);

            while ((Cadena = br.readLine()) != null) {
                Datos.add(ConvierteClase(Cadena));
            }
            br.close();
            archivo.close();

        } catch (Exception ex) {

            return false;
        }
        return true;
    }

    public void Agregar(Atributos dato) {
        Datos.add(dato);
    }

    public List<Atributos> getDatos() {
        return Datos;
    }

    private Atributos ConvierteClase(String dato) {
        Gson gson = new Gson();
        return gson.fromJson(dato, Atributos.class);
    }

    private String ConvierteGson(Atributos dato) {
        Gson gson = new Gson();
        return gson.toJson(dato);
    }
}
