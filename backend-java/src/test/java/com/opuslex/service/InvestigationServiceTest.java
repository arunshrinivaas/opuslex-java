package com.opuslex.service;

import com.opuslex.domain.investigation.Investigation;
import com.opuslex.domain.investigation.InvestigationRepository;
import com.opuslex.dto.InvestigationDto;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestigationServiceTest {
    @Test
    public void testListInvestigations() {
        InvestigationRepository dummyRepo = new InvestigationRepository() {
            public List<Investigation> findByUserId(Integer userId) { return Collections.emptyList(); }
            public Optional<Investigation> findByIdAndUserId(Integer id, Integer userId) { return Optional.empty(); }
            public void flush() {}
            public <S extends Investigation> S saveAndFlush(S entity) { return null; }
            public <S extends Investigation> java.util.List<S> saveAllAndFlush(Iterable<S> entities) { return null; }
            public void deleteAllInBatch(Iterable<Investigation> entities) {}
            public void deleteAllByIdInBatch(Iterable<Integer> ids) {}
            public void deleteAllInBatch() {}
            public Investigation getOne(Integer id) { return null; }
            public Investigation getById(Integer id) { return null; }
            public Investigation getReferenceById(Integer id) { return null; }
            public <S extends Investigation> java.util.List<S> findAll(org.springframework.data.domain.Example<S> example) { return null; }
            public <S extends Investigation> java.util.List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return null; }
            public <S extends Investigation> java.util.List<S> saveAll(Iterable<S> entities) { return null; }
            public java.util.List<Investigation> findAll() { return null; }
            public java.util.List<Investigation> findAllById(Iterable<Integer> ids) { return null; }
            public <S extends Investigation> S save(S entity) { return null; }
            public Optional<Investigation> findById(Integer id) { return Optional.empty(); }
            public boolean existsById(Integer id) { return false; }
            public long count() { return 0; }
            public void deleteById(Integer id) {}
            public void delete(Investigation entity) {}
            public void deleteAllById(Iterable<? extends Integer> ids) {}
            public void deleteAll(Iterable<? extends Investigation> entities) {}
            public void deleteAll() {}
            public java.util.List<Investigation> findAll(org.springframework.data.domain.Sort sort) { return null; }
            public org.springframework.data.domain.Page<Investigation> findAll(org.springframework.data.domain.Pageable pageable) { return null; }
            public <S extends Investigation> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
            public <S extends Investigation> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return null; }
            public <S extends Investigation> long count(org.springframework.data.domain.Example<S> example) { return 0; }
            public <S extends Investigation> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
            public <S extends Investigation, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        };

        InvestigationService service = new InvestigationService(dummyRepo);
        List<InvestigationDto> result = service.listInvestigations(1);
        assertEquals(0, result.size());
    }
}
