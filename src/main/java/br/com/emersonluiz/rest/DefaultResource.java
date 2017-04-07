package br.com.emersonluiz.rest;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/default")
public class DefaultResource {
	
	private static final Logger log = Logger.getLogger(CacheResource.class);

	@Cacheable(value="default", keyGenerator = "customKeyGenerator")
	@RequestMapping(value="/default", method=RequestMethod.GET)
	public String getHello() {
		String df = "Default";
		log.debug("DEFAULT METHOD");
		return df;
	}
}
