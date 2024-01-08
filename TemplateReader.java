package yohx.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


/*
 * Author: YohanAmbinintsoa
 * Description: An utility class to get the needed syntaxes of a given sgbd 
 */


public class TemplateReader {
    String sgbd;
    HashMap<String,String> credentials;
    HashMap<String,String> syntax;

    public TemplateReader(String sgbd) throws Exception{
        this.sgbd = sgbd;
        this.initialize();
    }

    //Initialization of the fields of this class
    public void initialize() throws Exception {
        InputStream credStream = this.getClass().getClassLoader().getResourceAsStream("Templates/Credentials.json");
        InputStream syntStream = this.getClass().getClassLoader().getResourceAsStream("Templates/Syntax.json");

        if (credStream != null && syntStream != null) {
            Gson gson = new Gson();
            JsonObject cred = gson.fromJson(new InputStreamReader(credStream, StandardCharsets.UTF_8), JsonObject.class);
            JsonObject synt = gson.fromJson(new InputStreamReader(syntStream, StandardCharsets.UTF_8), JsonObject.class);

            TypeToken<HashMap<String, String>> typeToken = new TypeToken<HashMap<String, String>>() {
            };

            this.credentials = gson.fromJson(cred.get(this.sgbd), typeToken.getType());
            this.syntax = gson.fromJson(synt.get(this.sgbd), typeToken.getType());
        } else {
            throw new Exception("Unable to load JSON files.");
        }
    }

    //An utility function to retrieve a specific node in a given JSON file
    public JsonObject getObject(String path) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonString.toString()).getAsJsonObject();
            return jsonObject;
    }

    public String getSgbd() {
        return sgbd;
    }

    public HashMap<String, String> getCredentials() {
        return credentials;
    }

    public HashMap<String, String> getSyntax() {
        return syntax;
    }

}
