package r2s.com.demo.SpringWebDemo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "ADDRESS")
public class Address {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "STREET")
    private String street;

    @Column(name = "TYPE")
    private boolean type;

    @Column(name = "DF_ADDRESS")
    private boolean defaultAddress;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "ADDRESS_USER",
    joinColumns = @JoinColumn(name = "ADDRESS_ID"),
    inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> users;


}
