package com.delgadotrueba.api.data_servicies;


import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;


import com.delgadotrueba.api.dao.EmployeeRepository;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;


@Service
public class DatabaseSeederService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Value("${databaseSeeder.ymlFileName:#{null}}")
    private String ymlFileName;
	
	@PostConstruct
    public void constructor() {
        /*String[] profiles = this.environment.getActiveProfiles();
        if (Arrays.stream(profiles).anyMatch("dev"::equals)) {
            this.deleteAllAndInitializeAndLoadYml();
        } else if (Arrays.stream(profiles).anyMatch("prod"::equals)) {
            this.initialize();
        }*/
		
		this.deleteAllAndLoadYml();
    }
	
    
   public void deleteAllAndLoadYml() {
        this.deleteAll();
        this.seedDatabase();
        this.initialize();
   	}
	
  
	public void deleteAll() {
       LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
       
       // Delete Repositories -----------------------------------------------------
       this.employeeRepository.deleteAll();

       // -------------------------------------------------------------------------
   }
	
	private void initialize() {
       LogManager.getLogger(this.getClass()).warn("------- Initialize -----------");
       //TODO
	}
	
	
	public void seedDatabase() {
       LogManager.getLogger(this.getClass()).warn("------- Seed Database -----------");
       if (this.ymlFileName != null) {
           try {
               LogManager.getLogger(this.getClass()).warn("Initial Load: " + this.ymlFileName);
               this.seedDatabase(new ClassPathResource(this.ymlFileName).getInputStream());
           } catch (IOException e) {
               LogManager.getLogger(this.getClass()).error("File " + this.ymlFileName + " doesn't exist or can't be opened");
           }
       } else {
           LogManager.getLogger(this.getClass()).error("File db.yml doesn't configured");
       }
   }
	
	public void seedDatabase(InputStream input) {
        Yaml yamlParser = new Yaml(new Constructor(DatabaseGraph.class));
        DatabaseGraph tpvGraph = yamlParser.load(input);

        this.employeeRepository.saveAll(tpvGraph.getEmployeeList());
    

        LogManager.getLogger(this.getClass()).warn("Seed...");
    }
   
}
