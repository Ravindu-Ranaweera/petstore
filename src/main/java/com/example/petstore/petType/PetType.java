package com.example.petstore.petType;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "PetType")
public class PetType
{
    @Schema(required = true, description = "Pet id")
    private Integer petId;

    @Schema(required = true, description = "Pet type")
    private String petType;

    public PetType()
    {
    }

    public PetType( Integer petId, String petType )
    {
        this.petId = petId;
        this.petType = petType;
    }

    public PetType( String petType )
    {
        this.petType = petType;
    }

    public Integer getPetId()
    {
        return petId;
    }

    public void setPetId( Integer petId )
    {
        this.petId = petId;
    }

    public String getPetType()
    {
        return petType;
    }

    public void setPetType( String petType )
    {
        this.petType = petType;
    }
}
