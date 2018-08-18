package anel;

public class Processo {
	
	private int pid;
	private String msg;
	private boolean ehCoordenador;
	
	public Processo(int pid, String msg){
		setPid(pid);
		setMsg(msg);
	}
	public Processo(int pid, String msg, boolean ehCoordenador){
		setPid(pid);
		setMsg(msg);
		setEhCoordenador(true);
	}
	
	public boolean isEhCoordenador() {
		return ehCoordenador;
	}

	public void setEhCoordenador(boolean ehCoordenador) {
		this.ehCoordenador = ehCoordenador;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
