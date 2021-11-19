package petstore;

import com.example.petstore.data.PetDataObject;
import com.example.petstore.pet.Pet;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withArgs;
import static java.util.concurrent.CompletableFuture.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PetResourceTest
{


@BeforeEach
public void resetTheValues()
        {
        System.out.println( "Reset the Values" );
        List<Pet> pets = new ArrayList<>()
        {{
                add( new Pet(1,"dog","Sheeba",6) );
                add( new Pet(2,"cat","Garfield",3) );
                add( new Pet(3,"pig","Taf",6) );
                add( new Pet(4,"fish","Goldi",3) );
                add( new Pet(5,"bird","Poli",1) );
        }};

        List<Pet> pets1 = PetDataObject.getInstance();
        pets1.clear();
        pets1.addAll( pets );
        }

@Test
@Order( 5 )
public void testPetGetEndPoint()
        {
        given().when().get( "/pets" ).then().statusCode( 200 );
        }


@Test
@Order( 5 )
    void testPetGetEndpointSuccessWithValues()
            {
            given()
            .when().get( "/pets" )
            .then()
            .assertThat()
            .statusCode( 200 )
            .body( "petId", notNullValue() )
            .body( "petAge", equalTo( new ArrayList()
            {{
            add( 5 );
            add( 2 );
            add( 2 );
            }} ) )
            .body( "petName", equalTo( new ArrayList()
            {{
            add( "Shingo" );
            add( "Shoee" );
            add( "Fooo" );
            add( "Feshi" );
            add( "Leno" );
            }} ) )
            .body( "petType", equalTo( new ArrayList()
            {{
            add( "dog" );
            add( "cat" );
            add( "pig" );
            add( "fish" );
            add( "bird" );
            }} ) );

            }
@Test
@Order( 1 )
public void testedAdding()
        {
        given()
        .header( "Content-Type", "application/json" )
        .body( "{\n" +
        "\t\"petType\":\"Dog\",\n" +
        "\t\"petName\":\"Rox\",\n" +
        "\t\"petAge\":15\n" +
        "}" )
        .when().post( "/pets" )
        .then()
        .assertThat()
        .statusCode( 200 )
        .body( "petId", notNullValue() )
        .body( "petAge", equalTo( 15 ) )
        .body( "petName", equalTo( "Rox" ) )
        .body( "petType", equalTo( "Dog" ) );

        }

@Test
@Order( 2 )
    void testUpdatePet()
            {
            given()
            .header( "Content-Type", "application/json" )
            .pathParam( "id", 1 )
            .body( "{\n" +
            "\t\"petType\":\"Dog\",\n" +
            "\t\"petName\":\"Rox\",\n" +
            "\t\"petAge\":15\n" +
            "}" )
            .when().put( "/pets/{id}" )
            .then()
            .assertThat()
            .statusCode( 200 )
            .body( "petId", notNullValue() )
            .body( "petAge", equalTo( 15 ) )
            .body( "petName", equalTo( "Rox" ) )
            .body( "petType", equalTo( "Dog" ) );

            }

@Test
@Order( 3 )
    void testUpdatePetNotValidId()
            {
            given()
            .header( "Content-Type", "application/json" )
            .pathParam( "id", 6 )
            .body( "{\n" +
            "\t\"petType\":\"Dog\",\n" +
            "\t\"petName\":\"Rox\",\n" +
            "\t\"petAge\":15\n" +
            "}" )
            .when().put( "/pets/{id}" )
            .then()
            .assertThat()
            .statusCode( 204 );

            }


@Test
@Order( 4 )
    void testDeletePet()
            {
            given()
            .header( "Content-Type", "application/json" )
            .pathParam( "id", 1 )
            .when().delete( "/pets/{id}" )
            .then()
            .assertThat()
            .statusCode( 200 );

            }

@Test
    void testSearchPets()
            {
            given().header( "Content-Type", "application/json" )
            .queryParam( "age", "5" )
            .when().get( "/pets/search" )
            .then()
            .assertThat()
            .statusCode( 200 )
            .body( "petId", notNullValue() )
            .body( "petAge", equalTo( new ArrayList()
            {{
            add( 5 );
            }} ) )
            .body( "petName", equalTo( new ArrayList()
            {{
            add( "boola" );
            }} ) )
            .body( "petType", equalTo( new ArrayList()
            {{
            add( "dog" );
            }} ) );
            ;
            }


/////////////////////////////////    Pet Type testCases   ///////////////////////////////////////////


@Test
    void testGetPetType()
            {
            given()
            .when().get( "/petTypes" )
            .then()
            .assertThat()
            .statusCode( 200 )
            .body( "petType", equalTo( new ArrayList()
            {{
            add( "dog" );
            add( "cat" );
            add( "bird" );
            }} ) );

            }

@Test
    void testAddPetType()
            {
            given()
            .header( "Content-Type", "application/json" )
            .body( "{\n" +
            "  \"petId\": 4,\n" +
            "  \"petType\": \"rat\"\n" +
            "}" )
            .when().post( "/petTypes" )
            .then()
            .assertThat()
            .statusCode( 200 )
            .body( "petId", notNullValue() )
            .body( "petType", equalTo( "rat" ) );

            }

@Test
    void testUpdatePetType()
            {
            given()
            .header( "Content-Type", "application/json" )
            .pathParam( "id", 3 )
            .body( "{\n" +
            "  \"petId\": 0,\n" +
            "  \"petType\": \"cats\"\n" +
            "}" )
            .when().put( "/petTypes/{id}" )
            .then()
            .assertThat()
            .statusCode( 200 );

            }

@Test
public void testDeletePetType()
        {

        given()
        .header( "Content-Type", "application/json" )
        .pathParam( "id", 1 )
        .when().delete( "/petTypes/{id}" )
        .then()
        .assertThat()
        .statusCode( 200 );

        }
        }