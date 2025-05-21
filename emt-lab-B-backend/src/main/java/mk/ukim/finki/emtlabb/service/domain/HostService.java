package mk.ukim.finki.emtlabb.service.domain;

import mk.ukim.finki.emtlabb.model.domain.Host;
import mk.ukim.finki.emtlabb.model.projections.HostProjection;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> save(Host host);

    Optional<Host> update(Long id, Host host);

    void deleteById(Long id);

    void refreshMaterializedView();

    List<HostProjection>getAllHostNames();
}
