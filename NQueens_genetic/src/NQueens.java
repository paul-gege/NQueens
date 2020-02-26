import java.util.Random;

public class NQueens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chromosome[] generation = new Chromosome[50];
		
		for(int i = 0; i < generation.length; i++) {
			generation[i] = new Chromosome(8);
		}
		
		int generations = 3000*8;
		
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
					System.out.println(" ");	
					for(int num = 0; num < generation[j].board_size; num++) {
						System.out.print(generation[j].chromosomes[num]);	
					}
				}
			}
			generations--;
		}		
		
	}

}
