package org.cps.quarkus.jdbc.resource;

import org.cps.quarkus.jdbc.model.Publisher;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/api/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublisherResource {

    @GET
    @Path("{id}")
    public Publisher findPublisherById(@PathParam("id") Long id) {
        return Publisher.<Publisher>findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @DELETE
    @PathParam("{id}")
    public void deletePublisher(@PathParam("id") Long id) {
        Publisher.deleteById(id);
    }

    @GET
    public List<Publisher> listAllPublishers() {
        return Publisher.listAll();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistPublisher(Publisher publisher, @Context UriInfo uriInfo) {
        Publisher.persist(publisher);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(publisher.id));
        return Response.created(uriBuilder.build()).build();
    }
}
