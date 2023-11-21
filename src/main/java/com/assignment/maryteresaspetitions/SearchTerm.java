package com.assignment.maryteresaspetitions;

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
