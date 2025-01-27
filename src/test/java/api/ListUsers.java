package api;

import java.util.ArrayList;
public class ListUsers {
    public int id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;
}

class Root{
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<ListUsers> data;
    public Support support;
}

class Support{
    public String url;
    public String text;
}

