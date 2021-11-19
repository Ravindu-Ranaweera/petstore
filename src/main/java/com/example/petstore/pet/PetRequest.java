package com.example.petstore.pet;



public class PetRequest
{

    private String petType;
    private String petName;
    private Integer petAge;

    public PetRequest()
    {
    }

    public PetRequest( String petType, String petName, Integer petAge )
    {
        this.petType = petType;
        this.petName = petName;
        this.petAge = petAge;
    }

    public String getPetType()
    {
        return petType;
    }

    public void setPetType( String petType )
    {
        this.petType = petType;
    }

    public String getPetName()
    {
        return petName;
    }

    public void setPetName( String petName )
    {
        this.petName = petName;
    }

    public Integer getPetAge()
    {
        return petAge;
    }

    public void setPetAge( Integer petAge )
    {
        this.petAge = petAge;
    }
}
