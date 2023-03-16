package r2s.com.demo.SpringWebDemo.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER_INFO")
public class User implements UserDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Address> addresses;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<RoleUser> roleUsers;

    public User(String username, String password, List<GrantedAuthority> grantList) {
    }

    public List<GrantedAuthority>  getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        List<String> roleTypes = new ArrayList<>();
        for (RoleUser roleUser : this.getRoleUsers()) {
            String roleType = roleUser.getRoleType();
            grantedAuthorities.add(new SimpleGrantedAuthority(roleType));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
