package com.empresa.resource;

import com.empresa.dao.ProdutoDAO;
import com.empresa.model.CategoriaTotal;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    ProdutoDAO produtoDAO;

    // Requisito: categorias adquiridas por mais de n clientes. Ex.: GET /categorias?n=2
    @GET
    public List<CategoriaTotal> maisDeNClientes(@QueryParam("n") @DefaultValue("0") int n) {
        return produtoDAO.categoriasAdquiridasPorMaisDe(n);
    }
}
