package com.tss.deliveryagents;

import com.tss.model.IDeliveryAgents;

public class Zomato implements IDeliveryAgents {

	private static final long serialVersionUID = 1L;

	@Override
	public IDeliveryAgents getAgent() {
		return new Zomato();
	}

}
