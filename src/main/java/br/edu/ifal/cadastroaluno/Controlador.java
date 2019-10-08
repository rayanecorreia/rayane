package br.edu.ifal.cadastroaluno;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class Controlador{
    @Autowired
    AlunoRepositorio repo;

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index.html");
    }

    @RequestMapping("/formulario")
    public ModelAndView formulario(Aluno a){
    	  ModelAndView retorno = new ModelAndView("formulario.html");
          retorno.addObject("aluno", a);
          return retorno;
    }

  
    @RequestMapping("/cadastro")
        public ModelAndView cadastroAluno(Aluno aluno, RedirectAttributes redirect) {
            repo.save(aluno);
            ModelAndView retorno = new ModelAndView("redirect:/Listar_alunos");
            redirect.addFlashAttribute("mensagem", aluno.getNome() + " cadastrado com sucesso.");
            return retorno;
    }
    

    @RequestMapping("/Listar_alunos")
    public ModelAndView listarAlunos() {
    ModelAndView retorno = new ModelAndView("Listar_alunos.html");
        Iterable<Aluno> alunos = repo.findAll();
        retorno.addObject("alunos", alunos);
       return retorno;
    }
    
    @RequestMapping("/excluir_aluno/{idAluno}")
    public ModelAndView excluirAlunos(@PathVariable("idAluno") Long alunoID, RedirectAttributes redirect) {
        Optional<Aluno> opcao = repo.findById(alunoID);
        ModelAndView retorno = new ModelAndView("redirect:/Listar_alunos");        
        if(opcao.isPresent()){
            Aluno a = opcao.get();
            repo.deleteById(a.getId());            
            redirect.addFlashAttribute("mensagem", a.getNome() + " excluído com sucesso.");
            return retorno;
        } else {
            redirect.addFlashAttribute("mensagem", "Aluno " + alunoID + " não existe no Banco de Dados.");
            return retorno;
        }
        
    }

    @RequestMapping("/atualizar_aluno/{idAluno}")
    public ModelAndView atualizar(@PathVariable("idAluno") Long alunoID) {
        Optional<Aluno> opcao = repo.findById(alunoID);
        ModelAndView retorno = new ModelAndView("formulario.html");
        if(opcao.isPresent()){
            Aluno a = opcao.get();
            retorno.addObject("aluno", a);
            return retorno;
        }
        return retorno;
    }
    @RequestMapping("/procurarAluno")
    public ModelAndView buscar(String nomeProcurado){
        ModelAndView retorno = new ModelAndView("Listar_alunos.html");
        List<Aluno> alunos = repo.findByNomeContaining(nomeProcurado);
        if(!alunos.isEmpty()){
            retorno.addObject("alunos", alunos);
            return retorno;
        }
        retorno.addObject("mensagem", "Aluno não cadastrado no Banco de Dados");
        return retorno;
    }
    }
