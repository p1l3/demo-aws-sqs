/**
 * 
 */
package br.com.sensedia.devportalsqs.integration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntegrationMessage {
	
	private String id;
	private String name;
	private String type;
	private String status;
}