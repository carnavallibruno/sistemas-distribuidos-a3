package aula.agenda.msagenda.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aula.agenda.msagenda.dto.AgendaDTO;
import aula.agenda.msagenda.model.Agenda;
import aula.agenda.msagenda.repository.AgendaRepository;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository repository;

    @GetMapping("/")
    public List<AgendaDTO> listarAgendas() {
        List<Agenda> agendaList = repository.findAll();
        List<AgendaDTO> dtos = AgendaDTO.converteListaAgenda(agendaList);
        return dtos;
    }

    @GetMapping("/{id}")
    public AgendaDTO consutarPorId(@PathVariable(name = "id") Long id) {
        Agenda agenda = repository.getReferenceById(id);
        return new AgendaDTO(agenda);
    }

    @PostMapping("/")
    public void inserirAgenda(@RequestBody AgendaDTO dto) {
        Agenda agenda = dto.converteParaAgenda();
        repository.save(agenda);
    }
}
