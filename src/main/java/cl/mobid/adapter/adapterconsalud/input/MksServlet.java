package cl.mobid.adapter.adapterconsalud.input;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.mobid.adapter.adapterconsalud.util.Parse;
import cl.mobid.adapterenterprise.common.dto.sendmt.normal.request.RequestEntMtType;
import cl.mobid.adapterenterprise.common.dto.sendmt.normal.response.ResonseEntMtType;
import cl.mobid.adapterenterprise.common.util.CommonParse;
import cl.mobid.adapter.adapterconsalud.service.ClientEnterpriseService;

public class MksServlet extends HttpServlet {

	private String USERNAME;
	private String PASSWORD;
	private String URL;
	private String TOKEN;

	public MksServlet(String user, String pass, String url, String token) {
		this.USERNAME = user;
		this.PASSWORD = pass;
		this.URL = url;
		this.TOKEN = token;
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response = processRequest(request, response);
	}

	private HttpServletResponse processRequest(HttpServletRequest httpReq, HttpServletResponse httpResp)
			throws IOException {
		RequestEntMtType request = null;
		ResonseEntMtType response = null;
		final PrintWriter writer = httpResp.getWriter();
		try {
			String celular = httpReq.getParameter("celular");
			String mensaje = httpReq.getParameter("mensaje");
			String idTrx = httpReq.getParameter("idTrx");
			httpResp.setContentType("text/html");
			request = Parse.obtenerDtoWsEnterprise(httpReq.getInputStream(), this.TOKEN, celular, mensaje, idTrx);
			response = llamarServicio(request);
			String resp = Parse.readResponseEnt(response);
			writer.print(resp);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResonseEntMtType llamarServicio(RequestEntMtType request) throws IOException {
		ResonseEntMtType response = null;
		try {
			System.out.println("requestData: " + CommonParse.ObjectToJson(request));
			ClientEnterpriseService cliente = new ClientEnterpriseService(this.URL, this.USERNAME, this.PASSWORD);
			response = cliente.callNormalService(request);
			System.out.println("responseData: " + CommonParse.ObjectToJson(response));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}