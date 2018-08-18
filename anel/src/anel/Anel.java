package anel;

import java.util.ArrayList;
import java.util.Random;

public class Anel {

	private final int ADICIONA = 30000;
	private final int REQUISICAO = 25000;
	private final int INATIVO_COORDENADOR = 100000;
	private final int INATIVO_PROCESSO = 80000;
	
	
	private ArrayList<Processo> processosAtivos;
	private Processo coordenador;

	public Anel() {
		processosAtivos = new ArrayList<Processo>();
	}

	public void adiciona() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (processosAtivos.isEmpty()) {
						processosAtivos.add(new Processo(1, "Criado", true));
						coordenador = processosAtivos.get(0);
					} else {
						processosAtivos.add(
								new Processo(processosAtivos.get(processosAtivos.size() - 1).getPid() + 1, "Criação"));
					}
					try {
						Thread.sleep(ADICIONA);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void requisicao() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Processo processoRequisita = processosAtivos.get(new Random().nextInt(processosAtivos.size()));
				boolean teste = false;
				for (Processo p : processosAtivos) {
					if (p.isEhCoordenador())
						teste = true;
				}
				if (!teste) 
					eleicao(processoRequisita);	
				try{
					Thread.sleep(REQUISICAO);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void eleicao(Processo processoConvocador){
		
	}

}
