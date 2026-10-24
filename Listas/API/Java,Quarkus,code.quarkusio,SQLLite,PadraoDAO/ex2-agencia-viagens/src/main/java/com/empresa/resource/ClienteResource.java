package com.empresa.resource;

import static com.empresa.resource.Respostas.*;

import com.empresa.dao.ClienteDAO;
import com.empresa.dao.DestinoDAO;
import com.empresa.model.Cliente;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteDAO clienteDAO;

    @Inject
    DestinoDAO destinoDAO;

    @GET
    public List<Cliente> listar() {
        return clienteDAO.buscarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Cliente c = clienteDAO.buscarPorId(id);
        if (c == null)
            return erro(Response.Status.NOT_FOUND, "Cliente não encontrado");
        return Response.ok(c).build();
    }

    @POST
    public Response criar(Cliente c) {
        if (c == null || vazio(c.nome()) || vazio(c.email()))
            return erro(Response.Status.BAD_REQUEST, "Campos 'nome' e 'email' são obrigatórios.");
        return Response.status(Response.Status.CREATED).entity(clienteDAO.criar(c)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Cliente c) {
        if (c == null || vazio(c.nome()) || vazio(c.email()))
            return erro(Response.Status.BAD_REQUEST, "Campos 'nome' e 'email' são obrigatórios.");
        Cliente atualizado = new Cliente(id, c.nome(), c.email());
        if (!clienteDAO.atualizar(atualizado))
            return erro(Response.Status.NOT_FOUND, "Cliente não encontrado para atualização");
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (!clienteDAO.deletar(id))
            return erro(Response.Status.NOT_FOUND, "Cliente não encontrado para remoção");
        return Response.noContent().build();
    }

    // Requisito: destinos visitados por um cliente (id no caminho).
    @GET
    @Path("/{id}/destinos")
    public Response destinosDoCliente(@PathParam("id") Long id) {
        if (clienteDAO.buscarPorId(id) == null)
            return erro(Response.Status.NOT_FOUND, "Cliente não encontrado");
        return Response.ok(destinoDAO.buscarPorCliente(id)).build();
    }
}
