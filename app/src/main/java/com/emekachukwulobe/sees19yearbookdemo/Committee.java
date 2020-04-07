package com.emekachukwulobe.sees19yearbookdemo;

public class Committee {

    private String name;
    private String role;
    private int picture;

    private boolean greeatable;

    private String phone_no;
    private String hi_message;

    public Committee(){
        name = "Nobody";
        role = "Didn't do anything";
        picture = -1;
    }

    public Committee(String name, String role, int picture, boolean greeatable){
        this.name = name;
        this.role = role;
        this.picture = picture;
        this.greeatable = greeatable;
    }

    public Committee(String name, String role, int picture, String phone_no, String hi_message){
        this.name = name;
        this.role = role;
        this.picture = picture;
        this.phone_no = phone_no;
        this.hi_message = hi_message;
    }

    public String getName(){
        return this.name;
    }

    public String getRole(){
        return this.role;
    }

    public int getPicture(){
        return this.picture;
    }

    public boolean isGreeatable(){return this.greeatable; }

    public String getPhone_no(){
        return this.phone_no;
    }

    public String getHi_message(){
        return this.hi_message;
    }


}
