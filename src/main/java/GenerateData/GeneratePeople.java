package GenerateData;

import Model.Event;
import Model.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GeneratePeople {
    private static final String FNAMES = "json/fnames.json";
    private static final String SNAMES = "json/snames.json";
    private static final String MNAMES = "json/mnames.json";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> surnames = new ArrayList<>();
    private ArrayList<String> fNames = new ArrayList<>();

    private GenerateEvents generateEvents;

    /**
     * From 100 to 999;
     */
    private LinkedList<Integer> ids;

    public GeneratePeople() {
        this.generateEvents = new GenerateEvents();


        File mNamesFile = new File(MNAMES);
        File fNamesFile = new File(FNAMES);
        File surnamesFile = new File(SNAMES);

        populateList(mNamesFile, this.mNames);
        populateList(fNamesFile, this.fNames);
        populateList(surnamesFile, this.surnames);

        this.ids = new LinkedList<>();

        for (int i = 100; i<1000; i++){
            this.ids.add(i);
        }
    }

    public Person generatePerson(String username, String gender) {
        String person_id;
        String firstname;
        String surname;
        String mother_id = null;
        String father_id = null;
        String spouse_id = null;

        Random rand = new Random();

        Collections.shuffle(this.ids);
        int id = this.ids.pop();
        person_id = id + "";



        int firstNameIdx= rand.nextInt(this.mNames.size()-1);
        int surnameIdx= rand.nextInt(this.surnames.size()-1);


        if (gender.equals("m")){
            firstname = this.mNames.get(firstNameIdx);
        } else {
            firstname = this.fNames.get(firstNameIdx);
        }
        surname = this.surnames.get(surnameIdx);


        return new Person(person_id,username,firstname,surname,gender,father_id,mother_id,spouse_id);

    }

    public HashMap<String, HashSet<?>> generateImmediateFamily(Person person){

        HashMap<String, HashSet<?>> data = new HashMap<>();
        HashSet<Event> events = new HashSet<>();
        HashSet<Person> persons = new HashSet<>();

        Event birth = generateEvents.generateBirth(person);
        Person father = generatePerson(person.getAssociatedUsername(),"m");
        Person mother = generatePerson(person.getAssociatedUsername(), "f");
        person.setFatherID(father.getPersonID());
        person.setMotherID(mother.getPersonID());
        events.add(birth);
        persons.add(father);
        persons.add(mother);

        int birth_year = birth.getYear(); //TODO fix -> parents bday is 30yrs before that, no need to be random, fixed

        int marriageYear =  birth_year-30;
        HashSet<Event> marriages = generateEvents.marry(father,mother, marriageYear);

        for (Event e:marriages){events.add(e);}

        if (birth_year>90){
            Event death = generateEvents.generateDeath(person, birth_year);
            events.add(death);
        }


        data.put("persons", persons);
        data.put("events",events);
        return data;
    }

    public HashMap<String, HashSet<?>> generations(Person person, int generations){
        HashMap<String, HashSet<?>> data = generateImmediateFamily(person);

        for(int i = 0; i<generations; i++){
            HashSet<Event> events = (HashSet<Event>) data.get("events");
            HashSet<Person> persons = (HashSet<Person>) data.get("persons");

            HashSet<Person> newPersons = new HashSet<>();
            HashSet<Event> newEvents = new HashSet<>();

            for (Person p: persons){
                HashMap<String,HashSet<?>> genData = generateImmediateFamily(p);
                HashSet<Person> genPersons = (HashSet<Person>) genData.get("persons");
                HashSet<Event> genEvents = (HashSet<Event>) genData.get("events");
                for (Person per:genPersons){
                    newPersons.add(per);
                }
                for(Event e : genEvents){
                    newEvents.add(e);
                }
            }

            data.put("persons",newPersons);
            data.put("events",newEvents);
        }
        if(data != null){
            return data;

        }
        return null;
    }

    private void populateList(File file, ArrayList<String> list) {

        try {
            Scanner scanner = null;
            scanner = new Scanner(file);
            while(scanner.hasNext()){
                list.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
