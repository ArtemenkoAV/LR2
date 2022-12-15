package niokr;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Equipment {
        private String equipmentType;
        private String voltage;
        private String capacity;
        private String maximumDischargeCurrent;
        private String power;
        private String ultimateOutput;
}
