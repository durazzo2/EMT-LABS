package mk.ukim.finki.mk.emtlab1.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.mk.emtlab1.model.Host;
import mk.ukim.finki.mk.emtlab1.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public List<Host> getAllHosts(){
       return hostService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getHostById(@PathVariable Long id){
       return hostService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Host createHost(@RequestBody Host host){
        return hostService.save(host);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Host> updateHost(@PathVariable Long id, @RequestBody Host host){
        if (!hostService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        host.setId(id);
        return ResponseEntity.ok(hostService.save(host));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHost(@PathVariable Long id,@RequestBody Host host){
        if (!hostService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        hostService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
