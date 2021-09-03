package Entity;

import Entity.Link_person_team;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-09-03T11:57:46")
@StaticMetamodel(Team.class)
public class Team_ { 

    public static volatile SingularAttribute<Team, String> name;
    public static volatile ListAttribute<Team, Link_person_team> link_person_teams;
    public static volatile SingularAttribute<Team, Long> id;

}