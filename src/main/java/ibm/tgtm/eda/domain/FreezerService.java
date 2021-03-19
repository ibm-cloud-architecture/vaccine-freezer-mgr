package ibm.tgtm.eda.domain;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import ibm.tgtm.eda.infrastructure.FreezerRepository;
import ibm.tgtm.eda.infrastructure.events.ReeferAlert;
import io.smallrye.reactive.messaging.annotations.Broadcast;

@ApplicationScoped
public class FreezerService {
    private static Logger logger = Logger.getLogger("ReeferService");

    @Inject
    @Channel("reefers") Emitter<Freezer> freezers;

    @Inject
    public FreezerRepository repository;

    public Freezer getReeferById(String id) {
        return repository.getById(id);
    }

    @Incoming("reefer-alerts")                                     
    @Outgoing("internal-alert-stream")                             
    @Broadcast                                              
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public ReeferAlert process(ReeferAlert inAlert){
        logger.info(inAlert.toString());
        return inAlert;
    }

    public List<Freezer> getAllReefers() {
        return repository.getAll();
    }

    public Freezer saveReefer(Freezer r){
        repository.addReefer(r);
        freezers.send(r);
        return r;
    }
}
