package janta;

import java.util.Random;

public class Filosofo extends Thread{
	String nome;
	int garfoDireita;
	int garfoEsquerda;
	int tempoEspera=0;
	boolean garfoEsquerdoMao=false;
	boolean garfoDireitoMao=false;
	Random rd = new Random();
	int tentativas=0;
	int vezesQueComeu=0;
	int vezesQueFaltouGarfo=0;
	int vezesQuePensou=0;
	int vezesQueTentouPegarGarfo=0;
	
	
	//Contrutor do filósofo. É feito com nome, número do garfo a direita e número do garfo a esquerda
	public Filosofo(String nome, int garfoDireita, int garfoEsquerda) {
		super();
		this.nome = nome;
		this.garfoDireita = garfoDireita;
		this.garfoEsquerda = garfoEsquerda;
	}
	//Coloca o filosofo para pensar
	public void pensar()  {
		tempoEspera=rd.nextInt(100,1500);
		System.out.println("O filosofo "+this.nome+" vai pensar\n");
		//Adiciona +1 a vezes que pensou
		vezesQuePensou++;
		try {
			Thread.sleep(tempoEspera);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Filosofo tenta pegar o garfo
	public boolean pegarGarfo() {
		//acessa a area de exclusão mutua
		Mesa.mutex.decrementar();
		//Adicona +1 as vezes que tentou pegar o garfo
		vezesQueTentouPegarGarfo++;
		//verifica a disponibilidade do garfo e coloca ele na mao
		if(Mesa.garfos[garfoDireita]) {
			Mesa.garfos[garfoDireita]=false;
			garfoDireitoMao=true;
		}
		if(Mesa.garfos[garfoEsquerda]) {
			Mesa.garfos[garfoEsquerda]=false;
			garfoEsquerdoMao=true;
		}
		Mesa.mutex.incrementar();
		if (garfoDireitoMao&&garfoEsquerdoMao) {
			return true;
		}else {
			return false;
		}
	}
	
	public void tentarComer()  {
		//Verifica se o filosofo tem os dois garfo na mão. 
		//Se tiver ele come por um numero aleátorio de tempo, senão ele n come
		if(garfoDireitoMao&&garfoEsquerdoMao) {
			tempoEspera=rd.nextInt(100,1500);
			try {
				Thread.sleep(tempoEspera);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Filosofo "+this.nome+" acabou de comer\n");
			//Aumenta em +1 o número que vezes que comeu
			vezesQueComeu++;
		}else {
			//Diz que o filósofo não comeu e mostra como false o garfo que faltou
			System.out.println("Filosofo "+this.nome+" não comeu\ngarfo direito = "+garfoDireitoMao+"\ngarfo esquerdo = "+garfoEsquerdoMao+"\n");
			//Aumenta o número de vezes que faltou garfo
			vezesQueFaltouGarfo++;
		}
	}
	
	public void devolverGarfos() {
		//acessa area de exclusao mutua
		Mesa.mutex.decrementar();
		//caso esteja com o garfo na mão, devolve
		if(garfoDireitoMao) {
			Mesa.garfos[garfoDireita]=true;
			garfoDireitoMao=false;
		}
		if(garfoEsquerdoMao) {
			Mesa.garfos[garfoEsquerda]=true;
			garfoEsquerdoMao=false;
		}
		Mesa.mutex.incrementar();
	}
	
	public void dados() {
		//Função para realizar a impressão dos dados
		System.out.println(this.nome+" comeu "+this.vezesQueComeu+" vezes");
		System.out.println("faltou garfo "+this.vezesQueFaltouGarfo+" vezes");
		System.out.println("pensou "+this.vezesQuePensou+" vezes");
		System.out.println("tentou pegar os garfos "+this.vezesQueTentouPegarGarfo+" vezes\n");
	}
	
	@Override
	public void run() {
		do {
			//pensa
			pensar();
			//Define o número máximo que o filósofo vai tentar pegar o garfos
			tentativas=rd.nextInt(1,5);
			for (int i = 0; i < tentativas; i++) {
				if(pegarGarfo()) {
					break;
				}
			}
			tentarComer();
			devolverGarfos();
			dados();
			 
		}while(true);

	}
}
