package ibm.tgtm.eda.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Singleton;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import ibm.tgtm.eda.domain.Freezer;

@Singleton
public class FreezerRepository {
    private static HashMap<String,Freezer> repo = new HashMap<String,Freezer>();

    private Jsonb mapper = JsonbBuilder.create();

    public FreezerRepository() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("reefers.json");
        if (is == null) 
            throw new IllegalAccessError("file not found for reefer json");
        try {
            List<Freezer> reefers = mapper.fromJson(is, new ArrayList<Freezer>(){}.getClass().getGenericSuperclass());
            reefers.stream().forEach( (t) -> repo.put(t.reeferID,t));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public List<Freezer> getAll(){
        return new ArrayList<Freezer>(repo.values());
    }

    public void addReefer(Freezer p) {
        repo.put(p.reeferID, p);
    }

    public Freezer getById(String key){
        return repo.get(key);
    }
}
