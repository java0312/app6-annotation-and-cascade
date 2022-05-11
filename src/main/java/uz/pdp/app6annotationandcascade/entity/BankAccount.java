package uz.pdp.app6annotationandcascade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    /*
    * @OnDelete(action = OnDeleteAction.CASCADE) --> //Agara client o'chsa bankAccount ham o'chadi
    * */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Client client;
}
