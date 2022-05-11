package uz.pdp.app6annotationandcascade.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddressDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String street;

    @NotNull
    @Size(min = 3, max = 50)
    private String city;


    @NotNull
    private Integer personId;
}
