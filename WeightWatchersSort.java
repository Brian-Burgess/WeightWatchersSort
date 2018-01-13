import java.util.*;
import java.lang.Math;

public class WeightWatchersSort {

    public static void main(String args[]) {

        ///////////////////
        //Unrelated setup//
        ///////////////////
        
        int size = 10000;
        int[] arrayValues = new int[size];

        for (int i = 0; i < size; i++) {
            arrayValues[i] = i + 1;
        }

        //shuffling the array...
        List<Integer> tmpList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tmpList.add(arrayValues[i]);
        }
        Collections.shuffle(tmpList);

        for (int i = 0; i < size; i++) {
            arrayValues[i] = tmpList.get(i);
        }

        //////////////////////
        //WeightWatchersSort//
        //////////////////////

        boolean isSorted = false;

        while (!isSorted) {

            WeightNode[] listOfWeights = new WeightNode[size];

            int maxValue = arrayValues[0]; //will hold the "running" max of arrayValues
            int maxWeightCount = 0; // counts how many times a local max was reached (how many times maxValue changed)
            int multiplier = (int) Math.pow(10, Math.log10(size)); // guards against duplicate values

            int minWeight = multiplier, maxWeight = 0; // act as starting/ending positions for the store session

            for (int i = 0; i < size; i++) { //

                if (arrayValues[i] > maxValue) {
                    maxValue = arrayValues[i];
                }

                WeightNode newWeight = new WeightNode();
                newWeight.weight = (int)(multiplier * ((double) arrayValues[i] / (double) maxValue)); //new weight == (multiplier * val/maxVal)
                newWeight.value = arrayValues[i];
                listOfWeights[i] = newWeight;

                if (listOfWeights[i].weight == multiplier) { //new max value reached
                    maxWeightCount++;
                }

                if (newWeight.weight < minWeight) {
                    minWeight = newWeight.weight;
                }
                if (newWeight.weight > maxWeight) {
                    maxWeight = newWeight.weight;
                }

            }

            if(minWeight != maxWeight) {

                for (int i = 0, minWeightInsert = 0, maxWeightInsert = maxWeightCount; i < size; i++) {

                    if (listOfWeights[i].weight == multiplier) {
                        //places vals with max weight at the location where non-max weights end
                        arrayValues[size - maxWeightInsert] = listOfWeights[i].value;
                        maxWeightInsert--;
                    } else {
                        arrayValues[minWeightInsert] = listOfWeights[i].value;
                        minWeightInsert++;
                    }

                }
            } else{
                isSorted = true;
            }
        }

        for(int i = 0; i< size; i++){
            System.out.print(arrayValues[i] + " ");
        }

    }
}

class WeightNode{
    int weight;
    int value;
}
