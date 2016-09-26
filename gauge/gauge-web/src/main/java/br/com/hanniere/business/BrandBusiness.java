package br.com.hanniere.business;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

/**
 * Classe que contem regras de negocio aplicado a entidade {@link Brand}
 * @author Hanniere
 *
 */
public class BrandBusiness implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String uri = "http://localhost:8080/gauge-service/api/brands";
	
	public List<Brand> loadBrands(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		String usersString = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		List<Brand> brandList = null;
		try {
			brandList = Arrays.asList(mapper.readValue(usersString, Brand[].class));
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
		return brandList;
	}
	
	/**
	 * Retorna uma brand passando seu id como parametro
	 * @param id
	 * @param brandList
	 * @return Brand - se existir, null se não existir
	 */
	public Brand getBrandById(Integer id, List<Brand> brandList){
	
		for (Brand brand : brandList) {
			if (brand.getId().intValue() == id.intValue()) {
				return brand;
			}
		}
		return null;
	}
	
	/**
	 * Classe utilizada para filtragem da lista de brand de acordo com o name.
	 * @param name
	 * @param brandList
	 * @return filteredList
	 */
	public Brand retrieveBrandByName(String name, List<Brand> brandList){
		
		for (Brand brand : brandList) {
			if(name.equals(brand.getName())){
				return brand;
			}
		}
		return null;
	}
}
