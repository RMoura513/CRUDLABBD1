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
import persistence.GenericDao;
import persistence.MotoristaDao;


@WebServlet("/motorista")
public class ControleMotorista extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ControleMotorista() {
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("motorista.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//entrada
		String cmd = request.getParameter("botao");
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String naturalidade = request.getParameter("naturalidade");
		
		//retorno
		String saida = "";
		String erro = "";
		Motorista m = new Motorista();
		List<Motorista> motoristas = new ArrayList<>();
		
		if (!cmd.contains("Listar")){
			m.setCodigo(Integer.parseInt(codigo));
		}
		if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			m.setNome(nome);
			m.setNaturalidade(naturalidade);
		}
		try {
			if (cmd.contains("Cadastrar")) {
				cadastrarMotorista(m);
				saida = "Motorista cadastrado com sucesso";
				m = null;
			}
			if (cmd.contains("Alterar")) {
				alterarMotorista(m);
				saida = "Motorista alterado com sucesso";
				m = null;
			}
			if (cmd.contains("Excluir")) {
				excluirMotorista(m);
				saida = "Motorista excluido com sucesso";
				m = null;
			}
			if (cmd.contains("Buscar")) {
				m = buscarMotorista(m);
			}
			if (cmd.contains("Listar")) {
				motoristas = listarMotoristas();
			}
			
		}catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		}finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("motorista", m);
			request.setAttribute("motoristas", motoristas);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("motorista.jsp");
			rd.forward(request, response);
			
		}
		
	}

	private void cadastrarMotorista(Motorista m) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		mDao.inserir(m);
		
	}

	private void alterarMotorista(Motorista m) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		mDao.atualizar(m);
		
	}

	private void excluirMotorista(Motorista m) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		mDao.excluir(m);
		
	}

	private Motorista buscarMotorista(Motorista m) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		m = mDao.buscar(m);
		return m;
	}

	private List<Motorista> listarMotoristas() throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		List<Motorista> motoristas = mDao.listar();
		return motoristas;
		}

}
