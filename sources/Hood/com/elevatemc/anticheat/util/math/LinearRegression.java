package com.elevatemc.anticheat.util.math;

public final class LinearRegression
{
    private final double intercept;
    private final double slope;
    private final double r2;
    private final double svar0;
    private final double svar1;

    public LinearRegression(final Double[] x, final Double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        final int n = x.length;
        double sumx = 0.0;
        double sumy = 0.0;
        double sumx2 = 0.0;
        for (int i = 0; i < n; ++i) {
            sumx += x[i];
            sumx2 += x[i] * x[i];
            sumy += y[i];
        }
        final double xbar = sumx / n;
        final double ybar = sumy / n;
        double xxbar = 0.0;
        double yybar = 0.0;
        double xybar = 0.0;
        for (int j = 0; j < n; ++j) {
            xxbar += (x[j] - xbar) * (x[j] - xbar);
            yybar += (y[j] - ybar) * (y[j] - ybar);
            xybar += (x[j] - xbar) * (y[j] - ybar);
        }
        this.slope = xybar / xxbar;
        this.intercept = ybar - this.slope * xbar;
        double rss = 0.0;
        double ssr = 0.0;
        for (int k = 0; k < n; ++k) {
            final double fit = this.slope * x[k] + this.intercept;
            rss += (fit - y[k]) * (fit - y[k]);
            ssr += (fit - ybar) * (fit - ybar);
        }
        final int degreesOfFreedom = n - 2;
        this.r2 = ssr / yybar;
        final double svar = rss / degreesOfFreedom;
        this.svar1 = svar / xxbar;
        this.svar0 = svar / n + xbar * xbar * this.svar1;
    }

    public double intercept() {
        return this.intercept;
    }

    public double slope() {
        return this.slope;
    }

    public double R2() {
        return this.r2;
    }

    public double interceptStdErr() {
        return Math.sqrt(this.svar0);
    }

    public double slopeStdErr() {
        return Math.sqrt(this.svar1);
    }

    public double predict(final double x) {
        return this.slope * x + this.intercept;
    }

    public double predict(final double x1, final double x2) {
        return this.slope * x1 + this.intercept + this.slope * x2;
    }

    @Override
    public String toString() {
        return String.format("%.2f n + %.2f", this.slope(), this.intercept()) +
                "  (R^2 = " + String.format("%.3f", this.R2()) + ")";
    }
}

