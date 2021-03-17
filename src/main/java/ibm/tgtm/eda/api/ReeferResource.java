package ibm.tgtm.eda.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.reactivestreams.Publisher;

import ibm.tgtm.eda.domain.Reefer;
import ibm.tgtm.eda.domain.ReeferService;
import ibm.tgtm.eda.infrastructure.events.ReeferAlert;


@Path("/reefers")
public class ReeferResource {

    @Inject
    public ReeferService service;

    @Inject
    @Channel("internal-alert-stream")
    Publisher <ReeferAlert> alerts;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reefer> getAll() {
        return service.getAllReefers();
    }

    @GET
    @Path("/{reeferId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Reefer getReeferById(@PathParam String reeferId) {
        return service.getReeferById(reeferId);
    }

    @GET
    @Path("/alerts")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Publisher<ReeferAlert> streamAlerts(){
        return alerts;
    }

}