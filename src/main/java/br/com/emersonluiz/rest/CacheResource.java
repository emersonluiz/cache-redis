package br.com.emersonluiz.rest;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.emersonluiz.service.CacheService;

@RestController
@RequestMapping("/cache")
public class CacheResource {

	private static final Logger log = Logger.getLogger(CacheResource.class);

	@Inject
	CacheService cacheService;

	@Cacheable(value="register", keyGenerator = "customKeyGenerator")
	@RequestMapping(value="/information", method=RequestMethod.GET)
	public String getInformation() {
		String info = cacheService.getInformation();
		log.debug("GET ON DATA: " + info);
		return info;
	}

	@CacheEvict(value="register", allEntries=true)
	@RequestMapping(value="/information/{info}", method=RequestMethod.GET)
	public void setTransferency(@PathVariable("info") String info) {
		log.info("SET DATA: " + info);
		cacheService.setInformation(info);
	}
}
