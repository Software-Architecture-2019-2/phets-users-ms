package co.edu.unal.phets.user.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "username"})
)
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(min = 8, max = 30)
    @Column(name = "password")
    private String password;

    @Size(min = 8, max = 300)
    @Column(name = "description")
    private String description;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @NotNull
    @Column(name = "longitude")
    private Double longitude;

    @NonNull
    @Column(name = "confirmed", columnDefinition = "boolean default true")
    private Boolean confirmed = false;

    @NotNull
    @Column(name = "creation")
    private Date creation;

    @Size(min = 36, max = 36)
    @Column(name = "media")
    private String media;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

}
