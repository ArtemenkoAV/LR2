package niokr;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@XmlRootElement(name="agent")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentBatteryXmlCfg implements Serializable {

    @XmlElement
    private String agentName = "";
    @XmlElement
    private String type = "";
    @XmlElement(name="ConnectionToServices")
    private ConnectionToServices logpass;
    @XmlElement(name="EquipmentUnderControl")
    private EquipmentUnderControl equipment;
    @XmlElement
    private String platformName = "";
    @XmlElement
    private String platformAddress = "";
    @XmlElement
    private String iface = "";
    @XmlElement
    private int platformPort;
    @XmlElement
    private int deltaT;
    @XmlElement
    private String ifaceIP = "";
    @XmlElement
    private long receivedTime;



}
