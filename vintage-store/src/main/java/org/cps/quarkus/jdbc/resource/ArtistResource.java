package org.cps.quarkus.jdbc.resource;

import org.cps.quarkus.jdbc.Artist;
import org.cps.quarkus.jdbc.repository.ArtistRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistResource {

    @Inject
    ArtistRepository artistRepository;

    @GET
    @Path("{id}")
    public Artist findArtistById(@PathParam("id") Long id) {
        return artistRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @DELETE
    @PathParam("{id}")
    public void deleteArtist(@PathParam("id") Long id) {
        artistRepository.deleteById(id);
    }

    @GET
    public List<Artist> listAllArtists() {
        return artistRepository.listAllArtistsSorted();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistArtist(Artist artist, @Context UriInfo uriInfo) {
        artistRepository.persist(artist);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.getId()));
        return Response.created(uriBuilder.build()).build();
    }
}
