package Test;

import java.sql.Date;
import yohx.annotations.*;

@Table(name = "personne")
public class Personne{
    @Id(name = "id", idtype = Generation.AUTO)
    String id;
    @Column(name = "nom")
    String name;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
