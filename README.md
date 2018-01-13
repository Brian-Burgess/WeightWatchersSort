# WeightWatchersSort

Sorts an array of integers by:

1. Set location [0] as the maxValue. Whenever a value is seen that is higher, change it to the maxValue. Each time the maxValue changes, increment maxWeightCount.
2. Iterate across the array, storing weights of each integer as 10^(log10(sizeOfArray)) * (valueInArray/currentMaxValue)
3. Iterate across the list of weights. If the weight is equal to the maximum possible weight (10^(log10(sizeOfArray))), place it at size-maxWeightCount (to have all non max values come before). If it is not a maxWeight, place it at the first possible index.
4. Repeat until the minWeight equals 10^(log10(sizeOfArray)), where the array will be sorted.
