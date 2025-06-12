package service;

import model.Obito;
import java.util.ArrayList;
import java.util.List;

public class ObitoService {
    private List<Obito> obitos = new ArrayList<>();

    public boolean inserirObito(Obito obito, Pacienteservice pacienteService) {
        if (!pacienteService.existe(obito.getCpfPaciente())) {
            return false;
        }
        obitos.add(obito);
        return true;
    }

    public List<Obito> getObitos() {
        return new ArrayList<>(obitos);
    }
}
