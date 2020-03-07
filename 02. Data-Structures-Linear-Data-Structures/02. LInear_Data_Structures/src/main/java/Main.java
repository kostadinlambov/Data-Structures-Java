import implementations.ArrayList;
import implementations.Queue;
import implementations.Stack;
import interfaces.AbstractQueue;
import interfaces.AbstractStack;
import interfaces.List;

public class Main {
    public static void main(String[] args) {

        /*
        * ArrayList implementation
        */
        List<String> elements = new ArrayList<>();

//        elements.add("first element");
//        elements.add("second element");
//        elements.add("third element");
//        elements.add(1, "test element");
//        elements.add("fourth element");

//        String testElement1 = elements.get(1);
//
//        String setResult = elements.set(1, "secondTest element");
//        System.out.println("setResult: " +  setResult);
//
//        String testElement = elements.get(1);
//        System.out.println(testElement);
//
//        String remove = elements.remove(0);
//        System.out.println("remove: " +  remove);
//
//        System.out.println("size: " + elements.size());

//        System.out.println();

        /*
         * Stack implementation
         */

        AbstractStack<String> stringStack = new Stack<>();

        stringStack.push("firstElement");
        stringStack.push("secondElement");
        stringStack.push("thirdElement");

        String pop = stringStack.pop();
        System.out.println("pop: " + pop);

        System.out.println();

        /*
         * Queue implementation
         */

        AbstractQueue<String> queue = new Queue<>();

//        stringStack.push("firstElement");
//        stringStack.push("secondElement");
//        stringStack.push("thirdElement");
//
//        String pop = stringStack.pop();
//        System.out.println("pop: " + pop);

        System.out.println();
    }
}
