package it.prova.gestionepazienti.repository.paziente;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepazienti.model.Paziente;

public interface PazienteRepository extends CrudRepository<Paziente, Long>{

}
