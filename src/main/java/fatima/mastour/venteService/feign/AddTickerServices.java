package fatima.mastour.venteService.feign;



import com.netflix.servo.monitor.LongGauge;
import fatima.mastour.venteService.model.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="TICKET-SERVICE")
public interface AddTickerServices {



	@PostMapping("/tickets/")
	public Ticket addTicket(@PathVariable Long idArticle,@PathVariable String codeBarres,@PathVariable Boolean statut,@PathVariable Long nVente);

}
