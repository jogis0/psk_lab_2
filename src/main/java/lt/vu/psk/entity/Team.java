package lt.vu.psk.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "players")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Team {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @ManyToMany(mappedBy = "teams")
    private List<Tournament> tournaments;
}
