package com.pfa.jobseeking.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.offer.Offer;

@Entity
public class OfferCreationRequest extends Request {

	@OneToOne
	@JoinColumn(name = "offer_id")
	Offer offer;
}
