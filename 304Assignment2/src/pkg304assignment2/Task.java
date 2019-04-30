/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg304assignment2;

/**
 *
 * @author Trishen
 */
public class Task {
    /*Represents an individual unique person-task object*/
    int value;
    char person;
    String task;
    String taskID;
    public Task(int v,char p, String t){
        value=v;
        person=p;
        task=t;
        taskID =p+t;
    }
    
    public String toString(){
        return taskID;
    }
    
}
