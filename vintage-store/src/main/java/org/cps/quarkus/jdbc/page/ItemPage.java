package org.cps.quarkus.jdbc.page;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.cps.quarkus.jdbc.model.Book;
import org.cps.quarkus.jdbc.model.CD;

import javax.persistence.Index;
import javax.ws.rs.*;
import java.util.List;

@Path("page/items")
public class ItemPage {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance book(Book book);
        public static native TemplateInstance books(List<Book> books);
        public static native TemplateInstance cd(CD cd);
        public static native TemplateInstance cds(List<CD> cds);
    }

    @GET
    @Path("/books/{id}")
    public TemplateInstance showBookById(@PathParam("id") Long id) {
        return Templates.book(Book.findById(id));
    }

    @GET
    @Path("/books")
    public TemplateInstance showBooks(@QueryParam("query") String query,
                                      @QueryParam("sort") @DefaultValue("id") String sort,
                                      @QueryParam("pageIndex") @DefaultValue("0") Integer pageIndex,
                                      @QueryParam("pageSize") @DefaultValue("1000") Integer pageSize) {
        return Templates.books(Book.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
                .data("query", query)
                .data("sort", sort)
                .data("pageIndex", pageIndex)
                .data("pageSize", pageSize);
    }

    @GET
    @Path("/cds/{id}")
    public TemplateInstance showCdById(@PathParam("id") Long id) {
        return Templates.cd(CD.findById(id));
    }

    @GET
    @Path("/cds")
    public TemplateInstance showCds(@QueryParam("query") String query,
                                    @QueryParam("sort") @DefaultValue("id") String sort,
                                    @QueryParam("pageIndex") @DefaultValue("0") Integer pageIndex,
                                    @QueryParam("pageSize") @DefaultValue("1000") Integer pageSize) {
        return Templates.cds(CD.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
                .data("query", query)
                .data("sort", sort)
                .data("pageIndex", pageIndex)
                .data("pageSize", pageSize);
    }
}
