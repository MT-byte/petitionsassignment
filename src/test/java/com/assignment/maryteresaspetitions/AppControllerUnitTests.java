package com.assignment.maryteresaspetitions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AppControllerUnitTests {

    // Unit test to test the create petition feature.
    // A SubmittedPetition data structure is passed as a parameter to the createPetition method
    // and the test asserts if the petition is added to the ArrayList, i.e. the "model" of the
    // MVC architecture.
    @Test
    void createPetition() {
        // GIVEN
        AppController petitionAC = new AppController();
        SubmittedPetition newPetition = new SubmittedPetition("my title","my description", "my name", "my@email.com");

        // WHEN
        Petition actualPetition = petitionAC.createPetition(newPetition);

        // THEN
        ArrayList<Petition> petitionList = petitionAC.getPetitionList();
        Petition expectedPetition = new Petition(petitionList.size(), newPetition.getTitle(), newPetition.getDesc(), newPetition.getSignedby(), newPetition.getEmail());
        Assertions.assertEquals(expectedPetition.toString(), actualPetition.toString());
    }


    // Unit test to test the search petition feature.
    // Just to ensure that the search term exists in the description field of one of the petitions,
    // a new petition is created with the description containing the search term. Then the searchPetitions
    // method is called. The test asserts that the correct petition exists in the result.
    @Test
    void searchPetitions() {
        // GIVEN
        AppController petitionAC = new AppController();
        SubmittedPetition newPetition = new SubmittedPetition("my title","my description", "my name", "my@email.com");
        Petition expectedPetition = petitionAC.createPetition(newPetition);
        SearchTerm searchTerm = new SearchTerm(newPetition.getDesc());

        // WHEN
        ArrayList<Petition> results = petitionAC.searchPetitions(searchTerm);

        // THEN
        Petition actualPetition = results.get(0);
        Assertions.assertEquals(expectedPetition.toString(), actualPetition.toString());
    }


    // Unit test to test the sign petition feature.
    // A new petition is created to ensure that the signedBy and email fields have known values in them. signPetition
    // method is called with name and email of the person signing the petition. The test then asserts that the correct
    // data exists in the signedBy and the email field.
    @Test
    void signPetition() {
        // GIVEN
        AppController petitionAC = new AppController();
        SubmittedPetition newPetition = new SubmittedPetition("my title","my description", "my name", "my@email.com");
        petitionAC.createPetition(newPetition);
        ArrayList<Petition> petitionList = petitionAC.getPetitionList();
        Petition petitionBeforeNewSignature = petitionList.get(petitionList.size()-1);
        SignName signName = new SignName("myname", "myname@email.com");
        String expectedSignatures = petitionBeforeNewSignature.getSignedby() + "; " + signName.getSignName();

        // WHEN
        Petition signedPetition = petitionAC.signPetition(petitionList.size(), signName);

        // THEN
        String actualSignatures = signedPetition.getSignedby();
        Assertions.assertEquals(expectedSignatures, actualSignatures);
    }

}
