package com.assignment.maryteresaspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class AppController {

    ArrayList<Petition> petitionList;


    public ArrayList<Petition> getPetitionList() {
        return petitionList;
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
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


    @GetMapping("/create")
    public String creationPage() {
        return "create";
    }


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

    public Petition createPetition(SubmittedPetition submittedPetition) {
        System.out.println("New petition: " + submittedPetition.toString());
        Petition petition = new Petition(petitionList.size()+1, submittedPetition.getTitle(),
                submittedPetition.getDesc(), submittedPetition.getSignedby(), submittedPetition.getEmail());
        petitionList.add(petition);
        return petition;
    }


    @GetMapping("/viewall")
    public String viewPetitionsPage(Model model) {
        model.addAttribute("petitions", petitionList);
        return "viewall";
    }

}
