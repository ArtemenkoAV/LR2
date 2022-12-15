package lab4.Config;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name="ConsumerLoad")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsumerCfg {
    @XmlElement
    private List<Double> load;
    @XmlElement
    private String distributerName;
    @XmlElement
    private double maxPrice;

}
