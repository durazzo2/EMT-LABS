package mk.ukim.finki.mk.emtlab1.service;

import mk.ukim.finki.mk.emtlab1.model.Host;
import mk.ukim.finki.mk.emtlab1.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostService {
    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    public Host save(Host host) {
        return hostRepository.save(host);
    }

    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
