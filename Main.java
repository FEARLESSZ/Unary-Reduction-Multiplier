import java.util.Scanner;
public class Main
{
    private static int[] maxes = new int[10];
    private static int[] maxesA = new int[10];
    private static int[] maxesB = new int[10];
    private static int rows = 0;
    private static int answer = 0;
 
    Scanner input = new Scanner(System.in);
 
    public static void main(String[] args)
    {
        checkAllSolutions();  
        //Multiply(233, 111, true);
    }
    public static int Multiply(int a, int b, boolean print)
    {
        rows = 0;
        int[] weights = new int[16];
        int[] carry = new int[16];
        String Answer = " ";

        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                if (convertToBinary(a)[i] == 1 && convertToBinary(b)[j] == 1) 
                weights[j + i + 1]++; 
            }
        }
        if (print)
        {
            for (int i : weights) 
            {
                System.out.print(i + " ");
            }
            System.out.println(" ");         
        }

        while (!isComplete(weights)) 
        {
            if (!isReady(weights))
            rows++;
            
            for (int j = 0; j < 15; j ++) 
            {
                carry[j] = 0;
            }
            for (int i = 15; i > 0; i--) 
            {
                
                if(weights[i] == 0) 
                {
                    carry[i - 1] += 0;
                    weights[i] = 0;
                }
                if(weights[i] == 1) 
                {
                    carry[i - 1] += 0;
                    weights[i] = 1;
                }
                if(weights[i] == 2) 
                {
                    carry[i - 1] += 1;
                    weights[i] = 0;
                }
                if(weights[i] == 3) 
                {
                    carry[i - 1] += 1;
                    weights[i] = 1;
                }
                if(weights[i] == 4) 
                {
                    carry[i - 2] += 1;
                    carry[i - 1] += 0;
                    weights[i] = 0;
                }
                if(weights[i] == 5) 
                {
                    carry[i - 2] += 1;
                    carry[i - 1] += 0;
                    weights[i] = 1;
                }
                if(weights[i] == 6) 
                {
                    carry[i - 2] += 1;
                    carry[i - 1] += 1;
                    weights[i] = 0;
                }
                if(weights[i] == 7) 
                {
                    carry[i - 2] += 1;
                    carry[i - 1] += 1;
                    weights[i] = 1;
                }
                if(weights[i] == 8) 
                {
                    carry[i - 3] += 0;
                    carry[i - 2] += 0;
                    carry[i - 1] += 1;
                    weights[i] = 6;
                }
            }
            for (int i = 0; i < 15; i++) {
                weights[i] += carry[i];
 
            }
            if (print)
            {
                for (int i : weights) 
                {
                    System.out.print(i + " ");
                }
                System.out.println(" ");
            }
        }
        if (print)
        System.out.println(" ");
        for (int k : weights)
        {
            Answer += String.valueOf(k);
        }
        Answer = Answer.substring(1, 17); 
        if (rows - 1 >= maxes[0])
        {
            for(int i = 9; i > 0; i--)
            {
                maxes[i] = maxes[i - 1];
                maxesA[i] = maxesA[i - 1];
                maxesB[i] = maxesB[i - 1];
                maxes[0] = rows;
                maxesA[0] = a;
                maxesB[0] = b;

                 
            }
        }
        
        return Integer.parseInt(Answer, 2);
        
    }
    public static boolean isComplete (int[] w)
    {
        boolean notDone = false;
        for (int i : w)
        {
            if (!(i == 1 || i == 0))
            notDone = true;
        }
        if (notDone)
        {
        return false;
        }
        return true;  
    }
    public static boolean isReady (int[] w)
    {
        boolean ready = true;
        for (int i : w)
        {
            if (i > 2)
            ready = false; 
        }
        return ready;
        
    }
    public static int[] convertToBinary(int n) 
    {
        int[] result = new int[8];
        String s = Integer.toBinaryString(n);
        int k = 8;
        for (int i = s.length(); i > 0; i--) 
        {
            result[k - 1] = Integer.parseInt(s.substring(i - 1, i));
            k--;
        }
        return result;
    }
    public static void checkAllSolutions()
    {
        int number = 0;
        for (int i = 0; i < 256; i++)
        {
            for (int j = i; j < 256; j++)
            {
                answer = Multiply(i, j, false);
                if (rows > 2)
                {
                    number++;
                    //answer = Multiply(i, j, true);
                }
                
                //System.out.println(i + " * " + j + " = " + answer);
                if ((i * j) != answer)
                {
                System.out.println("FAIL!!!!!" + i + " " + j);
                Multiply(i, j, true);
                j = 256;
                i = 256;
                }
            }
        }
        System.out.println(number);
        for (int i = 1; i < 10; i++) 
        {
        System.out.println(maxes[i] + "   A: " + maxesA[i] + " B: " + maxesB[i]);
        }
    }
}
 
 
