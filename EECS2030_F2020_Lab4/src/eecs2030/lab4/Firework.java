package eecs2030.lab4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Firework {

	/**
	 * The random number generator used by the class.
	 */
	private static Random rng = new Random();

	/**
	 * The number of fireworks created.
	 */
	private static int numberOfFireworksCreated = 0;

	/**
	 * The number of particles created.
	 */
	private static int numberOfParticlesCreated = 0;

	/**
	 * The particles for this firework.
	 */
	private List<Particle> particles;

	/**
	 * Initializes this firework to have zero particles.
	 */
	private Firework() {

		// create an empty particle list (use ArrayList)
		// track number of instances of fireworks using one of the class fields above
		this.particles = new ArrayList<Particle>();

		Firework.numberOfFireworksCreated++;

	}

	/**
	 * Returns a yellow firework made up of a single particle that travels straight
	 * up.
	 * 
	 * @param position the starting position of the firework
	 * @param speed    the starting speed of the firework
	 * @return a yellow firework made up of a single particle that travels straight
	 *         up
	 */
	public static Firework pearl(Point2 position, double speed) {

		Firework f = new Firework();

		Vector2 vel = new Vector2(0.0, 1.0);
		vel.multiply(speed);

		Particle p = new Particle(position, vel, 5, Color.YELLOW);

		f.particles.add(p);

		Firework.numberOfParticlesCreated += f.particles.size();

		return f;
	}

	/**
	 * Returns a yellow firework made up of n particles that travel straight up. The
	 * particles are randomly spread out around the given position and travel with a
	 * speed that is randomly distributed around the given speed. See the Lab 4
	 * document for details.
	 * 
	 * @param n        the number of particles in the firework
	 * @param position the average starting position of the particles
	 * @param speed    the average starting speed of the particles
	 * @return a yellow firework made up of n particles that travel straight up
	 */
	public static Firework pearls(int n, Point2 position, double speed) {

		// to implement
		Firework f = new Firework();

		for (int i = 0; i < n; i += 1) {

			Vector2 vel = new Vector2(0.0, 1.0);

			double qe = Firework.rng.nextGaussian() * (0.05 * speed) + speed;
			vel.multiply(qe);

			double vX = Firework.rng.nextDouble();
			vX = (vX - 0.5) * 10 + position.getX();

			double vY = Firework.rng.nextDouble();
			vY = (vY - 0.5) * 30 + position.getY();

			Point2 pos = new Point2(vX, vY);

			Particle p = new Particle(pos, vel, 5, Color.YELLOW);

			f.particles.add(p);
		}

		Firework.numberOfParticlesCreated += f.particles.size();

		return f;

	}

	/**
	 * Returns a blue firework made up of 36 particles moving outward in a circle
	 * centered on the given position. The particles are arranged evenly on the
	 * perimeter of a circle (i.e., every 10 degrees).
	 * 
	 * @param position the starting position of each particle in the firework
	 * @param speed    the speed of each particle
	 * @return a blue firework made up of 36 particles moving outward in a circle
	 *         centered on the given position
	 */
	public static Firework ring(Point2 position, double speed) {

		Firework f = new Firework();

		for (int i = 0; i < 360; i += 10) {

			Vector2 vel = Vector2.dirVector(i);

			double s = Firework.rng.nextGaussian() * (0.03 * speed) + speed;
			vel.multiply(s);

			Particle p = new Particle(position, vel, 3, Color.BLUE);
			f.particles.add(p);
		}

		Firework.numberOfParticlesCreated += f.particles.size();
		return f;
	}

	/**
	 * Returns a firework made up of a pair of rings. The outer blue ring and the
	 * inner red ring are both made up of 36 particles moving outward in a circle
	 * centered on the given position. The inner red ring has particles that move at
	 * half of the speed of the particles in the blue ring. The particles are
	 * arranged evenly on the perimeter of a circle (i.e., every 10 degrees).
	 * 
	 * @param position the starting position of each particle in the firework
	 * @param speed    the speed of each particle in the blue ring
	 * @return a firework made up of a pair rings as described above
	 */
	public static Firework rings(Point2 position, double speed) {

		// to implement

		Color col = Color.BLUE;
		int plife = 3;

		Firework f = new Firework();

		for (int i = 0; i < 2; i += 1) {
			for (int j = 0; j < 360; j += 10) {
				Vector2 vel = Vector2.dirVector(j);

				double qe = Firework.rng.nextGaussian() * (0.03 * speed) + speed;
				vel.multiply(qe);

				Particle p = new Particle(position, vel, plife, col);
				f.particles.add(p);
			}

			col = Color.RED;
			plife = 2;
			speed /= 2;
		}

		Firework.numberOfParticlesCreated += f.particles.size();

		return f;

	}

	/**
	 * Creates a firework made up of n magenta particles that travel primarily
	 * upwards. The particles travel in a direction that is randomly distributed
	 * around the vertical direction with a speed that is randomly distributed
	 * around the given speed.
	 * 
	 * @param n        the number of particles in the fountain
	 * @param position the starting position of the particles
	 * @param speed    the starting average speed of the particles
	 * @return a firework made up of n magenta particles that travel primarily
	 *         upwards
	 */
	public static Firework fountain(int n, Point2 position, double speed) {

		// to implement
		Firework f = new Firework();

		for (int i = 0; i < n; i += 1) {
			double deg = Firework.rng.nextGaussian();
			deg = deg * 2 + 90;
			Vector2 vel = Vector2.dirVector(deg);

			double qe = Firework.rng.nextGaussian() * (0.05 * speed) + speed;
			vel.multiply(qe);

			Particle p = new Particle(position, vel, 10, Color.MAGENTA);
			f.particles.add(p);
		}

		Firework.numberOfParticlesCreated += f.particles.size();

		return f;

	}

	/**
	 * Creates a firework that forms the pistil pattern; see the Lab 4 document for
	 * details.
	 * 
	 * @param position the starting position of the particles
	 * @param speed    the starting average speed of the particles
	 * @return a firework forming the pistil pattern
	 */
	public static Firework pistil(Point2 position, double speed) {

		// to implement

		Color rcol = Color.BLUE;
		int plife = 3;
		int nu = 360;

		Firework f = new Firework();
		for (int i = 0; i < nu; i += 10) {

			Vector2 vel = Vector2.dirVector(i);

			double qe = Firework.rng.nextGaussian() * (0.03 * speed) + speed;

			vel.multiply(qe);

			Particle p = new Particle(position, vel, plife, rcol);
			f.particles.add(p);
		}

		rcol = Color.GREEN;
		plife = 2;
		nu = 500;

		for (int i = 0; i < nu; i += 1) {
			double deg = Firework.rng.nextDouble();
			deg = deg * 360 + 0;
			Vector2 vel = Vector2.dirVector(deg);

			double valS = Firework.rng.nextDouble();

			valS = valS * 0.3 + 0;

			double qe = (valS * speed);
			vel.multiply(qe);

			Particle p = new Particle(position, vel, plife, rcol);

			f.particles.add(p);
		}

		Firework.numberOfParticlesCreated += f.particles.size();
		return f;

	}

	/**
	 * Returns the number of fireworks created.
	 * 
	 * @return the number of fireworks created
	 */
	public static int getNumberOfFireworksCreated() {

		// to implement

		return numberOfFireworksCreated;

	}

	/**
	 * Returns the number of particles created.
	 * 
	 * @return the number of particles created
	 */
	public static int getNumberOfParticlesCreated() {

		// to implement

		return numberOfParticlesCreated;

	}

	/**
	 * Updates all of the firework particles. Causes every particle in the firework
	 * to move.
	 * 
	 * @param dt the amount of time over which to move the firework particles
	 */
	public void update(double dt) {

		// to implement
		for (int i = 0; i < this.particles.size(); i += 1) {
			Particle currentParticle = this.particles.get(i);
			currentParticle.move(dt);
		}

	}

	/**
	 * Returns true if at least one particle in the firework is alive, and false
	 * otherwise.
	 * 
	 * @return true if at least one particle in the firework is alive, and false
	 *         otherwise
	 */
	public boolean isAlive() {

		// to implement
		for (int i = 0; i < this.particles.size(); i += 1) {
			Particle currentParticle = this.particles.get(i);
			
			if (currentParticle.isAlive()) {
				
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the number of particles in this firework.
	 * 
	 * @return the number of particles in this firework
	 */
	public int getNumberOfParticles() {

		// to implement
		
		return this.particles.size();
	}

	/**
	 * Returns a list of particles making up this firework. Modifying the list or
	 * the particles in the list will modify the firework.
	 * 
	 * @return a list of particles making up this firework
	 */
	public List<Particle> getParticles() {

		// use aggregation (i.e. an alias to particle list) when implementing this
		// method

		List<Particle> reparticles = this.particles;
		return reparticles;
	}
}
