import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator {

	Random rand = new Random();
	
	@Override
	public int getNextInt() {
		return rand.nextInt();
	}

	@Override
	public boolean getTrueWithProbability(double p) {
		double d = rand.nextDouble();
		if(d <= p)
			return true;
		return false;
	}

}
