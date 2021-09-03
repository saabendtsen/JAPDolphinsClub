package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    List<Link_person_team> link_person_teams;

    private String name;

    public Team() {
    }

    public Team( String name) {
        this.name = name;
    }

    public List<Link_person_team> getLink_person_teams() {
        return link_person_teams;
    }

    public void setLink_person_teams(List<Link_person_team> link_person_teams) {
        this.link_person_teams = link_person_teams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
