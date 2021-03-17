package ibm.tgtm.eda.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Singleton;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import ibm.tgtm.eda.domain.Reefer;

@Singleton
public class ReeferRepository {
    private static HashMap<String,Reefer> repo = new HashMap<String,Reefer>();

    private Jsonb mapper = JsonbBuilder.create();

    public ReeferRepository() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("reefers.json");
        if (is == null) 
            throw new IllegalAccessError("file not found for reefer json");
        try {
            List<Reefer> reefers = mapper.fromJson(is, new ArrayList<Reefer>(){}.getClass().getGenericSuperclass());
            reefers.stream().forEach( (t) -> repo.put(t.reeferID,t));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public List<Reefer> getAll(){
        return new ArrayList<Reefer>(repo.values());
    }

    public void addReefer(Reefer p) {
        repo.put(p.reeferID, p);
    }

    public Reefer getById(String key){
        return repo.get(key);
    }
}
