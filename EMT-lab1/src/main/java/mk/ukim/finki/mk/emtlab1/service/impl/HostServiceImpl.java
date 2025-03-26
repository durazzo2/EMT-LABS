package mk.ukim.finki.mk.emtlab1.service.impl;

import mk.ukim.finki.mk.emtlab1.model.Host;
import mk.ukim.finki.mk.emtlab1.repository.HostRepository;
import mk.ukim.finki.mk.emtlab1.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }


    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }
}
