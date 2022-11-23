package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;

public class PowerOutages {

	private int id;	
	private Nerc nerc;
	
	private LocalDateTime date_event_began;
	private LocalDateTime date_event_finished;
	
	private int customers_affected;
	
	private long outageDuration;
	private int year;
	
	
	public PowerOutages(int id, Nerc nerc, LocalDateTime date_event_began, LocalDateTime date_event_finished, int customers_affected) {
		this.id = id;
		this.nerc=nerc;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.customers_affected = customers_affected;
		
		LocalDateTime tempDateTime= LocalDateTime.from((TemporalAccessor) date_event_began);
		this.outageDuration=tempDateTime.until((Temporal) date_event_finished, ChronoUnit.HOURS);
		this.year=date_event_began.getYear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getCustomers_affected() {
		return customers_affected;
	}

	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}

	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}

	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}

	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}

	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}

	public long getOutageDuration() {
		return outageDuration;
	}

	public void setOutageDuration(long outageDuration) {
		this.outageDuration = outageDuration;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int compareTo(PowerOutages o) {
		return this.getDate_event_began().compareTo(o.getDate_event_began());
	}

	@Override
	public String toString() {
		return "\n PowerOutages [nerc=" + nerc + ", customers_affected=" + customers_affected + ", outageDuration="
				+ outageDuration + ", year=" + year + "]";
	}

	
	
	

	
	
	
	
}
