package uz.pdp.app6annotationandcascade.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private String fullName;
    private List<AddressDTO> addressDTOList;
}
