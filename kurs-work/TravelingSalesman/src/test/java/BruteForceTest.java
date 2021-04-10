package test.java;

import main.java.ru.tsp.full.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BruteForceTest {

	private static double[][] four;
	private static double[][] nine;
	private static double[][] ten;
	private static double[][] eleven;
	private static double[][] twelve;
	private static double[][] thirteen;
	private static double[][] twenty;

	@BeforeClass
	public static void init() {
		four = new double[][]{
				{0, 20, 42, 35},
				{20, 0, 30, 34},
				{42, 30, 0, 12},
				{35, 34, 12, 0},
		};
		nine = new double[][]
				{{0, 1118, 644, 535, 683, 995, 334, 609, 1153},
						{1118, 0, 620, 583, 918, 908, 784, 621, 997},
						{644, 620, 0, 158, 605, 795, 359, 395, 939,},
						{535, 583, 158, 0, 447, 637, 201, 237, 781,},
						{683, 918, 605, 447, 0, 319, 407, 297, 506},
						{995, 908, 795, 637, 319, 0, 733, 400, 187},
						{334, 784, 359, 201, 407, 733, 0, 333, 877},
						{609, 621, 395, 237, 297, 400, 333, 0, 544,},
						{1153, 997, 939, 781, 506, 187, 877, 544, 0}};

		ten = new double[][]
				{{0, 1118, 644, 535, 683, 995, 334, 609, 1153, 1010},
						{1118, 0, 620, 583, 918, 908, 784, 621, 997, 590},
						{644, 620, 0, 158, 605, 795, 359, 395, 939, 796},
						{535, 583, 158, 0, 447, 637, 201, 237, 781, 638},
						{683, 918, 605, 447, 0, 319, 407, 297, 506, 654},
						{995, 908, 795, 637, 319, 0, 733, 400, 187, 444},
						{334, 784, 359, 201, 407, 733, 0, 333, 877, 734},
						{609, 621, 395, 237, 297, 400, 333, 0, 544, 401},
						{1153, 997, 939, 781, 506, 187, 877, 544, 0, 407},
						{1010, 590, 796, 638, 654, 444, 734, 401, 407, 0}};

		eleven = new double[][]
				{{0, 1118, 644, 535, 683, 995, 334, 609, 1153, 1010, 340},
						{1118, 0, 620, 583, 918, 908, 784, 621, 997, 590, 902},
						{644, 620, 0, 158, 605, 795, 359, 395, 939, 796, 304},
						{535, 583, 158, 0, 447, 637, 201, 237, 781, 638, 322},
						{683, 918, 605, 447, 0, 319, 407, 297, 506, 654, 525},
						{995, 908, 795, 637, 319, 0, 733, 400, 187, 444, 851},
						{334, 784, 359, 201, 407, 733, 0, 333, 877, 734, 118},
						{609, 621, 395, 237, 297, 400, 333, 0, 544, 401, 451},
						{1153, 997, 939, 781, 506, 187, 877, 544, 0, 407, 995},
						{1010, 590, 796, 638, 654, 444, 734, 401, 407, 0, 852},
						{340, 902, 304, 322, 525, 851, 118, 451, 995, 852, 0}};

		twelve = new double[][]
				{{0, 1118, 644, 535, 683, 995, 334, 609, 1153, 1010, 340, 738},
						{1118, 0, 620, 583, 918, 908, 784, 621, 997, 590, 902, 437},
						{644, 620, 0, 158, 605, 795, 359, 395, 939, 796, 304, 159},
						{535, 583, 158, 0, 447, 637, 201, 237, 781, 638, 322, 203},
						{683, 918, 605, 447, 0, 319, 407, 297, 506, 654, 525, 650},
						{995, 908, 795, 637, 319, 0, 733, 400, 187, 444, 851, 807},
						{334, 784, 359, 201, 407, 733, 0, 333, 877, 734, 118, 404},
						{609, 621, 395, 237, 297, 400, 333, 0, 544, 401, 451, 407},
						{1153, 997, 939, 781, 506, 187, 877, 544, 0, 407, 995, 951},
						{1010, 590, 796, 638, 654, 444, 734, 401, 407, 0, 852, 714},
						{340, 902, 304, 322, 525, 851, 118, 451, 995, 852, 0, 463},
						{738, 437, 159, 203, 650, 807, 404, 407, 951, 714, 463, 0}};

		thirteen = new double[][]
				{{0, 1118, 644, 535, 683, 995, 334, 609, 1153, 1010, 340, 738, 473},
						{1118, 0, 620, 583, 918, 908, 784, 621, 997, 590, 902, 437, 778},
						{644, 620, 0, 158, 605, 795, 359, 395, 939, 796, 304, 159, 395},
						{535, 583, 158, 0, 447, 637, 201, 237, 781, 638, 322, 203, 237},
						{683, 918, 605, 447, 0, 319, 407, 297, 506, 654, 525, 650, 210},
						{995, 908, 795, 637, 319, 0, 733, 400, 187, 444, 851, 807, 529},
						{334, 784, 359, 201, 407, 733, 0, 333, 877, 734, 118, 404, 197},
						{609, 621, 395, 237, 297, 400, 333, 0, 544, 401, 451, 407, 212},
						{1153, 997, 939, 781, 506, 187, 877, 544, 0, 407, 995, 951, 756},
						{1010, 590, 796, 638, 654, 444, 734, 401, 407, 0, 852, 714, 613},
						{340, 902, 304, 322, 525, 851, 118, 451, 995, 852, 0, 463, 315},
						{738, 437, 159, 203, 650, 807, 404, 407, 951, 714, 463, 0, 440},
						{473, 778, 395, 237, 210, 529, 197, 212, 756, 613, 315, 440, 0}};


		twenty = new double[][]{{0, 1118, 644, 535, 683, 995, 334, 609, 1153, 1010, 340, 738, 473, 763, 947, 676, 961, 455, 411, 833},
				{1118, 0, 620, 583, 918, 908, 784, 621, 997, 590, 902, 437, 778, 529, 1046, 453, 349, 663, 759, 296},
				{644, 620, 0, 158, 605, 795, 359, 395, 939, 796, 304, 159, 395, 119, 993, 257, 633, 280, 376, 324},
				{535, 583, 158, 0, 447, 637, 201, 237, 781, 638, 322, 203, 237, 232, 775, 141, 517, 122, 218, 287},
				{683, 918, 605, 447, 0, 319, 407, 297, 506, 654, 525, 650, 210, 679, 264, 490, 636, 325, 272, 622},
				{995, 908, 795, 637, 319, 0, 733, 400, 187, 444, 851, 807, 529, 869, 138, 631, 545, 578, 616, 725},
				{334, 784, 359, 201, 407, 733, 0, 333, 877, 734, 118, 404, 197, 433, 671, 342, 685, 134, 135, 488},
				{609, 621, 395, 237, 297, 400, 333, 0, 544, 401, 451, 407, 212, 469, 538, 231, 352, 193, 248, 325},
				{1153, 997, 939, 781, 506, 187, 877, 544, 0, 407, 995, 951, 756, 1013, 219, 775, 648, 737, 792, 869},
				{1010, 590, 796, 638, 654, 444, 734, 401, 407, 0, 852, 714, 613, 807, 534, 589, 241, 594, 649, 539},
				{340, 902, 304, 322, 525, 851, 118, 451, 995, 852, 0, 463, 315, 423, 789, 463, 803, 252, 253, 604},
				{738, 437, 159, 203, 650, 807, 404, 407, 951, 714, 463, 0, 440, 92, 945, 176, 501, 325, 421, 175},
				{473, 778, 395, 237, 210, 529, 197, 212, 756, 613, 315, 440, 0, 469, 474, 325, 564, 115, 62, 482},
				{763, 529, 119, 232, 679, 869, 433, 469, 1013, 807, 423, 92, 469, 0, 1007, 268, 594, 354, 450, 268},
				{947, 1046, 993, 775, 264, 138, 671, 538, 219, 534, 789, 945, 474, 1007, 0, 769, 697, 589, 536, 863},
				{676, 453, 257, 141, 490, 631, 342, 231, 775, 589, 463, 176, 325, 268, 769, 0, 376, 210, 306, 157},
				{961, 349, 633, 517, 636, 545, 685, 352, 648, 241, 803, 501, 564, 594, 697, 376, 0, 545, 600, 326},
				{455, 663, 280, 122, 325, 578, 134, 193, 737, 594, 252, 325, 115, 354, 589, 210, 545, 0, 96, 367},
				{411, 759, 376, 218, 272, 616, 135, 248, 792, 649, 253, 421, 62, 450, 536, 306, 600, 96, 0, 463},
				{833, 296, 324, 287, 622, 725, 488, 325, 869, 539, 604, 175, 482, 268, 863, 157, 326, 367, 463, 0}};
	}

	@Test
	public void fourTest() {
		TravellingSalesmanBruteForce tsp = new TravellingSalesmanBruteForce(four);
		Assert.assertEquals(97.0, tsp.calculateResult(), 0.01);
	}

	@Test
	public void nineTest() {
		TravellingSalesmanBruteForce tsp = new TravellingSalesmanBruteForce(nine);
		Assert.assertEquals(3667, tsp.calculateResult(), 0.01);
	}
	@Test
	public void tenTest() {
		TravellingSalesmanBruteForce tsp = new TravellingSalesmanBruteForce(ten);
		Assert.assertEquals(3722, tsp.calculateResult(), 0.01);
	}
	@Test
	public void elevenTest() {
		TravellingSalesmanBruteForce tsp = new TravellingSalesmanBruteForce(eleven);
		Assert.assertEquals(3836, tsp.calculateResult(), 0.01);
	}
	@Test
	public void twelveTest() {
		TravellingSalesmanBruteForce tsp = new TravellingSalesmanBruteForce(twelve);
		Assert.assertEquals(3812, tsp.calculateResult(), 0.01);
	}
	@Test
	public void thirteenTest() {
		TravellingSalesmanBruteForce tsp = new TravellingSalesmanBruteForce(thirteen);
		Assert.assertEquals(3898, tsp.calculateResult(), 0.01);
	}


}
