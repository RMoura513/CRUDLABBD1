package model;

public class Viagem {
	
	private int codigo;
	private String onibusPlaca;
	private int motoristaCodigo;
	private int horaSaida;
	private int horaChegada;
	private String partida;
	private String destino;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getOnibusPlaca() {
		return onibusPlaca;
	}
	public void setOnibusPlaca(String onibusPlaca) {
		this.onibusPlaca = onibusPlaca;
	}
	public int getMotoristaCodigo() {
		return motoristaCodigo;
	}
	public void setMotoristaCodigo(int motoristaCodigo) {
		this.motoristaCodigo = motoristaCodigo;
	}
	public int getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(int horaSaida) {
		this.horaSaida = horaSaida;
	}
	public int getHoraChegada() {
		return horaChegada;
	}
	public void setHoraChegada(int horaChegada) {
		this.horaChegada = horaChegada;
	}
	public String getPartida() {
		return partida;
	}
	public void setPartida(String partida) {
		this.partida = partida;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	@Override
	public String toString() {
		return "Viagem [codigo=" + codigo + ", onibusPlaca=" + onibusPlaca + ", motoristaCodigo=" + motoristaCodigo
				+ ", horaSaida=" + horaSaida + ", horaChegada=" + horaChegada + ", partida=" + partida + ", destino="
				+ destino + "]";
	}
	
	
	

}
