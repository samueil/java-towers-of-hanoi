package ch.hslu.prg1.allg.hanoi;

public class Main {

	public static void main(String[] args) {

		Tower turm = new Tower(4);
		turm.createPoles();
		turm.createTower();
		turm.runMove();

		

	}

}
