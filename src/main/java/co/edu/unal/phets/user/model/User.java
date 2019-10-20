package co.edu.unal.phets.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author julian
 */
@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(updatable = false)
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(min = 3, max = 15)
    @Column(updatable = false, unique = true)
    private String username;

    @NotNull
    @Size(min = 7, max = 50)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 8, max = 30)
    @Column
    private String password;

    @Size(min = 8, max = 300)
    @Column
    private String description;

    @NotNull
    @Size(min = 3, max = 30)
    @Column
    private String city;

    @NotNull
    @Column
    private Double latitude;

    @NotNull
    @Column
    private Double longitude;

    @NonNull
    @Column(columnDefinition = "boolean default true")
    private Boolean confirmed = false;

    @NotNull
    @Column
    private Date creation;

    @Size(min = 36, max = 36)
    @Column
    private String media;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @JsonIgnore
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions;

}
