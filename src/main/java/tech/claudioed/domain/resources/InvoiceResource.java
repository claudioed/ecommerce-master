package tech.claudioed.domain.resources;

import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import tech.claudioed.domain.resources.data.InvoiceRequest;
import tech.claudioed.domain.service.InvoiceService;

@ApplicationScoped
@Path("/api/invoices")
public class InvoiceResource {

  final InvoiceService invoiceService;

  @Inject
  public InvoiceResource(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @GET
  @Produces("application/json")
  public Response list() {
    return Response.ok().entity(this.invoiceService.invoices()).build();
  }

  @GET
  @Produces("application/json")
  @Path("{id}")
  public Response get(@PathParam("id") Long id) {
    return Response.ok().entity(this.invoiceService.find(id)).build();
  }

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public Response add(InvoiceRequest request) {
    var invoice = this.invoiceService.add(request);
    return Response.created(URI.create("/api/invoices/"+ invoice.id)).entity(invoice).build();
  }

}
