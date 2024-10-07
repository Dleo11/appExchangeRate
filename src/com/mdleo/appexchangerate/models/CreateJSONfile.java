package com.mdleo.appexchangerate.models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateJSONfile {

    public void createFile (ArrayList<Rate> rates) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        // Obtener la fecha actual para el nombre del archivo
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String fileName = currentDate + ".json";  // Nombre del archivo con la fecha actual

        try (FileWriter escritura = new FileWriter(fileName)) {
            // Convertir la lista 'rates' a formato JSON
            escritura.write(gson.toJson(rates));
            escritura.close();
//            gson.toJson(rates, escritura);
            System.out.println("Archivo JSON creado correctamente: " + fileName);
        } catch (IOException e) {
            System.out.println("Error al crear el archivo JSON: " + e.getMessage());
        }
    }
}