package pr;

import lombok.SneakyThrows;
import lab4.XMLHelper;

public class CreateXml {
    @SneakyThrows
    public static void main(String[] args) {
        CfgClasse c = new CfgClasse();
        c.setA(3);
        c.setB(2);
        XMLHelper.marshalAny(c, "src/main/resources/newCfg2.xml");
    }

}
