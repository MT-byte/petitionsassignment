package com.assignment.maryteresaspetitions;


// This class implements a data structure required to pass the data (search keywords) between
// the "view" to "controller" of the MVC architecture.
public class SearchTerm {

    private String searchTerm;


    public SearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }


    public String getSearchTerm() {
        return searchTerm;
    }


    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }


    @Override
    public String toString() {
        return "SearchTerm{" +
                "searchTerm='" + searchTerm + '\'' +
                '}';
    }

}
