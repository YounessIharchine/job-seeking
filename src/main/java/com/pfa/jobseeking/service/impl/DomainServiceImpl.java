package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.repository.DomainRepository;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.DomainService;

@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	DomainRepository domainRepository;
	

	
	@Override
	public List<Domain> findAll() {
		return domainRepository.findAll();
	}
	


	@Transactional
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void save(NameDto domainName) throws AlreadyExistsException {
		
		if(domainRepository.findDomainByName(domainName.getName()) != null)
			throw new AlreadyExistsException("This domain already exists in the database.");
		
		Domain domain = new Domain(domainName.getName());
		
		domainRepository.save(domain);
	}

	
	@Transactional
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(String domainName) throws NotFoundException {

		if(domainRepository.findDomainByName(domainName) == null)
			throw new NotFoundException("This domain doesn't exist in the database.");
		
		domainRepository.deleteDomainByName(domainName);
		
	}
	
	
	
	//for filling database
	@Override
	public Domain save(Domain domain) {
		return domainRepository.save(domain);
	}
	@Override
	public Domain findByName(String name) {
		return domainRepository.findDomainByName(name);
	}
}







//@Override
//public List<Domain> findAll(Pageable pageable) {
//	return domainRepository.findAll(pageable).getContent();
//}
