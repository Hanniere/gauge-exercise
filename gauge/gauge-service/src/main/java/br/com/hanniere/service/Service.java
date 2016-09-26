package br.com.hanniere.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.hanniere.util.Util;

/**
 * Esse servico foi implementado para simular um WebService que sera consumido pelo sistema "gauge-web". Este servico
 * tem como fonte os arquivos Json disponibilizados
 * @author Hanniere
 *
 */
@Path("/")
public class Service {
	
	private @Inject Util util;
	
	@GET
	@Produces("application/json")
	@Path("brands")
	public String brands(){
		return util.readJsonFile("brands").toString();
	}
	
	@GET
	@Produces("application/json") 
	@Path("interactions")
	public String interactions(){
		return util.readJsonFile("interactions").toString();
	}
	
	@GET
	@Produces("application/json")
	@Path("users")
	public String users(){
		return util.readJsonFile("users").toString();
	}
	
}
