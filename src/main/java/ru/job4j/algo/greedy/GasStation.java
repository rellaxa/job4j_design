package ru.job4j.algo.greedy;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int tank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i];
            tank -= cost[i];
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }
        }
        if (totalGas < totalCost) {
            return -1;
        }
        return start;
    }

    public static void main(String[] args) {
        GasStation gasStation = new GasStation();
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        int result = gasStation.canCompleteCircuit(gas, cost);
        System.out.println(result);
    }
}
