package Model;

import Model.Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

/**
 * Manages all JSON activity.
 */
public class JSONParser {

    /**
     * Generates a json file at "model.json"
     */
    public void modelToJson(Model model){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonStr = gson.toJson(model);
        try{
            File jsonFile = new File("model.json");
            FileWriter myWriter = new FileWriter(jsonFile);
            Scanner scn = new Scanner(jsonStr);
            while (scn.hasNext()){
                myWriter.write(scn.next());
            }
            myWriter.close();
        } catch(IOException e){
            System.out.println("File error while writing to JSON file");
            e.printStackTrace();
        }
    }

    /**
     * Returns a model from json
     */
    public Model jsonToModel(File jsonFile){
        try (FileReader fileReader = new FileReader(jsonFile)) {
            Gson gson = new Gson();
            return gson.fromJson(fileReader, Model.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found while parsing JSON");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File error while reading JSON file");
        }
        return null;
    }

}
