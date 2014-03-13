package com.ideaiseletronics.catalogo.spring.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="ITEM")
public class Item {
	@Id
	@SequenceGenerator(name="item_id", sequenceName="item_id")
	@GeneratedValue(generator="item_id", strategy=GenerationType.AUTO)
	@Column(name="CD_ITEM")
	private Long id;
	
	@Column(name="NR_SKU")
	private Long sku;
	
	@Column(name="NR_PRECO_DE", nullable=false)
	private BigDecimal priceFrom;
	
	@Column(name="NR_PRECO_POR", nullable=false)
	private BigDecimal priceFor;
	
	@Column(name="NM_NOME_OPCAO")
	private String optionName;
	
	@Column(name="NM_VALOR_OPCAO")
	private String optionValue;
	
	@Column(name="NR_ESTOQUE", nullable=false)
	private Integer stock;
	
	@Column(name="BO_ATIVO", nullable=false)
	private Boolean active;
	
	@Transient
	private int discount;
	
	@Transient
	private LinkedHashMap<Integer, BigDecimal> installments;
	
	@Transient
	private String formatedPriceFrom;

	@Transient
	private String formatedPriceFor;

	
	@ManyToOne
	@JoinColumn(name="CD_PRODUCT", referencedColumnName="CD_PRODUCT", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Product product;
	
	@OneToMany(mappedBy="item", fetch = FetchType.EAGER)
	@Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	private List<Image> images;

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public BigDecimal getPriceFor() {
		return priceFor;
	}

	public void setPriceFor(BigDecimal priceFor) {
		this.priceFor = priceFor;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public int getDiscount() {
		discount = calculateDescount(getPriceFrom(), getPriceFor());
		return discount;
	}
	
	public LinkedHashMap<Integer, String> getInstallments() {
		return calculateInstallments(priceFor);
	}
	
	/* Preços em String formatados com . e , exemplo-> 2.999,00 */
	public String getFormatedPriceFrom() {
	    return valueFormater(priceFrom);
	}

	public String getFormatedPriceFor() {
		return valueFormater(priceFor);
	}
	
	public int calculateDescount(BigDecimal priceFrom, BigDecimal priceFor) {
		double porcentagem = (((Double.valueOf(priceFor.doubleValue()) / Double.valueOf(priceFrom.doubleValue())) - 1) * 100) * -1;
		porcentagem = Double.valueOf(String.format(Locale.US, "%.0f", Math.floor(porcentagem)));
		return (int) porcentagem;
	}
	
	public LinkedHashMap<Integer, String> calculateInstallments(BigDecimal priceFor) {
		int parcela = 1;
		LinkedHashMap<Integer, String> parcelas = new LinkedHashMap<Integer, String>();
		double value = Double.valueOf(priceFor.doubleValue()) / parcela;
		
		do{
			parcelas.put(parcela, valueFormater(new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN)));
			parcela++;
			value = Double.valueOf(priceFor.doubleValue()) / parcela;
		} while(parcela <= 12 && value >= 10.00);
		
		return parcelas;
	}
	
		
	public String valueFormater(BigDecimal value) {
	    Locale Local = new Locale("pt", "BR");
	    DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Local));
	    return df.format(value);
	}

}