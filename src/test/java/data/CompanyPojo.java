package data;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class CompanyPojo {

    private String name;
    private String catchPhrase;
    private String bs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyPojo)) return false;
        CompanyPojo that = (CompanyPojo) o;
        return Objects.equals(name, that.name) && Objects.equals(catchPhrase, that.catchPhrase) && Objects.equals(bs, that.bs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, catchPhrase, bs);
    }
}
