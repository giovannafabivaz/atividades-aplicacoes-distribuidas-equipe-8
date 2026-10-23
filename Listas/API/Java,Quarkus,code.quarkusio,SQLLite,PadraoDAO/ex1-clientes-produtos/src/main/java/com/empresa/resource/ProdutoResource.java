package com.empresa.resource;

import static com.empresa.resource.Respostas.*;

import com.empresa.dao.ProdutoDAO;
import com.empresa.model.Produto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoDAO produtoDAO;

    private boolean invalido(Produto p) {
        return p == null || vazio(p.nome()) || vazio(p.categoria()) || p.preco() == null;
    }

    @GET
    public List<Produto> listar() {
        return produtoDAO.buscarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Produto p = produtoDAO.buscarPorId(id);
        if (p == null)
            return erro(Response.Status.NOT_FOUND, "Produto não encontrado");
        return Response.ok(p).build();
    }

    @POST
    public Response criar(Produto p) {
        if (invalido(p))
            return erro(Response.Status.BAD_REQUEST, "Campos 'nome', 'preco' e 'categoria' são obrigatórios.");
        return Response.status(Response.Status.CREATED).entity(produtoDAO.criar(p)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Produto p) {
        if (invalido(p))
            return erro(Response.Status.BAD_REQUEST, "Campos 'nome', 'preco' e 'categoria' são obrigatórios.");
        Produto atualizado = new Produto(id, p.nome(), p.preco(), p.categoria());
        if (!produtoDAO.atualizar(atualizado))
            return erro(Response.Status.NOT_FOUND, "Produto não encontrado para atualização");
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        if (!produtoDAO.deletar(id))
            return erro(Response.Status.NOT_FOUND, "Produto não encontrado para remoção");
        return Response.noContent().build();
    }
}
