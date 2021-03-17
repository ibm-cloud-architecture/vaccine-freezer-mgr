package ibm.tgtm.eda.infrastructure.events;

import java.time.LocalDateTime;

public class ReeferAlert {
    public String containerID;
    public Object record;
    public LocalDateTime timestamp;
    public String type;

    public ReeferAlert() {
        super();
    }

    @Override
    public String toString(){
        return containerID + " " + type;
    }
}
