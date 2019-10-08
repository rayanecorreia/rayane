package br.edu.ifal.cadastroaluno;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String email;
    private String cpf;
    private String modulo;
    private String[] areaDeAtuacao;
    private String sexo;

    

    
    public String[] getareaDeAtuacao() {
		return areaDeAtuacao;
	}


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String[] getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public String getAreasPorString() {
        String ret = "";
        for (String var : areaDeAtuacao) {
            ret = ret + var + ",";
        }
        return ret;
    }

    public void setAreaDeAtuacao(String[] areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }


    public void setAreas(String[] areas) {
        this.areaDeAtuacao = areas;
    }

	public String getTodosCampos() {
		return nome + ", " + email + ", " + modulo + ", " + getAreasPorString();
	}

    @Override
    public String toString() {
        return getTodosCampos();
}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}