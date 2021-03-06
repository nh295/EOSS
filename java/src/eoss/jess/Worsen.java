package eoss.jess;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dani
 */
import eoss.attributes.GlobalAttributes;
import eoss.attributes.EOAttribute;
import jess.*;
import java.io.Serializable;

public class Worsen implements Userfunction, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getName() {
        return "Worsen";
    }

    @Override
    public Value call(ValueVector vv, Context c) throws JessException {
        Value v1 = vv.get(1).resolveValue(c);
        Value v2 = vv.get(2).resolveValue(c);

        String attribute = v1.toString();
        String value = v2.stringValue(c);
        //System.out.println(attribute);
        //System.out.println(value);
        if (value.equalsIgnoreCase("nil")) {
            return new Value(-1, RU.INTEGER);
        }

        EOAttribute tmp = (EOAttribute) GlobalAttributes.measurementAttributeSet.get(attribute);
        EOAttribute newAttrib = (EOAttribute) tmp.cloneAttribute(tmp);
        newAttrib.setValue(value);
        String worsenedValue = newAttrib.worsen();
        Value rv = new Value(worsenedValue, RU.STRING);
        return rv;
    }
}
