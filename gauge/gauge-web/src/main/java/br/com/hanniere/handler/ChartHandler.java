package br.com.hanniere.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.hanniere.business.BrandBusiness;
import br.com.hanniere.business.InteractionBusiness;
import br.com.hanniere.business.UserBusiness;
import br.com.hanniere.entity.Brand;
import br.com.hanniere.entity.Interaction;
import br.com.hanniere.entity.User;
import br.com.hanniere.util.BrandComparator;

/**
 * Handler da tela do sistema
 * @author Hanniere
 *
 */
@Named
@ViewScoped
public class ChartHandler implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarChartModel barModel;
    
    private @Inject UserBusiness userBusiness;
    private @Inject BrandBusiness brandBusiness;
    private @Inject InteractionBusiness interactionBusiness;
    
    private List<User> userList;
    private List<Interaction> interactionList;
    private List<Brand> brandList;
    
    private String[] selectedBrands;
    private List<String> availableBrands;
    private List<Brand> filteredBrandList;
  
    
     
    @PostConstruct
    public void init() {
    	userList = userBusiness.loadUsers();
    	interactionList = interactionBusiness.loadInteractions();
    	brandList = brandBusiness.loadBrands();
    	generateNumIteracoesBrand();
    	generateAvailableBrandsCombo();
    	createBarModel();
    	
    }
    
    /**
     * Este metodo obtem o numero de iteractions por marca
     */
    public void generateNumIteracoesBrand(){
    	filteredBrandList = new ArrayList<Brand>();
    	
		for (Brand brand : brandList) {
			List<Interaction> interactionByBrand = interactionBusiness.retrieveAllInteractionsByBrand(brand.getId(), brandList, interactionList);
			brand.setNumeroIteracoes(interactionByBrand.size());
			filteredBrandList.add(brand);
		}
    }
    
    /**
     * Retorna os usuarios presentes nas brands selecionadas.
     * @return
     */
    public Set<User> retrieveFilteredUserInteractionSet(){
    	Set<User> userSet = new HashSet<User>();
    	
    	for (Brand brand : filteredBrandList) {
    		userSet.addAll(userBusiness.retrieveUserListByBrandId(brand.getId(), brandList, interactionList, userList));
		}
    	
    	return userSet;
    }
    
    /**
     * Metodo resposavel pela construcao do combobox de filtragem das marcas
     */
    public void  generateAvailableBrandsCombo(){
        availableBrands = new ArrayList<String>();
    	selectedBrands = new String[brandList.size()];
        
    	for (int i = 0; i < brandList.size(); i++) {
        	selectedBrands[i] = brandList.get(i).getName();
			availableBrands.add(brandList.get(i).getName());
		}
    }
 
    /**
     * Metodo responsavel por filtrar as marcas selecionadas
     */
    public void brandFiler(){
    	filteredBrandList.clear();
    	for (String name : selectedBrands) {
    		filteredBrandList.add(brandBusiness.retrieveBrandByName(name, brandList));
		}
    	Collections.sort(filteredBrandList, new BrandComparator());
    	createBarModel();
    	
    }
    
    private BarChartModel generateBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries Interactions = new ChartSeries();
        Interactions.setLabel("Users");
        
        for (Brand brand : filteredBrandList) {
        	 Interactions.set(brand.getName(), brand.getNumeroIteracoes());
		}
        model.addSeries(Interactions);
        return model;
    }
    
    private void createBarModel() {
        barModel = generateBarModel();
         
        barModel.setTitle("Gauge Exercise");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Brands");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Num Interactions");
        yAxis.setMin(0);
        yAxis.setMax(40);
    }
    
    //getters and setters
    public BarChartModel getBarModel() {
		return barModel;
	}
    
    public List<Brand> getBrands(){
    	return filteredBrandList;
    }
    
    public String[] getSelectedBrands() {
		return selectedBrands;
	}
    
    public void setSelectedBrands(String[] selectedBrands) {
		this.selectedBrands = selectedBrands;
	}
    
    public List<String> getAvailableBrands() {
		return availableBrands;
	}
    

}
