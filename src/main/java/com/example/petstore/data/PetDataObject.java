package com.example.petstore.data;

import com.example.petstore.petType.PetType;
import com.example.petstore.pet.Pet;

import java.util.ArrayList;
import java.util.List;


public class PetDataObject
{
    public static List<Pet> pets = new ArrayList<>(){{
        add( new Pet(1,"dog","Sheeba",6) );
        add( new Pet(2,"cat","Garfield",3) );
        add( new Pet(3,"pig","Taf",6) );
        add( new Pet(4,"fish","Goldi",3) );
        add( new Pet(5,"bird","Poli",1) );
    }};

    public static List<PetType> petTypes = new ArrayList<>(){{
        add(new PetType(1,"dog")  );
        add(new PetType(2,"cat")  );
        add(new PetType(3,"pig")  );
        add(new PetType(4,"fish")  );
        add(new PetType(5,"bird")  );
    }};

    public static int petId = pets.size();
    public static int petTypeId = petTypes.size();

    private PetDataObject(){
    }

    public static List<Pet> getInstance(){
        System.out.println(pets);
        return pets;
    }
    public static List<PetType>getPetTypesInstance(){
        return petTypes;
    }

    public static int getInstancePetId(){
        return petId;
    }

    public static int getInstanceTypeId(){
        return petTypeId;
    }


}
