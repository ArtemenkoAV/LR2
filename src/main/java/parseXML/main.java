package parseXML;

import lab4.XMLHelper;
import lombok.SneakyThrows;
import pr.CfgClasse;

public class main {
    @SneakyThrows
    public static void main(String[] args) {
//        String cfgPath = "testik.xml";
//        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        Person person = (Person) unmarshaller.unmarshal(new File(cfgPath));
        CfgClasse person = XMLHelper.unMarshalAny(CfgClasse.class, "src/main/resources/newCfg1.xml");

        System.out.println(person.getA());
    }
}
