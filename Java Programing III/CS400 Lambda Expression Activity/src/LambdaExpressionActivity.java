import java.util.ArrayList;

interface MathOperation {
    public int compute(int a, int b);
}

class Addition implements MathOperation {
    public int compute(int a, int b) {                                               
        return a + b;
    }
}

public class LambdaExpressionActivity {

    public void performAllOperations(int a, int b) {

        ArrayList<MathOperation> ops = new ArrayList<>();

        // TODO: use named class Addition for addition:
        // ops.add( ??? ); // addition 10+6=16
        ops.add(new Addition());

        // TODO: use anonymous class for multiplication:
        // ops.add( ??? ); // multiplication 10*6=60
        ops.add(new MathOperation() {
            @Override
            public int compute(int a, int b) {
                return a * b;
            }
        });

        // TODO: use lambda expression for subtraction:
        // ops.add( ??? ); // subtraction 10-6=4
        ops.add((a1, b1) -> a1 - b1);

        // TODO: use lambda expressions for division:
        // ops.add( ??? ); // division 10/6 = 1 (integer arithmetic)
        ops.add((a1, b1) -> a1 / b1);

        for(MathOperation op : ops) {
            System.out.println( op.compute(a,b) + " // computed by " + op.getClass().toString());
        }
    }

    public static void main(String args[]){
        LambdaExpressionActivity lambdaActivity = new LambdaExpressionActivity();
        lambdaActivity.performAllOperations(10, 6);
    }
}
