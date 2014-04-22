package com.ideaiselectronics.catalogo.spring.domain.json;

import java.util.List;

public class ProductJSON {
	
	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private Integer weight;
	private Integer warranty;
	private String brand;
	private String model;
	private Boolean active;
	private Integer rank;
	private List<LinkJSON> links;
	private Integer count;
	
	/* atributos usados para vizualizacao nos jsp - nao fazem parte do json */
	private List<ItemJSON> items;
	private ItemJSON itemToDisplayOnShowcase;
	
	public ProductJSON() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public List<LinkJSON> getLinks() {
		return links;
	}

	public void setLinks(List<LinkJSON> links) {
		this.links = links;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ItemJSON> getItems() {
		return items;
	}

	public void setItems(List<ItemJSON> items) {
		this.items = items;
	}	
	
	public ItemJSON getItemToDisplayOnShowcase() {
		if(this.itemToDisplayOnShowcase == null){
			this.itemToDisplayOnShowcase = getItemWithLowerPriceFor();
		}
		return this.itemToDisplayOnShowcase;
	}
	
	public ItemJSON getItemWithLowerPriceFor() {
		ItemJSON itemCheaper = items.get(0);
		for (ItemJSON item : items) {
			if( itemCheaper.isPriceForGreaterThan(item.getPriceFor()) ) {
				itemCheaper = item;
			}
		}
		return itemCheaper;
	}
	
}
