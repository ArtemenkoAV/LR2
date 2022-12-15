package lab4.Config;

import lab4.XMLHelper;

public class CreatingTPPCfg {
    public static void main(String[] args) {
        TPPCfg tpp = new TPPCfg();
        tpp.setA(10.7);
        XMLHelper.marshalAny(tpp, "TPP2.xml");
    }
}
