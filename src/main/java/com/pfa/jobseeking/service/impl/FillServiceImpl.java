package com.pfa.jobseeking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.model.company.CompanyProfile;
import com.pfa.jobseeking.model.offer.Duration;
import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.InternshipType;
import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.offer.JobType;
import com.pfa.jobseeking.model.seeker.Profile;
import com.pfa.jobseeking.model.user.Admin;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.repository.DomainRepository;
import com.pfa.jobseeking.repository.DurationRepository;
import com.pfa.jobseeking.repository.InternshipTypeRepository;
import com.pfa.jobseeking.repository.JobTypeRepository;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.repository.RoleRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.CityService;
import com.pfa.jobseeking.service.DomainService;
import com.pfa.jobseeking.service.FillService;

@Service
public class FillServiceImpl implements FillService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	DomainService domainService;
	
	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	InternshipTypeRepository internshipTypeRepository;
	
	@Autowired
	JobTypeRepository jobTypeRepository;
	
	@Autowired
	DurationRepository durationRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void fill() throws NotFoundException, AlreadyExistsException {

		roleRepository.save(new Role("ROLE_SEEKER"));
		roleRepository.save(new Role("ROLE_COMPANY"));
		roleRepository.save(new Role("ROLE_ADMIN"));
		
		cityService.save(new City("Agadir"));
		cityService.save(new City("Marrakech"));
		cityService.save(new City("Casablanca"));
		cityService.save(new City("Rabat"));
		cityService.save(new City("Meknes"));
		
		Domain domain1 = new Domain("Information Technology");
		Domain domain2 = new Domain("Finance");
		Domain domain3 = new Domain("Industry");
		Domain domain4 = new Domain("Telecommunications");
		Domain domain5 = new Domain("Design");
		Domain domain6 = new Domain("Food Industry");
		domainService.save(domain1);
		domainService.save(domain2);
		domainService.save(domain3);
		domainService.save(domain4);
		domainService.save(domain5);
		domainService.save(domain6);

		
		
		
		Seeker seeker = new Seeker("seeker@gmail.com", passwordEncoder.encode("seeker"), "first", "last");
		seeker.addRole(roleRepository.findRoleByName("ROLE_SEEKER"));
		seeker.setFirstName("First");
		seeker.setLastName("Last");
		seeker.setPhone("0624151985");
		seeker.setAddress("This is an address");
		seeker.setBirthDate("06/12/2012");
		seeker.setCity(cityService.findByName("Agadir"));
		Profile profile = new Profile();
		profile.setCv("\\\\cv\\\\cv-1");
		profile.setPhoto("\\\\profilePhotos\\\\photo-1");
		profile.setSpeciality("Légumes du Front-end");
		profile.setDescription("This is a description");
		profile.setPortefolio("portefolio.com");
		profile.setGithub("github.com/firstlast");
		seeker.setProfile(profile);
		
		
		
		Admin admin = new Admin("admin@gmail.com", passwordEncoder.encode("admin"));
		admin.addRole(roleRepository.findRoleByName("ROLE_ADMIN"));
		
		userRepository.save(seeker);
		userRepository.save(admin);
		
		
		
		internshipTypeRepository.save(new InternshipType("End of Year Project"));
		internshipTypeRepository.save(new InternshipType("End of Study Project"));
		internshipTypeRepository.save(new InternshipType("Paid"));
		internshipTypeRepository.save(new InternshipType("Pre-employment"));
		
		
		jobTypeRepository.save(new JobType("Part-Time"));
		jobTypeRepository.save(new JobType("Permanent Employment"));
		jobTypeRepository.save(new JobType("Temporary Employment"));
		jobTypeRepository.save(new JobType("Regular"));
		
		
		durationRepository.save(new Duration("1 Month"));
		durationRepository.save(new Duration("2 Months"));
		durationRepository.save(new Duration("3 Months"));
		durationRepository.save(new Duration("4 Months"));
		durationRepository.save(new Duration("5 Months"));
		durationRepository.save(new Duration("6 Months"));
		
		
		//******************************ORANGE******************************
		Company orange = new Company("orange@gmail.com", passwordEncoder.encode("orange"));
		orange.addRole(roleRepository.findRoleByName("ROLE_COMPANY"));
		orange.setName("Orange");
		orange.setPhone("0652968335");
		orange.setPublicEmail("contact@orange.com");
		orange.setVerified(true);
		orange.setCity(cityService.findByName("Casablanca"));
		orange.setDomain(domainService.findByName("Télécommunications"));
		CompanyProfile orangeProfile = new CompanyProfile();
		orangeProfile.setLogo("\\\\logos\\\\logo-3");
		orangeProfile.setCoverPhoto("\\\\coverPhotos\\\\cover-3");
		orangeProfile.setWebSite("orange.com");
		orange.setCompanyProfile(orangeProfile);
		userRepository.save(orange);
		
		JobOffer offerOrange1 = new JobOffer();
		offerOrange1.setDate("24/06/2021");
		offerOrange1.setDescription("Vous participez à la mise en oeuvre de projets Salesforce, dans des contextes variés.<br>"
				+ "<br>"
				+ "Vous interviendrez sur l'ensemble des technologies de l'éditeur Salesforce (plateforme Cloud, SalesCloud, ServiceCloud, Pardot, Wave Analytics, Mobilité etc.) en lien avec différentes fonctions d'une organisation (Achat, Vente, Marketing, Management, Service Client, etc.).<br>"
				+ "<br>"
				+ "Au sein d'une équipe de spécialistes, vous concevez, paramétrez et développez des solutions adaptées à nos clients, dans le respect des besoins, des performances, de la qualité et des règles de l'art en la matière. Ceci avec le souci constant d'optimiser l'expérience utilisateur.<br>"
				+ "<br>"
				+ "Vous évoluerez dans un environnement de travail épanouissant, au sein d'une équipe attachée au travail collaboratif.<br>"
				+ "<br>"
				+ "<br>"
				+ "about you<br>"
				+ "Vous êtes de formation Ingénieur ou équivalent, et vous êtes référent dans le domaine porteur de la Gestion de la Relation Client.<br>"
				+ "<br>"
				+ "Vous disposez d'une première expérience réussie d'au moins 5 ans en tant que développeur Salesforce, ainsi que d'une ou plusieurs certifications éditeur.<br>"
				+ "<br>"
				+ "Vous maîtrisez les langages et technologies web (Apex, Java, JEE, SOQL, JavaScript, Html, CSS) et connaissez les API Salesforce.<br>"
				+ "<br>"
				+ "Vous comprenez les besoins fonctionnels de vos clients et vous savez leur apporter des solutions techniques répondant à leurs attentes (Apex ou lightning) en respectant les bonnes pratiques Salesforce.<br>"
				+ "<br>"
				+ "Vous êtes à l'aise dans la communication orale et écrite, et appréciez le travail en équipe.<br>"
				+ "<br>"
				+ "La pratique de l'anglais constituera un atout.<br>"
				+ "<br>"
				+ "Vous pourrez évoluer vers des fonctions d'Expert Technique, d'Architecte, ou de Consultant Technico-Fonctionnel.<br>"
				+ "<br>"
				+ "Le poste est à pourvoir sur Paris, Lyon et Rennes.");
		offerOrange1.setOpen(true);
		offerOrange1.setTitle("Software Engineer - Référent technique SalesForce");
		offerOrange1.setVerified(true);
		offerOrange1.setCity(cityService.findByName("Casablanca"));
		offerOrange1.setDomain(domainService.findByName("Telecommunications"));
		offerOrange1.setCompany((Company) userRepository.findUserByEmail("orange@gmail.com"));
		offerOrange1.setJobType(jobTypeRepository.findJobTypeByName("Regular"));
		
		InternshipOffer offerOrange2 = new InternshipOffer();
		offerOrange2.setDate("20/05/2021");
		offerOrange2.setDescription("Mettre en place la direction artistique des tous les projets issus de l’école du code : développés ou accompagnés.<br>"
				+ "<br>"
				+ "Assurer une qualité optimale des maquettes graphiques et des supports de communication pour les projets technologiques issus de l’Orange Digital Center : production des supports graphiques et des prototypes.<br>"
				+ "<br>"
				+ "Assurer des formations aux jeunes à l’écoles du code et/ou dans les université sur les dernières tendances graphiques (UX/UI) liées à son domaine d’expertise.<br>"
				+ "<br>"
				+ "<br>"
				+ "Activités principales<br>"
				+ "<br>"
				+ "Développer la conception artistique ou assurer le suivi des travaux qui lui sont confiés.<br>"
				+ "Gérer les projets graphiques de la conception à la livraison : pilotage, planification, coordination avec l’équipe projet, documentation, respect des délais et du périmètre du projet.<br>"
				+ "Superviser et s’impliquer dans toutes les étapes de développement du projet technologique : veiller au respect de la maquette graphique et à la validation finale du projet.<br>"
				+ "Accompagner, encadrer les apprenants (étudiants ou diplômés) en stage à l’école du code dans leurs conceptions graphiques.<br>"
				+ "Participer à la mise en place des processus de gestion de projets à l’école du code.<br>"
				+ "Sélectionner et évaluer les apprenants qui vont intégrer l’école du code dans les différentes cohortes.<br>"
				+ "Etre force de proposition par rapport aux activités de l’école du code et aux projets proposés : idées de projets innovants, concepts d’évènements, intervenants, veille technologique…<br>"
				+ "Participer activement aux activités de l’école du code : formations, évènements, Talks et challenges et être force de proposition.<br>"
				+ "Rendre compte à la hiérarchie de son activité.");
		offerOrange2.setOpen(true);
		offerOrange2.setTitle("UI/UX Designer à l’école du code");
		offerOrange2.setVerified(true);
		offerOrange2.setCity(cityService.findByName("Rabat"));
		offerOrange2.setDomain(domainService.findByName("Design"));
		offerOrange2.setCompany((Company) userRepository.findUserByEmail("orange@gmail.com"));
		offerOrange2.setInternshipType(internshipTypeRepository.findInternshipTypeByName("End of Year Project"));
		offerOrange2.setDuration(durationRepository.findByDuration("3 Months"));



		offerRepository.save(offerOrange1);
		offerRepository.save(offerOrange2);
		
		
		
		//******************************DANONE******************************
		Company danone = new Company("danone@gmail.com", passwordEncoder.encode("danone"));
		danone.addRole(roleRepository.findRoleByName("ROLE_COMPANY"));
		danone.setName("Danone");
		danone.setPhone("0614635593");
		danone.setPublicEmail("contact@danone.com");
		danone.setVerified(true);
		danone.setCity(cityService.findByName("Casablanca"));
		danone.setDomain(domainService.findByName("Food Industry"));
		CompanyProfile danoneProfile = new CompanyProfile();
		danoneProfile.setLogo("\\\\logos\\\\logo-4");
		danoneProfile.setCoverPhoto("\\\\coverPhotos\\\\cover-4");
		danoneProfile.setWebSite("danone.com");
		danone.setCompanyProfile(danoneProfile);
		userRepository.save(danone);
		
		JobOffer offerDanone1 = new JobOffer();
		offerDanone1.setDate("24/05/2021");
		offerDanone1.setDescription("Centrale Danone est à la recherche de son nouveau Ingénieur Qualité qui sera basé à l'Usine de Meknès.<br>"
				+ "<br>"
				+ "Missions :<br>"
				+ "<br>"
				+ "En tant qu’Ingénieur Qualité, vous allez être amenés à :<br>"
				+ "<br>"
				+ "Assurer la vérification de la conformité et la capabilité des équipements process et conditionnement ;<br>"
				+ "Apporter une assistance aux équipes opérationnelles en matière de traitement des non-conformités internes et externes ;<br>"
				+ "Suivre avec les acteurs processus le traitement des non-conformités internes et externes ;<br>"
				+ "Suivre et traiter les résultats satisfaction des clients (Enquête, FRP….) ;<br>"
				+ "Participer au comité suivie projet usine ;<br>"
				+ "Participer au suivi des Essais Produits ;<br>"
				+ "Participer à la réalisation des audits et des inspections internes CD ;<br>"
				+ "Participer aux séances de formation et de sensibilisation des équipes opérationnelles ;<br>"
				+ "Assurer l’intérim en cas d’absence du Responsable Qualité & Sécurité alimentaire ou RQM.");
		offerDanone1.setOpen(true);
		offerDanone1.setTitle("Ingénieur Qualité - Usine de Meknès (H/F)");
		offerDanone1.setVerified(true);
		offerDanone1.setCity(cityService.findByName("Meknes"));
		offerDanone1.setDomain(domainService.findByName("Food Industry"));
		offerDanone1.setCompany((Company) userRepository.findUserByEmail("danone@gmail.com"));
		offerDanone1.setJobType(jobTypeRepository.findJobTypeByName("Temporary Employment"));
		
		InternshipOffer offerDanone2 = new InternshipOffer();
		offerDanone2.setDate("20/03/2021");
		offerDanone2.setDescription("Centrale Danone est à la recherche de son nouveau Regulatory Affairs Manager au sein de la direction Juridique.<br>"
				+ "<br>"
				+ "Le Regulatory Manager est l’interlocuteur principal sur tous les sujets réglementaires de Centrale Danone. A ce titre, ses principales missions s’articulent autour de :<br>"
				+ "<br>"
				+ "La Procédure réglementaire :<br>"
				+ "<br>"
				+ "Coordination avec les homologues régions ;<br>"
				+ "Mise à Jour / Rédaction et validation de la procédure locale ;<br>"
				+ "Formations aux clients internes concernés ;<br>"
				+ "L’Audit Réglementaire :<br>"
				+ "<br>"
				+ "Mise à jour de la grille d’audit / Recueil réglementaire ;<br>"
				+ "Préparation et conduite de l’audit ;<br>"
				+ "Analyse des résultats de l’audit ;<br>"
				+ "Rédaction du compte rendu, du plan d’action et de la présentation d’audit ;<br>"
				+ "La Révision et rédaction du Décret du Lait :<br>"
				+ "<br>"
				+ "Préparation et présence aux commissions du groupe de travail ;<br>"
				+ "Rédaction et validation des paragraphes du décret ;<br>"
				+ "Compte-rendu de la commission ;<br>"
				+ "La CVC (Cellule Validation claim) :<br>"
				+ "<br>"
				+ "Rédaction & mise à jour des procédures CVC ;<br>"
				+ "Analyse des demandes sur la boite mail CVC ;<br>"
				+ "Rédaction et diffusion du compte-rendu de la CVC ;<br>"
				+ "Mise à Jour du reporting et analyse des KPI’s ;<br>"
				+ "La CVM (Claim Validation Management) :<br>"
				+ "<br>"
				+ "Gestion de l’outil CVM<br>"
				+ "Animation de la CVM<br>"
				+ "Formations des équipes<br>"
				+ "Réunions annuelles CVM<br>"
				+ "Les Formalités courrier ONSSA (Réunions, Réponses) ;<br>"
				+ "<br>"
				+ "Les Représentations externes/internes (COSUP/CODEV) ;<br>"
				+ "<br>"
				+ "La Mise à jour de l’outil FDE (Fiche d’étiquetage) ;<br>"
				+ "<br>"
				+ "La Veille réglementaire, analyses d’impacts et partage de notes avec les parties prenantes concernées ;<br>"
				+ "<br>"
				+ "Des projets transverses intra-entreprise/Groupe.");
		offerDanone2.setOpen(true);
		offerDanone2.setTitle("Regulatory Affairs Manager (H/F)");
		offerDanone2.setVerified(true);
		offerDanone2.setCity(cityService.findByName("Rabat"));
		offerDanone2.setDomain(domainService.findByName("Food Industry"));
		offerDanone2.setCompany((Company) userRepository.findUserByEmail("danone@gmail.com"));
		offerDanone2.setInternshipType(internshipTypeRepository.findInternshipTypeByName("Pre-Employment"));
		offerDanone2.setDuration(durationRepository.findByDuration("3 Months"));
		
		
		
		offerRepository.save(offerDanone1);
		offerRepository.save(offerDanone2);
		
		
		
		//******************************ORACLE******************************
		Company oracle = new Company("oracle@gmail.com", passwordEncoder.encode("oracle"));
		oracle.addRole(roleRepository.findRoleByName("ROLE_COMPANY"));
		oracle.setName("Oracle");
		oracle.setPhone("0614635593");
		oracle.setPublicEmail("contact@oracle.com");
		oracle.setVerified(true);
		oracle.setCity(cityService.findByName("Casablanca"));
		oracle.setDomain(domainService.findByName("Information Technology"));
		CompanyProfile oracleProfile = new CompanyProfile();
		oracleProfile.setLogo("\\\\logos\\\\logo-5");
		oracleProfile.setCoverPhoto("\\\\coverPhotos\\\\cover-5");
		oracleProfile.setWebSite("oracle.com");
		oracle.setCompanyProfile(oracleProfile);
		userRepository.save(oracle);
		
		JobOffer offerOracle1 = new JobOffer();
		offerOracle1.setDate("13/04/2021");
		offerOracle1.setDescription("Software Developer - Oracle Labs -21000I0F\r\n"
				+ "\r\n"
				+ "Applicants are required to read, write, and speak the following languages: English\r\n"
				+ "Preferred Qualifications\r\n"
				+ "\r\n"
				+ "Software Developer – Oracle Labs Casablanca\r\n"
				+ "Are you passionate about software engineering and want to join an international team that is developing innovative technologies that are used across different Oracle products? Then we are looking for you.\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "The challenge we are tackling: Creating distributed (meaning multi-user, multi-server, multi-tenant) platforms that are scalable, performant, and secure.\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "Oracle Labs is a department of Oracle devoted to research. Our research is focused on real-world outcomes: we aim to develop technologies that will someday play a significant role in the evolution of technology and society.\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "We are looking for people with a good coverage on the following skills:\r\n"
				+ "\r\n"
				+ "Distinguished problem-solving skills\r\n"
				+ "Curiosity to be part of significant ongoing research\r\n"
				+ "Good communication and presentation skills\r\n"
				+ "Working proficiency in verbal and written English\r\n"
				+ "Good knowledge and understanding of one or more of the following fundamentals\r\n"
				+ "computer architecture\r\n"
				+ "database architecture and implementation\r\n"
				+ "data structures, algorithms, and complexity analysis\r\n"
				+ "continuous integration and delivery\r\n"
				+ "cloud computing\r\n"
				+ "automation\r\n"
				+ "compilers\r\n"
				+ "distributed systems and data structures design\r\n"
				+ "linear algebra, machine learning, and deep learning algorithms\r\n"
				+ "Rest APIs and the concepts of RESTful architecture\r\n"
				+ "software language engineering techniques such as compilation and intermediate representation\r\n"
				+ "having completed relevant courses in these areas is a plus\r\n"
				+ "Hands-on experience in some of the following programming paradigms, languages, libraries, and frameworks:\r\n"
				+ "parallel computing\r\n"
				+ "distributed computing\r\n"
				+ "Java\r\n"
				+ "C/C++\r\n"
				+ "JavaScript\r\n"
				+ "Python\r\n"
				+ "R\r\n"
				+ "Ruby\r\n"
				+ "Docker / Kubernetes\r\n"
				+ "Jenkins\r\n"
				+ "Elastic Stack\r\n"
				+ "Semantic HTML5/CSS3\r\n"
				+ "SQL\r\n"
				+ "JavaScript frameworks such as React, Angular, or knockout\r\n"
				+ "jQuery, lodash and other popular libraries\r\n"
				+ "data visualization libraries such as D3, Cytoscape.js\r\n"
				+ "machine learning libraries such as Sklearn and frameworks such as Tensorflow or PyTorch\r\n"
				+ "Oracle JET\r\n"
				+ "Hands-on experience with the following tools\r\n"
				+ "modern IDEs\r\n"
				+ "source code management and source code versioning tools\r\n"
				+ "build management tools on different operating systems\r\n"
				+ "automated testing and continuous delivery\r\n"
				+ "Linux\r\n"
				+ "Detailed Description and Job Requirements\r\n"
				+ "Design, develop, troubleshoot and debug software programs for databases, applications, tools, networks etc.\r\n"
				+ "\r\n"
				+ "As a member of the software engineering division, you will apply basic to intermediate knowledge of software architecture to perform software development tasks associated with developing, debugging or designing software applications or operating systems according to provided design specifications. Build enhancements within an existing software architecture and occasionally suggest improvements to the architecture.\r\n"
				+ "\r\n"
				+ "Duties and tasks are standard with some variation; displays understanding of roles, processes and procedures. Performs moderately complex problem solving with assistance and guidance in understanding and applying company policies and processes. BS degree or equivalent experience relevant to functional area. 1 year of software engineering or related experience.\r\n"
				+ "\r\n"
				+ "As part of Oracle's employment process candidates will be required to successfully complete a pre-employment screening process. This will involve identity and employment verification, professional references, education verification and professional qualifications and memberships (if applicable).");
		offerOracle1.setOpen(true);
		offerOracle1.setTitle("Software Developer - Oracle Labs");
		offerOracle1.setVerified(true);
		offerOracle1.setCity(cityService.findByName("Casablanca"));
		offerOracle1.setDomain(domainService.findByName("Information Technology"));
		offerOracle1.setCompany((Company) userRepository.findUserByEmail("oracle@gmail.com"));
		offerOracle1.setJobType(jobTypeRepository.findJobTypeByName("Temporary Employment"));
		
		InternshipOffer offerOracle2 = new InternshipOffer();
		offerOracle2.setDate("28/01/2021");
		offerOracle2.setDescription("Research assistant-21000OB8\r\n"
				+ "\r\n"
				+ "Applicants are required to read, write, and speak the following languages: English\r\n"
				+ "Preferred Qualifications\r\n"
				+ "Do you want to get a foot in the door and perhaps even get the chance to interview early for one of the best technology jobs in the world? If so, you may be an ideal candidate for our internship program.\r\n"
				+ "Gain invaluable experience in what it is like to work at a leading global hardware and software systems innovator - Oracle. Your colleagues are industry experts and knowledgeable veterans. Your contributions will enhance real Oracle products and services. By the end of your internship you will be connected to a powerful network of professionals, managers, and executives.\r\n"
				+ "As part of Oracle's employment process candidates will be required to complete a pre-employment screening process, prior to an offer being made. This will involve identity and employment verification,professional references, education verification and professional qualifications and memberships (if applicable).\r\n"
				+ "\r\n"
				+ "Detailed Description and Job Requirements\r\n"
				+ "This job code is utilized for the majority of our temporary hires. The individual is performing hourly job duties as defined under the Fair Labor Standards Act.\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "As part of Oracle's employment process candidates will be required to successfully complete a pre-employment screening process. This will involve identity and employment verification, professional references, education verification and professional qualifications and memberships (if applicable).");
		offerOracle2.setOpen(true);
		offerOracle2.setTitle("Research Assistant");
		offerOracle2.setVerified(true);
		offerOracle2.setCity(cityService.findByName("Casablanca"));
		offerOracle2.setDomain(domainService.findByName("Information Technology"));
		offerOracle2.setCompany((Company) userRepository.findUserByEmail("oracle@gmail.com"));
		offerOracle2.setInternshipType(internshipTypeRepository.findInternshipTypeByName("End of Study Project"));
		offerOracle2.setDuration(durationRepository.findByDuration("5 Months"));
		
		
		
		offerRepository.save(offerOracle1);
		offerRepository.save(offerOracle2);
	}

}
