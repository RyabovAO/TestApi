package data;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class AddressPojo {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoPojo geo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressPojo)) return false;
        AddressPojo that = (AddressPojo) o;
        return Objects.equals(street, that.street) && Objects.equals(suite, that.suite) && Objects.equals(city, that.city) && Objects.equals(zipcode, that.zipcode) && Objects.equals(geo, that.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, suite, city, zipcode, geo);
    }
}
