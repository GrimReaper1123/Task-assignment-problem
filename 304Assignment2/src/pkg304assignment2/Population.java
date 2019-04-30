/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg304assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Trishen
 */
public class Population {
    private HashSet<Chromosome> pop = new HashSet<Chromosome>();//store population
   // ArrayList<Chromosome>pop;
    ArrayList<Task>tasks;
    int length,numTasks;
    public static int generation;
    Chromosome fittest;
    private ArrayList<Chromosome> arrayPop; 
    ArrayList<Chromosome>parents = new ArrayList<>();
    int popSize;
    int optFitness;
    public Population(ArrayList<Task>t, int length, int numTasks){
        tasks = t;
        this.length=length;
        this.numTasks = numTasks;
                
    }
    
    public void generateInitialPopulation(){
        arrayPop = new ArrayList<>();
        System.out.println("Gen init pop\n"+numTasks+" "+length);
       
         popSize=(int)(Math.round(Math.pow(numTasks, length))/(Math.pow(numTasks,(length-3))));
         System.out.println(popSize);
       //pop=new ArrayList<>();
        Chromosome chromo = new Chromosome();
        System.out.println("init created");
        fittest = chromo;
        int index = 0;
        
        while(popSize>pop.size()){
			chromo = new Chromosome(length, tasks, numTasks);
                       // System.out.println("c1 created");
			while(!pop.add(chromo)){
				chromo = new Chromosome(length, tasks, numTasks);
                                System.out.println(chromo.chromosome);
			}
                       // System.out.println(chromo.chromosome);
			arrayPop.add(index,chromo);
                        if(chromo.fitness>fittest.fitness){
                            fittest = chromo;
                        }
                        index++;

         /*    for (int i = 0; i < popSize; i++) {
            Chromosome c = new Chromosome(length, tasks, numTasks);
            pop.add(c);
            if(c.calcFitness()>fittest.fitness){
                fittest = c;
            }
        }*/
             
        }
        optFitness = generateOptimalChromosomeFitness();
        System.out.println(optFitness);
             System.out.println(""+popSize);
             
             generation=1;
        
       
    }
    
    public int getGeneration(){
        return generation;
    }
   public void generatePopulation(){
       selectParents();
       pop.clear();
       arrayPop.clear();
       Collections.shuffle(parents);
       int index = 1;
       while(index<=parents.size()-1 && pop.size()!=popSize){
           
           ArrayList<Integer>parent1 = parents.get(index-1).chromosome;
           ArrayList<Integer>parent2 = parents.get(index).chromosome;
           ArrayList<Integer>child1 = new ArrayList<>();
           ArrayList<Integer>child2 = new ArrayList<>();
           
           Random r = new Random();
           int z = r.nextInt(100)+1;
           if(z<=80){                   //crossover
              // System.out.println("Crossover");
             Random rand = new Random();
           int point = rand.nextInt(length);
           for (int i = 0; i < length; i++) {
          if(i<point){
              child1.add(i, parent1.get(i));
              child2.add(i,parent2.get(i));
          }
          else{
               child1.add(i, parent2.get(i));
              child2.add(i,parent1.get(i));
          }
      }
           }
           else{
               child1 = parent1;
               child2 = parent2;
           }
           if(z>=90){                       //Mutation
              // System.out.println("Mutation");
               Random rand = new Random();
           int point = rand.nextInt(length);
           int hold = child1.get(point);
           child1.set(point, child2.get(point));
           child2.set(point, hold);
           
           }
           
           Chromosome c1 = new Chromosome(tasks,child1, length, numTasks);
           Chromosome c2 = new Chromosome(tasks,child2,length,numTasks);
           boolean c1Add = pop.add(c1);
           boolean c2Add=pop.add(c2);
           if(c1Add){
                arrayPop.add(c1);
                if(c1.fitness>fittest.fitness){
                    fittest=c1;
                }
           }
           else{
               arrayPop.add(parents.get(index-1));
               if(parents.get(index-1).fitness>fittest.fitness){
                    fittest=parents.get(index-1);
                }
           }
          if(c2Add){
              arrayPop.add(c2);
              if(c2.fitness>fittest.fitness){
                    fittest=c2;
                }
          }
          else{
              arrayPop.add(parents.get(index));
              if(parents.get(index).fitness>fittest.fitness){
                    fittest=parents.get(index);
                }
          }
           
           index+=2;
           
       }
       generation++;
       
       
   }

  
  
   private void selectParents(){//create parent population
       
       int k =25;
       Random ran = new Random();
       Chromosome fittestParent = new Chromosome();
       while(parents.size()!=pop.size()){
           
       
       for (int i = 0; i < k; i++) {
           int t =ran.nextInt(pop.size());
           if(arrayPop.get(t).fitness> fittestParent.fitness){
               fittestParent = arrayPop.get(t);
           }
          
       }
       parents.add(fittestParent);
       
       }
   }
   
    public boolean checkTermination(){
        if(generation>=1000 || fittest.fitness>=optFitness) {
            return true;
        }
        else{
            return false;
        }
    }
    public Chromosome getFittest(){
        return fittest;
    }
   
    public int generateOptimalChromosomeFitness(){//calculate optimal fitness
        int highest =0;
       int sum =0;
        for (int i = 1; i < numTasks+1; i++) {
            highest = 0;
            for (int j = 0; j < tasks.size(); j++) {
                if(tasks.get(j).task.equals(""+i) && tasks.get(j).value>highest){
                    highest = tasks.get(j).value;
                }
            }
            sum+=highest;
        }
        return sum;
    }
}
