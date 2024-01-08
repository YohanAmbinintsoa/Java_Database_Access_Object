package Test;

import java.util.List;
import java.util.Map;

import Test.Personne;
import yohx.utils.TemplateReader;
import yohx.DAO.*;

public class Main {
    public static void main(String[] args) {
        try {
            DAO requester=new DAO("postgres", "root", "test", "postgresql");
            Personne pers=new Personne();
            requester.delete(null, "PERSONNE0005", pers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}