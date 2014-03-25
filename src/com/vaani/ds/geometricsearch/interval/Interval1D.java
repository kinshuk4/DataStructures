package com.vaani.ds.geometricsearch.interval;
/**
 * 
 * @author kchandra
 * Copyright � 2002�2010, Robert Sedgewick and Kevin Wayne.
Last updated: Wed Jun 22 08:04:19 EDT 2011. 
 */
public class Interval1D implements Comparable<Interval1D> {
    public final int low;   // left endpoint
    public final int high;  // right endpoint

    // precondition: left <= right
    public Interval1D(int left, int right) {
        if (left <= right) {
            this.low  = left;
            this.high = right;
        }
        else throw new RuntimeException("Illegal interval");
    }

    // does this interval intersect that one?
    public boolean intersects(Interval1D that) {
        if (that.high < this.low) return false;
        if (this.high < that.low) return false;
        return true;
    }

    // does this interval a intersect b?
    public boolean contains(int x) {
        return (low <= x) && (x <= high);
    }

    public int compareTo(Interval1D that) {
        if      (this.low  < that.low)  return -1;
        else if (this.low  > that.low)  return +1;
        else if (this.high < that.high) return -1;
        else if (this.high < that.high) return +1;
        else                            return  0;
    }

    public String toString() {
        return "[" + low + ", " + high + "]";
    }




    // test client
    public static void main(String[] args) {
        Interval1D a = new Interval1D(15, 20);
        Interval1D b = new Interval1D(25, 30);
        Interval1D c = new Interval1D(10, 40);
        Interval1D d = new Interval1D(40, 50);

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);

        System.out.println("b intersects a = " + b.intersects(a));
        System.out.println("a intersects b = " + a.intersects(b));
        System.out.println("a intersects c = " + a.intersects(c));
        System.out.println("a intersects d = " + a.intersects(d));
        System.out.println("b intersects c = " + b.intersects(c));
        System.out.println("b intersects d = " + b.intersects(d));
        System.out.println("c intersects d = " + c.intersects(d));

    }

}