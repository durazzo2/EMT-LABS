package mk.ukim.finki.mk.emtlab1.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.mk.emtlab1.model.Host;
import mk.ukim.finki.mk.emtlab1.service.HostService;
import mk.ukim.finki.mk.emtlab1.service.impl.HostServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "managing hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    @Operation(summary = "return all hosts")
    public ResponseEntity<List<Host>> findAll() {
        return ResponseEntity.ok(hostService.findAll());
    }

}
