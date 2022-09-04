package data;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class UserPojo {

    private int id;
    private String name;
    private String username;
    private String email;
    private AddressPojo address;
    private String phone;
    private String website;
    private CompanyPojo company;

    public UserPojo(int id, String name, String username, String email, AddressPojo address, String phone, String website, CompanyPojo company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPojo)) return false;
        UserPojo userPojo = (UserPojo) o;
        return id == userPojo.id && Objects.equals(name, userPojo.name)
                && Objects.equals(username, userPojo.username)
                && Objects.equals(email, userPojo.email)
                && Objects.equals(address, userPojo.address)
                && Objects.equals(phone, userPojo.phone)
                && Objects.equals(website, userPojo.website)
                && Objects.equals(company, userPojo.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, address, phone, website, company);
    }
}
