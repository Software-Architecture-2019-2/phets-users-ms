package co.edu.unal.phets.user.model;

import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 *
 * @author julian
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "sessions")
public class Session implements Serializable {

    @Id
    @Column(updatable = false)
    @GeneratedValue
    private Long id;

    @NonNull
    @NotNull
    @Column(unique = true, updatable = false)
    private String token;

    @NotNull
    @Column(columnDefinition = "boolean default true")
    private Boolean valid = true;

    @NotNull
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    private User user;

}
