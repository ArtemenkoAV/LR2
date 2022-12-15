package parseXML;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name="agent")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {
    @XmlElement
    private String agentName;
    @XmlElement
    private String type;
    @XmlElement
    private Address address;
}
