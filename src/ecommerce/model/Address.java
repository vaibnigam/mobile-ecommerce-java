package ecommerce.model;

import java.util.Objects;

public class Address {

    private final String addressLine;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;

    public Address(
            String addressLine,
            String city,
            String state,
            String postalCode,
            String country) {

        if (addressLine == null || addressLine.isBlank()) {
            throw new IllegalArgumentException("Address line cannot be null or blank");
        }

        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }

        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("State cannot be null or blank");
        }

        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code cannot be null or blank");
        }

        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }

        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Address address)) {
            return false;
        }

        return Objects.equals(addressLine, address.addressLine)
                && Objects.equals(city, address.city)
                && Objects.equals(state, address.state)
                && Objects.equals(postalCode, address.postalCode)
                && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                addressLine,
                city,
                state,
                postalCode,
                country
        );
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressLine='" + addressLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}