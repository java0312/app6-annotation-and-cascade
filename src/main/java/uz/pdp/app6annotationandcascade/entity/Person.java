package uz.pdp.app6annotationandcascade.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "birth_date is not null") //personnni chaqirsak birth_date not null bolganlarni olib keladi
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;


    /**
     * mappedBy = "person"  -->  Aslida person_addresses table yaratilishi kerak edi, shuni yaratmayd
     * cascade = CascadeType.PERSIST  -->  IKKALA tableni bir paytda saqlash
     * cascade = CascadeType.MERGE --> update qilganda lekin persist ham buning ishini bajaradi
     * cascade = CascadeType.REMOVE --> ochirishda forinkey xatolikni bermaydi va addresslar bilan ochiradi
     */
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @OrderBy(value = "street asc, city desc ")
    private List<Address> addresses;


    private LocalDate birthDate;

    @Transient
    private Integer age;

    /**
     * @Transient  --> count_full_name_latter ustuni database dagi table ga qo'shimaydi
     */
    @Transient
    private Integer countFullNameLatter;

    public Integer getCountFullNameLatter() {
        return fullName == null ? 0 : fullName.length();
    }

    public Integer getAge() {
        if (birthDate == null)
        return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
