package lt.vu.psk.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Player {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Integer jerseyNumber;

    @Version
    private int version;
}