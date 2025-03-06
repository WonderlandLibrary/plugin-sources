package me.frep.vulcan.spigot.util.type;

public final class Pair<X, Y>
{
    private X x;
    private Y y;
    
    public X getX() {
        return this.x;
    }
    
    public Y getY() {
        return this.y;
    }
    
    public void setX(final X x) {
        this.x = x;
    }
    
    public void setY(final Y y) {
        this.y = y;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>)o;
        final Object this$x = this.getX();
        final Object other$x = other.getX();
        Label_0055: {
            if (this$x == null) {
                if (other$x == null) {
                    break Label_0055;
                }
            }
            else if (this$x.equals(other$x)) {
                break Label_0055;
            }
            return false;
        }
        final Object this$y = this.getY();
        final Object other$y = other.getY();
        if (this$y == null) {
            if (other$y == null) {
                return true;
            }
        }
        else if (this$y.equals(other$y)) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $x = this.getX();
        result = result * 59 + (($x == null) ? 43 : $x.hashCode());
        final Object $y = this.getY();
        result = result * 59 + (($y == null) ? 43 : $y.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Pair(x=" + this.getX() + ", y=" + this.getY() + ")";
    }
    
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }
}
