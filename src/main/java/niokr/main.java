package niokr;

import lombok.SneakyThrows;
import lab4.XMLHelper;

public class main {
    @SneakyThrows
    public static void main(String[] args) {

        AgentBatteryXmlCfg person = XMLHelper.unMarshalAny(AgentBatteryXmlCfg.class, "TestBatteryConfig.xml");

        System.out.println(person);
    }
}
