package sbnz.integracija.example.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.UserService;

@RestController
@RequestMapping(value = "new-rule")
public class NewRuleController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> newRule(@RequestBody NewRule newRule) {
		int ret = 1;
		
		InputStream template = RestEndpoints.class.getResourceAsStream("/sbnz/template/template.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{newRule.strings[0], newRule.strings[1], newRule.strings[2], newRule.strings[2]},
            new String[]{newRule.strings[3], newRule.strings[4], newRule.strings[5], newRule.strings[5]},
            new String[]{newRule.strings[6], newRule.strings[7], newRule.strings[8], newRule.strings[8]},
        });
        
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        
        System.out.println(drl);
        
        try {
        	String s = System.getProperty("user.dir");
        	s =  s.substring(0, s.length()-17);
            System.out.println(s + "drools-spring-kjar\\src\\main\\resources\\sbnz\\user\\user-category.drl");
            File f = new File(s + "drools-spring-kjar\\src\\main\\resources\\sbnz\\user\\user-category.drl");
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(f, false);
            
            fileWriter.write(drl);
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File(
				System.getProperty("user.dir").replace("drools-spring-app", "drools-spring-kjar") + "\\pom.xml"));
		request.setGoals(Collections.singletonList("install"));

		Invoker invoker = new DefaultInvoker();

		try {
			invoker.execute(request);
		} catch (MavenInvocationException e) {
			e.printStackTrace();
		}
		
		ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
		
		for(User u : users) {
			userService.updateUserCategory(u);
		}
		
		return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
	}
	
	public static class NewRule {
		public String[] strings;
	}

}
