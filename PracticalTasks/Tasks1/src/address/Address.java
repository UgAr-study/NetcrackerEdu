package address;

public class Address {

    private String city;
    private String street;
    private String building;
    private String flat;

    public Address (String city, String street, String building, String flat) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.flat = flat;
    }

    public boolean isSameCity (Address addr) {
        return city.equals(addr.city);
    }

    public boolean isSameStreet (Address addr) {
        return (isSameCity(addr) && (street.equals(addr.street)));
    }

    public boolean isSameBuilding (Address addr) {
        return (isSameStreet(addr) && (building.equals(addr.building)));
    }

    public boolean isSameFlat (Address addr) {
        return (isSameBuilding(addr) && (flat.equals(addr.flat)));
    }

    public boolean equals (Address addr) {
        return isSameFlat(addr);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String toString() {

        return  "\nCity: " + city +
                "\nStreet: " + street +
                "\nBuilding: " + building +
                "\nFlat: " + flat;
    }
}
