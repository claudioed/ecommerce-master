package tech.claudioed.domain.service;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import tech.claudioed.domain.service.data.Transaction;

@Path("/")
@RegisterRestClient
public interface TransactionCloudEventsSender {

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  void send(@HeaderParam("Ce-Type")String ceType,
            @HeaderParam("Ce-Source")String ceSource,
            @HeaderParam("Ce-Specversion")String ceSpecVersion,
            @HeaderParam("Ce-Id")String ceId,
            @HeaderParam("Content-Type")String contentType, Transaction transaction);

}
