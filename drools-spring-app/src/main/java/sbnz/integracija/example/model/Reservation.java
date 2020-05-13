package sbnz.integracija.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Reservation {

	@EmbeddedId
	private ReservationId id = new ReservationId();
	
	@JsonManagedReference
    @ManyToOne
    @MapsId("user_id")
    private User user;

    @JsonManagedReference
    @ManyToOne
    @MapsId("accommodation_id")
    private Accommodation accommodation;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "discount")
	private Long discount;
	
	@Column(name = "total_price")
	private Long totalPrice;
	
	
	public ReservationId getId() {
		return id;
	}

	public void setId(ReservationId id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Embeddable
	public static class ReservationId implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long user_id;
		private Long accommodation_id;

		public ReservationId() {

		}

		public ReservationId(Long user_id, Long accommodation_id) {
			super();
			this.user_id = user_id;
			this.accommodation_id = accommodation_id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
			result = prime * result + ((accommodation_id == null) ? 0 : accommodation_id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ReservationId other = (ReservationId) obj;
			return Objects.equals(getAccommodation_id(), other.getAccommodation_id())
					&& Objects.equals(getUser_id(), other.getUser_id());
		}

		public Long getUser_id() {
			return user_id;
		}

		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}

		public Long getAccommodation_id() {
			return accommodation_id;
		}

		public void setAccommodation_id(Long accommodation_id) {
			this.accommodation_id = accommodation_id;
		}

	}
}
