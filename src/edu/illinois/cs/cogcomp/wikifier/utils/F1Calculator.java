package edu.illinois.cs.cogcomp.wikifier.utils;

import java.util.Set;

import com.google.common.collect.Sets;
/**
 * Requires type {@link T} to implement the correct equals() method
 * so that gold and answer can be directly compared
 * @author cheng88
 *
 * @param <T>
 */
public class F1Calculator<T> {

    private int goldCount = 0;
    private int answerCount = 0;
    private int correctCount = 0;
    
    public void addUnasnweredGold(){
        goldCount++;
    }
    
    public void addWrongAnswer(){
        answerCount++;
    }
    
    public void addInstance(T gold,T answer){
        goldCount += gold == null ? 0 : 1;
        answerCount += answer == null ? 0 : 1;
        if (gold != null && gold.equals(answer))
            correctCount++;
    }
    
    public void addInstances(Set<T> gold,Set<T> answer){
        goldCount += gold.size();
        answerCount += answer.size();
        correctCount += Sets.intersection(gold, answer).size();
    }
    
    public double getPrecision(){
        return correctCount/(double)answerCount;
    }
    
    public double getRecall(){
        return correctCount/(double)goldCount;
    }
    
    public double getF1(){
        double r = getRecall();
        double p = getPrecision();
        return (2*p*r)/(p+r);
    }
    
    public void printStat(){
        System.out.printf("P: %.2f;R: %.2f;F1: %.2f\n",getPrecision(),getRecall(),getF1());
    }
    
    public void printDetailedStat(){
        System.out.printf("Gold: %d;Answer: %d;Correct: %d\n",goldCount,answerCount,correctCount);
        printStat();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        F1Calculator<Boolean> c = new F1Calculator<>();
        c.addInstance(true, true);
        c.addUnasnweredGold();
        c.addWrongAnswer();
        c.printStat();
    }

}
