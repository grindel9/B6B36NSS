package model;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="common_user")
@NamedQuery(name = "User.findByUsername",
        query = "select u from User u where u.username = :username")
public class User extends AbstractEntity implements UserDetails {
    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    protected String username;

    @Basic(optional = false)
    @Column(nullable = false)
    protected String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "common_user_user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_authority_id"))
    protected Set<UserAuthority> userAuthorities;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    protected Set<Session> sessionOwner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "common_user_session",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id"))
    @JsonIgnore
    protected Set<Session> sessionMember;

    public void encodePassword(PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAuthorities.stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return false;
    }
}
