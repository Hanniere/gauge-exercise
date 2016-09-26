package br.com.hanniere.business;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
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
 * Classe que contem regras de negocio aplicado a entidade {@link User}
 * @author Hanniere
 *
 */
public class UserBusiness implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String uri = "http://localhost:8080/gauge-service/api/users";
	@Inject InteractionBusiness interactionBusiness;
	
	public List<User> loadUsers(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		String usersString = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		List<User> userList = null;
		try {
			userList = Arrays.asList(mapper.readValue(usersString, User[].class));
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
		return userList;
	}
	
	/**
	 * Retorna um usuario passando seu id como parametro
	 * @param id
	 * @param userList
	 * @return User - se existir, null se não existir
	 */
	public User getUserById(Integer id, List<User> userList){
	
		for (User user : userList) {
			if (user.getId().intValue() ==  id.intValue()) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Retorna uma lista de usuario passando um Brand como parametro
	 * @param id
	 * @param userList
	 * @return User - se existir, null se não existir
	 */
	public Set<User> retrieveUserListByBrandId(Integer brandId, List<Brand> brandList, List<Interaction> interactionList, List<User> userList){
		
		Set<User> userSet = new HashSet<User>();
		
		List<Interaction> interactionListFiltered = interactionBusiness.retrieveAllInteractionsByBrand(brandId, brandList, interactionList);
		
		for (Interaction interaction : interactionListFiltered) {
			userSet.add(getUserById(interaction.getUser(), userList));
		}
		
		return userSet;
	}
}
