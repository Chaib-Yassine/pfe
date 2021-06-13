package fatima.mastour.venteService.feign;

import fatima.mastour.venteService.model.Test;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CLIENT-SERVICE")
public interface TestRestClient {
    @GetMapping(path = "/tests/{id}")
    Test getTestById(@PathVariable(name ="id") Long id);

}
