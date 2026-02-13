package api.models;

public class UserType {

    private String usertype;

    public UserType() {}

    public UserType(String usertype) {
        this.usertype = usertype;
    }


    public String getUsertype() {
        return usertype;
    }
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}