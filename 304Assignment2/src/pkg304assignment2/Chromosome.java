/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg304assignment2;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Trishen
 */

public class Chromosome {
    ArrayList<Task>tasks;
    ArrayList<Integer>chromosome ;
    int length,fitness,numTasks;
    
    public Chromosome(int l,ArrayList<Task>t,int tLength){//inital chromosome creation
      this.tasks=t;
        this.length=l;
        this.numTasks=tLength;
       chromosome = new ArrayList<>();
        ArrayList<Integer> val = new ArrayList<>();
        for (int i = 1; i < numTasks; i++) {
            val.add(i);
        }
        
       // System.out.println("Val init size "+val.size());
        
        for (int i = 0; i < length; i++) {
            
            Random rand = new Random();
            int holder = rand.nextInt(val.size());
           // System.out.println(holder);
            //System.out.println(val.get(holder));
            chromosome.add(val.get(holder));
            val.set(holder,-1);
             //System.out.println("Val size "+val.size());
        }
        this.fitness=calcFitness();
    }
    
    public Chromosome(){//for use as a dummy chromosome for fittest calculation
        this.fitness = 0;
        
    }
    public Chromosome(ArrayList<Task>tasks,ArrayList<Integer>chr,int length,int numtask){//child chromosomes
        this.tasks=tasks;
        this.length=length;
        this.numTasks = numtask;
        this.chromosome = chr;
        this.fitness = calcFitness();
        
        
    }
   private int calcFitness(){//fitness function
        int sum =0;
        for (int i = 0; i < chromosome.size(); i++) {
           if(chromosome.get(i)!=-1){
               int t = (i*numTasks)+chromosome.get(i);
               //System.out.println(tasks.get(t).value);
               sum+=tasks.get(t).value;
               //System.out.println(sum);
           } 
           else{
               sum+=0;
           }
        }
        return sum;
    }
   public String getTask(int index){
       int t = (index*numTasks)+chromosome.get(index);
       return tasks.get(index).task;
   }
   public int getValue(int index){
       int t = (index*numTasks)+chromosome.get(index);
       return tasks.get(index).value;
   }
     public String getName(int index){
           int t = (index*numTasks)+chromosome.get(index);
           return ""+tasks.get(t).person;
     }
    public String toString(){
        String s  = "";
       for (int i = 0; i < chromosome.size(); i++) {
           
               int t = (i*numTasks)+chromosome.get(i);
               s+=tasks.get(t).taskID+",";
           
        }
       return s;
    }
}
