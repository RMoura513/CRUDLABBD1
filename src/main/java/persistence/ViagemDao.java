package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Onibus;
import model.Viagem;

public class ViagemDao implements ICrud<Viagem> {
	
	private GenericDao gDao;
	
	public ViagemDao(GenericDao gDao) {
		this.gDao = gDao;
		
	}

	@Override
	public void inserir(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO viagem VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ps.setString(2, v.getOnibusPlaca());
		ps.setInt(3, v.getMotoristaCodigo());
		ps.setInt(4, v.getHoraSaida());
		ps.setInt(5, v.getHoraChegada());
		ps.setString(6, v.getPartida());
		ps.setString(7, v.getDestino());
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public void atualizar(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE viagem SET onibusPlaca = ?, motoristaCodigo = ?, horaSaida = ?, horaChegada = ?, partida = ?,"
				   + " destino = ? WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(7, v.getCodigo());
		ps.setString(1, v.getOnibusPlaca());
		ps.setInt(2, v.getMotoristaCodigo());
		ps.setInt(3, v.getHoraSaida());
		ps.setInt(4, v.getHoraChegada());
		ps.setString(5, v.getPartida());
		ps.setString(6, v.getDestino());
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public void excluir(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE viagem WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public Viagem buscar(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, onibusPlaca, motoristaCodigo, horaSaida, horaChegada, partida, destino "
				   + "FROM viagem WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			v.setCodigo(rs.getInt("codigo"));
			v.setOnibusPlaca(rs.getString("onibusPlaca"));
			v.setMotoristaCodigo(rs.getInt("motoristaCodigo"));
			v.setHoraSaida(rs.getInt("horaSaida"));
			v.setHoraChegada(rs.getInt("horaChegada"));
			v.setPartida(rs.getString("partida"));
			v.setDestino(rs.getString("destino"));
		}
		rs.close();
		ps.close();
		c.close();
		return v;
	}

	@Override
	public List<Viagem> listar() throws SQLException, ClassNotFoundException {
		List<Viagem> viagens = new ArrayList<Viagem>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, onibusPlaca, motoristaCodigo, horaSaida, horaChegada, partida, destino FROM viagem";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Viagem v = new Viagem();
			v.setCodigo(rs.getInt("codigo"));
			v.setOnibusPlaca(rs.getString("onibusPlaca"));
			v.setMotoristaCodigo(rs.getInt("motoristaCodigo"));
			v.setHoraSaida(rs.getInt("horaSaida"));
			v.setHoraChegada(rs.getInt("horaChegada"));
			v.setPartida(rs.getString("partida"));
			v.setDestino(rs.getString("destino"));
			
			viagens.add(v);
		}
		rs.close();
		ps.close();
		c.close();
		return viagens;
	}
	
	public List<Viagem> listarDescricaoOnibus() throws SQLException, ClassNotFoundException {
		List<Viagem> viagens = new ArrayList<Viagem>();
		Connection c = gDao.getConnection();
		String sql = "SELECT CONCAT(SUBSTRING(oni.placa, 1, 3), '-', SUBSTRING(oni.placa, 4, 4)) AS Placa, oni.marca AS MarcaOnibus,\r\n"
				+ "	oni.ano AS AnoOnibus, oni.descricao AS DescricaoOnibus FROM viagem vi\r\n"
				+ "JOIN motorista mo ON vi.motoristaCodigo = mo.codigo\r\n"
				+ "	JOIN onibus oni ON vi.onibusPlaca = oni.placa";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Viagem v = new Viagem();
			Onibus o = new Onibus();
			v.setOnibusPlaca(rs.getString("Placa"));
			o.setMarca(rs.getString("MarcaOnibus"));
			o.setAno(rs.getInt("AnoOnibus"));
			o.setDescricao(rs.getString("DescricaoOnibus"));
			
			viagens.add(v);
			
		}
		rs.close();
		ps.close();
		c.close();
		return viagens;
	}
	
	public List<Viagem> listarDescricaoViagem() throws SQLException, ClassNotFoundException {
		List<Viagem> viagens = new ArrayList<Viagem>();
		Connection c = gDao.getConnection();
		String sql = "SELECT vi.codigo AS CodigoViagem, CONCAT(SUBSTRING(oni.placa, 1, 3), '-', SUBSTRING(oni.placa, 4, 3)) AS Placa,\r\n"
				+ "RIGHT('0' + CAST(vi.horaSaida AS varchar(2)), 2) + ':00' AS horaSaida, RIGHT('0' + CAST(vi.horaChegada AS varchar(2)), 2) + ':00' AS horaChegada,\r\n"
				+ "vi.partida AS Partida, vi.destino AS Destino FROM viagem vi\r\n"
				+ "JOIN motorista mo ON vi.motoristaCodigo = mo.codigo\r\n"
				+ "	JOIN onibus oni ON vi.onibusPlaca = oni.placa";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Viagem v = new Viagem();
			Onibus o = new Onibus();
			v.setCodigo(rs.getInt("CodigoViagem"));
			o.setPlaca(rs.getString("Placa"));
			v.setHoraSaida(rs.getInt("horaSaida"));
			v.setHoraChegada(rs.getInt("horaChegada"));
			v.setPartida(rs.getString("Partida"));
			v.setDestino(rs.getString("Destino"));
			
			viagens.add(v);
					
		}
		rs.close();
		ps.close();
		c.close();
		return viagens;
	}
	

}
