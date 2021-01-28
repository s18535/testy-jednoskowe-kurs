package pl.pakinio.testyjednoskowe;

public class Account {

    private boolean active;
    private Address defaultDeliveyAddress;

    public Account() {
        this.active=false;
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
}
