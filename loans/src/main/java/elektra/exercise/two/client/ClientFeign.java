package elektra.exercise.two.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@FeignClient(name = "client", url = "http://localhost:8080/clients")
public interface ClientFeign {
    @GetMapping("/{id}")
    HashMap<String,Object> getClientById(@PathVariable Long id);
}