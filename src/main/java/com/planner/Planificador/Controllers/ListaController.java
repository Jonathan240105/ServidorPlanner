package com.planner.Planificador.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planner.Planificador.Dtos.UsuarioToken;
import com.planner.Planificador.Dtos.Actualizaciones.ActualizarListaSolicitud;
import com.planner.Planificador.Dtos.Entidades.ListaDto;
import com.planner.Planificador.Services.ListaServices;
import com.planner.Planificador.Variables.Endpoints;

@RestController
@RequestMapping(Endpoints.Lista.encabezadoLista)
public class ListaController {

    @Autowired
    private ListaServices listaServices;

    @GetMapping(Endpoints.Lista.getListasWorkspace)
    public ResponseEntity<List<ListaDto>> getTodasListasDeUnWorkSpace(@PathVariable Integer id,
            @AuthenticationPrincipal UsuarioToken usuarioToken) {
        return ResponseEntity.ok(listaServices.getTodasListasDeUnWorkSpace(id, usuarioToken.getId()));
    }

    @DeleteMapping(Endpoints.Lista.eliminarLista)
    public ResponseEntity<?> eliminarLista(@PathVariable Integer id, 
            @AuthenticationPrincipal UsuarioToken usuarioToken) {
        listaServices.deleteLista(id, usuarioToken.getId());
        return ResponseEntity.ok("Lista eliminada");
    }

    @PostMapping(Endpoints.Lista.nuevaLista)
    public ResponseEntity<?> crearLista(@RequestParam String nombre, @RequestParam Integer idWorkSpace,
            @AuthenticationPrincipal UsuarioToken usuarioToken) {
        ListaDto lista = listaServices.addLista(nombre, idWorkSpace, usuarioToken.getId());
        return ResponseEntity.ok(lista);
    }

    @PutMapping(Endpoints.Lista.actualizarLista)
    public ResponseEntity<?> actualizarLista(@PathVariable Integer id, @RequestBody ActualizarListaSolicitud body,
            @AuthenticationPrincipal UsuarioToken usuarioToken) {
        ListaDto listaActualizada = listaServices.updateNombreLista(id, body, usuarioToken.getId());
        return ResponseEntity.ok(listaActualizada);
    }
}