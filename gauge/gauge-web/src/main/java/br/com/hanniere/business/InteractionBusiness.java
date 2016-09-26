package br.com.hanniere.business;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hanniere.entity.Brand;
import br.com.hanniere.entity.Interaction;
import br.com.hanniere.entity.User;

/**
 * Classe que contem regras de negocio aplicado a entidade {@link Interaction}
 * @author Hanniere
 *
 */
public class InteractionBusiness implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String uri = "http://localhost:8080/gauge-service/api/interactions";
	
	/**
	 * Metodo que obtem via webservice uma lista de interacoes entre usuarios e marcas
	 * @return List<Interaction> - Lista de interacoes dos usuarios
	 */
	public List<Interaction> loadInteractions(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		String usersString = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		List<Interaction> interactionList = null;
		try {
			interactionList = Arrays.asList(mapper.readValue(usersString, Interaction[].class));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return interactionList;
	}
	
	/**
	 * Metodo que lista as interacoes utilizando um User como parametro
	 * @param userId
	 * @param userList
	 * @param interactionList
	 * @return List<Interaction> - lista de interaction de um usuario
	 */
	public List<Interaction> retrieveAllInteractionsByUser(Integer userId, List<User> userList, List<Interaction> interactionList){
		List<Interaction> filteredList = new ArrayList<Interaction>();
		
		for (Interaction interaction : filteredList) {
			if(interaction.getUser().intValue() == userId.intValue())
				filteredList.add(interaction);
		}
		
		return filteredList;
	}
	
	/**
	 * Metodo que lista as interacoes utilizando um {@link Brand} como parametro
	 * @param brandId
	 * @param brandList
	 * @param interactionList
	 * @return List<Interaction> - lista de interaction feitas com uma marca
	 */
	public List<Interaction> retrieveAllInteractionsByBrand(Integer brandId, List<Brand> brandList, List<Interaction> interactionList){
		List<Interaction> filteredList = new ArrayList<Interaction>();
		
		for (Interaction interaction : interactionList) {
			if(interaction.getBrand().equals(brandId))
				filteredList.add(interaction);
		}
		
		return filteredList;
	}
}
