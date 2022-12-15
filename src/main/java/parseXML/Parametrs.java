package parseXML;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

//@XmlType(propOrder={"name", "ip", "capacity", "port"})
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Parametrs {
    private String name;
    private String ip;
    private String port;
}
