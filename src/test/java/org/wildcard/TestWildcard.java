package test.java.org.wildcard;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.java.org.wildcard.Wildcard;

public class TestWildcard {
	public static void main(String[] args) {
		int[] inputSizes = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
		int[] patternSizes = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};		
		String[] chars = {"*", "?"};
		
		Random random = new Random();
		
		int randomPotential = 5;
		
		while (true) {
			
			for (int inputSize: inputSizes) {
				for (int patternSize: patternSizes) {
					ArrayList<String> tests = new ArrayList<String>();
					ArrayList<String> patterns = new ArrayList<String>();
					
					int testcount = 100000;
					
					for (int i=0;i<testcount;i++) {
						tests.add(RandomStringUtils.randomAlphanumeric(inputSize));
						String p = RandomStringUtils.randomAlphanumeric(patternSize);
						
						int addChars = random.nextInt(randomPotential);
						for (int k=0; k<addChars;k++) {
							int index = random.nextInt(p.length());
							
							int choice = random.nextInt(2) % 2;
							
							p = p.substring(0, index) + chars[choice] + p.substring(index);
						}					
						patterns.add(p);
					}
					
					ArrayList<Boolean> customResults = new ArrayList<Boolean>();				
					
					long t = System.currentTimeMillis();
					
					for (int i=0;i<testcount;i++) {
						customResults.add(Wildcard.match(patterns.get(i), tests.get(i)));
					}
					
					long diff = System.currentTimeMillis() - t;
					
					System.out.println("Took: " + diff + " ms to complete: " + testcount + " matches using CustomMatcher inputSize: " + inputSize + " patternSize: " + patternSize);
					
					
				}
				
				System.out.println("\n\n");
			}
		}
	}
}
