package data;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class GeoPojo {

        private String lat;
        private String lng;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof GeoPojo)) return false;
                GeoPojo geoPojo = (GeoPojo) o;
                return Objects.equals(lat, geoPojo.lat) && Objects.equals(lng, geoPojo.lng);
        }

        @Override
        public int hashCode() {
                return Objects.hash(lat, lng);
        }
}
