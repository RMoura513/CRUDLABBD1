package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Motorista;
import model.Onibus;
import model.Viagem;
import persistence.GenericDao;
import persistence.MotoristaDao;
import persistence.OnibusDao;
import persistence.ViagemDao;


@WebServlet("/viagem")
public class ControleViagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ControleViagem() {
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String erro = "";
		List<Onibus> onibuss = new ArrayList<Onibus>();
		List<Motorista> motoristas = new ArrayList<Motorista>();
		
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		MotoristaDao mDao = new MotoristaDao(gDao);
		
		try {
			onibuss = oDao.listar();
			motoristas = mDao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			request.setAttribute("onibuss", onibuss);
			request.setAttribute("motoristas", motoristas);
			
			RequestDispatcher rd = request.getRequestDispatcher("viagem.jsp");
			rd.forward(request, response);
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//entrada
		String cmd = request.getParameter("botao");
		String codigo = request.getParameter("codigo");
		String onibusPlaca = request.getParameter("onibusPlaca");
		String motoristaCodigo = request.getParameter("motoristaCodigo");
		String horaSaida = request.getParameter("horaSaida");
		String horaChegada = request.getParameter("horaChegada");
		String partida = request.getParameter("horaChegada");
		String destino = request.getParameter("partida");
		
		//retorno
		String saida = "";
		String erro = "";
		Viagem v = new Viagem();
		List<Viagem> viagens = new ArrayList<>();
		List<Onibus> onibuss = new ArrayList<>();
		List<Motorista> motoristas = new ArrayList<>();
		
		if (!cmd.contains("Listar")){
			v.setCodigo(Integer.parseInt(codigo));
		}
		try {
			
			motoristas = listarMotoristas();
			onibuss = listarOnibuss();
			
		if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			
			Onibus o = new Onibus();
			Motorista m = new Motorista();
			
			o.setPlaca(onibusPlaca);
			o = buscarOnibus(o);
//			v.setOnibusPlaca(o);
			m.setCodigo(Integer.parseInt(motoristaCodigo));
			m = buscarMotorista(m);
			v.setHoraSaida(Integer.parseInt(horaSaida));
			v.setHoraChegada(Integer.parseInt(horaChegada));
			v.setPartida(partida);
			v.setDestino(destino);

		}
			
			if (cmd.contains("Cadastrar")) {
				cadastrarViagem(v);
				saida = "Viagem cadastrada com sucesso";
				v = null;
			}
			if (cmd.contains("Alterar")) {
				alterarViagem(v);
				saida = "Viagem alterada com sucesso";
				v = null;
			}
			if (cmd.contains("Excluir")) {
				excluirViagem(v);
				saida = "Viagem excluida com sucesso";
				v = null;
			}
			if (cmd.contains("Buscar")) {
				v = buscarViagem(v);
			}
			if (cmd.contains("Listar")) {
				viagens = listarViagens();
			}
			if (cmd.contains("Listar Onibus")) {
				viagens = listarDescricaoOnibus();
			}
			
		}catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		}finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("viagem", v);
			request.setAttribute("viagens", viagens);
			request.setAttribute("onibuss", onibuss);
			request.setAttribute("motoristas", motoristas);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("viagem.jsp");
			rd.forward(request, response);
			
		}
		
	}
	
	private Motorista buscarMotorista(Motorista m) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		m = mDao.buscar(m);
		return m;
	}
	
	private Onibus buscarOnibus(Onibus o) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		oDao.buscar(o);
		return o;
	}

	private List<Onibus> listarOnibuss() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		List<Onibus> onibuss = oDao.listar();
		return onibuss;
	}


	private List<Motorista> listarMotoristas() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		List<Motorista> motoristas = mDao.listar();
		return motoristas;
	}


	private void cadastrarViagem(Viagem v) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.inserir(v);
		
	}

	private void alterarViagem(Viagem v) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.atualizar(v);
		
	}

	private void excluirViagem(Viagem v) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.excluir(v);
		
	}

	private Viagem buscarViagem(Viagem v) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.buscar(v);
		return v;
	}

	private List<Viagem> listarViagens() throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		List<Viagem> viagens = vDao.listar();
		return viagens;
		
	}
	
	private List<Viagem> listarDescricaoOnibus() throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		List<Viagem> viagens = vDao.listarDescricaoOnibus();
		return viagens;
	}
	

	
}
