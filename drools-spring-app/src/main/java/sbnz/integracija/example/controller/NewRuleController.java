package sbnz.integracija.example.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "new-rule")
public class NewRuleController {
	
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
		
		return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
	}
	
	public static class NewRule {
		public String[] strings;
	}

}
