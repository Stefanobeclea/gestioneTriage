package it.prova.gestionepazienti.repository.paziente;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepazienti.model.Paziente;

public interface PazienteRepository extends PagingAndSortingRepository<Paziente, Long>, JpaSpecificationExecutor<Paziente>{

}
