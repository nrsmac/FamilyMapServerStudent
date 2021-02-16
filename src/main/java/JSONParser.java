import Model.Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

/**
 * Manages all JSON activity.
 */
public class JSONParser {
    Model model;

    public JSONParser(Model model) {
        this.model = model;
    }

    /**
     * Exports the current model to 'model.json'.
     */
    public void createJsonFile(){
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
     * Updates the model based on JSON file
     */
    public void refreshModelFromJson(){
        try (FileReader fileReader = new FileReader("model.json")) {
            Gson gson = new Gson();
            this.model = gson.fromJson(fileReader, Model.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found while parsing JSON");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File error while reading JSON file");
        }
    }
}
