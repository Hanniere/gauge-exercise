package br.com.hanniere.entity;


public class Brand {
	private Integer id;
	private String name;
	private String image;
	private Integer numeroIteracoes;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

	public Integer getNumeroIteracoes() {
		return numeroIteracoes;
	}
	
	public void setNumeroIteracoes(Integer numeroIteracoes) {
		this.numeroIteracoes = numeroIteracoes;
	}
	
}
