package com.github.aspirina765.ifood.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @GET
    public List<Restaurante> restaurantes() {
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    public void adicionar(Restaurante dto) {
      dto.persist();
    }

  @PUT
  @Path("{id} ")
  @Transactional
  public void adicionar(@PathParam("id") Long id, Restaurante dto) {
      Optional<PanacheEntityBase> restauranteOp = Restaurante.findByIdOptional(id);
      if(restauranteOp.isEmpty()) {
        throw new NotFoundException();
      }

      Restaurante restaurante = (Restaurante) restauranteOp.get();

      restaurante.nome = dto.nome;

      dto.persist();
  }
}
