package org.vaadin.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegistroNinoController {

    private static final String FILE_PATH = "src/main/resources/cp-national-datafile.json";
    private final List<RegistroNino> registros;

    public RegistroNinoController() {
        List<RegistroNino> registros1;
        try {
            LectorCPJSON lectorCPJSON = new LectorCPJSON();
            registros1 = lectorCPJSON.leerFicheroJson(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            registros1 = List.of();
        }
        this.registros = registros1;
    }



    @GetMapping("/registro")
    public List<RegistroNino> obtenerRegistros() {
        return registros;
    }

    @GetMapping
    public List<RegistroNino> obtenerTodos() {
        return registros;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroNino> obtenerPorId(@PathVariable String id) {
        Optional<RegistroNino> registroOptional = registros.stream()
                .filter(registro -> registro.get_id().equals(id))
                .findFirst();

        if (registroOptional.isPresent()) {
            return new ResponseEntity<>(registroOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> guardarRegistro(@RequestBody RegistroNino registro) {
        try {
            registros.add(registro);

            // Guardar la lista actualizada en el archivo
            LectorCPJSON escritorCPJSON = new LectorCPJSON();
            escritorCPJSON.escribirFicheroJson(FILE_PATH, registros);

            return new ResponseEntity<>("Registro guardado exitosamente", HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al guardar el registro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarRegistro(@PathVariable String id) {
        try {
            registros.removeIf(registro -> registro.get_id().equals(id));

            // Guardar la lista actualizada en el archivo
            LectorCPJSON escritorCPJSON = new LectorCPJSON();
            escritorCPJSON.escribirFicheroJson(FILE_PATH, registros);

            return new ResponseEntity<>("Registro eliminado exitosamente", HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al eliminar el registro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
