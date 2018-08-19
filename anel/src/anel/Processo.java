package anel;

import java.util.LinkedList;

public class Processo {
	
	private int pid;
	private String msg;
	private boolean ehCoordenador;
	
	public Processo (int pid, String msg){
		setPid(pid);
		setMsg(msg);
	}
	
	public Processo (int pid, String msg, boolean ehCoordenador){
		setPid(pid);
		setMsg(msg);
		setEhCoordenador(true);
	}
	
	public boolean isEhCoordenador () {
		return ehCoordenador;
	}

	public void setEhCoordenador (boolean ehCoordenador) {
		this.ehCoordenador = ehCoordenador;
	}

	public int getPid () {
		return pid;
	}
	
	public void setPid (int pid) {
		this.pid = pid;
	}
	
	public String getMsg () {
		return msg;
	}
	
	public void setMsg (String msg) {
		this.msg = msg;
	}
	
	public boolean enviarRequisicao () {
		boolean resultadoRequisicao = false;
		for (Processo p : Anel.processosAtivos) {
			if (p.isEhCoordenador())
				resultadoRequisicao = p.receberRequisicao(this.pid);
		}
		
		// Se nao existe um coordenador.
		if (!resultadoRequisicao)
			this.realizarEleicao();
		
		return resultadoRequisicao;
	}
	
	private boolean receberRequisicao (int pidOrigemRequisicao) {
		
		/* TRATAMENTO DA REQUISICAO AQUI... */
		
		System.out.println("Requisicao do processo " + pidOrigemRequisicao + " recebida com sucesso.");
		return true;
	}
	
	private void realizarEleicao () {
		
		// Primeiro consulta cada processo, adicionando o id de cada um em uma nova lista (etapa 2 do algoritmo).
		
		LinkedList<Integer> idProcessosConsultados = new LinkedList<>();
		for (Processo p : Anel.processosAtivos)
			p.consultarProcesso(idProcessosConsultados);
		
		// Depois percorre a lista de id's procurando pelo maior.
		
		int idNovoCoordenador = this.getPid();
		for (Integer id : idProcessosConsultados) {
			if (id > idNovoCoordenador)
				idNovoCoordenador = id;
		}
		
		// E entao atualiza o novo coordenador.
		
		boolean resultadoAtualizacao = false;
		resultadoAtualizacao = atualizarCoordenador(idNovoCoordenador);
		
		if (resultadoAtualizacao)
			System.out.println("Eleicao concluida com sucesso. O novo coordenador eh " + idNovoCoordenador + ".");
		else
			System.out.println("A eleicao falhou. Nao foi encontrado um novo coordenador.");
	}
	
	private void consultarProcesso (LinkedList<Integer> processosConsultados) {
		processosConsultados.add(this.getPid());
	}
	
	private boolean atualizarCoordenador (int idNovoCoordenador) {
		// Garante que nao exista nenhum outro processo cadastrado como coordenador.
		for (Processo p : Anel.processosAtivos) {
			if (p.isEhCoordenador())
				p.setEhCoordenador(false);
		}
		
		//Define o novo coordenador.
		for (Processo p : Anel.processosAtivos) {
			if (p.getPid() == idNovoCoordenador) {
				p.setEhCoordenador(false);
				return true;
			}
		}
		
		return false;
	}
}