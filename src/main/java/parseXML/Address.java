package parseXML;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    @XmlElement(name = "service")
    private List<Parametrs> parametrs;


}
