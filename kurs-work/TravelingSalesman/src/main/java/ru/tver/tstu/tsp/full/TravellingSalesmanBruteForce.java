package ru.tver.tstu.tsp.full;

public class TravellingSalesmanBruteForce {
	private int N; //число городов
	private double[][] dists; // матрица расстояний
	private int[] cities;

	private double minDistance = 1000000;

	public TravellingSalesmanBruteForce(double[][] dists) {
		this.N = dists[0].length;
		cities = new int[N];
		this.dists = dists;
		for (int i = 0; i < cities.length; i++) {
			cities[i] = i;
		}
	}

	public void permutation(int lf) {
		if (lf >= cities.length) {                           // перестановки окончены
			print(calculateDistance(dists));                 // выводим перестановку
			return;
		}

		permutation(lf + 1);                                // перестановки элементов справа от lf
		for (int i = lf + 1; i < cities.length; i++) {           // теперь каждый элемент ar[i], i > lf
			swap(lf, i);                            // меняем местами с ar[lf]
			permutation(lf + 1);                            // и снова переставляем всё справа
			swap(lf, i);                            // возвращаем элемент ar[i] назад
		}
	}

	private void swap(int i, int j) {
		int temp = cities[i];
		cities[i] = cities[j];
		cities[j] = temp;
	}

	private void print(double d) {
		if (d < minDistance) {
			minDistance = d;
		}
//		for (int item : cities) {
//			System.out.print(item);
//		}
//		System.out.print(" dist = " + d);
//		System.out.print('\n');
	}

	public double calculateResult() {
		long start = System.currentTimeMillis();
		this.permutation(1);
		long end = System.currentTimeMillis();
		System.out.println("Brute Force: " + dists[0].length + " cities - " + minDistance + ". Time: " + (end - start) + " ms");
		return minDistance;
	}

	private double calculateDistance(double[][] dists) {
		double d = dists[0][cities[0]] + dists[cities[cities.length - 1]][0]; // начало и конец
		for (int i = 1; i < cities.length; i++)
			d += dists[cities[i - 1]][cities[i]];                 // между ar[i-1] и ar[i]
		return d;                                      // длина пути перестановки ar
	}


}
