package com.empresa.resource;

import static com.empresa.resource.Respostas.*;

import com.empresa.dao.ClienteDAO;
import com.empresa.dao.CompraDAO;
import com.empresa.dao.ProdutoDAO;
import com.empresa.model.Compra;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

// Registra que um cliente adquiriu um produto (necessário para as consultas).
@Path("/compras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompraResource {

    @Inject
    CompraDAO compraDAO;

    @Inject
    ClienteDAO clienteDAO;

    @Inject
    ProdutoDAO produtoDAO;

    @GET
    public List<Compra> listar() {
        return compraDAO.buscarTodas();
    }

    @POST
    public Response criar(Compra c) {
        if (c == null || c.clienteId() == null || c.produtoId() == null)
            return erro(Response.Status.BAD_REQUEST, "Campos 'clienteId' e 'produtoId' são obrigatórios.");
        if (clienteDAO.buscarPorId(c.clienteId()) == null)
            return erro(Response.Status.NOT_FOUND, "Cliente não encontrado");
        if (produtoDAO.buscarPorId(c.produtoId()) == null)
            return erro(Response.Status.NOT_FOUND, "Produto não encontrado");

        int qtd = (c.quantidade() == null || c.quantidade() < 1) ? 1 : c.quantidade();
        LocalDate data = c.dataCompra() == null ? LocalDate.now() : c.dataCompra();
        Compra nova = compraDAO.criar(new Compra(null, c.clienteId(), c.produtoId(), qtd, data));
        return Response.status(Response.Status.CREATED).entity(nova).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (!compraDAO.deletar(id))
            return erro(Response.Status.NOT_FOUND, "Compra não encontrada");
        return Response.noContent().build();
    }
}
