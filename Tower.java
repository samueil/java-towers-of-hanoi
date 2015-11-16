package ch.hslu.prg1.allg.hanoi;

import java.util.ArrayList;
import java.util.Collections;

/**
 * In this class the Tower will be generated for a "Tower if Hanoi"-Simulation.
 * at first the 3 poles will be drawn on a Canvas. Then the Tower will be drawn,
 * possible with 1 - 8 blocks. This tower will then be transferred from one pole
 * to another pole via a third pole.
 * 
 * @Rules - During this transfer only one block at once can be moved<br>
 *        - Each moved block should then be placed in a pole again.<br>
 *        - A bigger block can't be put on a smaller block.
 * 
 */

public class Tower {

	private int amount; // Number of blocks
	private int top; // where the Poles start from above
	private int fullHeight; // height of the poles
	private String[] shades; // colors of the blocks, they will get from the
								// Canvas-class
	private ArrayList<Square> allParts; // this list saves all blocks which are
										// generated
	private ArrayList<Square> towerA; // here all blocks will be added which are
										// located at the first pole.
	private ArrayList<Square> towerB; // same here for the second block.
	private ArrayList<Square> towerC; // all blocks which are placed on the
										// third block.
	private int height;
	private int yPos;
	private int w;

	public Tower(int num) {
		amount = num;
		top = 80;
		fullHeight = 160;
		allParts = new ArrayList<Square>();
		shades = new String[8];
		shades[0] = "white";
		shades[1] = "fairGrey";
		shades[2] = "blue";
		shades[3] = "green";
		shades[4] = "yellow";
		shades[5] = "orange";
		shades[6] = "red";
		shades[7] = "lila";
		towerA = new ArrayList<Square>();
		towerB = new ArrayList<Square>();
		towerC = new ArrayList<Square>();
		height = 20;
		yPos = top + fullHeight;
		w = 20;

	}

	/**
	 * Creating the poles..
	 */
	public void createPoles() {
		Square poleA = new Square(6, fullHeight, 150, top, "black");
		Square poleB = new Square(6, fullHeight, 350, top, "black");
		Square poleC = new Square(6, fullHeight, 550, top, "black");
		poleA.makeVisible();
		poleB.makeVisible();
		poleC.makeVisible();
	}

	/**
	 * Creating the tower..
	 */
	public void createTower() {
		int size = 20;
		int xPos = 143;
		int colNum = 0;

		for (int i = 0; i < amount; i++) {
			allParts.add(new Square(size, height, xPos, yPos, shades[colNum]));
			allParts.get(i).makeVisible();
			size += 20;
			xPos -= 10;
			colNum++;
			for (int k = 0; k <= i; k++) {
				allParts.get(i - k).moveVertical(-height);
			}
		}
		towerA.addAll(allParts);
		Collections.reverse(towerA);
		timeout(w * 10);
	}

	/**
	 * action!
	 */
	public void runMove() {
		moveDisks("A", "B", "C", amount);
	}

	/**
	 * This is a recursive algorithm who will do all the work
	 * 
	 * @param from
	 *            start pole
	 * @param via
	 *            pole between
	 * @param to
	 *            destination pole
	 * @param n
	 *            number of blocks
	 */
	private void moveDisks(String from, String via, String to, int n) {
		if (n == 1) {
			/**
			 * ;
			 * 
			 */
			if (from.equals("A") && to.equals("C")) {
				int mV = yPos - height - towerA.get(towerA.size() - 1).getYPos() - towerC.size() * height;
				towerA.get(towerA.size() - 1).moveDiag(400, mV);
				towerC.add(towerA.get(towerA.size() - 1));
				towerA.remove(towerA.size() - 1);
				timeout(w);
			} else if (from.equals("A") && to.equals("B")) {
				int mV = yPos - height - towerA.get(towerA.size() - 1).getYPos() - towerB.size() * height;
				towerA.get(towerA.size() - 1).moveDiag(200, mV);
				towerB.add(towerA.get(towerA.size() - 1));
				towerA.remove(towerA.size() - 1);
				timeout(w);
			} else if (from.equals("B") && to.equals("C")) {
				int mV = yPos - height - towerB.get(towerB.size() - 1).getYPos() - towerC.size() * height;
				towerB.get(towerB.size() - 1).moveDiag(200, mV);
				towerC.add(towerB.get(towerB.size() - 1));
				towerB.remove(towerB.size() - 1);
				timeout(w);
			} else if (from.equals("B") && to.equals("A")) {
				int mV = yPos - height - towerB.get(towerB.size() - 1).getYPos() - towerA.size() * height;
				towerB.get(towerB.size() - 1).moveDiag(-200, mV);
				towerA.add(towerB.get(towerB.size() - 1));
				towerB.remove(towerB.size() - 1);
				timeout(w);
			} else if (from.equals("C") && to.equals("A")) {
				int mV = yPos - height - towerC.get(towerC.size() - 1).getYPos() - towerA.size() * height;
				towerC.get(towerC.size() - 1).moveDiag(-400, mV);
				towerA.add(towerC.get(towerC.size() - 1));
				towerC.remove(towerC.size() - 1);
				timeout(w);
			} else if (from.equals("C") && to.equals("B")) {
				int mV = yPos - height - towerC.get(towerC.size() - 1).getYPos() - towerB.size() * height;
				towerC.get(towerC.size() - 1).moveDiag(-200, mV);
				towerB.add(towerC.get(towerC.size() - 1));
				towerC.remove(towerC.size() - 1);
				timeout(w);
			}

		} else {
			moveDisks(from, to, via, n - 1); // A -> C -> B
			moveDisks(from, "", to, 1); // A -> C
			moveDisks(via, from, to, n - 1); // B -> A -> C
		}
	}

	/**
	 * method for short delays thus the animation is not too fast
	 * 
	 * @param wait
	 */
	private void timeout(int wait) {
		try {
			Thread.sleep(wait);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
