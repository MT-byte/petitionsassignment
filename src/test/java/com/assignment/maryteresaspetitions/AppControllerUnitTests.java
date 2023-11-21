package com.assignment.maryteresaspetitions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AppControllerUnitTests {

    @Test
    void createPetition() {
        AppController petitionAC = new AppController();
        SubmittedPetition newPetition = new SubmittedPetition("my title","my description", "my name", "my@email.com");

        Petition actualPetition = petitionAC.createPetition(newPetition);

        ArrayList<Petition> petitionList = petitionAC.getPetitionList();
        Petition expectedPetition = new Petition(petitionList.size(), newPetition.getTitle(), newPetition.getDesc(), newPetition.getSignedby(), newPetition.getEmail());
        Assertions.assertEquals(expectedPetition.toString(), actualPetition.toString());
    }


    @Test
    void searchPetitions() {
        AppController petitionAC = new AppController();
        SubmittedPetition newPetition = new SubmittedPetition("my title","my description", "my name", "my@email.com");
        Petition expectedPetition = petitionAC.createPetition(newPetition);
        SearchTerm searchTerm = new SearchTerm(newPetition.getDesc());

        ArrayList<Petition> results = petitionAC.searchPetitions(searchTerm);

        Petition actualPetition = results.get(0);
        Assertions.assertEquals(expectedPetition.toString(), actualPetition.toString());
    }

}
