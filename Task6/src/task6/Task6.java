package task6;

import java.util.Random;
import java.util.Scanner;

class Runner extends Thread{
    int[][] a1,a2,a3;
    int startNumber,endNumber,currentNumber,N;
    
    public Runner(int[][] a1,int[][] a2,int[][] a3,int startNumber,int endNumber,int N) {
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.N=N;
    }
    
    public void run() {
        for(int i=startNumber/N;i<endNumber/N+1;i++){
            for(int j=0;j<N;j++){
                currentNumber=i*N+j;
                for(int s=0;s<N;s++){                
                    if(currentNumber>=startNumber && currentNumber<=endNumber){
                    a3[i][j]=a3[i][j]+a1[i][s]*a2[s][j];
                    }
                }
            }
        }
    }
    
}
public class Task6 {

    public static void main(String[] args) throws InterruptedException{
        int startNumber=0,endNumber,kol,T,N,ost;
        int [][] a1,a2,a3;
        
        Scanner scan=new Scanner(System.in);
        System.out.println("Number of rows");
        N=scan.nextInt();
        a1=new int[N][N];
        a2=new int[N][N];
        a3=new int[N][N];
        /*
          Решение задачи подходит как для числа потоков, ограниченного половиной
          количества элементов матрицы, так и для числа потоков, ограниченного двумя
        */
        do{
        System.out.println("Number of threads(less than N/2)");
        T=scan.nextInt();
        }while(T>N*N/2);   
              
        initialize(a1,N);
        initialize(a2,N);
        print(a1,N);
        print(a2,N);
        kol=N*N/T;
        ost=(N*N)%T;
        
        for(int i=0;i<T;i++){
            endNumber=startNumber+kol-1;
            if(i==T-1) endNumber+=ost;
            Runner r=new Runner(a1,a2,a3,startNumber,endNumber,N);
            r.start();
            r.join();
            startNumber=endNumber+1;
        }
        
        print(a3,N);
    }
    
    public static void initialize(int[][] a,int N){
        Random rand=new Random();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                a[i][j]=rand.nextInt(9);
            }
        }
    }
    
    public static void print(int[][] a,int N){
        System.out.println();
        for(int i=0;i<N;i++){
            System.out.println();
            for(int j=0;j<N;j++){
                System.out.print(" "+a[i][j]);
            }
        }
        System.out.println();
    }
    
}
