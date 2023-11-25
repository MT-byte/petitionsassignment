package com.assignment.maryteresaspetitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


// The Petitions application is implemented using the MVC architecture. This class is implements
// the "controller" methods of the architecture. It implements all the REST endpoints and also
// has ArrayList that stores all the petitions data.
@SpringBootApplication
@Controller
public class AppController {

    // Data structure that stores all the petitions.
    ArrayList<Petition> petitionList;


    public ArrayList<Petition> getPetitionList() {
        return petitionList;
    }



    // Constructor
    public AppController() {
        petitionList = new ArrayList<Petition>();

        // The following are the sample petitions stored in the petitions list
        String title = "Library Petition";
        String desc = "This non profit organization was set up for everyone, \n " +
                "to especially increase student (of all ages) access to high-quality learning materials, \n" +
                "maintaining highest standards of academic rigor. \n " +
                "And to make resources available to all without any further cost.";
        petitionList.add(new Petition(1, title, desc, "Name1", "email1@email.com"));

        title = "Zoo Petition";
        desc = "Save the animals, support the workers and volunteers. \n" +
                "Come visit us with your family or school friends. \n" +
                "Learn through experience, watching our programs on line or in person.";
        petitionList.add(new Petition(2, title, desc, "Name2", "email2@email.com"));

        title = "Road Petition";
        desc = "Safety for all our road users includes everyone, work as a team. \n" +
                "Drivers are people in all vehicles, passengers of all ages, \n" +
                "pedestrians from little children to elderly and everyone in between, \n" +
                "along with the cyclists and scooters and all other users, time to work as a team.";
        petitionList.add(new Petition(3, title, desc, "Name3", "email3@email.com"));

        title = "Office Petition";
        desc = "Working more comfortably, resources and creches now available. \n" +
                "We support families, students, volunteeers, remote workers, all equally. \n" +
                "Thank you for your support.";
        petitionList.add(new Petition(4, title, desc, "Name4", "email4@email.com"));

        title = "School Petition";
        desc = "As an educational initiative, it's our mission to \n" +
                "transform learning so that education works for every student. Through our\n" +
                "partnerships with organizations and our alliance with other \n" +
                "educational resource companies, we're breaking down the most common barriers \n" +
                "to learning. Thank you for your support.";
        petitionList.add(new Petition(5, title, desc, "Name5", "email5@email.com"));
    }


    // Implements the control logic for the /home REST endpoint.
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }


    // Implements the control logic for the /create REST endpoint, i.e. the create feature
    // of the application.
    @GetMapping("/create")
    public String creationPage() {
        return "create";
    }


    // Implements the control logic for the /create REST endpoint, i.e. the create feature
    // of the application. Petition is stored in the model (ArrayList) and "view" is updated
    // to acknowledge to the user that the petition is created.
    @PostMapping("/create")
    public String createPetition(@ModelAttribute SubmittedPetition submittedPetition, Model model) {
        Petition petition = createPetition(submittedPetition);

        model.addAttribute("num", petition.getNum());
        model.addAttribute("title", petition.getTitle());
        model.addAttribute("desc", petition.getDesc());
        model.addAttribute("signedby", petition.getSignedby());
        model.addAttribute("email", petition.getEmail());
        return "added";
    }


    // This is the core method that creates the petition object and stores it in the
    // ArrayList. This method is used for unit testing as well.
    public Petition createPetition(SubmittedPetition submittedPetition) {
        System.out.println("New petition: " + submittedPetition.toString());
        Petition petition = new Petition(petitionList.size()+1, submittedPetition.getTitle(),
                submittedPetition.getDesc(), submittedPetition.getSignedby(), submittedPetition.getEmail());
        petitionList.add(petition);
        return petition;
    }


    // Implements the control logic for the /viewall REST endpoint, i.e. the view feature
    // of the application.
    @GetMapping("/viewall")
    public String viewPetitionsPage(Model model) {
        model.addAttribute("petitions", petitionList);
        return "viewall";
    }


    // Implements the control logic for the /search REST endpoint, i.e. the search feature
    // of the application.
    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }


    // Implements the control logic for the /search REST endpoint, i.e. the search feature
    // of the application. It searches for the supplied keywords in the description field
    // of all the petitions. The results are then shown to the user on the results webpage.
    @PostMapping("/search")
    public String searchPetitions(@ModelAttribute SearchTerm searchTerm, Model model) {
        ArrayList<Petition> searchResults = searchPetitions(searchTerm);
        model.addAttribute("searchFor", searchTerm.getSearchTerm());
        model.addAttribute("results", searchResults);
        return "results";
    }


    // This is the core method for the search feature. It searches the description field of
    // all the petitions. This method is also used in the unit tests to test the search feature.
    public ArrayList<Petition> searchPetitions(SearchTerm searchTerm) {
        System.out.println("searched: " + searchTerm.toString());
        ArrayList<Petition> searchResults = new ArrayList<Petition>();
        for(Petition p:petitionList) {
            if(p.getDesc().contains(searchTerm.getSearchTerm())) {
                searchResults.add(p);
            }
        }
        return searchResults;
    }


    // Implements the control logic for the /sign REST endpoint, i.e. the sign feature
    // of the application.
    @GetMapping("/sign")
    public String signPage(@RequestParam int petnum, Model model) {
        Petition petition = petitionList.get(petnum-1);
        model.addAttribute("num", petition.getNum());
        model.addAttribute("title", petition.getTitle());
        model.addAttribute("desc", petition.getDesc());
        model.addAttribute("signedby", petition.getSignedby());
        model.addAttribute("email", petition.getEmail());
        return "sign";
    }


    // Implements the control logic for the /sign REST endpoint, i.e. the sign feature
    // of the application. It updates the signedBy and the email field of the petitions
    // data structure. The "view" is updated to indicate to the user that the petition is
    // signed.
    @PostMapping("/sign")
    public String signPetition(@RequestParam int petnum, @ModelAttribute SignName signName, Model model) {
        Petition petition = signPetition(petnum, signName);
        model.addAttribute("num", petition.getNum());
        model.addAttribute("title", petition.getTitle());
        model.addAttribute("desc", petition.getDesc());
        model.addAttribute("signedby", petition.getSignedby());
        model.addAttribute("email", petition.getEmail());
        return "signed";
    }


    // This is the core method that implements the sign feature. It appends (delimited by ;)
    // the signedBy and the email field of the petition data structure. This method is also
    // used in the unit tests to test the sign feature.
    public Petition signPetition(int petnum, SignName signName) {
        System.out.println("signed by: " + signName.toString());
        Petition petition = petitionList.get(petnum-1);
        String newSignedbyList = petition.getSignedby() + "; " + signName.getSignName();
        String newEmailList = petition.getEmail() + "; " + signName.getEmail();
        petition.setSignedby(newSignedbyList);
        petition.setEmail(newEmailList);
        return petition;
    }

}
