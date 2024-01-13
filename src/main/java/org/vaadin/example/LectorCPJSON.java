package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LectorCPJSON {
    public List<RegistroNino> leerFicheroJson(String fichero) throws IOException {
        Path filePath = Paths.get(fichero);

        if (!Files.exists(filePath)) {
            throw new IOException("El archivo no existe: " + fichero);
        }

        try (Reader reader = Files.newBufferedReader(filePath)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Float.class, (JsonDeserializer<Float>) (json, typeOfT, context) -> {
                        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
                            String stringValue = json.getAsJsonPrimitive().getAsString();
                            if ("null".equals(stringValue)) {
                                return null;
                            }
                        }
                        return json.getAsFloat();
                    })
                    .create();

            return gson.fromJson(reader, new TypeToken<ArrayList<RegistroNino>>() {}.getType());
        } catch (Exception ex) {
            throw new IOException("Error al leer el archivo JSON", ex);
        }
    }

    public LectorCPJSON() {
    }

    public void escribirFicheroJson(String filePath, List<RegistroNino> registros) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new Gson();
            gson.toJson(registros, fileWriter);
        } catch (IOException ex) {
            // Capturar cualquier excepción relacionada con la escritura del archivo
            throw new IOException("Error al escribir en el archivo JSON", ex);
        } catch (Exception ex) {
            // Capturar otras excepciones inesperadas
            throw new IOException("Error inesperado al escribir en el archivo JSON", ex);
        }
    }

}
