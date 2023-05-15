package model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Entity
@Getter
@NamedQuery(name = "UserAuthority.findByName",
        query = "select u from UserAuthority u where u.name = :name")
public class UserAuthority extends AbstractEntity {

    @Column(unique=true)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "common_user_user_authority",
            joinColumns = @JoinColumn(name = "user_authority_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user;
}
