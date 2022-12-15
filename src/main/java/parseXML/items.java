package parseXML;

import lombok.Data;

import javax.xml.bind.annotation.XmlType;

@Data
@XmlType(propOrder={"name", "ip", "port"})
public class items {
    private String name;
    private String ip;
    private String port;
}
