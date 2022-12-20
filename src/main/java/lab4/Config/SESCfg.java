package lab4.Config;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@Data
@XmlRootElement(name = "SES")
@XmlAccessorType(XmlAccessType.FIELD)
public class SESCfg {
    @XmlElement
    private List<Double> C;
}
