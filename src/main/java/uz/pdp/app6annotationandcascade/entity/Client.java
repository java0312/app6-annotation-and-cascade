package uz.pdp.app6annotationandcascade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    private String phoneNumber;


    /*
    * mappedBy = "client"  -->  client tablida bank_account ustuni kerak emas
    * cascade = CascadeType.PERSIST   -->  client bilan birga bankAccountni ham qo'shish
    * */
    @OneToOne(mappedBy = "client", cascade = CascadeType.PERSIST)
    private BankAccount bankAccount;

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount=bankAccount;
        bankAccount.setClient(this);
    }
}
