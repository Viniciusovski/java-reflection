package br.vinicius;
import br.vinicius.refl.Transformator;

import java.lang.reflect.InvocationTargetException;

public class PessoaService {

    public PessoaDTO list() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pessoa pessoa = new PessoaRepository().list();
        PessoaDTO pessoaDTO = new Transformator().transform(pessoa);
        return pessoaDTO;
    }
}