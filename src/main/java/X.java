//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TraceDiversity 계산 과정에서 사용된다
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public final class X {
    private final int a;
    private double[] b;

    public X(int var1) {
        this.a = var1;
        this.b = new double[var1];
    }

    public X(double[] var1) {
        this.a = var1.length;
        this.b = new double[this.a];

        for (int var2 = 0; var2 < this.a; ++var2) {
            this.b[var2] = var1[var2];
        }

    }

    public final int a() {
        return this.a;
    }

    public final double a(X var1) {
        if (this.a != var1.a) {
            throw new IllegalArgumentException("dimensions disagree");
        } else {
            double var2 = 0.0D;

            for (int var4 = 0; var4 < this.a; ++var4) {
                var2 += this.b[var4] * var1.b[var4];
            }

            return var2;
        }
    }

    public final double b() {
        return Math.sqrt(this.a(this));
    }

    public final X b(X var1) {
        if (this.a != var1.a) {
            throw new IllegalArgumentException("dimensions disagree");
        } else {
            X var2 = new X(this.a);
            for (int var3 = 0; var3 < this.a; ++var3) {
                var2.b[var3] = this.b[var3] + var1.b[var3];
            }
            return var2;
        }
    }

    public final void c(X var1) {
        if (this.a != var1.a) {
            throw new IllegalArgumentException("dimensions disagree");
        } else {
            for (int var2 = 0; var2 < this.a; ++var2) {
                double[] var10000 = this.b;
                var10000[var2] += var1.b[var2];
            }

        }
    }

    public final X d(X var1) {
        if (this.a != var1.a) {
            throw new IllegalArgumentException("dimensions disagree");
        } else {
            X var2 = new X(this.a);

            for (int var3 = 0; var3 < this.a; ++var3) {
                var2.b[var3] = this.b[var3] - var1.b[var3];
            }

            return var2;
        }
    }

    public final X a(double var1) {
        X var3 = new X(this.a);

        for (int var4 = 0; var4 < this.a; ++var4) {
            var3.b[var4] = var1 * this.b[var4];
        }

        return var3;
    }

    public final void b(double var1) {
        for (int var3 = 0; var3 < this.a; ++var3) {
            this.b[var3] = var1 * this.b[var3];
        }

    }

    public final String toString() {
        StringBuilder builder;
        (builder = new StringBuilder()).append('(');

        for (int i = 0; i < this.a; ++i) {
            builder.append(this.b[i]);
            if (i < this.a - 1) {
                builder.append(", ");
            }
        }
        builder.append(')');
        return builder.toString();
    }
}
