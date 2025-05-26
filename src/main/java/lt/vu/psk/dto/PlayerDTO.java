package lt.vu.psk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer height;
    private Integer weight;
    private String country;
    private Integer jerseyNumber;
}
