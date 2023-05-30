package janta;


public class Mesa implements Runnable {

	//Cria os garfos
	public static boolean [] garfos = new boolean[5];

	//inicia o semaforo principal
	public static Semaforo mutex = new Semaforo(1);

	//Seta os garfos como true
	public static void setarGarfoTrue(boolean[]garfos) {
		for (int i = 0; i < garfos.length; i++) {
			garfos[i]=true;
		}
	}

	//vetor de filosofos
	static Filosofo []filosofos = new Filosofo[5];

	//função de inicialização
	public void init(){
		setarGarfoTrue(garfos);
		//Cria os filosofos
		filosofos[0]=new Filosofo("Felipe",4,0);
		filosofos[1]=new Filosofo("Fabiano",0,1);
		filosofos[2]=new Filosofo("Alex Jordan",1,2);
		filosofos[3]=new Filosofo("Lucas",2,3);
		filosofos[4]=new Filosofo("Ricardo",3,4);
		//startar filosofos
		filosofos[0].start();
		filosofos[1].start();
		filosofos[2].start();
		filosofos[3].start();
		filosofos[4].start();
		
		
	}

	@Override
	public void run() {
		while(true) {}
	}
}
