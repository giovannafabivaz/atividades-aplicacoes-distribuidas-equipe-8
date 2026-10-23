package com.empresa.resource;

import static com.empresa.resource.Respostas.*;

import com.empresa.dao.ClienteDAO;
import com.empresa.dao.DestinoDAO;
import com.empresa.model.Destino;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Path("/destinos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DestinoResource {

    @Inject
    DestinoDAO destinoDAO;

    @Inject
    ClienteDAO clienteDAO;

    private boolean invalido(Destino d) {
        return d == null || vazio(d.nome()) || vazio(d.pais());
    }

    @GET
    public List<Destino> listar() {
        return destinoDAO.buscarTodos();
    }

    // Requisito: destinos agrupados e ordenados por país.
    @GET
    @Path("/por-pais")
    public Map<String, List<Destino>> agrupadosPorPais() {
        Map<String, List<Destino>> grupos = new LinkedHashMap<>(); // mantém a ordem alfabética vinda do SQL
        for (Destino d : destinoDAO.buscarOrdenadosPorPais())
            grupos.computeIfAbsent(d.pais(), k -> new ArrayList<>()).add(d);
        return grupos;
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Destino d = destinoDAO.buscarPorId(id);
        if (d == null)
            return erro(Response.Status.NOT_FOUND, "Destino não encontrado");
        return Response.ok(d).build();
    }

    @POST
    public Response criar(Destino d) {
        if (invalido(d))
            return erro(Response.Status.BAD_REQUEST, "Campos 'nome' e 'pais' são obrigatórios.");
        return Response.status(Response.Status.CREATED).entity(destinoDAO.criar(d)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Destino d) {
        if (invalido(d))
            return erro(Response.Status.BAD_REQUEST, "Campos 'nome' e 'pais' são obrigatórios.");
        Destino atualizado = new Destino(id, d.nome(), d.pais(), d.descricao());
        if (!destinoDAO.atualizar(atualizado))
            return erro(Response.Status.NOT_FOUND, "Destino não encontrado para atualização");
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (!destinoDAO.deletar(id))
            return erro(Response.Status.NOT_FOUND, "Destino não encontrado para remoção");
        return Response.noContent().build();
    }

    // Requisito: clientes que visitaram um certo destino (id no caminho).
    @GET
    @Path("/{id}/clientes")
    public Response clientesDoDestino(@PathParam("id") Long id) {
        if (destinoDAO.buscarPorId(id) == null)
            return erro(Response.Status.NOT_FOUND, "Destino não encontrado");
        return Response.ok(clienteDAO.buscarPorDestino(id)).build();
    }
}
