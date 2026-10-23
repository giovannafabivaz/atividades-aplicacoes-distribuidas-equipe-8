package com.empresa.resource;

import static com.empresa.resource.Respostas.*;

import com.empresa.dao.ClienteDAO;
import com.empresa.dao.DestinoDAO;
import com.empresa.dao.ViagemDAO;
import com.empresa.model.Viagem;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

// Registra que um cliente visitou um destino (necessário para as consultas).
@Path("/viagens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ViagemResource {

    @Inject
    ViagemDAO viagemDAO;

    @Inject
    ClienteDAO clienteDAO;

    @Inject
    DestinoDAO destinoDAO;

    @GET
    public List<Viagem> listar() {
        return viagemDAO.buscarTodas();
    }

    @POST
    public Response criar(Viagem v) {
        if (v == null || v.clienteId() == null || v.destinoId() == null)
            return erro(Response.Status.BAD_REQUEST, "Campos 'clienteId' e 'destinoId' são obrigatórios.");
        if (clienteDAO.buscarPorId(v.clienteId()) == null)
            return erro(Response.Status.NOT_FOUND, "Cliente não encontrado");
        if (destinoDAO.buscarPorId(v.destinoId()) == null)
            return erro(Response.Status.NOT_FOUND, "Destino não encontrado");

        LocalDate data = v.dataVisita() == null ? LocalDate.now() : v.dataVisita();
        Viagem nova = viagemDAO.criar(new Viagem(null, v.clienteId(), v.destinoId(), data));
        return Response.status(Response.Status.CREATED).entity(nova).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (!viagemDAO.deletar(id))
            return erro(Response.Status.NOT_FOUND, "Viagem não encontrada");
        return Response.noContent().build();
    }
}
