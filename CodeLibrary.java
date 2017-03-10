

https://www.codechef.com/problems/TWSTR - trie problem

// taking input in the form 
// Input:
// 3
// 1 2 3
// 5 5 6

int tasks = Integer.parseInt(br.readLine());
int[] x = new int[tasks];
int[] y = new int[tasks];
            
String[] in = br.readLine().split(" ");
for(int i=0;i<in.length;i++){
    x[i]=Integer.parseInt(in[i]);
}
            
String[] ins = br.readLine().split(" ");
for(int j=0;j<ins.length;j++){
    y[j]=Integer.parseInt(ins[j]);
}


// template

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
class Codechef {

    public static void main(String[] args) throws NumberFormatException, IOException {
        
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int I = 1; I <= T; I++) {
            
      }
                
    } 
}


// helper method to print array elements
public static void display(int[] newarr) {
        int len = newarr.length;
        for (int i = 0; i < len; i++) {
            System.out.println(newarr[i]);
        }
}
 
// helper method to print 2d array elements 
public static void display2d(int[][] newarr){
         int len = newarr.length;
         for(int i=0;i<len;i++){
                for(int j=0;j<len;j++){
                    System.out.print(newarr[i][j] + " ");
                }
                System.out.println("\n");
            }
}    
  
 // Linear search
 // success - returns index
 // fail - returns -1
 // time complexity - O(n)
 public static int linear_search(int[] a,int key){
    int len = a.length;
    for(int i=0;i<len;i++){
        if(a[i]==key){
            return i;
        }
    }
    return -1;
 }

 // binary search
 // success - returns index
 // fail - returns -1
 // time complexity - O(logn)
 public static int binary_search(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi )/ 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
}


// Strassen Matrix Multiplication 
// Complexity : O(n2)
// naive - multiply takes - O(n3)
// i/p : 2 matrices
// o/p : resultant matrix

private static int[][] StrassenMultiply(int[][] A, int[][] B) {

        int n = A.length;

        int[][] res = new int[n][n];

        // if the input matrix is 1x1
        if (n == 1) {
            res[0][0] = A[0][0] * B[0][0];
        } else {

            // first matrix
            int[][] a = new int[n / 2][n / 2];
            int[][] b = new int[n / 2][n / 2];
            int[][] c = new int[n / 2][n / 2];
            int[][] d = new int[n / 2][n / 2];
            
            // second matrix
            int[][] e = new int[n / 2][n / 2];
            int[][] f = new int[n / 2][n / 2];
            int[][] g = new int[n / 2][n / 2];
            int[][] h = new int[n / 2][n / 2];

            // dividing matrix A into 4 parts
            divideArray(A, a, 0, 0);
            divideArray(A, b, 0, n / 2);
            divideArray(A, c, n / 2, 0);
            divideArray(A, d, n / 2, n / 2);

            // dividing matrix B into 4 parts
            divideArray(B, e, 0, 0);
            divideArray(B, f, 0, n / 2);
            divideArray(B, g, n / 2, 0);
            divideArray(B, h, n / 2, n / 2);
            
             /** 
              p1 = (a + d)(e + h)
              p2 = (c + d)e
              p3 = a(f - h)
              p4 = d(g - e)
              p5 = (a + b)h
              p6 = (c - a) (e + f)
              p7 = (b - d) (g + h)
            **/
           
            
            
            int[][] p1 = StrassenMultiply(addMatrices(a, d), addMatrices(e, h));
            int[][] p2 = StrassenMultiply(addMatrices(c,d),e);
            int[][] p3 = StrassenMultiply(a, subMatrices(f, h));           
            int[][] p4 = StrassenMultiply(d, subMatrices(g, e));
            int[][] p5 = StrassenMultiply(addMatrices(a,b), h);
            int[][] p6 = StrassenMultiply(subMatrices(c, a), addMatrices(e, f));
            int[][] p7 = StrassenMultiply(subMatrices(b, d), addMatrices(g, h));

            
           /**
              C11 = p1 + p4 - p5 + p7
              C12 = p3 + p5
              C21 = p2 + p4
              C22 = p1 - p2 + p3 + p6
            **/
           
            int[][] C11 = addMatrices(subMatrices(addMatrices(p1, p4), p5), p7);
            int[][] C12 = addMatrices(p3, p5);
            int[][] C21 = addMatrices(p2, p4);
            int[][] C22 = addMatrices(subMatrices(addMatrices(p1, p3), p2), p6);

            // adding all subarray back into one
            copySubArray(C11, res, 0, 0);
            copySubArray(C12, res, 0, n / 2);
            copySubArray(C21, res, n / 2, 0);
            copySubArray(C22, res, n / 2, n / 2);
        }
        return res;
    }

    // helper methods    
    // Adding 2 matrices
    public static int[][] addMatrices(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i][j] + b[i][j];
            }
        }
        return res;
    }

    // Subtracting 2 matrices
    public static int[][] subMatrices(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i][j] - b[i][j];
            }
        }
        return res;
    }

    // print matrix
    public static void printMatrix(int[][] a) {
        int n = a.length;
        System.out.println("Resultant Matrix ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // divides array
    public static void divideArray(int[][] P, int[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    // copies
    public static void copySubArray(int[][] C, int[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    } 


// Karatsuba Multiplication
// Naive approach takes - O(n2)
// Complexity - O(n 1.5)





     