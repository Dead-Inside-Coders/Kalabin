/**
 * Copyright 2020 EPAM Systems
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.tver.tstu.tsp.approximate;

import java.util.*;

public class TSP {
    public void calculateResult(double[][] paths) {
        Vector<Double> costs = new Vector<>();
        for(int i =0; i < paths[0].length; i++){
            for(int j =0; j< paths[0].length; j++){
                costs.add(paths[i][j]);
            }
        }
       Graph tsp = new Graph(paths[0].length,costs);
//        System.out.print(tsp.printLargeGraph() + "\n");
//

        int[] cities = new int[paths[0].length];

        for (int i = 0; i < paths[0].length; i++) {
            cities[i] = i;
        }

        long start = System.currentTimeMillis();

        Vector<Edge> tspTravel = tsp.tspPrim(tsp, 0);
        int totalCost = 0;
        for(Edge a : tspTravel){
            totalCost += a.coste;
            //System.out.println((cities[a.v])+ " -> " +(cities[a.w]) + " -- " + " Cost [ " + (a.coste) + " ]");
        }
        //System.out.println("Total distance: " + totalCost);
        long end = System.currentTimeMillis();

        System.out.println(paths[0].length + " cities - " + totalCost + ". Time: " + (end - start) + " ms");
    }
}
