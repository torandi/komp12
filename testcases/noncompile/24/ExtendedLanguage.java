// EXT:ISC

/**
 * Tests that the speed member is overriden in the sub class.
 *
 * The code is inspired by Java Tutorials (Inheritance chapter)
 * Modified to fit the MiniJava grammar.
 *
 */
class ExtendedLanguage {
	public static void main(String[] args) {
		System.out.println(new MountainBike().setMb().getSpeed());

	}
}

class Bicycle {

    int cadence;
    int gear;
    int speed;

    public int start() {
	cadence = 4;
	gear = 10;
	speed = 30;
	return 0;
    }

    public int setCadence(int newValue) {
	cadence = newValue;
	return 0;
    }

    public int setGear(int newValue) {
	gear = newValue;
	return 0;
    }

    public int applyBrake(int decrement) {
	speed = speed - decrement;
	return 0;
    }

    public int speedUp(int increment) {
	speed = speed + increment;
	return 0;
    }

	public int getCadence() { return cadence; }
	public int getGear() { return gear; }
	public int getSpeed() { return speed; }
}

class MountainBike extends Bicycle {

    int seatHeight;

	public int setMb() {
		seatHeight = 5;
		speed = 20; //override
		return 0;
	}

    public int setHeight(int newValue) {
	seatHeight = newValue;
	return 0;
    }

	public int getSeatHeight() { return seatHeight; }
}
