package ibm.tgtm.eda.infrastructure.events;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class ReeferAlertDeserializer extends JsonbDeserializer<ReeferAlert> {
    public ReeferAlertDeserializer(){
        // pass the class to the parent.
        super(ReeferAlert.class);
    }
    
}