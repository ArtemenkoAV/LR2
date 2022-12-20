package lab4.Config;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "WES")
@XmlAccessorType(XmlAccessType.FIELD)
public class WESCfg {
        @XmlElement
        private double B1;
        @XmlElement
        private double B2;


}
