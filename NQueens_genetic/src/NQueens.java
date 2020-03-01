import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NQueens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chromosome[] generation = new Chromosome[50];
		ArrayList<String> solutions = new ArrayList<String>();
		int count = 0;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the number of Queens:");
		int myint = keyboard.nextInt();
		
		for(int i = 0; i < generation.length; i++) {
			generation[i] = new Chromosome(myint);
		}
		
		int generations = 3000*myint;
		
		while(generations > 0) {
			for(int j = 0; j < generation.length; j++) {
				Random rand = new Random();
				int crossover_index = rand.nextInt(generation.length);
				
				Chromosome crossover_candidate = generation[crossover_index];
				
				generation[j].set_fitness();
				//System.out.println("Before crossover & mutation" + generation[j].get_fitness());
				
				generation[j].crossover_mutate(crossover_candidate);
				generation[j].set_fitness();
								
				//System.out.println("After crossover & mutation" + generation[j].get_fitness());
				if(generation[j].get_fitness() == 0) {
					count++;
					if(solutions.contains(Arrays.toString(generation[j].chromosomes))) {
						continue;
					}
					else {
						
						for(int row = 0; row < generation[j].chromosomes.length; row++) {
							for(int column = 0; column < generation[j].chromosomes.length; column++) {
								if(column != generation[j].chromosomes[row]) {
									System.out.print("   |");
								} else {
									System.out.print(" * |");
								}
							}
							System.out.println("");
						}
						
						System.out.println(Arrays.toString(generation[j].chromosomes));
						solutions.add(Arrays.toString(generation[j].chromosomes));
					}
				}
			}
			generations--;
		}	
		System.out.println("Number of solutions: " + solutions.size());
		
	}

}
