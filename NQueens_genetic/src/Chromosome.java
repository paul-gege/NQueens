import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Chromosome {

	public int[] chromosomes;
	public int board_size;
	public int fitness;
	
	//Pass board size so we can initialize our chromosomes
	public Chromosome(int size) {
		this.chromosomes = new int[size];
		this.board_size = size;
		InitializeChromosomes(size);
		//System.out.println(this.chromosomes[0]);
	}
	
	public void InitializeChromosomes(int size) {
		Random rand = new Random();
		for(int i = 0; i < size; i++) {
			int n = rand.nextInt(size);
			//System.out.println(n);
			this.chromosomes[i] = n;
		}
	}
	
	public void set_fitness() {
		int hits = 0;
		
		for(int i = 0; i < this.board_size; i++){
			for(int j = 0; j < this.board_size; j++) {
				if(i != j) {
					if(chromosomes[i] == chromosomes[j]) {
						hits++;
					}
					if(Math.abs(chromosomes[i] - chromosomes[j]) == Math.abs(i - j)) {
						hits++;
					}
				}
			}
		}
		
		this.fitness = hits;
//		System.out.println(hits);
	}
	
	public int get_fitness() {
		return fitness;
	}
	
	public void crossover_mutate(Chromosome random_candidate) {
		Chromosome child = this;
		Random rand = new Random();
		int items_to_swap = rand.nextInt(this.board_size - 1);
		int start = rand.nextInt(this.board_size);
		int index = start;
		//Crossover
		for(int i = 0; i < items_to_swap; i++) {
			child.chromosomes[index] = random_candidate.chromosomes[index];
			//System.out.println(child.chromosomes[index]);
			index = (index + 1) % this.board_size;
			//System.out.println(index);
		}
		
		int mutate_or_not = rand.nextInt(100);
		//System.out.println(mutate_or_not);
		//Mutate (70% chance of mutation)
		if(mutate_or_not > 29) {
			//boardsize - 1 is to account for the fact that when J is in its last index k being j+1 will be an issue
			//Getting rid of duplicates
			boolean[] taken = new boolean[this.board_size];
			for(int j = 0; j < this.board_size; j++) {
				taken[this.chromosomes[j]] = true;
			}
			
			Stack<Integer> myNumbers = new Stack<Integer>();
			
			for(int num = 0; num < this.board_size; num++) {
				if(taken[num] == false) {
					myNumbers.add(num);
				}
			}
			
			for(int k = 0; k < this.board_size; k++) {
				for(int n = k+1; n < this.board_size; n++) {
					if(child.chromosomes[k] == child.chromosomes[n]) {
						child.chromosomes[n] = myNumbers.pop();
					}
				}
			}
			
			//Swap random values	
			int mutate_swap = rand.nextInt(this.board_size);
			for(int l = 0; l < mutate_swap; l++) {
				int swap_one = rand.nextInt(this.board_size);
				int swap_two = rand.nextInt(this.board_size);
				int temp = child.chromosomes[swap_one];
				child.chromosomes[swap_one] = child.chromosomes[swap_two];
				child.chromosomes[swap_two] = temp;
			}
			
		}	
		
		//CALCULATE PARENT AND CHILD FITNESS AND USE THE MORE FIT ONE		
		this.set_fitness();
		child.set_fitness();
		
		int parent_fitness = this.get_fitness();
		int child_fitness = child.get_fitness();
		
		if(parent_fitness > child_fitness) {
			this.chromosomes = child.chromosomes;	
		}
	}
}
