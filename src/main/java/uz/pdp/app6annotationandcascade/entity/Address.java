package uz.pdp.app6annotationandcascade.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    /**
     * @JsonIgnore pastidagi fildni frontendga berib yubormaydi
     *
     */
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person person;

    public Address(String street, String city, Person person) {
        this.street = street;
        this.city = city;
        this.person = person;
    }
}
