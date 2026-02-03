package eus.fpsanturtzilh.pag.e2t2.model;

import java.util.Date;

public class Consumable extends Auditable{
	private Long id;
	private String name;
	private String description;
	private String batch;
	private String brand;
	private int stock;
	private int min_stock;
	private Date expiration_date;
}
