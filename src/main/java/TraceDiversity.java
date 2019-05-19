//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TraceDiversity {
    protected List aList = new ArrayList();

    // 이벤트 벡터 , restoreFSMFromFile 를 표현하는 것으로 판단된다
    // restoreFSMFromFile = (ε0, ... ,εi, .., εNe)
    protected List bList = new ArrayList(); /*original bList*/

    public TraceDiversity() {
    }

    public final double a() {
        ArrayList var1 = new ArrayList();
        X var2 = new X(this.bList.size());
        Iterator var3 = this.aList.iterator();

        while (var3.hasNext()) {
            ArrayList var4 = (ArrayList) var3.next();
            X var5 = this.a(var4);
            var1.add(var5);
            var2.c(var5);
        }

        var2.b(1.0D / (double) this.aList.size());
        double var8 = 0.0D;

        X var6;
        for (Iterator var7 = var1.iterator(); var7.hasNext(); var8 += var2.d(var6).b()) {
            var6 = (X) var7.next();
            if (var2.a() != var6.a()) {
                throw new IllegalArgumentException("dimensions disagree");
            }
        }

        return var8 / (double) var1.size();
    }

    private X a(ArrayList var1) {
        if (var1 != null && var1.size() != 0) {
            X var2 = this.a((Integer) var1.get(0));

            for (int var3 = 1; var3 < var1.size(); ++var3) {
                X var4 = this.a((Integer) var1.get(var3));
                X var10000 = var4 = var2.b(var4);
                X var10001 = var4;
                var4 = var2;
                var2 = var10001;
                double var8 = var10001.a(var4);
                double var10 = var2.b();
                double var12 = var4.b();
                if (var10 == 0.0D || var12 == 0.0D) {
                    throw new IllegalArgumentException("zero vector is not allowed");
                }

                var2 = var10000.a(var8 / var10 / var12);
            }

            return var2;
        } else {
            throw new IllegalArgumentException("empty trace is not allowed");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The event restoreFSMFromFile can be presented as aList N-dimensional vector, restoreFSMFromFile = (ε0, ... ,εi, .., εNe)
    // , where εi = 0 if the event restoreFSMFromFile is the i-th event in the event list
    // , otherwise εi = 1
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private X a(int var1) {
        double[] var2 = new double[this.bList.size()];

        for (int i = 0; i < this.bList.size(); ++i) {
            var2[i] = (Integer) this.bList.get(i) == var1 ? 0.0D : 1.0D;
        }

        return new X(var2);
    }

    public static void main(String[] args) {
        TraceDiversity diversity;
        (diversity = new TraceDiversity()).bList.add(1);
        diversity.bList.add(2);
        diversity.bList.add(3);
        diversity.bList.add(4);
        ArrayList var1;
        (var1 = new ArrayList()).add(1);
        var1.add(2);
        var1.add(3);
        diversity.aList.add(var1);
        (var1 = new ArrayList()).add(4);
        var1.add(1);
        var1.add(2);
        var1.add(3);
        diversity.aList.add(var1);
        System.out.println("1 Diversity: " + diversity.a());
        (diversity = new TraceDiversity()).bList.add(1);
        diversity.bList.add(2);
        diversity.bList.add(3);
        (var1 = new ArrayList()).add(1);
        var1.add(2);
        var1.add(1);
        diversity.aList.add(var1);
        (var1 = new ArrayList()).add(2);
        var1.add(1);
        var1.add(2);
        diversity.aList.add(var1);
        System.out.println("2 Diversity: " + diversity.a());
    }
}
