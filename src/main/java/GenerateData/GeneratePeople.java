package GenerateData;

import Model.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GeneratePeople {
    private static final String FNAMES = "json/fnames.json";
    private static final String SNAMES = "json/snames.json";
    private static final String MNAMES = "json/mnames.json";

    private ArrayList<String> ACCEPTABLE_USERNAMES = new ArrayList<>();

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> surnames = new ArrayList<>();
    private ArrayList<String> fNames = new ArrayList<>();

    private ArrayList<Person> persons;

    /**
     * From 100 to 999;
     */
    private LinkedList<Integer> ids;

    public GeneratePeople(int number) throws FileNotFoundException {
        File mNamesFile = new File(FNAMES);
        File fNamesFile = new File(MNAMES);
        File surnamesFile = new File(SNAMES);

        ACCEPTABLE_USERNAMES.add("nrsmac");
        ACCEPTABLE_USERNAMES.add("bella");
        ACCEPTABLE_USERNAMES.add("teej");

        populateList(mNamesFile, this.mNames);
        populateList(fNamesFile, this.fNames);
        populateList(surnamesFile, this.surnames);

        this.ids = new LinkedList<>();

        for (int i = 100; i<1000; i++){
            this.ids.add(i);
        }

        generatePersons(number);
    }

    private void generatePersons(int numberOfPeople) {
        persons = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            persons.add(generatePerson());
        }
    }

    public Person generatePerson() {
        String person_id;
        String username;
        String firstname;
        String surname;
        String gender;
        String mother_id = null;
        String father_id = null;
        String spouse_id = null;

        Random rand = new Random();

        username = this.ACCEPTABLE_USERNAMES.get(rand.nextInt(ACCEPTABLE_USERNAMES.size()));

        Collections.shuffle(this.ids);
        int id = this.ids.pop();
        person_id = id + "";



        int firstNameIdx= rand.nextInt(this.mNames.size()-1);
        int surnameIdx= rand.nextInt(this.surnames.size()-1);

        boolean isMale = rand.nextBoolean();


        if (isMale){
            firstname = this.mNames.get(firstNameIdx);
            gender = "m";
        } else {
            firstname = this.fNames.get(firstNameIdx);
            gender = "f";
        }
         surname = this.surnames.get(surnameIdx);

        return new Person(person_id,username,firstname,surname,gender,father_id,mother_id,spouse_id);
    }

    private void populateList(File file, ArrayList<String> list) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            list.add(scanner.next());
        }
    }

    public ArrayList<Person> getPersons() {
        return this.persons;
    }
}
