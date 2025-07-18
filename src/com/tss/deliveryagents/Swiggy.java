package com.tss.deliveryagents;

public class Swiggy implements IDeliveryAgents {

	private static final long serialVersionUID = 1L;

	@Override
	public IDeliveryAgents getAgent() {
		return new Swiggy();
	}

}
