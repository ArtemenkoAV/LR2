package lab4.Config;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="DistributerConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class DistributerCfg {
    @XmlElement(name = "ProducersName")
    private String ProducersName;
    @XmlElement(name = "TopicName")
    private String TopicName;

}
