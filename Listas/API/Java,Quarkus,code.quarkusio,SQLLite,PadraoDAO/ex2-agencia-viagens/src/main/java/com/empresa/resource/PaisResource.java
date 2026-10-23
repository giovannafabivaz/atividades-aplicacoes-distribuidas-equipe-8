package com.empresa.resource;

import com.empresa.dao.DestinoDAO;
import com.empresa.model.PaisTotal;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/paises")
@Produces(MediaType.APPLICATION_JSON)
public class PaisResource {

    @Inject
    DestinoDAO destinoDAO;

    // Requisito: países com mais de n destinos. Ex.: GET /paises?n=1
    @GET
    public List<PaisTotal> maisDeNDestinos(@QueryParam("n") @DefaultValue("0") int n) {
        return destinoDAO.paisesComMaisDe(n);
    }
}
