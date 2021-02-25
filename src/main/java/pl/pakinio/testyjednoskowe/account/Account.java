package pl.pakinio.testyjednoskowe.account;

public class Account {

    private boolean active;
    private Address defaultDeliveyAddress;
    private String email;

    public Account() {
        this.active=false;
    }

    public Account(Address defaultDeliveyAddress) {
        this.defaultDeliveyAddress = defaultDeliveyAddress;
        if (defaultDeliveyAddress != null){
            activate();
        }else {
            this.active =false;
        }
    }

    public void activate(){
        this.active=true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Address getDefaultDeliveyAddress() {
        return defaultDeliveyAddress;
    }

    public void setDefaultDeliveyAddress(Address defaultDeliveyAddress) {
        this.defaultDeliveyAddress = defaultDeliveyAddress;
    }

    public void setEmail(String email) {

        if(email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Wrong email format");
        }

    }
    public String getEmail() {
        return this.email;
    }
}
