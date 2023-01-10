import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Fraction a = new Fraction(in.nextInt(), in.nextInt());
        Fraction b = new Fraction(in.nextInt(), in.nextInt());
        a.print();
        b.print();
        a.plus(b).print();
        a.multiply(b).plus(new Fraction(5, 6)).print();
        a.print();
        b.print();
        in.close();

    }
}

class Fraction {
    int denominator;
    int numerator;

    Fraction() {
        this.numerator = 0;
        this.denominator = 0;
    }

    Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    void print() {
        int r;
        int x = denominator;
        int y = numerator;
        while (y != 0) {
            r = x % y;
            x = y;
            y = r;
        }
        numerator /= x;
        denominator /= x;

        if (numerator == denominator)
            System.out.println(numerator / denominator);
        else
            System.out.println(numerator + "/" + denominator);
        return;

//        if (numerator == denominator) {
//            System.out.println(1);
//        }
//        else if (numerator*2 == denominator) {
//            if (numerator <= 1) {
//                System.out.println(numerator + "/" + denominator);
//            }
//            else {
//                numerator /= 2;
//                denominator /= 2;
//                System.out.println(numerator + "/" + denominator);
//            }
//        }
//        else if (numerator == denominator*2) {
//            System.out.println(numerator / denominator);
//        }
//        else {
//            System.out.println(numerator + "/" + denominator);
//        }
    }

    double toDouble() {
        return (double) numerator / denominator;
    }

    Fraction plus(Fraction r) {
        Fraction f = new Fraction();
        f.numerator = r.numerator * this.denominator + this.numerator * r.denominator;
        f.denominator = r.denominator * this.denominator;
        return f;
    }

    Fraction multiply(Fraction r) {
        Fraction s = new Fraction();
        s.numerator = r.numerator * this.numerator;
        s.denominator = r.denominator * this.denominator;
        return s;
    }

}
