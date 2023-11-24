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
        String desc = "OpenStax is part of Rice University, which is a 501(c)(3) nonprofit \n" +
                "charitable corporation. As an educational initiative, it's our mission to \n" +
                "transform learning so that education works for every student. Through our\n" +
                "partnerships with philanthropic organizations and our alliance with other \n" +
                "educational resource companies, we're breaking down the most common barriers \n" +
                "to learning. Because we believe that everyone should and can have access \n" +
                "to knowledge.";
        petitionList.add(new Petition(1, title, desc, "Signedby1", "email1@email.com"));

        title = "Zoo Petition";
        desc = "Welcome to Preparing for College Success, an OpenStax resource. This textbook \n" +
                "was written to increase student access to high-quality learning materials, \n" +
                "maintaining highest standards of academic rigor at little to no cost.";
        petitionList.add(new Petition(2, title, desc, "Signedby2", "email2@email.com"));

        title = "Road Petition";
        desc = "Preparing for College Success is licensed under a Creative Commons Attribution \n" +
                "4.0 International (CC BY) license, which means that you can distribute, remix, \n" +
                "and build upon the content, as long as you provide attribution to OpenStax \n" +
                "and its content contributors";
        petitionList.add(new Petition(3, title, desc, "Signedby3", "email3@email.com"));

        title = "Office Petition";
        desc = "Because our books are openly licensed, you are free to use the entire book or \n" +
                "pick and choose the sections that are most relevant to the needs of your \n" +
                "course. Feel free to remix the content by assigning your students \n" +
                "certain chapters and sections in your syllabus, in the order that you \n" +
                "prefer. You can even provide a direct link in your syllabus to the \n" +
                "sections in the web view of your book.\n";
        petitionList.add(new Petition(4, title, desc, "Signedby4", "email4@email.com"));

        title = "School Petition";
        desc = "Instructors also have the option of creating a customized version of their OpenStax book. The custom version\n" +
                "can be made available to students in low-cost print or digital form through their campus bookstore. Visit the\n" +
                "Instructor Resources section of your book page on OpenStax.org for more information.";
        petitionList.add(new Petition(5, title, desc, "Signedby5", "email5@email.com"));
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
