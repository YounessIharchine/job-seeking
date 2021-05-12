package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.repository.DomainRepository;
import com.pfa.jobseeking.service.DomainService;

@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	DomainRepository domainRepository;
	
	@Override
	public Domain save(Domain domain) {
		return domainRepository.save(domain);
	}

	@Override
	public List<Domain> findAll() {
		return domainRepository.findAll();
	}
	
	@Override
	public List<Domain> findAll(Pageable pageable) {
		return domainRepository.findAll(pageable).getContent();
	}

	@Override
	public Domain findByName(String name) {
		return domainRepository.findDomainByName(name);
	}

}
