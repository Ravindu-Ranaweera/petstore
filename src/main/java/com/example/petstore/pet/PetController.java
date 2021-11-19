package com.example.petstore.pet;


import com.example.petstore.data.PetDataObject;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/pets" )
@Produces( "application/json" )
@Consumes( "application/json" )
@ApplicationScoped
public class PetController
{

    List<com.example.petstore.pet.Pet> pets = PetDataObject.getInstance();
    int sizeOfArray = PetDataObject.getInstancePetId();

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            ) )
    } )
    @POST
    public Response addPet( @RequestBody( required = true ) PetRequest pet )
    {
        com.example.petstore.pet.Pet pet1 = new com.example.petstore.pet.Pet( pet );
        System.out.println( pet1.getPetAge() );
        pet1.setPetId( sizeOfArray + 1 );
        pets.add( pet1 );
        sizeOfArray++;
        return Response.ok( pet1 ).build();
    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
            )
    } )
    @GET
    public Response getPets()
    {
        return Response.ok( pets ).build();
    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "Pet for id", content = @Content( mediaType = MediaType.APPLICATION_JSON ) ),
            @APIResponse( responseCode = "404", description = "Pet Id is not found" ) } )
    @PUT
    @Path( "{id}" )
    public Response updatePet( @PathParam( "id" ) int id, @RequestBody PetRequest petRequest )
    {
        try
        {
            for (Pet value : pets) {
                if (value.getPetId() == id) {
                    value.setPetAge(petRequest.getPetAge());
                    value.setPetName(petRequest.getPetName());
                    value.setPetType(petRequest.getPetType());
                    return Response.ok(value).build();
                }
            }
        }
        catch( Exception e )
        {
            return Response.serverError().build();
        }
        return Response.status( 204, "Not Found" ).build();

    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "pet id deleted" ),
            @APIResponse( responseCode = "404", description = "Pet Id is not found" ) } )
    @DELETE
    @Path( "{id}" )
    public Response deletePet( @PathParam( "id" ) int id )
    {

        for( int i = 0; i < pets.size(); i++ )
        {
            if( pets.get( i ).getPetId() == id )
            {
                pets.remove( i );
                return Response.status( 200 ,"Deleted").build();
            }

        }
        return Response.ok( " {\n" +
                "        \"status\": Filed,\n" +
                "        \"Massage\": Not found id,\n" +
                "    }",MediaType.APPLICATION_JSON  ).build();

    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
            )
    } )
    @GET
    @Path( "{id}" )
    public Response getPetsById( @PathParam( "id" ) int id )
    {
        for (Pet pet : pets) {
            if (pet.getPetId() == id) {
                return Response.ok(pet).build();
            }

        }
        return Response.status( 404, "Not found" ).build();

    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
            )
    } )
    @GET
    @Path( "search" )
    public Response getPetsByName(
            @DefaultValue( "false" ) @QueryParam( "name" ) String name,
            @DefaultValue( "false" ) @QueryParam( "type" ) String type,
            @DefaultValue( "-1" ) @QueryParam( "age" ) int age )
    {
        List<com.example.petstore.pet.Pet> pets1 = new ArrayList<>();
        if( !name.equals( "false" ) && type.equals( "false" ) && age == -1 )
        {
            for (Pet pet : pets) {
                if (name.equalsIgnoreCase(pet.getPetName())) {
                    pets1.add(pet);
                }

            }
        }
        else if( name.equals( "false" ) && !type.equals( "false" ) && age == -1 )
        {
            for (Pet pet : pets) {
                if (type.equalsIgnoreCase(pet.getPetType())) {
                    pets1.add(pet);
                }

            }
        }
        else if( name.equals( "false" ) && type.equals( "false" ) && age != -1 )
        {
            for (Pet pet : pets) {
                if (pet.getPetAge() == age) {
                    pets1.add(pet);
                }

            }
        }
        else if( !name.equals( "false" ) && !type.equals( "false" ) && age == -1 )
        {
            List<com.example.petstore.pet.Pet> pets2 = new ArrayList<>();
            for (Pet pet : pets) {
                if (type.equalsIgnoreCase(pet.getPetType())) {
                    pets2.add(pet);
                }

            }
            for (Pet pet : pets2) {
                if (name.equalsIgnoreCase(pet.getPetName())) {
                    pets1.add(pet);
                }
            }
        }
        else if( !name.equals( "false" ) && type.equals( "false" ) && age != -1 )
        {
            List<com.example.petstore.pet.Pet> pets2 = new ArrayList<>();
            for (Pet pet : pets) {
                if (age == pet.getPetAge()) {
                    pets2.add(pet);
                }

            }
            for (Pet pet : pets2) {
                if (name.equalsIgnoreCase(pet.getPetName())) {
                    pets1.add(pet);
                }
            }
        }
        else if( name.equals( "false" ) && !type.equals( "false" ) && age != -1 )
        {
            List<com.example.petstore.pet.Pet> pets2 = new ArrayList<>();
            for (Pet pet : pets) {
                if (age == pet.getPetAge()) {
                    pets2.add(pet);
                }

            }
            for (Pet pet : pets2) {
                if (type.equalsIgnoreCase(pet.getPetType())) {
                    pets1.add(pet);
                }
            }
        }

        else if( !name.equals( "false" ) && !type.equals( "false" ) && age != -1 )
        {
            List<com.example.petstore.pet.Pet> pets2 = new ArrayList<>();
            for (Pet pet : pets) {
                if (age == pet.getPetAge()) {
                    pets2.add(pet);
                }

            }
            List<com.example.petstore.pet.Pet> pets3 = new ArrayList<>();
            for (Pet pet : pets2) {
                if (type.equalsIgnoreCase(pet.getPetType())) {
                    pets3.add(pet);
                }
            }
            for (Pet pet : pets3) {
                if (pet.getPetAge() == age) {
                    pets1.add(pet);
                }
            }
        }else {
            pets1.addAll( pets );
        }


        if( pets1.size() > 0 )
        {
            return Response.ok( pets1 ).build();
        }
        return Response.status( 404, "Not found" ).build();

    }


}
