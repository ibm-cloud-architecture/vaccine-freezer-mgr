package ibm.tgtm.eda.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.reactivestreams.Publisher;

import ibm.tgtm.eda.domain.Freezer;
import ibm.tgtm.eda.domain.FreezerService;
import ibm.tgtm.eda.infrastructure.events.ReeferAlert;


@Path("/reefers")
public class FreezerResource {

    @Inject
    public FreezerService service;

    @Inject
    @Channel("internal-alert-stream")
    Publisher<ReeferAlert> alerts;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Freezer> getAll() {
        return service.getAllReefers();
    }

    @GET
    @Path("/{reeferId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Freezer getReeferById(@PathParam String reeferId) {
        return service.getReeferById(reeferId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Freezer saveNewFreezer( Freezer newFreezer) {
        return service.saveReefer(newFreezer);
    }

    @GET
    @Path("/alerts")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Publisher<ReeferAlert> streamAlerts(){
        return alerts;
    }

}