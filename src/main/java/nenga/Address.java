package nenga;

import java.util.ArrayList;

public class Address {

    private String address;
    private String postalCode;
    private String fullname;

    private Address(){

    }

    public Address(String address, String postalCode,String fullname){
        this.address = address;
        this.postalCode = postalCode;
        this.fullname = fullname;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getFullname() {
        return fullname;
    }
}
