package dev.antilef.chtvplan.controller;

import dev.antilef.chtvplan.dto.CreatePropertyRequest;
import dev.antilef.chtvplan.entity.Property;
import dev.antilef.chtvplan.repository.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1")
@RestController
public class PropertyController {

    @Autowired
    private final PropertiesRepository propertiesRepository;

    public PropertyController(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    @PostMapping("/properties")
    public void saveProperty(@RequestBody CreatePropertyRequest request){
        Property p = new Property();
        p.setKey(request.getKey());
        p.setValue(request.getValue());
        propertiesRepository.save(p);
    }
}
