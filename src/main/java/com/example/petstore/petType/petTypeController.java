package com.example.petstore.petType;

import com.example.petstore.data.PetDataObject;
import com.example.petstore.pet.Pet;
import com.example.petstore.pet.PetRequest;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/petTypes" )

@Produces( "application/json" )
@Consumes( "application/json" )
public class petTypeController
{
    List<PetType> petTypes = PetDataObject.getPetTypesInstance();
    int sizeOfTypes = PetDataObject.getInstanceTypeId();

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
            )
    } )
    @GET
    public Response getPets()
    {
        return Response.ok( petTypes ).build();
    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
            )
    } )
    @POST
    public Response createPetType( @RequestBody PetType petType )
    {

        PetType petType1 = new PetType();
        petType1.setPetType( petType.getPetType() );
        petType1.setPetId( sizeOfTypes + 1 );
        petTypes.add( petType1 );
        return Response.ok( petType1 ).build();
    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "Successfully deleted", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
            )
    } )
    @DELETE
    @Path( "{id}" )
    public Response deletePost( @PathParam( "id" ) int id )
    {
        int deleteTypeStatus = 0;
        for( int i = 0; i < petTypes.size(); i++ )
        {
            if( petTypes.get( i ).getPetId() == id )
            {
                petTypes.remove( i );
                return Response.status( 200, "Deleted" ).build();
            }

        }
        return Response.status( 404, "Not found" ).build();
    }

    @PUT
    @Path( "{id}" )
    public Response updatePet( @PathParam( "id" ) int id, @RequestBody PetType petType )
    {
        try
        {
            int deleteStatus = 0;
            for( int i = 0; i < petTypes.size(); i++ )
            {
                if( petTypes.get( i ).getPetId() == id )
                {
                    PetType pt = petTypes.get( i );
                    if( petType.getPetType() != pt.getPetType() )
                    {
                        pt.setPetType( petType.getPetType() );
                        return Response.ok( pt ).build();
                    }

                }

            }

        }
        catch( Exception e )
        {
            return Response.serverError().build();
        }
        return Response.status( 204, "Not Found" ).build();

    }


}
