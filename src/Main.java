import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;


public class Main {


	public static final File BINARY_FILE = new File("NaturalDisasters.dat");


	public static void main(String[] args) throws InterruptedException {
		Scanner keyboard = new Scanner(System.in);
		int count = 0, choice, year, maxWindSpeed;
		double magnitude, cost;
		String earthquake, name = null, location;

		// Maintain an array (named log) of NaturalDisaster objects, with a length of 10.
		NaturalDisaster[] log = new NaturalDisaster[10];

		//When the program first runs (before the user prompt), open the binary file (named "NaturalDisasters.dat")
		// for reading and read all the NaturalDisaster objects into the array.
		if (BINARY_FILE.exists() && BINARY_FILE.length() > 0) {
			try {
				ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));
				log = (NaturalDisaster[]) fileReader.readObject();
				System.out.println("~~~Previously Recorded Natural Disasters~~~");
				for (int i = 0; i < log.length; i++) {
					if (log[i] == null)
						break;
					count++;
					System.out.println(log[i]);
				}
				fileReader.close();
			} catch (IOException | ClassNotFoundException e) {
				System.err.println(e.getMessage());
			}

			// If the file does not exist or is empty, display the message "[No natural disasters recorded.]
			// Otherwise, loop through the array and print each of the NaturalDisaster objects to the console.
		} else
			System.out.println("[No natural disasters recorded.]");


		do {
			// In a separate loop, prompt the user with 3 options to record an Earthquake (option 1),
			// Hurricane (option 2) in the log. Option 3 is to display all natural disasters,
			// their average cost and the NaturalDisaster with the highest cost.  Option 4 is to exit.
			System.out.print(
					"\n********************************************************************\n"
							+ "**                                                                **\n"
							+ "**                  NATURAL DISASTER LOG                          **\n"
							+ "**                                                                **\n"
							+ "********************************************************************\n"
							+ "** Please select from the following choices:                      **\n"
							+ "** 1) Record an Earthquake                                        **\n"
							+ "** 2) Record a  Hurricane                                         **\n"
							+ "** 3) Display entire log (w/ stats)                               **\n"
							+ "** 4) Exit                                                        **\n"
							+ "********************************************************************\n"
							+ ">> ");

			// If the user enters option 1, prompt for name, year, location, cost and magnitude.
			// Create a new Earthquake object and add it to the array.
			choice = keyboard.nextInt();

			switch (choice) {
				case 1:
					System.out.print("Enter name of Earthquake: ");
					keyboard.nextLine();
					name = keyboard.nextLine();
					System.out.print("Enter year              : ");
					year = keyboard.nextInt();
					System.out.print("Enter location          : ");
					keyboard.nextLine();
					location = keyboard.nextLine();
					System.out.print("Enter cost   $ ");
					cost = keyboard.nextDouble();
					System.out.print("Enter magnitude         : ");
					magnitude = keyboard.nextDouble();
					log[count++] = new Earthquake(name, year, location, cost, magnitude);


					break;

				case 2:
					// Else if the user enters option 2, prompt for name, year, location, cost and max wind speed.
					// Create a new Hurricane object and add it to the array.
					System.out.print("Enter name of Hurricane: ");
					keyboard.nextLine();
					name = keyboard.nextLine();
					System.out.print("Enter year             : ");
					year = keyboard.nextInt();
					System.out.print("Enter location         : ");
					keyboard.nextLine();
					location = keyboard.nextLine();
					System.out.print("Enter cost  $ ");
					cost = keyboard.nextDouble();
					System.out.print("Enter max wind speed   : ");
					maxWindSpeed = keyboard.nextInt();
					try {
						if (maxWindSpeed < 74)
							throw new InsufficientWindSpeedException();

						log[count++] = new Hurricane(name, year, location, cost, maxWindSpeed);

					} catch (InsufficientWindSpeedException e) {
						System.err.println(e.getMessage());
					}
					break;


				case 3:
					// Else if the user enters option 3, display all the (non-null) objects in the array,
					// the average cost of all NaturalDisasters (formatted as currency)
					// and the NaturalDisaster with the highest cost.
					System.out.println("~~~ All Recorded Natural Disasters~~~");
					for (int i = 0; i < count; i++) {
						System.out.println(log[i]);
					}
					NumberFormat currency = NumberFormat.getCurrencyInstance();
					System.out.println("\nThe average cost per natural disaster = " +
							currency.format(calculateAverageCost(log, count)));
					System.out.println("\nThe Costliest Natural Disaster = " + findCostliestDisaster(log, count));
					System.out.println("\nThe Highest Magnitude CA Earthquake = " + findHighestMagnitudeCAEarthquake(log, count));

					break;


				//System.out.println("\n~~~         For (+5 Points) Extra Credit           ~~~");
				//System.out.println("~~~ The California Earthquake w/ Highest Magnitude ~~~");

				case 4:
					// Else if the user enters option 4 (exit), your program should write the array to the binary file
					// (named "NaturalDisasters.dat") and exit.
					try {
						ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));
						fileWriter.writeObject(log);
						fileWriter.close();
						System.out.println("Thanks for keeping us safe!");
					} catch (IOException e) {
						System.err.println(e.getMessage());
					}
					break;

			}
		} while (choice != 4);

		keyboard.close();

	}


			// Create a helper method named public static double calculateAverageCost(NaturalDisaster[] log, int count)
			// that will find average cost of ALL the natural disasters in the log. Use this in your main method (under case 3).


			// Create a helper method named public static NaturalDisaster findCostliestDisaster(NaturalDisaster[] log, int count)
			// that will find the natural disaster with the highest cost. Use this in your main method (under case 3).


			// +5 points extra credit] Create a helper method named public static NaturalDisaster findHighestMagnitudeCAEarthquake(NaturalDisaster[] log, int count)
			// that will find the Earthquake (ignore Hurricanes) in California (ignore other locations) with the highest magnitude. Use this in your main method (under case 3)

	public static double calculateAverageCost(NaturalDisaster[] log, int count)
	{
		double sum = 0.0;
		for (int i = 0; i < count; i++)
			sum += log[i].getCost();
		return sum / count;
	}

	public static NaturalDisaster findCostliestDisaster(NaturalDisaster[] log, int count)
	{
		double costliestDisaster = Double.MIN_VALUE;
		NaturalDisaster maxND = null;
		for (int i = 0; i < count; i++) {
			if (log[i] instanceof NaturalDisaster)
			{
				NaturalDisaster n = (NaturalDisaster) log[i];
				if (n.getCost() > costliestDisaster)
				{
					costliestDisaster = n.getCost();
					maxND = n;
				}
			}

		}
		return maxND;
	}

	public static NaturalDisaster findHighestMagnitudeCAEarthquake(NaturalDisaster[] log, int count) {
		double highestMagnitude = Double.MIN_VALUE;
		NaturalDisaster maxMagnitudeEarthquake = null;

		for (int i = 0; i < count; i++) {
			if (log[i] instanceof Earthquake) {
				Earthquake earthquake = (Earthquake) log[i];
				if (earthquake.getLocation().equalsIgnoreCase("California") && earthquake.getMagnitude() > highestMagnitude) {
					highestMagnitude = earthquake.getMagnitude();
					maxMagnitudeEarthquake = earthquake;
				}
			}
		}

		return maxMagnitudeEarthquake;
	}
}


