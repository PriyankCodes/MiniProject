package com.tss.deliveryagents;

public class DeliveryAgentFactory {
	public static IDeliveryAgents createAgent(String type) {
		return switch (type.toLowerCase()) {

		case "swiggy" -> new Swiggy().getAgent();
		case "zomato" -> new Zomato().getAgent();
		default -> throw new IllegalArgumentException("Unknown delivery agent type: " + type);
		};
	}
}