package com.example.WebBanVe.entity;

import java.util.List;

import com.example.WebBanVe.entity.Account.eAccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transport")
@Data
public class Transport 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="manufacture_id")
	private Manufacture manufacture;
	
	@Column(name = "seating_capacity")
	@Min(1)
	private int seatingcapacity;
	
	@Enumerated(EnumType.ORDINAL)
    private ETransportType type;
	
	@Column(name = "status")
	private eTransportStatus status;
	
	  @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL, orphanRemoval=true)
	  private List<Ticket> ticket;

	public enum eTransportStatus {
		STOPWORKING, MAINTENANCE, PREPARETODEPART, MOVING
	}

	public Transport() 
	{
		super();
	}

	public Transport(Long id, String name, Manufacture manufacture, int seatingcapacity, ETransportType type,
			eTransportStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.manufacture = manufacture;
		this.seatingcapacity = seatingcapacity;
		this.type = type;
		this.status = status;
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

	public Manufacture getManufacture() {
		return manufacture;
	}

	public void setManufacture(Manufacture manufacture) {
		this.manufacture = manufacture;
	}

	public int getSeatingcapacity() {
		return seatingcapacity;
	}

	public void setSeatingcapacity(int seatingcapacity) {
		this.seatingcapacity = seatingcapacity;
	}

	public ETransportType getType() {
		return type;
	}

	public void setType(ETransportType type) {
		this.type = type;
	}

	public eTransportStatus getStatus() {
		return status;
	}

	public void setStatus(eTransportStatus status) {
		this.status = status;
	}
}