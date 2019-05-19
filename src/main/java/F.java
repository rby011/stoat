//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class F {
    public List a = null;

    public F() {
        this.a = new ArrayList();
    }

    public final String toString() {
        String var1 = "";

        MA var3;
        for(Iterator var2 = this.a.iterator(); var2.hasNext(); var1 = var1 + var3.toString() + "  0" + "\n") {
            var3 = (MA)var2.next();
        }

        return var1;
    }
}
