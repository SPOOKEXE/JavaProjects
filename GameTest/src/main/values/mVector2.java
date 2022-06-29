package main.values;

public class mVector2 {

    // Variables, Properties //
    int x, y;

    // Constructors //
    public mVector2(int x, int y) { this.x = x; this.y = y; }
    public mVector2() { }

    // Methods //
    public double mag() {
        int max = Math.max(this.x, this.y);
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public mVector2 copy() {
        return new mVector2(this.x, this.y);
    }

    public mVector2 sub( mVector2 otherVector ) {
        this.x -= otherVector.x;
        this.y -= otherVector.y;
        return this;
    }

    public mVector2 add( mVector2 otherVector ) {
        this.x += otherVector.x;
        this.y += otherVector.y;
        return this;
    }



}
